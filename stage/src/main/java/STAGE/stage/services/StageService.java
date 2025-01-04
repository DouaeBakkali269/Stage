package STAGE.stage.services;

import STAGE.stage.dtos.StageDTO;

import java.util.List;

public interface StageService {
    StageDTO createStage(StageDTO stageDTO);
    StageDTO updateStage(Long id, StageDTO stageDTO);
    StageDTO getStageById(Long id);
    List<StageDTO> getStagesByEntrepriseId(Long entrepriseId); // Fetch stages by entreprise ID
    List<StageDTO> getStagesByStatus(String status); // Fetch stages by status
    StageDTO updateStageStatus(Long id, String newStatus); // Update status of a stage
    List<StageDTO> getAllStages();
    void deleteStage(Long id);
}