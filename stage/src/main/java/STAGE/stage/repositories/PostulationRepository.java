package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Etudiant;
import STAGE.stage.models.Offre;
import STAGE.stage.models.Postulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    List<Postulation> findByOffre(Offre offre);
    List<Postulation> findByEtudiant(Etudiant etudiant);


    @Query("SELECT COUNT(p) FROM Postulation p WHERE p.offre.idOffre = :offerId")
    long countByOffreIdOffre(@Param("offerId") Long offerId);
}



