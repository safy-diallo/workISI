package hasa.hafia.repository;

import hasa.hafia.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("from Users u join fetch u.roles where u.username =:username")
    Optional<Users> getByUsername(String username);
}
