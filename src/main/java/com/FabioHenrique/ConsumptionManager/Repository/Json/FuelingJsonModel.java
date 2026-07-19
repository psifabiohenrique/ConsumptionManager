package com.FabioHenrique.ConsumptionManager.Repository.Json;

import com.FabioHenrique.ConsumptionManager.Domain.FuelType;
import com.FabioHenrique.ConsumptionManager.Domain.Fueling;

public class FuelingJsonModel {
    private int id;
    private int vehicleId;
    private double odometer;
    private double liters;
    private FuelType fuelType;
    private double totalCost;

    public FuelingJsonModel() {
    }

    public static FuelingJsonModel serialize(Fueling fueling) {
        FuelingJsonModel model = new FuelingJsonModel();
        model.setId(fueling.getId());
        model.setVehicleId(fueling.getVehicleId());
        model.setOdometer(fueling.getOdometer());
        model.setLiters(fueling.getLiters());
        model.setFuelType(fueling.getFuelType());
        model.setTotalCost(fueling.getTotalCost());
        return model;
    }

    public Fueling deserialize() {
        return new Fueling(this.id, this.vehicleId, this.odometer, this.liters, this.fuelType, this.totalCost);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setOdometer(double odometer) {
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
