package ru.lakeevda.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.authservice.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByPhone(String phone);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    @Query(value = "select * from auth_service.users u where cast(u.phone as text) like concat(:phone, '%')",
            nativeQuery = true)
    List<UserEntity> findUsersByPhoneStartsWith(@Param("phone") String phone);
}
