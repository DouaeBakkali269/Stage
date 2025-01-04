package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Ecole;

public interface EcoleRepository extends JpaRepository<Ecole, Long> {
    // Ajoutez des méthodes de recherche personnalisées si nécessaire
}

