package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.repository.RequestsUtilsDAO;

import java.util.List;

public class RequestsUtilsService {

    private RequestsUtilsDAO requestsUtilsDAO = new RequestsUtilsDAO();

    public RequestsUtilsService() {

    }

    public List<Course> getallCoursesByCourseSessionDate(String date) {
        return requestsUtilsDAO.getAllCoursesByCourseSessionDate(date);
    }

    public List<Course> getAllCoursesByLocation(String location) {
        return requestsUtilsDAO.getAllCoursesByLocation(location);
    }

    public List<Course> getAllCoursesByKeyWord(String keyWord) {
        return requestsUtilsDAO.getAllCoursesByKeyWord(keyWord);
    }
}
