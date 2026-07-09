package com.FabioHenrique.ConsuptionManager.Repository.Json;

import com.FabioHenrique.ConsuptionManager.Domain.Vehicle;

public class VehicleJsonModel {
    private int id;
    private String name;
    private double initialOdometer;

    public VehicleJsonModel() {}

    public static VehicleJsonModel serialize(Vehicle vehicle) {
        VehicleJsonModel model = new VehicleJsonModel();
        model.setId(vehicle.getId());
        model.setName(vehicle.getName());
        model.setInitialOdometer(vehicle.getInitialOdometer());
        return model;
    }

    public Vehicle deserialize() {
        return new Vehicle(this.id, this.name, this.initialOdometer);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
