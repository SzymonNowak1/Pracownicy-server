package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szynow.workers.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.username LIKE %:text% OR u.email LIKE %:text% ")
    List<User> findByUsernameEmailTextSearch(@Param("text") String text);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
