package ru.lakeevda.listproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.listproductservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByPhone(long phone);
}
