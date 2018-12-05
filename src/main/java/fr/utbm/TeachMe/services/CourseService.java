package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.repository.CourseDao;

public class CourseService {
    public CourseService() {
    }

    public Course getCourse(Course c ){
        CourseDao cdao = new CourseDao();
        return cdao.getCourse(c);
    }
}
