package ru.lakeevda.listproductservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lakeevda.listproductservice.infrastructure.entity.ListUserJpaEntity;
import ru.lakeevda.listproductservice.infrastructure.entity.ListUserJpaId;

public interface ListUserJpaRepository extends JpaRepository<ListUserJpaEntity, ListUserJpaId> {
}
