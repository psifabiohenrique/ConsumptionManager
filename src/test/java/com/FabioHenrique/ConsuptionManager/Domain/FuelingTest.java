package com.FabioHenrique.ConsuptionManager.Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuelingTest {

    @Test
    void mustCreateFueling() {
        Fueling fueling = new Fueling(
                1,
                1,
                12345,
                10,
                FuelType.GASOLINA,
                50
        );

        Assertions.assertEquals(1, fueling.getId());
        Assertions.assertEquals(1, fueling.getVehicleId());
        Assertions.assertEquals(12345, fueling.getOdometer());
        Assertions.assertEquals(10, fueling.getLiters());
        Assertions.assertEquals(FuelType.GASOLINA, fueling.getFuelType());
        Assertions.assertEquals(50, fueling.getTotalCost());
    }

    @Test
    void mustSetFuelingAttributes() {
        Fueling fueling = new Fueling(
                1,
                1,
                12345,
                10,
                FuelType.GASOLINA,
                50
        );
        fueling.setFuelType(FuelType.DIESEL);
        fueling.setLiters(15);
        fueling.setOdometer(54321);
        fueling.setTotalCost(5);

        Assertions.assertEquals(1, fueling.getId());
        Assertions.assertEquals(1, fueling.getVehicleId());
        Assertions.assertEquals(54321, fueling.getOdometer());
        Assertions.assertEquals(15, fueling.getLiters());
        Assertions.assertEquals(FuelType.DIESEL, fueling.getFuelType());
        Assertions.assertEquals(5, fueling.getTotalCost());
    }
}
