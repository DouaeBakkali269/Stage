package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Offre;
import STAGE.stage.models.RH;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByRh(RH rh);

    List<Offre> findByEntrepriseIdEntreprise(Long entrepriseId);
}

