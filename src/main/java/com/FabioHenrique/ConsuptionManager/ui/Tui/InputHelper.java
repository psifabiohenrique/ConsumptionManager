package com.FabioHenrique.ConsuptionManager.ui.Tui;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public double readDouble(String message) {
        while(true) {
            try {
                System.out.println(message);
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor Inválido!");
            }
        }
    }

    public Integer readInteger (String message) {
        while(true) {
            try {
                System.out.println(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor Inválido!");
            }
        }
    }
}
