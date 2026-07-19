package com.FabioHenrique.ConsumptionManager.ui.Swing.Vehicle;

import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;
import com.FabioHenrique.ConsumptionManager.ui.Swing.AppContextGui;

import javax.swing.*;
import java.awt.*;

public class VehicleDialog extends JDialog {

    private JTextField nameTxt;
    private JTextField initialOdometerTxt;
    private final AppContextGui appContextGui;
    private VehicleOutDto vehicleOutDto;

    public VehicleDialog(JFrame parent, AppContextGui appContextGui) {
        super(parent, "Cadastrar Veículo", true);
        this.appContextGui = appContextGui;
        initForm();
    }
    public VehicleDialog(JFrame parent, AppContextGui appContextGui, VehicleOutDto vehicle) {
        super(parent, "Atualizar Veículo", true);
        this.appContextGui = appContextGui;
        this.vehicleOutDto = vehicle;
        initForm();
    }

    private void initForm() {

        nameTxt = new JTextField(15);
        initialOdometerTxt = new JTextField(15);

        if (vehicleOutDto != null) {
            nameTxt.setText(vehicleOutDto.getName());
            initialOdometerTxt.setText(String.valueOf(vehicleOutDto.getInitialOdometer()));
        }

        JButton saveBtn = new JButton("Salvar");

        setLayout(new GridLayout(3,2));

        add(new JLabel("Nome"));
        add(nameTxt);

        add(new JLabel("Odômetro Inicial"));
        add(initialOdometerTxt);

        add(saveBtn);

        pack();

        setLocationRelativeTo(this.getParent());


        saveBtn.addActionListener(e -> saveVehicle());
    }

    private void saveVehicle() {
        try {
            double initialOdometer = Double.parseDouble(initialOdometerTxt.getText());
            if (vehicleOutDto == null) {
                appContextGui.getVehicleService().create(new VehicleInDto(nameTxt.getText(), initialOdometer));
            } else {
                appContextGui.getVehicleService().update(
                        vehicleOutDto.getId(),
                        new VehicleInDto(vehicleOutDto.getName(), initialOdometer)
                );
            }
            dispose();
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    this,
                    "Verifique se você realmente colocou valores numéricos separando os decimais por ponto."
            );
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro: " + e.getMessage()
            );
        }
    }
}
