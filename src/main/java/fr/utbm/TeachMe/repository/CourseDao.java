package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Course;
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

    private void deleteCourse (Course c ){
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

    private void updateCourse (Course c ){
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
}
