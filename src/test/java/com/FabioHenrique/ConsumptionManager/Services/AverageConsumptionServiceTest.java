package com.FabioHenrique.ConsumptionManager.Services;

import com.FabioHenrique.ConsumptionManager.Domain.FuelType;
import com.FabioHenrique.ConsumptionManager.Repository.Interfaces.FuelingRepository;
import com.FabioHenrique.ConsumptionManager.Repository.Json.JsonFuelingRepository;
import com.FabioHenrique.ConsumptionManager.Services.dto.ConsumptionOutDto;
import com.FabioHenrique.ConsumptionManager.Services.dto.FuelingInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AverageConsumptionServiceTest {
    private FuelingService fuelingService;
    private AverageConsumptionService averageConsumptionService;

    @BeforeEach
    void setUp() throws IOException {
        File file = File.createTempFile("fueling-test", ".json");
        FuelingRepository fuelingRepository = new JsonFuelingRepository(file.getPath());
        fuelingService = new FuelingService(fuelingRepository);
        averageConsumptionService = new AverageConsumptionService(fuelingService);
    }

    private void generateFueling(int vehicleId, double odometer) {
        FuelingInDto fueling = new FuelingInDto(vehicleId, odometer, 10, FuelType.GASOLINA, 50);
        fuelingService.create(fueling);
    }

    @Test
    void mustGetAverageConsumption() {
        generateFueling(1, 100);
        generateFueling(1, 200);

        ConsumptionOutDto consumption = averageConsumptionService.getAverageConsumption(1);
        assertEquals(10, consumption.getAverageConsumption());
        assertEquals(0.5, consumption.getAverageCost());
        assertEquals(2, consumption.getFuelingCount());
        assertEquals(50, consumption.getTotalCost());
    }

    @Test
    void mustFailWithInsufficientFuelings() {
        generateFueling(1, 100);
        
        assertThrows(RuntimeException.class, () -> averageConsumptionService.getAverageConsumption(1));
    }
}