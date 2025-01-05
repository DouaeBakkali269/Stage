package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByEcoleIdEcole(Long ecoleId);

    List<Etudiant> findByFiliereIdFiliere(Long filiereId);


    @Query("SELECT COUNT(e) FROM Etudiant e WHERE e.ecole.idEcole = :idEcole")
    long countByEcole_IdEcole(@Param("idEcole") Long idEcole);

    @Query("SELECT COUNT(e) FROM Etudiant e WHERE e.ecole.idEcole = :idEcole AND e.idEtu NOT IN (SELECT s.etudiant.idEtu FROM Stage s)")
    long countByEcole_IdEcoleAndStagesIsEmpty(@Param("idEcole") Long idEcole);

    @Query("SELECT COUNT(e) FROM Etudiant e WHERE e.filiere.idFiliere = :filiereId AND e.ecole.idEcole = :idEcole AND e.idEtu IN (SELECT s.etudiant.idEtu FROM Stage s)")
    long countByFiliere_IdFiliereAndEcole_IdEcoleAndStagesIsNotEmpty(@Param("filiereId") Long filiereId, @Param("idEcole") Long idEcole);

    @Query("SELECT COUNT(e) FROM Etudiant e WHERE e.filiere.idFiliere = :filiereId")
    long countByFiliere_IdFiliere(@Param("filiereId") Long filiereId);

    Optional<Etudiant> findByUserId(Long userId);
}

