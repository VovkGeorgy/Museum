package by.home.museum.service.impl;

import by.home.museum.entity.RolesEntity;
import by.home.museum.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    public RolesEntity getByName(String name) {
        return rolesRepository.findByName(name);
    }
}
