package com.FabioHenrique.ConsuptionManager.Repository.Json;

import com.FabioHenrique.ConsuptionManager.Domain.FuelType;
import com.FabioHenrique.ConsuptionManager.Domain.Fueling;
import com.FabioHenrique.ConsuptionManager.Domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFuelingRepositoryTest {

    private JsonFuelingRepository repository;
    private Fueling standardFueling;

    @BeforeEach
    void setup() throws IOException {
        File tempFile = File.createTempFile("fueling-test", ".json");

        repository = new JsonFuelingRepository(tempFile.getPath());

        standardFueling = new Fueling(1,1,123456,10, FuelType.GASOLINA,50);
        repository.save(standardFueling);
    }
    @Test
    void mustSaveFuelingInArchiveAndListAllFueling() {


        assertNotNull(standardFueling);

        List<Fueling> fuelingList = repository.listAll();

        assertEquals(1, fuelingList.size());

        assertEquals(1, fuelingList.getFirst().getId());
        assertEquals(1, fuelingList.getFirst().getVehicleId());
        assertEquals(123456, fuelingList.getFirst().getOdometer());
        assertEquals(10, fuelingList.getFirst().getLiters());
        assertEquals(FuelType.GASOLINA, fuelingList.getFirst().getFuelType());
        assertEquals(50, fuelingList.getFirst().getTotalCost());

    }

    @Test
    void mustGetOne() {
        Fueling fueling = repository.getOne(standardFueling.getId());
        assertNotNull(fueling);

        assertEquals(1, fueling.getId());
        assertEquals(1, fueling.getVehicleId());
        assertEquals(123456, fueling.getOdometer());
        assertEquals(10, fueling.getLiters());
        assertEquals(FuelType.GASOLINA, fueling.getFuelType());
        assertEquals(50, fueling.getTotalCost());
    }

    @Test
    void mustUpdate() {
        standardFueling.setFuelType(FuelType.DIESEL);
        repository.update(standardFueling.getId(), standardFueling);

        List<Fueling> fuelingList = repository.listAll();

        assertEquals(1, fuelingList.size());

        assertEquals(1, fuelingList.getFirst().getId());
        assertEquals(1, fuelingList.getFirst().getVehicleId());
        assertEquals(123456, fuelingList.getFirst().getOdometer());
        assertEquals(10, fuelingList.getFirst().getLiters());
        assertEquals(FuelType.DIESEL, fuelingList.getFirst().getFuelType());
        assertEquals(50, fuelingList.getFirst().getTotalCost());
     }

    @Test
    void mustFailToUpdateNonExistent() {
        standardFueling.setFuelType(FuelType.DIESEL);
        assertThrows(RuntimeException.class, () -> repository.update(standardFueling.getId() + 1, standardFueling));

        List<Fueling> fuelingList = repository.listAll();

        assertEquals(1, fuelingList.size());

        assertEquals(1, fuelingList.getFirst().getId());
        assertEquals(1, fuelingList.getFirst().getVehicleId());
        assertEquals(123456, fuelingList.getFirst().getOdometer());
        assertEquals(10, fuelingList.getFirst().getLiters());
        assertEquals(FuelType.GASOLINA, fuelingList.getFirst().getFuelType());
        assertEquals(50, fuelingList.getFirst().getTotalCost());
    }

    @Test
    void mustDelete() {
        repository.delete(standardFueling.getId());

        List<Fueling> fuelingList = repository.listAll();

        assertEquals(0, fuelingList.size());
    }

    @Test
    void mustFailToDeleteNonExistent() {
        assertThrows(RuntimeException.class, () -> repository.delete(standardFueling.getId() + 1));

        List<Fueling> fuelingList = repository.listAll();

        assertEquals(1, fuelingList.size());
    }
}