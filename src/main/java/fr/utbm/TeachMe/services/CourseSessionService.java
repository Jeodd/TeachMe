package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.repository.CourseSessionDao;

import java.sql.Date;
import java.util.List;

public class CourseSessionService {
    CourseSessionDao csdao = new CourseSessionDao();

    public CourseSessionService(){

    }

    public void saveCoursesSession(CourseSession cs) {
        csdao.saveCourseSession(cs);
    }

    public void updateCoursesSession(CourseSession cs) {
        csdao.updateCourseSession(cs);
    }

    public void deleteCoursesSession(CourseSession cs) {
        csdao.deleteCourseSession(cs);
    }

    public List<CourseSession> getAllCoursesSession() {

        return csdao.getAllCourseSession();
    }

    public CourseSession getCoursesSession(CourseSession cs){
        return csdao.getCourseSession(cs);
    }
    public CourseSession getCoursesSessionById(String id){
        int _id = Integer.parseInt(id);
        CourseSession cs = new CourseSession();
        cs.setId(_id);
        return csdao.getCourseSession(cs);
    }
    public List<CourseSession> getAllCoursesSessionByDate (Date d){
        return csdao.getCourseSessionByDate(d);
    }
    public List<CourseSession> getCourseSessionByLocation (String cityName){
        return csdao.getCourseSessionByLocation(cityName);
    }
}
