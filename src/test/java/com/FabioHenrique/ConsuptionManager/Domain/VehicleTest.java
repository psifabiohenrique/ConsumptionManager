package com.FabioHenrique.ConsuptionManager.Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VehicleTest {
    @Test
    void mustCreateVehicle() {
        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        Assertions.assertEquals(1, vehicle.getId());
        Assertions.assertEquals("Teste", vehicle.getName());
        Assertions.assertEquals(12345, vehicle.getInitialOdometer());
    }

    @Test
    void mustSetVehicleAttributes() {
        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        vehicle.setName("Teste2");
        vehicle.setInitialOdometer(54321.0);

        Assertions.assertEquals(1, vehicle.getId());
        Assertions.assertEquals("Teste2", vehicle.getName());
        Assertions.assertEquals(54321, vehicle.getInitialOdometer());
    }
}
