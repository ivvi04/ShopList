package ru.lakeevda.listservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listservice.dto.ListDto;
import ru.lakeevda.listservice.entity.Lists;
import ru.lakeevda.listservice.entity.User;
import ru.lakeevda.listservice.enums.ListStatus;
import ru.lakeevda.listservice.exception.DataNotFoundException;
import ru.lakeevda.listservice.exception.UserNotAuthorException;
import ru.lakeevda.listservice.repository.ListRepository;
import ru.lakeevda.listservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final ListRepository listRepository;
    private final UserRepository userRepository;

    private Lists findListById(Long id) {
        return listRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Список не найден!"));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    private User findUserByPhone(Integer userPhone) {
        return userRepository.findByPhone(userPhone).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    private ListDto getListDto(Lists list) {
        return new ListDto(list.getId(), list.getName(), findUserById(list.getAuthorId()).getPhone(), list.getUsers());
    }


    public ListDto getListById(Long id) {
        Lists list = findListById(id);
        return getListDto(list);
    }

    public List<ListDto> getListByUserPhone(Integer userPhone) {
        User user = findUserByPhone(userPhone);
        List<Lists> lists = listRepository.findListsByAuthorIdOrUsersContains(user.getId(), user);
        List<ListDto> listDtos = new ArrayList<>();
        lists.forEach(list -> listDtos.add(getListDto(list)));
        return listDtos;
    }

    @Transactional
    public ListDto addList(ListDto newList) {
        User author = findUserByPhone(newList.getPhone());
        Lists list = new Lists();
        list.setName(newList.getName());
        list.setAuthorId(author.getId());
        list.setUsers(newList.getUsers());
        list.setStatus(ListStatus.CREATED);
        listRepository.save(list);
        return getListDto(list);
    }

    @Transactional
    public void updateList(ListDto updateList, Integer phone) {
        Lists list = findListById(updateList.getId());
        User user = findUserByPhone(phone);
        if (list.getAuthorId() != user.getId())
            throw new UserNotAuthorException("Только у автора есть права на изменение списка!");
        listRepository.delete(list);
    }

    @Transactional
    public void deleteList(Long id, Integer phone) {
        Lists list = findListById(id);
        User user = findUserByPhone(phone);
        if (list.getAuthorId() != user.getId())
            throw new UserNotAuthorException("Только у автора есть права на удаление!");
        listRepository.delete(list);
    }
    @Transactional
    public ListDto addUserToList(Long id, Integer phone) {
        Lists list = findListById(id);
        User user = findUserByPhone(phone);
        if (list.getAuthorId() != user.getId() && !list.getUsers().contains(user)) list.addUserToList(user);
        listRepository.save(list);
        return getListDto(list);
    }

    @Transactional
    public ListDto deleteUserFromList(Long id, Integer phone) {
        Lists list = findListById(id);
        User user = findUserByPhone(phone);
//        if (list.getAuthorId() == user.getId())
//            throw new UserNotAuthorException("Автора нельзя удалить!");
        list.deleteUserFromList(user);
        listRepository.save(list);
        return getListDto(list);
    }
}
