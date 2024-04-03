package ru.lakeevda.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.userservice.entity.User;
import ru.lakeevda.userservice.exception.DataNotFoundException;
import ru.lakeevda.userservice.exception.OtherUserExistException;
import ru.lakeevda.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public User getUser(Integer phone) {
        return userRepository.findByPhone(phone).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public List<User> getUsersByPhoneStartsWith(String phone) {
        return userRepository.findUsersByPhoneStartsWith(phone);
    }

    @Transactional
    public void updateUser(User updateUser) {
        User user = getUser(updateUser.getPhone());
        if (user.getId() != updateUser.getId())
            throw new OtherUserExistException("Пользователь с таким телефоном уже существует!");
        else updateUser.setId(user.getId());
        userRepository.save(updateUser);
    }
}
