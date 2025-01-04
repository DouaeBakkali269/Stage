package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
}

