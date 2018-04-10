package by.home.museum.repository;

import by.home.museum.entity.GuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuideEntityRepository extends JpaRepository<GuideEntity, Integer> {

    /**
     * Saves GuideEntity and flushes changes instantly.
     *
     * @param guide - saved student
     * @return GuideEntity
     */
    @Override
    GuideEntity saveAndFlush(GuideEntity guide);

    /**
     * This method get one GuideEntity where column Id = GuideEntity Id
     *
     * @param id - Guide Id
     * @return GuideEntity
     */
    @Override
    GuideEntity getOne(Integer id);

    /**
     * This method update age field at GuideEntity where fio = fio
     *
     * @param studentYearsOld - new YearsOld
     * @param studentFio      - current StudentEntity fio
     * @return int - count of changed rows
     */
//    @Transactional
//    @Modifying
//    @Query(value = "update StudentEntity student set student.yearsOld= ?1 where student.fio= ?2")
//    int updateStudentEntityYearsOld(int studentYearsOld, String studentFio);

    /**
     * This method delete Guide Entity
     *
     * @param guideId - Guide Entity id
     * @return GuideEntity
     */
    @Transactional
    void deleteByGuideId(Integer guideId);

    /**
     * This method return all Guide Entity
     *
     * @return List<GuideEntity>
     */
    @Override
    List<GuideEntity> findAll();

    GuideEntity findByFio(String fio);
}
