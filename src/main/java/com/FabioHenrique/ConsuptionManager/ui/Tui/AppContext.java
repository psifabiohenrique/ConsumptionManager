package com.FabioHenrique.ConsuptionManager.ui.Tui;


import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

public class AppContext {
    private VehicleOutDto selectedVehicle;

    public VehicleOutDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleOutDto selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }
}
