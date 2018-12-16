package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.entity.Location;
import fr.utbm.TeachMe.repository.CourseDao;

import java.util.List;

public class CourseService {
    private CourseDao cdao = new CourseDao();
    public CourseService() {
    }

    public void saveCourse(Course c) {
        cdao.saveCourse(c);
    }

    public void updateCourse(Course c) {
        cdao.updateCourse(c);
    }

    public void deleteCourse(Course c) {
        cdao.deleteCourse(c);
    }

    public Course getCourse(Course c ){
        return cdao.getCourse(c);
    }

    public List<Course> getAllCourses() {
        return cdao.getAllCourses();
    }

}

