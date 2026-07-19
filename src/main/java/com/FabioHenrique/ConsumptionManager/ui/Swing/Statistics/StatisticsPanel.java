package com.FabioHenrique.ConsumptionManager.ui.Swing.Statistics;

import com.FabioHenrique.ConsumptionManager.Services.dto.ConsumptionOutDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;
import com.FabioHenrique.ConsumptionManager.ui.Swing.AppContextGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StatisticsPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final AppContextGui appContextGui;

    public StatisticsPanel(AppContextGui appContextGui) {
        this.appContextGui = appContextGui;
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        JButton updateBtn = new JButton("Atualizar");
//        JComboBox<VehicleOutDto> vehiclesCb = new JComboBox<>();
//        for (VehicleOutDto vehicle : appContextGui.getVehicleService().getAll()) {
//            vehiclesCb.addItem(vehicle);
//        }

        buttonsPanel.add(updateBtn);
//        buttonsPanel.add(vehiclesCb);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Veículo");
        model.addColumn("Consumo médio");
        model.addColumn("Custo médio");
        model.addColumn("Custo total");
        model.addColumn("Abastecimentos usados");

        table = new JTable(model);

        add(buttonsPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        updateBtn.addActionListener(e -> updateTable());

        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);

        List<VehicleOutDto> vehicles = appContextGui.getVehicleService().getAll();

        for (VehicleOutDto vehicle : vehicles) {
            ConsumptionOutDto consumption;
            try {
                consumption = appContextGui.getAverageConsumptionService().getAverageConsumption(vehicle.getId());
            } catch (RuntimeException e) {
                consumption = new ConsumptionOutDto(
                        0,0,0,0
                );
            }
            model.addRow(new Object[] {
                    vehicle.getName(),
                    String.format("%.2f", consumption.getAverageConsumption()),
                    String.format("R$ %.2f", consumption.getAverageCost()),
                    String.format("R$ %.2f", consumption.getTotalCost()),
                    consumption.getFuelingCount(),

            });
        }
    }
}
