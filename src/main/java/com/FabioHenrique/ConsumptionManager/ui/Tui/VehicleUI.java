package com.FabioHenrique.ConsumptionManager.ui.Tui;

import com.FabioHenrique.ConsumptionManager.Services.VehicleService;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;

import java.util.List;
import java.util.Objects;

public class VehicleUI extends UI{
    private final VehicleService vehicleService;

    public VehicleUI(VehicleService vehicleService, InputHelper input, AppContextTui context) {
        this.vehicleService = vehicleService;
        this.inputHelper = input;
        this.appContextTui = context;
    }

    public void show() {
        int option;

        do {
            showHeader();
            System.out.println();
            System.out.println("====== VEÍCULOS ======");

            option = inputHelper.readInteger("""
                    
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
        int vehicleId = inputHelper.readInteger("Entre com o ID do veículo a ser selecionado: ");
        appContextTui.setSelectedVehicle(vehicleService.getById(vehicleId));
    }

    private void delete() {
        int option = inputHelper.readInteger("Entre com o Id do Veículo que deseja excluir: ");
        try {
            VehicleOutDto vehicleToExclude = vehicleService.getById(option);
            String confirmName = inputHelper.readString("Reescreva o nome do veículo \"" + vehicleToExclude.getName() + "\" para confirmar a exclusão: ");
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
            inputHelper.readString("Entre com alguma letra para voltar ao menu.");
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
        inputHelper.readString("Entre com alguma letra para voltar ao menu.");
    }

    private void registry() {
        System.out.println();
        System.out.println("=== CADASTRO DE VEÍCULO ===");

        String name = inputHelper.readString("Nome: ");
        double initialOdometer = inputHelper.readDouble("Odômetro inicial: ");

        vehicleService.create(new VehicleInDto(name, initialOdometer));

        System.out.println("Veículo cadastrado com sucesso");
        System.out.println();

        list();
    }
}
