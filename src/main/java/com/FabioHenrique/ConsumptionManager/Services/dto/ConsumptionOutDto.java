package com.FabioHenrique.ConsumptionManager.Services.dto;

public class ConsumptionOutDto {
    private double averageConsumption;
    private double averageCost;
    private double totalCost;
    private int fuelingCount;

    public ConsumptionOutDto(double averageConsumption, double averageCost, double totalCost, int fuelingCount) {
        this.averageConsumption = averageConsumption;
        this.averageCost = averageCost;
        this.totalCost = totalCost;
        this.fuelingCount = fuelingCount;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getFuelingCount() {
        return fuelingCount;
    }

    public void setFuelingCount(int fuelingCount) {
        this.fuelingCount = fuelingCount;
    }
}
