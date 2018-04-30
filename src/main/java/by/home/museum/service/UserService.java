package by.home.museum.service;

import by.home.museum.entity.UsersEntity;

public interface UserService {

    UsersEntity findByUsername(String username);

}
