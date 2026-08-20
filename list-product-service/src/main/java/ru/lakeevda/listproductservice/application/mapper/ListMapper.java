package ru.lakeevda.listproductservice.application.mapper;

import ru.lakeevda.listproductservice.application.boundary.model.list.ListDto;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListUserDto;
import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListStatus;
import ru.lakeevda.listproductservice.domain.entity.list.ListUser;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListMapper {

    public static ListDto toDto(List listEntity) {
        if (listEntity == null) {
            return null;
        }

        java.util.List<ListUserDto> userPhones = listEntity.getListUsers().stream()
                .map(user -> new ListUserDto(
                        user.getUserPhone().getValue(),
                        user.getIsAuthor()
                ))
                .collect(Collectors.toList());

        return new ListDto(
                listEntity.getId().getValue(),
                listEntity.getName().getValue(),
                userPhones
        );
    }

    public static List toDomain(ListDto listDto, ListStatus listStatus) {
        if (listDto == null) {
            return null;
        }

        java.util.List<ListUser> listUsers = listDto.userPhones().stream()
                .map(user -> listDto.id() == null
                        ? ListUser.create(ListUserPhone.of(user.userPhone()), user.isAuthor())
                        : ListUser.restore(ListId.of(listDto.id()), ListUserPhone.of(user.userPhone()), user.isAuthor()))
                .collect(Collectors.toList());

        return listDto.id() == null
                ? List.create(ListName.of(listDto.name()), listStatus, listUsers)
                : List.restore(ListId.of(listDto.id()), ListName.of(listDto.name()), listStatus, listUsers, new ArrayList<>());
    }
}
