package by.home.museum.service.impl;

import by.home.museum.entity.RolesEntity;
import by.home.museum.repository.RolesRepository;
import by.home.museum.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    /**
     * Find entity by name
     *
     * @param name - name
     * @return entity
     */
    public RolesEntity getByName(String name) {
        return rolesRepository.findByName(name);
    }
}
