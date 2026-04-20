package ru.lakeevda.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.authservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByPhone(long phone);

    boolean existsByPhone(long phone);

    boolean existsByEmail(String email);

    @Query(value = "select * from public.user u where cast(u.phone as text) like :phone%", nativeQuery = true)
    List<User> findUsersByPhoneStartsWith(@Param("phone") String phone);
}
