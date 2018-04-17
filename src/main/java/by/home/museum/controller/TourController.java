package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    TourService tourService;

//    /**
//     * Method add Student entity with @params
//     *
//     * @param fio       - student fio
//     * @param workGroup - student working group
//     * @param yearsOld  - student age
//     * @param teacherId - student teacher
//     * @return - message
//     * {@link Deprecated}
//     */
//    @RequestMapping(value = "/addEntity", method = RequestMethod.POST)
//    public String getStudentEntity(String fio, int workGroup, int yearsOld, int teacherId) {
//        StudentEntity stud = new StudentEntity();
//        stud.setFio(fio);
//        stud.setWorkGroup(workGroup);
//        try {
//            if (yearsOld < 18) throw new Exception();
//        } catch (Exception e) {
//            logger.error(messageSource.getMessage("object.field.updating.error", new Object[]{stud}, Locale.getDefault()), e);
//        }
//        stud.setYearsOld(yearsOld);
//        stud.setTeacher(teacherService.getOne(teacherId));
//        studentService.saveAndFlush(stud);
//        logger.info(messageSource.getMessage("object.create.ok", new Object[]{stud}, Locale.getDefault()));
//        return messageSource.getMessage("object.create.ok", new Object[]{stud}, Locale.getDefault());
//    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ResponseEntity<TourEntity> addStudent(@RequestBody TourEntity tour) {
        TourEntity newTour = new TourEntity();
        if (tour != null) {
            newTour.setTheme(tour.getTheme());
            newTour.setCost(tour.getCost());
            newTour.setDuration(tour.getDuration());
            newTour.setTypeOfExhibits(tour.getTypeOfExhibits());
            tourService.save(newTour);
        }
        return new ResponseEntity<TourEntity>(newTour, HttpStatus.OK);
    }

    /**
     * Method return all students entity from dataBase to view page at JSON format
     *
     * @param model - model
     * @return List<StudentEntity>
     */
//    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
//    public List<StudentEntity> getStudentsNames(ModelMap model) {
//        List<StudentEntity> all = studentService.findAll();
//        return all;
//    }
    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public List<TourEntity> getStudentsNames(ModelMap model) {
        List<TourEntity> all = (List<TourEntity>) tourService.findAll();
        return all;
    }

    @RequestMapping(value = "/deleteT", method = RequestMethod.POST)
    public void deleteTour(@RequestBody TourEntity tour) {
        tourService.delete(tour);
    }


}