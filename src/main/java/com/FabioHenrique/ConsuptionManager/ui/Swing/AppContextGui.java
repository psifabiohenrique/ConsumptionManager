package com.FabioHenrique.ConsuptionManager.ui.Swing;

import com.FabioHenrique.ConsuptionManager.Services.AverageConsumptionService;
import com.FabioHenrique.ConsuptionManager.Services.FuelingService;
import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Services.dto.ConsumptionOutDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

public class AppContextGui {
    private VehicleOutDto selectedVehicle;
    private final VehicleService vehicleService;
    private final FuelingService fuelingService;
    private final AverageConsumptionService averageConsumptionService;

    public AppContextGui(VehicleService vehicleService, FuelingService fuelingService, AverageConsumptionService averageConsumptionService) {
        this.vehicleService = vehicleService;
        this.fuelingService = fuelingService;
        this.averageConsumptionService = averageConsumptionService;
    }

    public VehicleOutDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleOutDto selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public FuelingService getFuelingService() {
        return fuelingService;
    }

    public AverageConsumptionService getAverageConsumptionService() {
        return averageConsumptionService;
    }
}
