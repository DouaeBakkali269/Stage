package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.RH;

import java.util.List;

public interface RHRepository extends JpaRepository<RH, Long> {
    List<RH> findByEntrepriseIdEntreprise(Long entrepriseId);
}
