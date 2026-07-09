package com.FabioHenrique.ConsuptionManager.Tui;

public class MainMenu {
    private final InputHelper input;
    private final VehicleUI vehicleUI;
    private final FuelingUI fuelingUI;
    private final AppContext context;

    public MainMenu(InputHelper input, VehicleUI vehicleUI, FuelingUI fuelingUI, AppContext context) {
        this.input = input;
        this.vehicleUI = vehicleUI;
        this.fuelingUI = fuelingUI;
        this.context = context;
    }

    public void start() {
        int opcao;

        do {
            showHeader();
            opcao = input.readInteger("""
                    
                    1 - Gerenciar veículos
                    2 - Gerenciar abastecimentos
                    3 - Exibir consumo médio
                    0 - Sair
                    
                    Opção:
                    """);

            switch (opcao) {
                case 1 -> // Veículos
                    vehicleUI.showMenu();
                case 2 -> {
                    // Abastecimentos
                    fuelingUI.showMenu();
                }
                case 3 -> {
                    // Consumo futuro
                }
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void showHeader() {
        System.out.println();
        System.out.println("=======================");
        try{
            System.out.println("Veículo selecionado: " + context.getSelectedVehicle().getName());
        } catch (Exception e){
            System.out.println("Nenhum veículo selecionado.");

        }
        System.out.println("=======================");

        System.out.println();
        System.out.println("=======================");
        System.out.println("CONTROLE DE ABASTECIMENTOS");
        System.out.println("=======================");
    }
}
