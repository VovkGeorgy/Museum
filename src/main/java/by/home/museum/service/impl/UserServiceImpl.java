package by.home.museum.service.impl;

import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import by.home.museum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UsersEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
