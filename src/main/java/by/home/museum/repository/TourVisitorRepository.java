package by.home.museum.repository;

import by.home.museum.entity.TourVisitorEntity;
import by.home.museum.entity.TourVisitorEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TourVisitorRepository extends CrudRepository<TourVisitorEntity, TourVisitorEntityPK> {

    TourVisitorEntity findByVisitorId(Long visitorId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tour_visitor WHERE visitor_id = ?2 and tour_id = ?1", nativeQuery = true)
    int deleteTourOfVisitor(Long tourId, Long visitorId);
}
