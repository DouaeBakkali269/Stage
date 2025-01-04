package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.CompteEcole;

public interface CompteEcoleRepository extends JpaRepository<CompteEcole, Long> {
    // Ajoutez des méthodes de recherche personnalisées si nécessaire
}

