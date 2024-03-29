package ru.lakeevda.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.authservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByPhone(Integer phone);
    boolean existsByPhone(Integer phone);
    boolean existsByEmail(String email);
    Optional<User> findOneWithRolesByUsername(String username);

    Optional<User> findOneWithRolesByPhone(Integer phone);
}
