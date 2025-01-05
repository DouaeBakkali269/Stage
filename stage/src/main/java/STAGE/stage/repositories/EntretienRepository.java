package STAGE.stage.repositories;

import STAGE.stage.models.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {

    @Query("SELECT e FROM Entretien e WHERE e.offre.entreprise.idEntreprise = :entrepriseId")
    List<Entretien> findByOffre_EntrepriseId(Long entrepriseId);


    @Query("SELECT COUNT(e) FROM Entretien e WHERE e.offre.entreprise.idEntreprise = :companyId")
    long countByEntrepriseIdEntreprise(@Param("companyId") Long companyId);

}