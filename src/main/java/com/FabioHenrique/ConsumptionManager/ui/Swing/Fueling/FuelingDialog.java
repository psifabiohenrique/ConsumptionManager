package com.FabioHenrique.ConsumptionManager.ui.Swing.Fueling;

import com.FabioHenrique.ConsumptionManager.Domain.FuelType;
import com.FabioHenrique.ConsumptionManager.Services.dto.FuelingInDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.FuelingOutDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;
import com.FabioHenrique.ConsumptionManager.ui.Swing.AppContextGui;

import javax.swing.*;
import java.awt.*;

public class FuelingDialog extends JDialog {
    private JComboBox<VehicleOutDto> vehiclesCb;
    private JTextField odometerTxt;
    private JTextField litersTxt;
    private JComboBox<FuelType> fuelTypeCb;
    private JTextField totalCostTxt;

    private final AppContextGui appContextGui;
    private FuelingOutDto fuelingOutDto;

    public FuelingDialog(JFrame parent, AppContextGui appContextGui) {
        super(parent, "Cadastrar Abastecimento", true);
        this.appContextGui = appContextGui;
        initForm();
    }

    public FuelingDialog(JFrame parent, AppContextGui appContextGui, FuelingOutDto fueling) {
        super(parent, "Atualizar Abastecimento", true);
        this.appContextGui = appContextGui;
        this.fuelingOutDto = fueling;
        initForm();
    }

    private void initForm() {
        vehiclesCb = new JComboBox<>();
        for (VehicleOutDto vehicle : appContextGui.getVehicleService().getAll()) {
            vehiclesCb.addItem(vehicle);
        }
        odometerTxt = new JTextField(15);
        litersTxt = new JTextField(15);
        fuelTypeCb = new JComboBox<>(FuelType.values());

        totalCostTxt = new JTextField(15);

        if (fuelingOutDto != null) {
            vehiclesCb.setSelectedItem(appContextGui.getVehicleService().getById(fuelingOutDto.getVehicleId()));
            odometerTxt.setText(String.valueOf(fuelingOutDto.getOdometer()));
            litersTxt.setText(String.valueOf(fuelingOutDto.getLiters()));
            fuelTypeCb.setSelectedItem((FuelType) fuelingOutDto.getFuelType());
            totalCostTxt.setText(String.valueOf(fuelingOutDto.getTotalCost()));
        }

        JButton saveBtn = new JButton("Salvar");

        setLayout(new GridLayout(6,2));

        add(new JLabel("Veículo"));
        add(vehiclesCb);

        add(new JLabel("Odômetro"));
        add(odometerTxt);

        add(new JLabel("Litros"));
        add(litersTxt);

        add(new JLabel("Tipo de Combustível"));
        add(fuelTypeCb);

        add(new JLabel("Custo"));
        add(totalCostTxt);


        add(saveBtn);

        pack();

        setLocationRelativeTo(this.getParent());

        saveBtn.addActionListener(e -> saveFueling());

    }

    private void saveFueling() {
        try {
            if (fuelingOutDto == null) {
                VehicleOutDto vehicle = (VehicleOutDto) vehiclesCb.getSelectedItem();

                assert vehicle != null;
                appContextGui.getFuelingService().create(new FuelingInDto(
                        vehicle.getId(),
                        Double.parseDouble(odometerTxt.getText()),
                        Double.parseDouble(litersTxt.getText()),
                        (FuelType) fuelTypeCb.getSelectedItem(),
                        Double.parseDouble(totalCostTxt.getText())
                ));
            } else {
                VehicleOutDto vehicle = (VehicleOutDto) vehiclesCb.getSelectedItem();

                assert vehicle != null;
                appContextGui.getFuelingService().update(
                        fuelingOutDto.getId(),
                        new FuelingInDto(
                            vehicle.getId(),
                            Double.parseDouble(odometerTxt.getText()),
                            Double.parseDouble(litersTxt.getText()),
                            (FuelType) fuelTypeCb.getSelectedItem(),
                            Double.parseDouble(totalCostTxt.getText())
                        )
                );
            }
            dispose();
        } catch (NumberFormatException e) {
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
