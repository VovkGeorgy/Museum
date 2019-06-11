package by.home.museum.service.impl;

import by.home.museum.dto.TourGuideDto;
import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.GuideRepository;
import by.home.museum.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Guide service class
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuideServiceImpl implements GuideService {
    private final GuideRepository repository;
    private final UserService userService;
    private final SignupService signupService;
    private final RolesService rolesService;
    private final TourService tourService;

    @Override
    public void delete(GuideEntity deleted) {
        deleted.getTourEntitySet().forEach(tour -> tour.setGuideEntity(null));
        repository.delete(deleted);
        if (!repository.exists(deleted.getGuideId())) {
            signupService.delUser(userService.findByUsername(deleted.getUsername()));
        }
    }

    @Override
    public List<GuideEntity> findAll() {
        return repository.findAllByOrderByFio();
    }

    @Override
    public GuideEntity findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public GuideEntity save(GuideEntity persisted) {
        return repository.save(persisted);
    }

    @Override
    public GuideEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public GuideEntity updateGuide(GuideEntity guide) {
        GuideEntity persistGuide = findOne(guide.getGuideId());
        persistGuide.update(guide);
        save(persistGuide);
        UsersEntity usersEntity = userService.findByUsername(persistGuide.getUsername());
        if (usersEntity == null) {
            UsersEntity newUser = new UsersEntity(persistGuide.getUsername(), persistGuide.getPassword());
            newUser.setRoles(Arrays.asList(rolesService.getByName("GUIDE"), rolesService.getByName("USER")));
            signupService.addUser(newUser);
        } else {
            usersEntity.setPassword(persistGuide.getPassword());
            signupService.addUser(usersEntity);
        }
        return persistGuide;
    }


    @Override
    public GuideEntity removeTours(TourGuideDto tgd) {
        Arrays.asList(tgd.getTourIdsArray()).forEach(tourId -> {
            TourEntity tourEntity = tourService.findOne(tourId);
            tourEntity.setGuideEntity(null);
            tourService.save(tourEntity);
        });
        return findOne(tgd.getGuideId());
    }

    @Override
    public GuideEntity addTours(TourGuideDto tgd) {
        GuideEntity guideEntity = findOne(tgd.getGuideId());
        Arrays.asList(tgd.getTourIdsArray()).forEach(tourId -> {
            TourEntity tourEntity = tourService.findOne(tourId);
            tourEntity.setGuideEntity(guideEntity);
            tourService.save(tourEntity);
        });
        return findOne(tgd.getGuideId());
    }
}
