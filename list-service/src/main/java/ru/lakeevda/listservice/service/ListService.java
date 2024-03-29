package ru.lakeevda.listservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.listservice.entity.Lists;
import ru.lakeevda.listservice.entity.User;
import ru.lakeevda.listservice.enums.ListStatus;
import ru.lakeevda.listservice.exception.DataNotFoundException;
import ru.lakeevda.listservice.exception.UserNotAuthorException;
import ru.lakeevda.listservice.repository.ListRepository;
import ru.lakeevda.listservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListService {
    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public Lists findListById(Long id) {
        return listRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Список не найден!"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public User findUserByUserPhone(Integer userPhone) {
        return userRepository.findByUserPhone(userPhone).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    @Transactional
    public Lists addList(Lists list) {
        User author = findUserByUserPhone(list.getUserAuthor().getUserPhone());
        list.setUserAuthor(author);
        list.addUserToList(author);
        list.setStatus(ListStatus.CREATED);
        return listRepository.save(list);
    }

    public List<Lists> getListByUserPhone(Integer userPhone) {
        User user = findUserByUserPhone(userPhone);
        return listRepository.findListsByUsersContains(user);
    }
    @Transactional
    public Lists addUserToList(Long id, Integer userPhone) {
        Lists lists = findListById(id);
        User user = findUserByUserPhone(userPhone);
        if (!lists.getUsers().contains(user)) lists.addUserToList(user);
        return lists;
    }

    @Transactional
    public Lists deleteUserFromList(Long id, Integer userPhone) {
        Lists lists = findListById(id);
        User user = findUserByUserPhone(userPhone);
        if (lists.getUserAuthor() == user)
            throw new UserNotAuthorException("Автора нельзя удалить!");
        lists.deleteUserFromList(user);
        return lists;
    }

    @Transactional
    public void deleteList(Long id, Integer userPhone) {
        Lists lists = findListById(id);
        User user = findUserByUserPhone(userPhone);
        if (lists.getUserAuthor() != user)
            throw new UserNotAuthorException("Только у автора есть права на удаление!");
        listRepository.delete(lists);
    }
}
