package com.FabioHenrique.ConsumptionManager.ui.Swing.Vehicle;

import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;
import com.FabioHenrique.ConsumptionManager.ui.Swing.AppContextGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VehiclePanel extends JPanel {
    private final JFrame parent;
    private final JTable table;
    private final DefaultTableModel model;
    private final AppContextGui appContextGui;

    public VehiclePanel(JFrame parent, AppContextGui appContextGui) {

        this.appContextGui = appContextGui;
        this.parent = parent;
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        JButton newBtn = new JButton("Novo");
        JButton editBtn = new JButton("Editar");
        JButton excludeBtn = new JButton("Excluir");

        buttonsPanel.add(newBtn);
        buttonsPanel.add(editBtn);
        buttonsPanel.add(excludeBtn);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Odômetro Inicial");

        table = new JTable(model);

        add(buttonsPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        newBtn.addActionListener(e -> newVehicle());
        excludeBtn.addActionListener(e -> deleteVehicle());
        editBtn.addActionListener(e -> editVehicle());

        loadVehicles();
    }

    private void newVehicle() {
        VehicleDialog dialog = new VehicleDialog((JFrame) this.parent, this.appContextGui);
        dialog.setVisible(true);
        loadVehicles();
    }

    private void editVehicle() {
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {
            int vehicleId = (int) table.getValueAt(table.getSelectedRow(), 0);
            VehicleDialog dialog = new VehicleDialog(
                    (JFrame) this.parent,
                    this.appContextGui,
                    this.appContextGui.getVehicleService().getById(vehicleId)
            );
            dialog.setVisible(true);
            loadVehicles();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um veículo a ser editado!"
            );
        }
    }

    private void deleteVehicle() {
        int selectedRow = table.getSelectedRow();

        if(selectedRow != -1) {
            int vehicleId = (int) table.getValueAt(table.getSelectedRow(), 0);
            appContextGui.getVehicleService().delete(vehicleId);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um veículo a ser deletado!"
            );
        }
        loadVehicles();
    }

    private void loadVehicles() {
        model.setRowCount(0);

        for(VehicleOutDto vehicle : appContextGui.getVehicleService().getAll()) {
            model.addRow(new Object[] {
                    vehicle.getId(),
                    vehicle.getName(),
                    vehicle.getInitialOdometer()
            });
        }
    }
}
