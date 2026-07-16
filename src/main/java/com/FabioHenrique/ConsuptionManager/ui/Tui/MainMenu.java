package com.FabioHenrique.ConsuptionManager.ui.Tui;

public class MainMenu extends UI {
    private final VehicleUI vehicleUI;
    private final FuelingUI fuelingUI;
    private final AverageConsumptionUI avgConsumptionUI;

    public MainMenu(InputHelper input, VehicleUI vehicleUI, FuelingUI fuelingUI, AppContext context, AverageConsumptionUI avgConsumptionUI) {
        this.inputHelper = input;
        this.vehicleUI = vehicleUI;
        this.fuelingUI = fuelingUI;
        this.appContext = context;
        this.avgConsumptionUI = avgConsumptionUI;
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
                    avgConsumptionUI.show();
                }
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

}
