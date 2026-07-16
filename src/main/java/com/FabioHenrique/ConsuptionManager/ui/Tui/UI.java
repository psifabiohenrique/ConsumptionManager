package com.FabioHenrique.ConsuptionManager.ui.Tui;

public abstract class UI {
    InputHelper inputHelper;
    AppContext appContext;

    public void show(){}

    void showHeader() {
        clearTerminal();
        System.out.println();
        System.out.println("=======================");
        try{
            System.out.println("Veículo selecionado: " + appContext.getSelectedVehicle().getName());
        } catch (Exception e){
            System.out.println("Nenhum veículo selecionado.");

        }
        System.out.println("=======================");

        System.out.println();
        System.out.println("=======================");
        System.out.println("CONTROLE DE ABASTECIMENTOS");
        System.out.println("=======================");
    }

    void clearTerminal() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
