package STAGE.stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import STAGE.stage.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}

