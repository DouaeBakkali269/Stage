package STAGE.stage.repositories;

import STAGE.stage.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query("SELECT s FROM Stage s WHERE s.offre.entreprise.idEntreprise = :entrepriseId")
    List<Stage> findByOffre_EntrepriseId(Long entrepriseId);

    List<Stage> findByStatut(String statut);// Automatically generated by Spring Data JPA


    // Count stages ending before a certain date (to compute ongoing internships)
    long countByDateFinBefore(LocalDate currentDate);

    @Query("SELECT COUNT(s) FROM Stage s WHERE s.offre.entreprise.idEntreprise = :companyId")
    long countByEntrepriseIdEntreprise(@Param("companyId") Long companyId);


    @Query("SELECT COUNT(s) FROM Stage s WHERE s.encadrant.idEncadrant = :supervisorId AND s.dateFin > :currentDate")
    long countByEncadrantIdEncadrantAndDateFinBefore(@Param("supervisorId") Long supervisorId, @Param("currentDate") LocalDate currentDate);

    @Query("SELECT COUNT(s) FROM Stage s WHERE s.encadrant.idEncadrant = :supervisorId")
    long countByEncadrantIdEncadrant(@Param("supervisorId") Long supervisorId);
}