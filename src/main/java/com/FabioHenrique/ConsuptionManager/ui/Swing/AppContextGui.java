package com.FabioHenrique.ConsuptionManager.ui.Swing;

import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

public class AppContextGui {
    private VehicleOutDto selectedVehicle;
    private final VehicleService vehicleService;

    public AppContextGui(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
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
}
