package ru.lakeevda.listservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lakeevda.listservice.entity.Lists;
import ru.lakeevda.listservice.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface ListRepository extends JpaRepository<Lists, Long> {
    List<Lists> findListsByUsersContains(User user);
    List<Lists> findListsByAuthorIdOrUsersContains(Long userId, User user);
}
