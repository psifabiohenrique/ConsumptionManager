package com.FabioHenrique.ConsuptionManager.Services;

import com.FabioHenrique.ConsuptionManager.Domain.FuelType;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsuptionManager.Repository.Json.JsonFuelingRepository;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingInDto;
import com.FabioHenrique.ConsuptionManager.Services.dto.FuelingOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuelingServiceTest {
    private FuelingService fuelingService;
    @BeforeEach
    void setUp() throws IOException {
        File file = File.createTempFile("fueling-test", ".json");
        FuelingRepository fuelingRepository = new JsonFuelingRepository(file.getPath());
        fuelingService = new FuelingService(fuelingRepository);
    }

    private FuelingInDto createFuelingDto() {
        return new FuelingInDto(1, 12345, 10, FuelType.GASOLINA, 50);
    }

    @Test
    void mustGetAllByVehicle() {
        List<FuelingInDto> fuelingList = new ArrayList<>(3);
        fuelingList.add(createFuelingDto());
        fuelingList.add(createFuelingDto());
        fuelingList.add(createFuelingDto());
        fuelingList.get(2).setVehicleId(2);
        for (FuelingInDto fueling : fuelingList) {
            fuelingService.create(fueling);
        }

        assertEquals(3, fuelingService.getAll().size());
        assertEquals(2, fuelingService.getAllByVehicle(1).size());
        assertEquals(1, fuelingService.getAllByVehicle(2).size());

    }

    @Test
    void mustFailToGetAllByVehicleWithWrongId() {
        List<FuelingInDto> fuelingList = new ArrayList<>(3);
        fuelingList.add(createFuelingDto());
        fuelingList.add(createFuelingDto());
        fuelingList.add(createFuelingDto());
        for (FuelingInDto fueling : fuelingList) {
            fuelingService.create(fueling);
        }
        assertEquals(3, fuelingService.getAll().size());
        assertEquals(0, fuelingService.getAllByVehicle(10).size());
    }

    @Test
    void mustUpdate() {
        FuelingInDto fueling = createFuelingDto();
        FuelingOutDto fuelingOutDto = fuelingService.create(fueling);

        fueling.setFuelType(FuelType.DIESEL);

        FuelingOutDto fuelingUpdatedOutDto = fuelingService.update(fuelingOutDto.getId(), fueling);

        assertEquals(fuelingUpdatedOutDto.getFuelType(), fueling.getFuelType());
        assertEquals(fuelingUpdatedOutDto.getId(), fuelingOutDto.getId());
    }

    @Test
    void mustDelete() {
        FuelingInDto fueling = createFuelingDto();
        FuelingOutDto fuelingOutDto = fuelingService.create(fueling);

        fuelingService.delete(fuelingOutDto.getId());

        List<FuelingOutDto> fuelingOutDtoList = fuelingService.getAll();

        assertEquals(0, fuelingOutDtoList.size());
    }
}