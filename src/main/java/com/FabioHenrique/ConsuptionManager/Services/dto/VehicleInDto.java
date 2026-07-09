package com.FabioHenrique.ConsuptionManager.Services.dto;

public class VehicleInDto {
    private String name;
    private double initialOdometer;

    public VehicleInDto(String name, double initialOdometer) {
        this.name = name;
        this.initialOdometer = initialOdometer;
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
}
