package com.FabioHenrique.ConsumptionManager.Services.dto;

import com.FabioHenrique.ConsumptionManager.Domain.FuelType;

public class FuelingInDto {
    private int vehicleId;
    private double odometer;
    private double liters;
    private FuelType fuelType;
    private double totalCost;

    public FuelingInDto(int vehicleId, double odometer, double liters, FuelType fuelType, double totalCost) {
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.liters = liters;
        this.fuelType = fuelType;
        this.totalCost = totalCost;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
