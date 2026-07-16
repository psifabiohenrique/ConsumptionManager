package com.FabioHenrique.ConsuptionManager.ui.Tui;

import com.FabioHenrique.ConsuptionManager.Services.AverageConsumptionService;
import com.FabioHenrique.ConsuptionManager.Services.dto.ConsumptionOutDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

public class AverageConsumptionUI extends UI {
    private final AverageConsumptionService averageConsumptionService;
    public AverageConsumptionUI(InputHelper inputHelper, AppContextTui appContextTui, AverageConsumptionService averageConsumptionService) {
        this.inputHelper = inputHelper;
        this.appContextTui = appContextTui;
        this.averageConsumptionService = averageConsumptionService;
    }

    @Override
    public void show() {
        VehicleOutDto vehicle = appContextTui.getSelectedVehicle();
        if (vehicle == null) {
            System.out.println("É necessário selecionar algum veículo.");
            return;
        }
        try {
            ConsumptionOutDto consumption = averageConsumptionService.getAverageConsumption(vehicle.getId());
            System.out.printf("O veículo '%s' está consumindo %.2f km/l.\nGerando um custo por km de R$ %.2f\nUm custo total dos últimos %s abastecimentos de R$ %.2f",
                    vehicle.getName(),
                    consumption.getAverageConsumption(),
                    consumption.getAverageCost(),
                    consumption.getFuelingCount(),
                    consumption.getTotalCost()
            );
        } catch (RuntimeException e) {
            System.out.println("Houve um erro ao gerar o relatório de consumo, verifique se há abastecimentos suficientes.");
        }

        System.out.println();
        System.out.println();
        int option = inputHelper.readInteger("Entre com qualquer tecla para voltar ao menu principal: ");

    }
}
