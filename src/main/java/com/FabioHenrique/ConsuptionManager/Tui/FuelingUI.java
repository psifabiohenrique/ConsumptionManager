package com.FabioHenrique.ConsuptionManager.Tui;

import com.FabioHenrique.ConsuptionManager.Domain.FuelType;
import com.FabioHenrique.ConsuptionManager.Domain.Fueling;
import com.FabioHenrique.ConsuptionManager.Services.FuelingService;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingInDto;

import java.util.List;

public class FuelingUI {
    private final FuelingService fuelingService;
    private final InputHelper input;
    private final AppContext context;

    public FuelingUI(FuelingService fuelingService, InputHelper input, AppContext context) {
        this.fuelingService = fuelingService;
        this.input = input;
        this.context = context;
    }

    public void showMenu() {
        int option;

        do {
            System.out.println();
            System.out.println("==== ABASTECIMENTO ====");
            System.out.println();

            option = input.readInteger("""
                    1 - Cadastrar
                    2 - Listar
                    3 - Editar
                    4 - Remover
                    0 - Voltar
                    
                    Opção:
                    """);

            switch (option) {
                case 1 -> registry();
                case 2 -> list();
                case 3 -> update();
                case 4 -> delete();
                case 0 -> {}
                default -> System.out.println("Input inválido.");
            }
        } while (option != 0);

    }

    private void registry() {
        try {
            FuelingInDto dto = fuelingInputs();
            fuelingService.create(dto);
            System.out.println("Abastecimento salvo com sucesso.");
        } catch (Exception _) {

        }
    }

    private void list() {
        try {
            int vehicleId = getVehicleId();
            List<Fueling> fuelings = fuelingService.getAllByVehicle(vehicleId);

            if (fuelings.isEmpty()) {
                System.out.println("Nenhum abastecimento cadastrado.");
                return;
            }

            System.out.println();
            System.out.printf("%-5s %-10s %-10s %-10s %-15s %-10s%n",
                    "ID", "Veículo", "Odômetro", "Litros", "Combustível", "Custo total");

            for (Fueling fueling : fuelings) {
                System.out.printf("%-5s %-10s %-10s %-10s %-15s %-10s%n",
                        fueling.getId(),
                        context.getSelectedVehicle().getName(),
                        fueling.getOdometer(),
                        fueling.getLiters(),
                        fueling.getFuelType(),
                        fueling.getTotalCost());
            }
        } catch (Exception _) {
            System.out.println("Não há abastecimentos a serem exibidos.");
        }
    }

    private void update() {
        try {
            int fuelingId = input.readInteger("Entre com o ID do abastecimento a ser ALTERADO:");
            FuelingInDto dto = fuelingInputs();
            fuelingService.update(fuelingId, dto);
            System.out.println("Abastecimento salvo com sucesso.");
        } catch (Exception _) {

        }
    }

    private void delete() {
        try {
            int fuelingId = input.readInteger("Entre com o ID do abastecimento a ser EXCLUIDO:");
            fuelingService.delete(fuelingId);
            System.out.println("Abastecimento excluido com sucesso.");
        } catch (Exception _) {

        }
    }
    private int getVehicleId() {
        try {
            return context.getSelectedVehicle().getId();
        } catch (RuntimeException e) {
            System.out.println("É necessário ter um veículo selecionado.");
            throw new RuntimeException();
        }
    }
    private FuelingInDto fuelingInputs() {
        System.out.println();

        int vehicleId = getVehicleId();

        int odometer = input.readInteger("Informe o Odômetro atual: ");
        double liters = input.readDouble("Informe quantos litros foram abastecidos: ");
        FuelType fuelType;
        int fuelOption = input.readInteger("""
                    Informe o tipo de combustível utilizado:
                    
                    1 - Gasolina
                    2 - Álcool
                    3 - Diesel
                    
                    Opção:
                    """);

        switch (fuelOption) {
            case 1 -> fuelType = FuelType.GASOLINA;
            case 2 -> fuelType = FuelType.ALCOOL;
            case 3 -> fuelType = FuelType.DIESEL;
            default -> {
                System.out.println("Input incorreto, operação cancelada, inicie novamente.");
                throw new RuntimeException();
            }
        }
        double totalCost = input.readDouble("Informe o custo total do abastecimento: ");
        return new FuelingInDto(vehicleId, odometer, liters, fuelType, totalCost);
    }

}
