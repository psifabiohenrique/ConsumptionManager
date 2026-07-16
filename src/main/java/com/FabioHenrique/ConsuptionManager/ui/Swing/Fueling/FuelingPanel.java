package com.FabioHenrique.ConsuptionManager.ui.Swing.Fueling;

import com.FabioHenrique.ConsuptionManager.ui.Swing.AppContextGui;

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

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Odômetro Inicial");

        table = new JTable(model);

        add(buttonsPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
