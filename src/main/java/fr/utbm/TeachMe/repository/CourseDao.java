package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.entity.CourseSession;
import fr.utbm.TeachMe.entity.Location;
import fr.utbm.TeachMe.utils.HibernateUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class CourseDao {

    private static final Logger logger = Logger.getLogger(CourseDao.class);

    public void saveCourse(Course c){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(c);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Save : OK");
        }catch (Exception e){
            logger.fatal("Error during course saving", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public void deleteCourse (Course c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(c);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Deletion : OK");
        }catch (Exception e){
            logger.fatal("Error during course deletion", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public void updateCourse (Course c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(c);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Update : OK");
        }catch (Exception e){
            logger.fatal("Error during course update", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public List<Course> getAllCourses (){
        List<Course> allCourses = null;
        Session mySession = HibernateUtils.openSession();
        try{
            CriteriaQuery<Course> critQuery = mySession.getCriteriaBuilder().createQuery(Course.class);
            critQuery.from(Course.class);
            allCourses = mySession.createQuery(critQuery).getResultList();
            logger.log(Level.INFO, "Getting all courses : OK");
        }catch (Exception e){
            logger.fatal("Error during courses recovery (all of them)", e);
        } finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return allCourses;
    }

    public Course getCourse(Course c){
        Course selectedCourse = null;
        Session mySession = HibernateUtils.openSession();
        try {
            mySession.beginTransaction();
            selectedCourse =  mySession.get(Course.class, c.getCode());
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Getting a course : OK");
        }catch (Exception e){
            logger.fatal("Error during course recovery", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return selectedCourse;
    }
    public List<Course> getAllCoursesByKeyWord(String keyWord) {
        Session mySession = HibernateUtils.openSession();
        List<Course> coursesByKeyWord = null;
        try{
            coursesByKeyWord= mySession.createQuery(
                    "select CO.title " +
                            "from Course CO " +
                            "where title like '%" + keyWord + "%'"
            ).getResultList();
        }catch(Exception e){
            logger.fatal("Error during coursesByKeyWord recovery", e);
        }
        return coursesByKeyWord;

    }

    public List<Course> getAllCoursesByCourseSessionDate(CourseSession cs) {
        Session mySession = HibernateUtils.openSession();
        List<Course> coursesByDates = null;
        try{
            coursesByDates = mySession.createQuery(
                    "select CO.title " +
                            "from Course CO " +
                            "inner join CourseSession CS on CS.course.code = CO.code " +
                            "where CS.startDate = '" + cs.getStartDate().toString() + "'")
                    .getResultList();
        }catch(Exception e){
            logger.fatal("Error during coursesByDates recovery", e);
        }
        return coursesByDates;
    }

    public List<Course> getAllCoursesByLocation(Location loc) {
        Session mySession = HibernateUtils.openSession();
        List<Course> courseByLocation = null;
        try{
            courseByLocation = mySession.createQuery(
                    "select CO.title " +
                            "from Course CO " +
                            "inner join CourseSession  CS on CS.course.code = CO.code " +
                            "inner join Location LOC on LOC.idLocation = CS.location.idLocation " +
                            "where LOC.city = '" + loc.getCity() + "'"
            ).getResultList();
        }catch(Exception e){
            logger.fatal("Error during courseByLocation recovery", e);
        }
        return courseByLocation;
        }
    }
