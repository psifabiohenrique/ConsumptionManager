package com.FabioHenrique.ConsuptionManager.Services;

import com.FabioHenrique.ConsuptionManager.Domain.Fueling;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingInDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingOutDto;

import java.util.ArrayList;
import java.util.List;

public class FuelingService {
    private final FuelingRepository fuelingRepository;

    public FuelingService(FuelingRepository fuelingRepository) {
        this.fuelingRepository = fuelingRepository;
    }

    public FuelingOutDto create(FuelingInDto dto) {


        int lastId = 0;
        try {
            lastId += fuelingRepository.listAll().getLast().getId();
        } catch (Exception _) {}

        Fueling fueling = new Fueling(
                lastId + 1,
                dto.getVehicleId(),
                dto.getOdometer(),
                dto.getLiters(),
                dto.getFuelType(),
                dto.getTotalCost()
        );
        return new FuelingOutDto(fuelingRepository.save(fueling));
    }
    public FuelingOutDto getById(int fuelingId) {
        return new FuelingOutDto(fuelingRepository.getOne(fuelingId));
    }
    public List<FuelingOutDto> getAll() {

        List<Fueling> fuelingList = fuelingRepository.listAll();
        List<FuelingOutDto> fuelingOutDtoList = new ArrayList<>();

        for (Fueling fueling : fuelingList) {
            fuelingOutDtoList.add(new FuelingOutDto(fueling));
        }
        return fuelingOutDtoList;
    }
    public List<FuelingOutDto> getAllByVehicle(int vehicleId) {
        List<Fueling> fuelingList = fuelingRepository.listAll().stream().filter(fueling -> fueling.getVehicleId() == vehicleId).toList();
        if (fuelingList.isEmpty()) {
            return new ArrayList<>();
        }

        List<FuelingOutDto> fuelingOutDtoList = new ArrayList<>();

        for (Fueling fueling : fuelingList) {
            fuelingOutDtoList.add(new FuelingOutDto(fueling));
        }
        return fuelingOutDtoList;
    }
    public FuelingOutDto update(int fuelingId, FuelingInDto dto) {
        Fueling udatedFueling = new Fueling(
                fuelingId,
                dto.getVehicleId(),
                dto.getOdometer(),
                dto.getLiters(),
                dto.getFuelType(),
                dto.getTotalCost()
        );

        return new FuelingOutDto(fuelingRepository.update(fuelingId, udatedFueling));
    }
    public FuelingOutDto delete(int fuelingId) {
        FuelingOutDto fueling = getById(fuelingId);
        fuelingRepository.delete(fuelingId);
        return fueling;
    }
}
