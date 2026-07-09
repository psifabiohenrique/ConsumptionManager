package com.FabioHenrique.ConsuptionManager.Services;

import com.FabioHenrique.ConsuptionManager.Domain.Fueling;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingInDto;

import java.util.List;

public class FuelingService {
    private final FuelingRepository fuelingRepository;

    public FuelingService(FuelingRepository fuelingRepository) {
        this.fuelingRepository = fuelingRepository;
    }

    public Fueling create(FuelingInDto dto) {


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
        return fuelingRepository.save(fueling);
    }
    public Fueling getById(int fuelingId) {
        return fuelingRepository.getOne(fuelingId);
    }
    public List<Fueling> getAll() {
        return fuelingRepository.listAll();
    }
    public List<Fueling> getAllByVehicle(int vehicleId) {
        List<Fueling> fuelings = fuelingRepository.listAll().stream().filter(fueling -> fueling.getVehicleId() == vehicleId).toList();
        if (fuelings.isEmpty()) {
            throw new RuntimeException("Não há abastecimentos cadastrados");
        }
        return fuelings;
    }
    public Fueling update(int fuelingId, FuelingInDto dto) {
        Fueling udatedFueling = new Fueling(
                fuelingId,
                dto.getVehicleId(),
                dto.getOdometer(),
                dto.getLiters(),
                dto.getFuelType(),
                dto.getTotalCost()
        );

        return fuelingRepository.update(fuelingId, udatedFueling);
    }
    public Fueling delete(int fuelingId) {
        Fueling fueling = getById(fuelingId);
        fuelingRepository.delete(fuelingId);
        return fueling;
    }
}
