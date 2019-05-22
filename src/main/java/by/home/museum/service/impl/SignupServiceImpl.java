package by.home.museum.service.impl;

import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import by.home.museum.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SignUp service class
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignupServiceImpl implements SignupService {

    private final UsersRepository userRepository;

    @Override
    public UsersEntity addUser(UsersEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void delUser(UsersEntity user) {
        userRepository.delete(user.getId());
    }

}
