package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.authservice.dto.UserRequest;
import ru.lakeevda.authservice.dto.UserResponse;
import ru.lakeevda.authservice.entity.UserEntity;
import ru.lakeevda.authservice.exception.DataNotFoundException;
import ru.lakeevda.authservice.exception.UserExistException;
import ru.lakeevda.authservice.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserServiceMapper mapper;

    public UserResponse getUserByPhone(Long phone) {
        UserEntity user = findUserByPhone(phone);
        return mapper.toResponse(user);
    }

    public List<UserResponse> getUsersByPhoneStartsWith(String phone) {
        return repository.findUsersByPhoneStartsWith(phone).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse updateInfo(UserRequest updateUser) {
        UserEntity existUser = findUserByPhone(updateUser.getPhone());
        if (!Objects.equals(existUser.getId(), updateUser.getId()))
            throw new UserExistException("Пользователь с таким телефоном уже существует!");
        existUser = mapper.partialUpdate(updateUser, existUser);
        return mapper.toResponse(repository.save(existUser));
    }

    private UserEntity findUserByPhone(Long phone) {
        return repository.findByPhone(phone)
                .orElseThrow(() -> new DataNotFoundException("Пользователь не найден!"));
    }
}
