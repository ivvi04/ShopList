package ru.lakeevda.listproductservice.infrastructure.mapper;

import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListStatus;
import ru.lakeevda.listproductservice.domain.entity.list.ListUser;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;
import ru.lakeevda.listproductservice.domain.entity.product.Product;
import ru.lakeevda.listproductservice.infrastructure.entity.ListJpaEntity;
import ru.lakeevda.listproductservice.infrastructure.entity.ListUserJpaEntity;
import ru.lakeevda.listproductservice.infrastructure.entity.ProductJpaEntity;

import java.util.stream.Collectors;

public class ListMapper {

    public static List toDomain(ListJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        java.util.List<ListUser> listUsers = entity.getListUsers().stream()
                .map(listUserJpaEntity ->
                        ListUser.restore(ListId.of(listUserJpaEntity.getListJpaEntity().getId()),
                                ListUserPhone.of(listUserJpaEntity.getUserPhone()),
                                listUserJpaEntity.getIsAuthor()))
                .collect(Collectors.toList());

        java.util.List<Product> products = entity.getProducts().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());

        return List.restore(ListId.of(entity.getId()),
                ListName.of(entity.getName()),
                ListStatus.fromValue(entity.getStatus()),
                listUsers,
                products);
    }

    public static ListJpaEntity toJpaEntity(List domain) {
        if (domain == null) {
            return null;
        }

        ListJpaEntity entity = new ListJpaEntity();
        if (domain.getId() != null)
            entity.setId(domain.getId().getValue());
        entity.setName(domain.getName().getValue());
        entity.setStatus(domain.getStatus().toString());

        java.util.List<ListUserJpaEntity> listUsers = domain.getListUsers().stream()
                .map(user -> {
                    ListUserJpaEntity listUserJpaEntity = new ListUserJpaEntity();
                    listUserJpaEntity.setListJpaEntity(entity);
                    listUserJpaEntity.setUserPhone(user.getUserPhone().getValue());
                    listUserJpaEntity.setIsAuthor(user.getIsAuthor());
                    return listUserJpaEntity;
                })
                .collect(Collectors.toList());
        entity.setListUsers(listUsers);

        java.util.List<ProductJpaEntity> products = domain.getProducts().stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
        entity.setProducts(products);

        return entity;
    }
}
