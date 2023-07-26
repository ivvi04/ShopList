package ru.lakeevda.userservice.service;

import ru.lakeevda.userservice.entity.UserData;

import java.util.List;

public interface UserService {
    public List<UserData> getAllUser();

    public UserData getUser(Integer id);

    public String getNameByPhone(Integer phone);

    public void saveUser(UserData userData);
}
