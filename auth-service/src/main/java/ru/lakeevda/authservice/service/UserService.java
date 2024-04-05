package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.entity.enums.UserRole;
import ru.lakeevda.authservice.exception.UserEmailExistException;
import ru.lakeevda.authservice.exception.UserPhoneExistException;
import ru.lakeevda.authservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    public User saveUser(User user) {
        return saveUser(user, true);
    }

    public User saveUser(User user, boolean checked) {
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

    public boolean existByPhone(long phone) {
        return userRepository.existsByPhone(phone);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

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

    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }
}
