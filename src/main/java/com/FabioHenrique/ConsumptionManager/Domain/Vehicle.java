package com.FabioHenrique.ConsumptionManager.Domain;

public class Vehicle {
    private final int id;
    private String name;
    private double initialOdometer;

    public Vehicle(int id, String name, double initialOdometer) {
        this.id = id;
        this.name = name;
        this.initialOdometer = initialOdometer;
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

    public Double getInitialOdometer() {
        return initialOdometer;
    }

    public void setInitialOdometer(Double initialOdometer) {
        this.initialOdometer = initialOdometer;
    }
}
