package STAGE.stage.repositories;

import STAGE.stage.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByEncadrant_IdEncadrant(Long encadrantId);
    List<Evaluation> findByStage_IdStage(Long stageId);
    List<Evaluation> findByStage_Etudiant_IdEtu(Long etudiantId);
}