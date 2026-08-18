package ru.lakeevda.listproductservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.listproductservice.entity.ListEntity;

import java.util.List;

public interface ListRepository extends JpaRepository<ListEntity, Long> {

    @Query(value = "select l from list_product_service.lists l " +
            "join list_product_service.list_users lu on l.id = lu.list_id " +
            "where lu.user_phone = :userPhone", nativeQuery = true)
    List<ListEntity> findListEntitiesByUserPhoneContains(@Param("userPhone") Long userPhone);
}
