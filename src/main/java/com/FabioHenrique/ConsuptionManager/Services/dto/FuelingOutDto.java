package com.FabioHenrique.ConsuptionManager.Services.dto;

import com.FabioHenrique.ConsuptionManager.Domain.FuelType;
import com.FabioHenrique.ConsuptionManager.Domain.Fueling;

public class FuelingOutDto {
    private final int id;
    private final int vehicleId;
    private double odometer;
    private double liters;
    private FuelType fuelType;
    private double totalCost;

    public FuelingOutDto(int id, int vehicleId, double odometer, double liters, FuelType fuelType, double totalCost) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.liters = liters;
        this.fuelType = fuelType;
        this.totalCost = totalCost;
    }

    public FuelingOutDto(Fueling fueling){
        this.id = fueling.getId();
        this.vehicleId = fueling.getVehicleId();
        this.odometer = fueling.getOdometer();
        this.liters = fueling.getLiters();
        this.fuelType = fueling.getFuelType();
        this.totalCost = fueling.getTotalCost();
    }

    public int getId() {
        return id;
    }

    public int getVehicleId() {
        return vehicleId;
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
