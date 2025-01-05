package STAGE.stage.services;

import STAGE.stage.dtos.EvaluationDTO;

import java.util.List;

public interface EvaluationService {
    EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO);
    EvaluationDTO updateEvaluation(Long id, EvaluationDTO evaluationDTO);
    EvaluationDTO getEvaluationById(Long id);
    List<EvaluationDTO> getAllEvaluations();
    void deleteEvaluation(Long id);
    List<EvaluationDTO> getEvaluationsByEncadrantId(Long encadrantId); // Optional filtering
}