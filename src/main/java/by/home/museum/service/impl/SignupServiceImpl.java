package by.home.museum.service.impl;

import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import by.home.museum.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UsersRepository userRepository;

    /**
     * Saves a given entity.
     *
     * @param user - entity to save
     * @return the saved entity
     */
    @Override
    public UsersEntity addUser(UsersEntity user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a given entity.
     *
     * @param user - entity to delete
     */
    @Override
    public void delUser(UsersEntity user) {
        userRepository.delete(user.getId());
    }

}
