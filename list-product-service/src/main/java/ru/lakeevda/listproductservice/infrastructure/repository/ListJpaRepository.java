package ru.lakeevda.listproductservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lakeevda.listproductservice.infrastructure.entity.ListJpaEntity;

import java.util.List;
import java.util.Optional;

public interface ListJpaRepository extends JpaRepository<ListJpaEntity, Long> {

    @Query(value = "select * from list_product_service.lists l " +
            "join list_product_service.list_users lu on l.id = lu.list_id " +
            "where lu.user_phone = :userPhone", nativeQuery = true)
    List<ListJpaEntity> findAllByUserPhone(@Param("userPhone") Long userPhone);

    @Query(value = "select * from list_product_service.lists l " +
            "join list_product_service.list_users lu on l.id = lu.list_id " +
            "where l.name = :name and lu.user_phone = :userPhone", nativeQuery = true)
    Optional<ListJpaEntity> findByNameAndUserPhone(@Param("name") String name, @Param("userPhone") Long userPhone);
}
