package com.FabioHenrique.ConsuptionManager;

import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.VehicleRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Json.JsonFuelingRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Json.JsonVehicleRepository;
import com.FabioHenrique.ConsuptionManager.Services.AverageConsumptionService;
import com.FabioHenrique.ConsuptionManager.Services.FuelingService;
import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.ui.Swing.AppContextGui;
import com.FabioHenrique.ConsuptionManager.ui.Swing.MainFrame;
import com.FabioHenrique.ConsuptionManager.ui.Tui.*;

import javax.swing.*;

public class Main {
    static void main(String[] args){

//        MainMenu tui = getTui();
//        tui.show();
        getGui();
    }

    private static void getGui() {

        // Vehicle
        VehicleRepository vehicleRepository= new JsonVehicleRepository();
        VehicleService vehicleService = new VehicleService(vehicleRepository);

        // Fueling
        FuelingRepository fuelingRepository = new JsonFuelingRepository();
        FuelingService fuelingService = new FuelingService(fuelingRepository);

        // Average Consumption
        AverageConsumptionService avgConsumptionService = new AverageConsumptionService(fuelingService);

        AppContextGui appContextGui = new AppContextGui(vehicleService, fuelingService);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(appContextGui);
            frame.setVisible(true);
        });
    }

    private static MainMenu getTui() {
        InputHelper input = new InputHelper();
        AppContextTui appContextTui = new AppContextTui();

        // Vehicle
        VehicleRepository vehicleRepository= new JsonVehicleRepository();
        VehicleService vehicleService = new VehicleService(vehicleRepository);
        VehicleUI vehicleUI = new VehicleUI(vehicleService, input, appContextTui);

        // Fueling
        FuelingRepository fuelingRepository = new JsonFuelingRepository();
        FuelingService fuelingService = new FuelingService(fuelingRepository);
        FuelingUI fuelingUI = new FuelingUI(fuelingService, input, appContextTui);

        // Average Consumption
        AverageConsumptionService avgConsumptionService = new AverageConsumptionService(fuelingService);
        AverageConsumptionUI avgConsumption = new AverageConsumptionUI(input, appContextTui, avgConsumptionService);


        return new MainMenu(input, vehicleUI, fuelingUI, appContextTui, avgConsumption);
    }
}
