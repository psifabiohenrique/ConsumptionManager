package com.FabioHenrique.ConsuptionManager.Repository.Json;

import com.FabioHenrique.ConsuptionManager.Domain.Vehicle;
import com.FabioHenrique.ConsuptionManager.Repository.Interfaces.VehicleRepository;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonVehicleRepository implements VehicleRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File archive = new File("vehicles.json");

    @Override
    public Vehicle save(Vehicle vehicle) {
        List<VehicleJsonModel> currentModels = readAllModelsFromFile();

        VehicleJsonModel newModel = VehicleJsonModel.serialize(vehicle);
        currentModels.add(newModel);
        writeAllModelsToFile(currentModels);
        return vehicle;
    }

    @Override
    public List<Vehicle> listAll() {
        return readAllModelsFromFile().stream()
                .map(VehicleJsonModel::deserialize)
                .toList();
    }

    @Override
    public Vehicle getOne(int vehicleId) {
        return readAllModelsFromFile().stream()
                .filter(model -> model.getId() == vehicleId)
                .findFirst()
                .map(VehicleJsonModel::deserialize)
                .orElseThrow(() -> new RuntimeException("Veículo com o ID " + vehicleId + " não foi encontrado"));
    }

    @Override
    public Vehicle update(int vehicleId, Vehicle updatedVehicle) {
        List<VehicleJsonModel> currentModels = readAllModelsFromFile();
        boolean found = false;

        for (int i = 0; i < currentModels.size(); i++) {
            if (currentModels.get(i).getId() == vehicleId) {
                // Substitui o registro antigo pelas informações novas do veículo atualizado
                currentModels.set(i, VehicleJsonModel.serialize(updatedVehicle));
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Não foi possível atualizar: Veículo não encontrado.");
        }

        writeAllModelsToFile(currentModels);
        return updatedVehicle;
    }

    @Override
    public void delete(int vehicleId) {
        List<VehicleJsonModel> currentModels = readAllModelsFromFile();

        // Remove da lista o modelo que corresponder ao ID enviado
        boolean removed = currentModels.removeIf(model -> model.getId() == vehicleId);

        if (!removed) {
            throw new RuntimeException("Não foi possível deletar: Veículo não encontrado.");
        }

        writeAllModelsToFile(currentModels);
    }

    private List<VehicleJsonModel> readAllModelsFromFile() {
        if (!archive.exists() || archive.length() == 0) {
            return new ArrayList<>(); // Retorna lista vazia se o arquivo não existir
        }
        try {
            // No Jackson 3 usamos TypeReference para mapear listas corretamente
            return mapper.readValue(archive, new TypeReference<>() {});
        } catch (JacksonException e) {
            throw new RuntimeException("Erro ao ler dados do banco de dados", e);
        }
    }

    private void writeAllModelsToFile(List<VehicleJsonModel> models) {
        try {
            mapper.writeValue(archive, models);
        } catch (JacksonException e) {
            throw new RuntimeException("Erro ao escrever dados no banco de dados", e);
        }
    }
}
