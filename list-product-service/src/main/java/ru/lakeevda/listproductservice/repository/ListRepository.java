package ru.lakeevda.listproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.listproductservice.entity.Lists;
import ru.lakeevda.listproductservice.entity.User;

import java.util.List;

public interface ListRepository extends JpaRepository<Lists, Long> {
    List<Lists> findListsByUsersContains(User user);
    List<Lists> findListsByAuthorIdOrUsersContains(Long userId, User user);
}
