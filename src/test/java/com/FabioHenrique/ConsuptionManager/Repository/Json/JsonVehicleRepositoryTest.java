package com.FabioHenrique.ConsuptionManager.Repository.Json;

import com.FabioHenrique.ConsuptionManager.Domain.Vehicle;
import com.FabioHenrique.ConsuptionManager.Domain.VehicleTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonVehicleRepositoryTest {

    @Test
    void mustSaveVehicleInArchiveAndListAllVehicles() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());

        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        Vehicle saved = repository.save(vehicle);

        assertNotNull(saved);

        List<Vehicle> vehicleList = repository.listAll();

        assertEquals(1, vehicleList.size());

        assertEquals(1, vehicleList.getFirst().getId());
        assertEquals("Teste", vehicleList.getFirst().getName());
        assertEquals(12345, vehicleList.getFirst().getInitialOdometer());
    }

    @Test
    void mustGetOne() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());

        Vehicle vehicle = new Vehicle(1, "Teste", 12345);
        Vehicle vehicle2 = new Vehicle(2, "OutroTeste", 54321);

        repository.save(vehicle);

        Vehicle vehicleSaved = repository.getOne(1);

        assertEquals("Teste", vehicleSaved.getName());
        assertEquals(12345, vehicleSaved.getInitialOdometer());
    }

    @Test
    void mustUpdate() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());

        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        repository.save(vehicle);

        vehicle.setInitialOdometer(123456.0);
        repository.update(1, vehicle);

        List<Vehicle> vehicleList = repository.listAll();

        assertEquals(1, vehicleList.size());

        assertEquals(1, vehicleList.getFirst().getId());
        assertEquals("Teste", vehicleList.getFirst().getName());
        assertEquals(123456, vehicleList.getFirst().getInitialOdometer());
    }

    @Test
    void mustFailToUpdateNonExistent() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());
        Vehicle vehicle = new Vehicle(1, "Teste", 12345);
        repository.save(vehicle);

        vehicle.setInitialOdometer(123456.0);
        assertThrows(RuntimeException.class,  () -> repository.update(2, vehicle));
    }

    @Test
    void mustDelete() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());

        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        repository.save(vehicle);
        List<Vehicle> vehicleList = repository.listAll();

        assertEquals(1, vehicleList.size());

        repository.delete(1);

        vehicleList = repository.listAll();

        assertEquals(0, vehicleList.size());
    }

    @Test
    void mustFailToDeleteNonExistent() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");

        JsonVehicleRepository repository = new JsonVehicleRepository(tempFile.getPath());

        Vehicle vehicle = new Vehicle(1, "Teste", 12345);

        repository.save(vehicle);

        List<Vehicle> vehicleList = repository.listAll();

        assertEquals(1, vehicleList.size());

        assertThrows(RuntimeException.class, () -> repository.delete(2));

        vehicleList = repository.listAll();

        assertEquals(1, vehicleList.size());
    }
}