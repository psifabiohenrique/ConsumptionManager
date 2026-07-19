package com.FabioHenrique.ConsumptionManager.ui.Swing.Fueling;

import com.FabioHenrique.ConsumptionManager.Services.dto.FuelingOutDto;
import com.FabioHenrique.ConsumptionManager.ui.Swing.AppContextGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FuelingPanel extends JPanel{
    private final JFrame parent;
    private final AppContextGui appContextGui;
    private JTable table;
    private final DefaultTableModel model;

    public FuelingPanel(JFrame parent, AppContextGui appContextGui) {
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
        model.addColumn("Veículo");
        model.addColumn("Odômetro");
        model.addColumn("Litros");
        model.addColumn("Tipo de Combustível");
        model.addColumn("Custo total");

        table = new JTable(model);

        add(buttonsPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadFueling();


        newBtn.addActionListener(e -> newFueling());
        editBtn.addActionListener(e -> editFueling());
        excludeBtn.addActionListener(e -> deleteFueling());
    }

    private void newFueling() {
        FuelingDialog dialog = new FuelingDialog((JFrame) this.parent, this.appContextGui);
        dialog.setVisible(true);
        loadFueling();
    }

    private void editFueling() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            int fuelingId = (int) table.getValueAt(selectedRow, 0);
            FuelingDialog dialog = new FuelingDialog(
                    this.parent,
                    this.appContextGui,
                    this.appContextGui.getFuelingService().getById(fuelingId)
            );
            dialog.setVisible(true);
            loadFueling();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um abastecimento a ser editado!"
            );
        }
    }

    private void deleteFueling() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            int fuelingId = (int) table.getValueAt(selectedRow, 0);
            appContextGui.getFuelingService().delete(fuelingId);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um abastecimento a ser deletado!"
            );
        }
        loadFueling();
    }

    private void loadFueling() {
        model.setRowCount(0);

        for(FuelingOutDto fueling : appContextGui.getFuelingService().getAll()) {
            model.addRow(new Object[] {
                fueling.getId(),
                appContextGui.getVehicleService().getById(fueling.getVehicleId()).getName(),
                fueling.getOdometer(),
                fueling.getLiters(),
                fueling.getFuelType(),
                fueling.getTotalCost()
            });
        }
    }
}
