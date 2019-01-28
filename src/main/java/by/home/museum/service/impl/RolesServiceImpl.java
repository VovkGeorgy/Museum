package by.home.museum.service.impl;

import by.home.museum.entity.RolesEntity;
import by.home.museum.repository.RolesRepository;
import by.home.museum.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Roles service class
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

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
