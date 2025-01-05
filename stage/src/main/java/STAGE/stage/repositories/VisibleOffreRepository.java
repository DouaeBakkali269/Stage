package STAGE.stage.repositories;

import STAGE.stage.models.VisibleOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisibleOffreRepository extends JpaRepository<VisibleOffre, Long> {
    // Find all VisibleOffre entries by Filiere ID
    List<VisibleOffre> findByFiliere_IdFiliere(Long idFiliere);

    // Find all VisibleOffre entries by Offre ID
    List<VisibleOffre> findByOffre_IdOffre(Long idOffre);

    // Find a specific VisibleOffre by Filiere ID and Offre ID
    Optional<VisibleOffre> findByFiliere_IdFiliereAndOffre_IdOffre(Long idFiliere, Long idOffre);
}