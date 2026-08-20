package ru.lakeevda.listproductservice.domain.repository;

import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;

import java.util.Optional;

public interface ListRepository {
    Optional<List> findById(ListId id);
    java.util.List<List> findAllByUserPhone(ListUserPhone userPhone);
    Optional<List> findByNameAndUserPhone(ListName name, ListUserPhone userPhone);
    List save(List list);
    void delete(List list);
}
