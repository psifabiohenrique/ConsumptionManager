package com.FabioHenrique.ConsumptionManager.Services;

import com.FabioHenrique.ConsumptionManager.Domain.Vehicle;
import com.FabioHenrique.ConsumptionManager.Repository.Interfaces.VehicleRepository;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;

import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public VehicleService(VehicleRepository repository) {
        this.vehicleRepository = repository;
    }

    public VehicleOutDto create(VehicleInDto dto) {
        int lastId = 0;
        try{
            lastId += vehicleRepository.listAll().getLast().getId();
        } catch (Exception _) {}
        Vehicle newVehicle = new Vehicle(lastId + 1, dto.getName(), dto.getInitialOdometer());

        return new VehicleOutDto(vehicleRepository.save(newVehicle));
    }
    public VehicleOutDto getById(int vehicleId) {
        return new VehicleOutDto(vehicleRepository.getOne(vehicleId));
    }
    public List<VehicleOutDto> getAll() {
        List<Vehicle> vehicleList = vehicleRepository.listAll();
        List<VehicleOutDto> vehicleOutDtos = new ArrayList<>();

        for (Vehicle vehicle : vehicleList) {
            vehicleOutDtos.add(new VehicleOutDto(vehicle));
        }
        return vehicleOutDtos;
    }
    public VehicleOutDto update(int vehicleId, VehicleInDto dto) {
        Vehicle updatedVehicle = vehicleRepository.getOne(vehicleId);
        updatedVehicle.setName(dto.getName());
        updatedVehicle.setInitialOdometer(dto.getInitialOdometer());
        return new VehicleOutDto(vehicleRepository.update(vehicleId, updatedVehicle));
    }
    public VehicleOutDto delete(int vehicleId) {
        VehicleOutDto vehicle = getById(vehicleId);
        vehicleRepository.delete(vehicle.getId());
        return vehicle;
    }
}
