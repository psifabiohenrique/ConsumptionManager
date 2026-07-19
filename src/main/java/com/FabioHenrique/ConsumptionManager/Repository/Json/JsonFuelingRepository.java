package com.FabioHenrique.ConsumptionManager.Repository.Json;

import com.FabioHenrique.ConsumptionManager.Domain.Fueling;
import com.FabioHenrique.ConsumptionManager.Repository.Interfaces.FuelingRepository;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonFuelingRepository implements FuelingRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File archive;

    public JsonFuelingRepository () {
        this.archive = new File("fueling.json");
    }
    public JsonFuelingRepository (String archiveName) {
        this.archive = new File(archiveName);
    }


    @Override
    public Fueling save(Fueling fueling) {
        List<FuelingJsonModel> currentModels = readAllModelsFromFile();

        FuelingJsonModel newModel = FuelingJsonModel.serialize(fueling);
        currentModels.add(newModel);
        writeAllModelsToFile(currentModels);
        return fueling;
    }

    @Override
    public List<Fueling> listAll() {
        return readAllModelsFromFile().stream()
                .map(FuelingJsonModel::deserialize)
                .toList();
    }

    @Override
    public Fueling getOne(int fuelingId) {
        return readAllModelsFromFile().stream()
                .filter(model -> model.getId() == fuelingId)
                .findFirst()
                .map(FuelingJsonModel::deserialize)
                .orElseThrow(() -> new RuntimeException("Abastecimento com o ID " + fuelingId + " não encontrado"));
    }

    @Override
    public Fueling update(int fuelingId, Fueling updatedFueling) {
        List<FuelingJsonModel> currentModels = readAllModelsFromFile();
        boolean found = false;

        for (int i = 0; i < currentModels.size(); i++) {
            if (currentModels.get(i).getId() == fuelingId) {
                currentModels.set(i, FuelingJsonModel.serialize(updatedFueling));
                found = true;
                break;
            }
        }

        if (!found) {
            throw  new RuntimeException("Não foi possível atualizar: Anastecimento não encontrado");
        }

        writeAllModelsToFile(currentModels);
        return updatedFueling;
    }

    @Override
    public void delete(int fuelingId) {
        List<FuelingJsonModel> currentModels = readAllModelsFromFile();

        boolean removed = currentModels.removeIf(model -> model.getId() == fuelingId);

        if (!removed) {
            throw new RuntimeException("Não foi possível deletar: Abastecimento não encontrado");
        }

        writeAllModelsToFile(currentModels);
    }

    private List<FuelingJsonModel> readAllModelsFromFile() {
        if (!archive.exists() || archive.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(archive, new TypeReference<>() {});
        } catch (JacksonException e) {
            throw new RuntimeException("Erro ao ler dados do banco de dados", e);
        }
    }

    private void writeAllModelsToFile(List<FuelingJsonModel> models) {
        try {
            mapper.writeValue(archive, models);
        } catch (JacksonException e) {
            throw new RuntimeException("Erro ao escrever dados no banco de dados", e);
        }
    }
}
