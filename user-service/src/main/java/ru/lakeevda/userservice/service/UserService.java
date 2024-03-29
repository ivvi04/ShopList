package ru.lakeevda.userservice.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.userservice.entity.User;
import ru.lakeevda.userservice.exception.DataNotFoundException;
import ru.lakeevda.userservice.exception.OtherUserExistException;
import ru.lakeevda.userservice.repository.UserRepository;

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

    @Transactional
    public void updateUser(User user) {
        User userFind = getUser(user.getPhone());
        if (userFind.getId() != user.getId())
            throw new OtherUserExistException("Пользователь с таким телефоном уже существует!");
        userRepository.save(user);
    }
}
