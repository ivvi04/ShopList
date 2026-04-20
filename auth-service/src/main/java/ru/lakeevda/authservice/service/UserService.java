package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.entity.enums.UserRole;
import ru.lakeevda.authservice.exception.DataNotFoundException;
import ru.lakeevda.authservice.exception.UserEmailExistException;
import ru.lakeevda.authservice.exception.UserExistException;
import ru.lakeevda.authservice.exception.UserPhoneExistException;
import ru.lakeevda.authservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User loadUserByPhone(long phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return user;
    }

    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return userDetails;
    }

    public User getUserByPhone(long phone) {
        return userRepository.findByPhone(phone).orElseThrow(() ->
                new DataNotFoundException("Пользователь не найден!"));
    }

    public List<User> getUsersByPhoneStartsWith(String phone) {
        return userRepository.findUsersByPhoneStartsWith(phone);
    }

    public User create(User user) {
        return create(user, true);
    }

    public User create(User user, boolean checked) {
        if (checked) {
            if (userRepository.existsByPhone(user.getPhone()))
                throw new UserPhoneExistException("Пользователь с таким телефоном уже существует!");
            if (userRepository.existsByEmail(user.getEmail()))
                throw new UserEmailExistException("Пользователь с таким email уже существует!");
            if (user.getUsername().isEmpty()) user.setUsername("user" + user.getId());
            user.setRole(UserRole.USER);
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(User updateUser) {
        User user = getUserByPhone(updateUser.getPhone());
        if (user.getId() != updateUser.getId())
            throw new UserExistException("Пользователь с таким телефоном уже существует!");
        else updateUser.setId(user.getId());
        return userRepository.save(updateUser);
    }
}
