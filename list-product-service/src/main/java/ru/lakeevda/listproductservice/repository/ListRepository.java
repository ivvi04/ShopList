package ru.lakeevda.listproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.listproductservice.entity.ShopListEntity;
import ru.lakeevda.listproductservice.entity.User;

import java.util.List;

public interface ListRepository extends JpaRepository<ShopListEntity, Long> {
    List<ShopListEntity> findListsByUsersContains(User user);
    List<ShopListEntity> findListsByAuthorIdOrUsersContains(Long userId, User user);
}
