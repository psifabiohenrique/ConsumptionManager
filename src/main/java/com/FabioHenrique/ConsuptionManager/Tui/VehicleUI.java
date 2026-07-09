package com.FabioHenrique.ConsuptionManager.Tui;

import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

import java.util.List;

public class VehicleUI {
    private final VehicleService vehicleService;
    private final InputHelper input;
    private final AppContext context;

    public VehicleUI(VehicleService vehicleService, InputHelper input, AppContext context) {
        this.vehicleService = vehicleService;
        this.input = input;
        this.context = context;
    }

    public void showMenu() {
        int option;

        do {
            System.out.println();
            System.out.println("====== VEÍCULOS ======");

            option = input.readInteger("""
                    
                    1 - Cadastrar
                    2 - Listar
                    3 - Buscar por ID
                    4 - Remover
                    5 - Selecionar veículo
                    0 - voltar
                    
                    Opção:
                    """);

            switch (option) {
                case 1 -> registry();
                case 2 -> list();
                case 3 -> search();
                case 4 -> delete();
                case 5 -> vehicleSelect();
                case 0 -> {}
                default -> System.out.println("Opção Inválida!");
            }
        } while (option != 0);
    }

    private void vehicleSelect() {
        int vehicleId = input.readInteger("Entre com o ID do veículo a ser selecionado: ");
        context.setSelectedVehicle(vehicleService.getById(vehicleId));
    }

    private void delete() {
        System.out.println("Não implementado ainda");
    }

    private void search() {
        System.out.println("Não implementado ainda");
    }

    private void list() {
        List<VehicleOutDto> vehicles = vehicleService.getAll();

        if (vehicles.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        System.out.println();

        System.out.printf("%-5s %-10s %-30s%n",
                "ID", "Nome", "Odômetro Inicial");

        for (VehicleOutDto vehicle : vehicles) {
            System.out.printf("%-5s %-10s %-30s%n",
                    vehicle.getId(),
                    vehicle.getName(),
                    vehicle.getInitialOdometer());
        }
    }

    private void registry() {
        System.out.println();
        System.out.println("=== CADASTRO DE VEÍCULO ===");

        String name = input.readString("Nome: ");
        double initialOdometer = input.readDouble("Odômetro inicial: ");

        VehicleOutDto vehicle = vehicleService.create(new VehicleInDto(name, initialOdometer));

        System.out.println("Veículo cadastrado com sucesso");
        System.out.println();

        list();
    }
}
