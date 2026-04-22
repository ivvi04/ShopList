package ru.lakeevda.listproductservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listproductservice.dto.ShopListDto;
import ru.lakeevda.listproductservice.entity.ShopListEntity;
import ru.lakeevda.listproductservice.entity.User;
import ru.lakeevda.listproductservice.enums.ShopListStatus;
import ru.lakeevda.listproductservice.exception.DataNotFoundException;
import ru.lakeevda.listproductservice.exception.UserNotAuthorException;
import ru.lakeevda.listproductservice.repository.ListRepository;
import ru.lakeevda.listproductservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListRepository listRepository;

    private ShopListEntity findListById(Long id) {
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

    private ShopListDto getListDto(ShopListEntity shopList) {
        return new ShopListDto(shopList.getId(), shopList.getName(), findUserById(shopList.getAuthorId()).getPhone(), shopList.getUsers());
    }


    public ShopListDto getListById(Long id) {
        ShopListEntity list = findListById(id);
        return getListDto(list);
    }

    public List<ShopListDto> getListByUserPhone(long userPhone) {
        User user = findUserByPhone(userPhone);
        List<ShopListEntity> lists = listRepository.findListsByAuthorIdOrUsersContains(user.getId(), user);
        List<ShopListDto> shopListDtos = new ArrayList<>();
        lists.forEach(list -> shopListDtos.add(getListDto(list)));
        return shopListDtos;
    }

    @Transactional
    public ShopListDto addList(ShopListDto newShopList) {
        User author = findUserByPhone(newShopList.getPhone());
        ShopListEntity shopList = new ShopListEntity();
        shopList.setName(newShopList.getName());
        shopList.setAuthorId(author.getId());
        shopList.setUsers(newShopList.getUsers());
        shopList.setStatus(ShopListStatus.CREATED.toString());
        listRepository.save(shopList);
        return getListDto(shopList);
    }

    @Transactional
    public void updateList(ShopListDto updateShopList, long phone) {
        ShopListEntity existShopList = findListById(updateShopList.getId());
        User user = findUserByPhone(phone);
        if (existShopList.getAuthorId() != user.getId())
            throw new UserNotAuthorException("Только у автора есть права на изменение списка!");
        existShopList.setName(updateShopList.getName());
        existShopList.setUsers(updateShopList.getUsers());
        listRepository.save(existShopList);
    }

    @Transactional
    public void deleteList(Long id, long phone) {
        ShopListEntity list = findListById(id);
        User user = findUserByPhone(phone);
        if (list.getAuthorId() != user.getId())
            throw new UserNotAuthorException("Только у автора есть права на удаление!");
        listRepository.delete(list);
    }
    @Transactional
    public ShopListDto addUserToList(Long id, long phone) {
        ShopListEntity shopList = findListById(id);
        User user = findUserByPhone(phone);
        if (shopList.getAuthorId() != user.getId() && !shopList.getUsers().contains(user)) shopList.addUser(user);
        listRepository.save(shopList);
        return getListDto(shopList);
    }

    @Transactional
    public ShopListDto deleteUserFromList(Long id, long phone) {
        ShopListEntity shopList = findListById(id);
        User user = findUserByPhone(phone);
        shopList.deleteUser(user);
        listRepository.save(shopList);
        return getListDto(shopList);
    }
}
