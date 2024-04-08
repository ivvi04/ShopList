package ru.lakeevda.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.userservice.entity.User;
import ru.lakeevda.userservice.exception.DataNotFoundException;
import ru.lakeevda.userservice.exception.UserExistException;
import ru.lakeevda.userservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public User getUserByPhone(long phone) {
        return userRepository.findByPhone(phone).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public List<User> getUsersByPhoneStartsWith(String phone) {
        return userRepository.findUsersByPhoneStartsWith(phone);
    }

    @Transactional
    public User updateUser(User updateUser) {
        User user = getUserByPhone(updateUser.getPhone());
        if (user.getId() != updateUser.getId())
            throw new UserExistException("Пользователь с таким телефоном уже существует!");
        else updateUser.setId(user.getId());
        return userRepository.save(updateUser);
    }
}
