package com.FabioHenrique.ConsuptionManager.ui.Swing;

import com.FabioHenrique.ConsuptionManager.Services.FuelingService;
import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

public class AppContextGui {
    private VehicleOutDto selectedVehicle;
    private final VehicleService vehicleService;
    private final FuelingService fuelingService;

    public AppContextGui(VehicleService vehicleService, FuelingService fuelingService) {
        this.vehicleService = vehicleService;
        this.fuelingService = fuelingService;
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
}
