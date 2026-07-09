package com.FabioHenrique.ConsuptionManager.Tui;

import com.FabioHenrique.ConsuptionManager.Services.VehicleService;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.VehicleOutDto;

import java.util.List;
import java.util.Objects;

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
                    
                    1 - Selecionar veículo
                    2 - Cadastrar
                    3 - Listar
                    4 - Remover
                    0 - voltar
                    
                    Opção:
                    """);

            switch (option) {
                case 1 -> vehicleSelect();
                case 2 -> registry();
                case 3 -> list();
                case 4 -> delete();
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
        int option = input.readInteger("Entre com o Id do Veículo que deseja excluir: ");
        try {
            VehicleOutDto vehicleToExclude = vehicleService.getById(option);
            String confirmName = input.readString("Reescreva o nome do veículo \"" + vehicleToExclude.getName() + "\" para confirmar a exclusão: ");
            if (Objects.equals(confirmName, vehicleToExclude.getName())) {
                vehicleService.delete(vehicleToExclude.getId());
            } else {
                System.out.println("Os nomes não coincidem, tente novamente.");
            }
        } catch (RuntimeException e) {
            System.out.println("Veículo não encontrado, informe um ID correto.");
        }
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

        vehicleService.create(new VehicleInDto(name, initialOdometer));

        System.out.println("Veículo cadastrado com sucesso");
        System.out.println();

        list();
    }
}
