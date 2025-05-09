package co.ucentral.creditaplication.repositories;

import co.ucentral.creditaplication.models.Cliente;
import co.ucentral.creditaplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}