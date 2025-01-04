package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
}

