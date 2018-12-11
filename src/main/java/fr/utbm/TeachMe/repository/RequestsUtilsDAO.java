package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.utils.HibernateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class RequestsUtilsDAO {
    private static final Logger logger = Logger.getLogger(CourseDao.class);

    public List<Course> getAllCoursesByKeyWord(String keyWord) {
        Session mySession = HibernateUtils.openSession();

        List<Course> coursesByKeyWord= mySession.createQuery(
                "select CO.title " +
                        "from Course CO " +
                        "where title like '%" + keyWord + "%'"
        ).getResultList();

        return coursesByKeyWord;

    }

    public List<Course> getAllCoursesByCourseSessionDate(String date) {
        Session mySession = HibernateUtils.openSession();

        List<Course> coursesByDates = mySession.createQuery(
                "select CO.title " +
                        "from Course CO " +
                        "inner join CourseSession CS on CS.course.code = CO.code " +
                        "where CS.startDate = '" + date + "'")
                .getResultList();

        return coursesByDates;
    }

    public List<Course> getAllCoursesByLocation(String location) {
        Session mySession = HibernateUtils.openSession();

        List<Course> courseByLocation = mySession.createQuery(
                "select CO.title " +
                        "from Course CO " +
                        "inner join CourseSession  CS on CS.course.code = CO.code " +
                        "inner join Location LOC on LOC.idLocation = CS.location.idLocation " +
                        "where LOC.city = '" + location + "'"
        ).getResultList();

        return courseByLocation;
    }
}
