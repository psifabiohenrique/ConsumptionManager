package com.FabioHenrique.ConsumptionManager.ui.Swing;

import com.FabioHenrique.ConsumptionManager.ui.Swing.Fueling.FuelingPanel;
import com.FabioHenrique.ConsumptionManager.ui.Swing.Statistics.StatisticsPanel;
import com.FabioHenrique.ConsumptionManager.ui.Swing.Vehicle.VehiclePanel;

import javax.swing.*;

public class MainFrame  extends JFrame {
    private final AppContextGui appContextGui;

    public MainFrame(AppContextGui appContextGui) {
        this.appContextGui = appContextGui;
        setTitle("Controle de Abastecimento");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Veículos", new VehiclePanel(this, appContextGui));
        tabs.addTab("Abastecimentos", new FuelingPanel(this, appContextGui));
        tabs.addTab("Relatórios", new StatisticsPanel(appContextGui));

        add(tabs);
    }
}
