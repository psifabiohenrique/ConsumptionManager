package com.FabioHenrique.ConsuptionManager;

import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.VehicleRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Json.JsonFuelingRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Json.JsonVehicleRepository;
import com.FabioHenrique.ConsuptionManager.Services.FuelingService;
import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Tui.*;

public class Main {
    public static void main(String[] args){
        InputHelper input = new InputHelper();
        AppContext appContext = new AppContext();

        // Vehicle
        VehicleRepository vehicleRepository= new JsonVehicleRepository();
        VehicleService vehicleService = new VehicleService(vehicleRepository);
        VehicleUI vehicleUI = new VehicleUI(vehicleService, input, appContext);

        // Fueling
        FuelingRepository fuelingRepository = new JsonFuelingRepository();
        FuelingService fuelingService = new FuelingService(fuelingRepository);
        FuelingUI fuelingUI = new FuelingUI(fuelingService, input, appContext);


        MainMenu tui = new MainMenu(input, vehicleUI, fuelingUI, appContext);
        tui.start();
    }
}
