package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.exception.UserPhoneNotFoundException;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userService.loadUserByUsername(username);
        return userDetails;
    }

    public User loadUserByPhone(long phone) throws UserPhoneNotFoundException {
        User user = userService.loadUserByPhone(phone);
        return user;
    }

}
