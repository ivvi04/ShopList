package ru.lakeevda.listproductservice.application.mapper;

import ru.lakeevda.listproductservice.application.boundary.model.list.ListRequest;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListResponse;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListUserResponse;
import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListStatus;
import ru.lakeevda.listproductservice.domain.entity.list.ListUser;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;

import java.util.Collections;
import java.util.stream.Collectors;

public class ListMapper {

    public static ListResponse toDto(List listEntity) {
        if (listEntity == null) {
            return null;
        }

        java.util.List<ListUserResponse> userPhones = listEntity.getListUsers().stream()
                .map(user -> new ListUserResponse(
                        user.getUserPhone().getValue(),
                        user.getIsAuthor()
                ))
                .collect(Collectors.toList());

        return new ListResponse(
                listEntity.getId().getValue(),
                listEntity.getName().getValue(),
                userPhones
        );
    }

    public static List toDomain(ListRequest listRequest, ListStatus listStatus) {
        if (listRequest == null) {
            return null;
        }

        java.util.List<ListUser> listUsers = Collections
                .singletonList(ListUser.create(ListUserPhone.of(listRequest.phone())));

        return List.create(ListName.of(listRequest.name()), listStatus, listUsers);
    }
}
