package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.CompteEntreprise;

public interface CompteEntrepriseRepository extends JpaRepository<CompteEntreprise, Long> {
    // Ajoutez des méthodes de recherche personnalisées si nécessaire
}

