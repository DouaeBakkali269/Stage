package STAGE.stage.repositories;

import STAGE.stage.models.VisibleOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisibleOffreRepository extends JpaRepository<VisibleOffre, Long> {
    List<VisibleOffre> findByFiliere_Id(Long filiereId);
    List<VisibleOffre> findByOffre_Id(Long offreId);
}