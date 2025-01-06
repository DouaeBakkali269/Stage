package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Filiere;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    List<Filiere> findByEcoleIdEcole(Long ecoleId);
}

