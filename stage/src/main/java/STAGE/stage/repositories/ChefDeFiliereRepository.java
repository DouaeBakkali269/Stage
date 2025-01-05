package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.ChefDeFiliere;

import java.util.List;

public interface ChefDeFiliereRepository extends JpaRepository<ChefDeFiliere, Long> {
    List<ChefDeFiliere> findByEcole_IdEcole(Long idEcole);
    List<ChefDeFiliere> findByFiliere_IdFiliere(Long idFiliere);
}

