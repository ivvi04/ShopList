package ru.lakeevda.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.authservice.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByPhone(long phone);

    boolean existsByPhone(long phone);

    boolean existsByEmail(String email);

    @Query(value = "select * from public.user u where cast(u.phone as text) like :phone%", nativeQuery = true)
    List<UserEntity> findUsersByPhoneStartsWith(@Param("phone") String phone);
}
