package com.FabioHenrique.ConsuptionManager.Repository.Interfaces;

import com.FabioHenrique.ConsuptionManager.Domain.Vehicle;

import java.util.List;

public interface VehicleRepository {
    public Vehicle save(Vehicle vehicle);
    public List<Vehicle> listAll();
    public Vehicle getOne(int vehicleId);
    public Vehicle update(int vehicleId, Vehicle updatedVehicle);
    public void delete(int vehicleId);
}
