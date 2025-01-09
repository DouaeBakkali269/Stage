package STAGE.stage.repositories;

import STAGE.stage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query("SELECT COUNT(u.id) FROM User u")
    long countTotalUsers();
}
