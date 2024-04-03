package ru.lakeevda.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(Integer userPhone);
    @Query(value = "select * from public.user u where cast(u.phone as text) like :phone%", nativeQuery = true)
    List<User> findUsersByPhoneStartsWith(@Param("phone") String phone);
}
