package pl.honestit.spring.demo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.honestit.spring.demo.model.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Transactional
    List<User> findAllByUsername(String username);
}
