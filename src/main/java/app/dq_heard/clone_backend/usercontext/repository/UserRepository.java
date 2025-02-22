package app.dq_heard.clone_backend.usercontext.repository;

import app.dq_heard.clone_backend.usercontext.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findOneByEmail(String email);

}
