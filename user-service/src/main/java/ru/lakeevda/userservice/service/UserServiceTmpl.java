package ru.lakeevda.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lakeevda.userservice.entity.UserData;
import ru.lakeevda.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceTmpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserData> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserData getUser(Integer id) {
        Optional optional = userRepository.findById(id);
        UserData userData = null;
        if (optional.isPresent()) userData = (UserData)optional.get();
        return userData;
    }

    @Override
    public String getNameByPhone(Integer phone) {
        UserData userData = userRepository.findByPhone(phone);
        if (userData != null)
            return userData.getPhone().toString();
        else
            return "User not found";
    }

    @Override
    public void saveUser(UserData userData) {
        UserData curUserData = userRepository.findByPhone(userData.getPhone());
        if (curUserData != null) userData = curUserData;
        userRepository.save(userData);
    }
}
