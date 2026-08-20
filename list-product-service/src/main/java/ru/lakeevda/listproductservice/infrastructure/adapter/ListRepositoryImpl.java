package ru.lakeevda.listproductservice.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;
import ru.lakeevda.listproductservice.domain.repository.ListRepository;
import ru.lakeevda.listproductservice.infrastructure.entity.ListJpaEntity;
import ru.lakeevda.listproductservice.infrastructure.mapper.ListMapper;
import ru.lakeevda.listproductservice.infrastructure.repository.ListJpaRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListRepositoryImpl implements ListRepository {
    private final ListJpaRepository jpaRepository;

    @Override
    public Optional<List> findById(ListId id) {
        return jpaRepository.findById(id.getValue())
                .map(ListMapper::toDomain)
                .or(Optional::empty);
    }

    @Override
    public java.util.List<List> findAllByUserPhone(ListUserPhone userPhone) {
        java.util.List<ListJpaEntity> entities = jpaRepository.findAllByUserPhone(userPhone.getValue());
        return entities.stream()
                .map(ListMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List> findByNameAndUserPhone(ListName name, ListUserPhone userPhone) {
        return jpaRepository
                .findByNameAndUserPhone(name.getValue(), userPhone.getValue())
                .map(ListMapper::toDomain);
    }

    @Override
    @Transactional
    public List save(List list) {
        ListJpaEntity entity = ListMapper.toJpaEntity(list);
        return ListMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(List list) {
        jpaRepository.deleteById(list.getId().getValue());
    }
}
