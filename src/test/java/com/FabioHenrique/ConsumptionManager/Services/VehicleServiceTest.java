package com.FabioHenrique.ConsumptionManager.Services;

import com.FabioHenrique.ConsumptionManager.Repository.Interfaces.VehicleRepository;
import com.FabioHenrique.ConsumptionManager.Repository.Json.JsonVehicleRepository;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleInDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.VehicleOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceTest {
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() throws IOException {
        File tempFile = File.createTempFile("vehicle-test", ".json");
        VehicleRepository vehicleRepository = new JsonVehicleRepository(tempFile.getPath());
        vehicleService = new VehicleService(vehicleRepository);
    }

    private VehicleInDto createVehicleDto() {
        return new VehicleInDto("Teste", 12345);
    }

    @Test
    void mustCreateVehicle() {
        vehicleService.create(createVehicleDto());
        List<VehicleOutDto> vehicleOutDtoList = vehicleService.getAll();

        assertEquals(1, vehicleOutDtoList.size());
    }

    @Test
    void mustGetOneById() {
        VehicleOutDto vehicleOutDto = vehicleService.create(createVehicleDto());
        VehicleOutDto vehicleGeteredById = vehicleService.getById(vehicleOutDto.getId());


        assertEquals(vehicleGeteredById.getId(), vehicleOutDto.getId());
    }

    @Test
    void mustUpdate() {
        VehicleInDto vehicleInDto = createVehicleDto();
        VehicleOutDto vehicleOutDto = vehicleService.create(vehicleInDto);

        vehicleInDto.setName("TesteTeste");
        vehicleService.update(vehicleOutDto.getId(), vehicleInDto);

        VehicleOutDto vehicleUpdatedOutDto = vehicleService.getById(vehicleOutDto.getId());

        assertEquals(vehicleUpdatedOutDto.getName(), vehicleInDto.getName());
    }

    @Test
    void mustDelete() {
        VehicleInDto vehicleInDto = createVehicleDto();
        VehicleOutDto vehicleOutDto = vehicleService.create(vehicleInDto);

        vehicleService.delete(vehicleOutDto.getId());

        List<VehicleOutDto> vehicleOutDtoList = vehicleService.getAll();
        assertEquals(0, vehicleOutDtoList.size());

    }
}