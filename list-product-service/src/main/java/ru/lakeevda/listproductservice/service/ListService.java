package ru.lakeevda.listproductservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.dto.ListDto;
import ru.lakeevda.listproductservice.entity.ListEntity;
import ru.lakeevda.listproductservice.entity.ListUserEntity;
import ru.lakeevda.listproductservice.enums.ShopListStatus;
import ru.lakeevda.listproductservice.exception.DataNotFoundException;
import ru.lakeevda.listproductservice.exception.UserNotAuthorException;
import ru.lakeevda.listproductservice.repository.ListRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListRepository listRepository;

    private ListEntity findListById(Long id) {
        return listRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Список не найден!"));
    }

//    private User findUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }
//
//    private User findUserByUsername(String username) {
//        return userRepository.findByUsername(username).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }
//
//    private User findUserByPhone(long userPhone) {
//        return userRepository.findByPhone(userPhone).orElseThrow(() ->
//                new DataNotFoundException("Пользователь не найден!"));
//    }

    private ListDto getListDto(ListEntity shopList) {
        return new ListDto(shopList.getId(),
                shopList.getName(),
                (HashMap<Long, Boolean>) shopList.getListUsers().stream()
                        .collect(Collectors.toMap(ListUserEntity::getUserPhone,
                                ListUserEntity::getIsAuthor)));
    }


    public ListDto getListById(Long id) {
        ListEntity list = findListById(id);
        return getListDto(list);
    }

    public List<ListDto> getListByUserPhone(Long userPhone) {
        List<ListEntity> lists = listRepository.findListEntitiesByUserPhoneContains(userPhone);
        List<ListDto> shopListDtos = new ArrayList<>();
        lists.forEach(list -> shopListDtos.add(getListDto(list)));
        return shopListDtos;
    }

    @Transactional
    public ListDto create(ListDto list) {
        ListEntity listEntity = new ListEntity();
        listEntity.setName(list.getName());
        listEntity.setStatus(ShopListStatus.CREATED.toString());
        listEntity.setListUsers(list.getUserPhones()
                .entrySet().stream()
                .map(entry ->
                        new ListUserEntity(list.getId(), entry.getKey(), entry.getValue()))
                .toList());
        listRepository.save(listEntity);
        return getListDto(listEntity);
    }

    @Transactional
    public void update(ListDto list, Long userPhone) {
        var existShopList = findListById(list.getId());
        checkAuthor(existShopList.getListUsers(), userPhone);
        existShopList.setName(list.getName());
        listRepository.save(existShopList);
    }

    @Transactional
    public void delete(Long id, Long userPhone) {
        var list = findListById(id);
        checkAuthor(list.getListUsers(), userPhone);
        listRepository.delete(list);
    }

    @Transactional
    public ListDto addUser(Long id, Long userPhone) {
        var listEntity = findListById(id);
        var notHasUserPhone = listEntity.getListUsers().stream()
                .map(ListUserEntity::getUserPhone)
                .noneMatch(userPhone::equals);
        if (notHasUserPhone) {
            listEntity.getListUsers().add(new ListUserEntity(id, userPhone, Boolean.FALSE));
            listRepository.save(listEntity);
        }
        return getListDto(listEntity);
    }

    @Transactional
    public ListDto deleteUser(Long id, Long userPhone) {
        var listEntity = findListById(id);

        listEntity.getListUsers().removeIf(listUserEntity -> listUserEntity.getUserPhone().equals(userPhone));
        listRepository.save(listEntity);
        return getListDto(listEntity);
    }

    private static void checkAuthor(List<ListUserEntity> listUserEntities, Long userPhone) {
        listUserEntities.stream()
                .filter(listUserEntity -> userPhone.equals(listUserEntity.getUserPhone())
                        && listUserEntity.getIsAuthor())
                .findFirst()
                .orElseThrow(() -> new UserNotAuthorException("Только у автора есть права на удаление!"));
    }
}
