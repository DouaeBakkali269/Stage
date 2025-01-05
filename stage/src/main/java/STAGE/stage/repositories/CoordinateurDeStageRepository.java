package STAGE.stage.repositories;

import STAGE.stage.models.CompteEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.CoordinateurDeStage;

import java.util.Optional;

public interface CoordinateurDeStageRepository extends JpaRepository<CoordinateurDeStage, Long> {
    Optional<CoordinateurDeStage> findByUserId(Long userId);
}

