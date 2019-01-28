package by.home.museum.service.impl;

import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import by.home.museum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Users service class
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UsersRepository userRepository;

    @Override
    public UsersEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
