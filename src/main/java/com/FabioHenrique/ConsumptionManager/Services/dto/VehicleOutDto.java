package com.FabioHenrique.ConsumptionManager.Services.dto;

import com.FabioHenrique.ConsumptionManager.Domain.Vehicle;

public class VehicleOutDto {
    private int id;
    private String name;
    private double initialOdometer;

    public VehicleOutDto(int id, String name, double initialOdometer) {
        this.id = id;
        this.name = name;
        this.initialOdometer = initialOdometer;
    }

    public VehicleOutDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.initialOdometer = vehicle.getInitialOdometer();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialOdometer() {
        return initialOdometer;
    }

    public void setInitialOdometer(double initialOdometer) {
        this.initialOdometer = initialOdometer;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }
}
