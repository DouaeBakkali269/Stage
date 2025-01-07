package STAGE.stage.services;

import STAGE.stage.dtos.StageDTO;
import STAGE.stage.repositories.EtudiantRepository;
import STAGE.stage.repositories.FiliereRepository;
import STAGE.stage.repositories.StageRepository;

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

    List<StageDTO> getStagesByCF(Long idCF);

    List<StageDTO> getValidatedStagesByEcoleId(Long ecoleId);

    List<StageDTO> getAValiderStagesByEcoleId(Long ecoleId);

    void setStatusAndDeleteRest(Long etudiantId, Long stageId);

    List<StageDTO> getStagesByEtudiantId(Long etudiantId);
}