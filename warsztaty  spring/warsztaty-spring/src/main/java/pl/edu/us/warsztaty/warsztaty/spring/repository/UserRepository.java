package pl.edu.us.warsztaty.warsztaty.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.us.warsztaty.warsztaty.spring.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query("select u from User u where u.name = :name")
    Optional<User> findUserByName(@Param("name") String name);


}
