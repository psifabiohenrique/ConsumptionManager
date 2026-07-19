package com.FabioHenrique.ConsumptionManager.ui.Tui;


import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;

public class AppContextTui {
    private VehicleOutDto selectedVehicle;

    public VehicleOutDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleOutDto selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }
}
