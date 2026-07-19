package com.FabioHenrique.ConsumptionManager.Repository.Interfaces;

import com.FabioHenrique.ConsumptionManager.Domain.Fueling;

import java.util.List;

public interface FuelingRepository {
    public Fueling save(Fueling fueling);
    public List<Fueling> listAll();
    public Fueling getOne(int fuelingId);
    public Fueling update(int fuelingId, Fueling updatedFueling);
    public void delete(int fuelingId);
}
