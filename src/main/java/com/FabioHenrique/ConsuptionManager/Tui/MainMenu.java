package com.FabioHenrique.ConsuptionManager.Tui;

public class MainMenu extends UI {
    private final VehicleUI vehicleUI;
    private final FuelingUI fuelingUI;

    public MainMenu(InputHelper input, VehicleUI vehicleUI, FuelingUI fuelingUI, AppContext context) {
        this.inputHelper = input;
        this.vehicleUI = vehicleUI;
        this.fuelingUI = fuelingUI;
        this.appContext = context;
    }

    public void show() {
        int opcao;

        do {
            showHeader();
            opcao = inputHelper.readInteger("""
                    
                    1 - Gerenciar veículos
                    2 - Gerenciar abastecimentos
                    3 - Exibir consumo médio
                    0 - Sair
                    
                    Opção:
                    """);

            switch (opcao) {
                case 1 -> // Veículos
                    vehicleUI.show();
                case 2 -> {
                    // Abastecimentos
                    fuelingUI.show();
                }
                case 3 -> {
                    // Consumo futuro
                }
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

}
