package by.home.museum.repository;

import by.home.museum.entity.VisitorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends CrudRepository<VisitorEntity, Long> {

    VisitorEntity findByUsername(String username);

//    @Query(value = "INSERT INTO tour_visitor (tour_id, visitor_id) VALUES (?1, ?2)", nativeQuery = true)
//    void addTourToVisitor(Long tourId, Long visitorId);
}
