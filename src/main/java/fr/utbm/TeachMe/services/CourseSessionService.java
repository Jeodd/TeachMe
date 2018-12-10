package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.repository.CourseSessionDao;

public class CourseSessionService {

    public CourseSessionService(){

    }

    public CourseSession getCoursesSession(CourseSession cs){
        CourseSessionDao csdao = new CourseSessionDao();
        return csdao.getCourseSession(cs);
    }
}
