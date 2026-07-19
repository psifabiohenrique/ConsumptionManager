package com.FabioHenrique.ConsumptionManager.Services;

import com.FabioHenrique.ConsumptionManager.Services.dto.ConsumptionOutDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.FuelingOutDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AverageConsumptionService {
    private final FuelingService fuelingService;


    public AverageConsumptionService(FuelingService fuelingService) {
        this.fuelingService = fuelingService;
    }

    public ConsumptionOutDto getAverageConsumption(int vehicleId) {
        List<FuelingOutDto> allFuelingOutDtoList = this.fuelingService.getAllByVehicle(vehicleId);
        List<FuelingOutDto> finalFuelingOutDtoList = new ArrayList<>(allFuelingOutDtoList.subList(
                Math.max(0, allFuelingOutDtoList.size() - 10),
                allFuelingOutDtoList.size()
        ));

        if (finalFuelingOutDtoList.size() < 2) {
            throw new RuntimeException("São necessários pelo menos 2 abastecimentos para calcular a média de consumo.");
        }

        finalFuelingOutDtoList.sort(
                Comparator.comparing(FuelingOutDto::getOdometer)
        );

        double totalKm = finalFuelingOutDtoList.getLast().getOdometer() - finalFuelingOutDtoList.getFirst().getOdometer();
        double litersConsumed = finalFuelingOutDtoList.stream()
                .skip(1)
                .mapToDouble(FuelingOutDto::getLiters)
                .sum();

        double totalCost = finalFuelingOutDtoList.stream()
                .skip(1)
                .mapToDouble(FuelingOutDto::getTotalCost)
                .sum();

        double averageConsumption = totalKm / litersConsumed;
        double averageCost = totalCost / totalKm;

        return  new ConsumptionOutDto(averageConsumption, averageCost, totalCost, finalFuelingOutDtoList.size());
    }
}
