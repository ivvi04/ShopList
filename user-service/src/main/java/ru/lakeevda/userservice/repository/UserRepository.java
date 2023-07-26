package ru.lakeevda.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.userservice.entity.UserData;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    public UserData findByPhone(Integer phone);
}
