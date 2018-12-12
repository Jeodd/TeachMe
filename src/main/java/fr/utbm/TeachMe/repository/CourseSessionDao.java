package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.utils.HibernateUtils;
import org.apache.log4j.Level;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import fr.utbm.TeachMe.entity.CourseSession;

import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaQuery;

import java.sql.Date;
import java.util.List;

public class CourseSessionDao {

    private static final Logger logger = Logger.getLogger(CourseSessionDao.class);

    public void saveCourseSession(CourseSession cs){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(cs);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Save : OK");
        }catch (Exception e){
            logger.fatal("Error during course session saving", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public void deleteCourseSession (CourseSession cs ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(cs);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Deletion : OK");
        }catch (Exception e){
            logger.fatal("Error during course session deletion", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public void updateCourseSession (CourseSession cs ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(cs);
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Update : OK");
        }catch (Exception e){
            logger.fatal("Error during course session update", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
    }

    public List<CourseSession> getAllCourseSession (){
        List<CourseSession> allCourseSession = null;
        Session mySession = HibernateUtils.openSession();
        try{
            CriteriaQuery<CourseSession> critQuery = mySession.getCriteriaBuilder().createQuery(CourseSession.class);
            critQuery.from(CourseSession.class);
            allCourseSession = mySession.createQuery(critQuery).getResultList();
            logger.log(Level.INFO, "Getting all courses sessions : OK");
        }catch (Exception e){
            logger.fatal("Error during courses session recovery (all of them)", e);
        } finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return allCourseSession;
    }

    public CourseSession getCourseSession(CourseSession cs){
        CourseSession selectedCourseSession = null;
        Session mySession = HibernateUtils.openSession();
        try {
            mySession.beginTransaction();
            selectedCourseSession =  mySession.get(CourseSession.class, cs.getId());
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Getting a courses session : OK");
        }catch (Exception e){
            logger.fatal("Error during course session recovery", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return selectedCourseSession;
    }

    public List<CourseSession> getCourseSessionByDate (Date date){
        List<CourseSession> returnedList = null;
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            String hqlStr ="FROM CourseSession cs WHERE cs.startDate >= :_date";
            Query q = mySession.createQuery(hqlStr);
            q.setDate("_date", date);
            returnedList = q.getResultList();
            mySession.getTransaction().commit();

            logger.log(Level.INFO, "Getting all courses sessions : OK");
        }catch (Exception e){
            logger.fatal("Error during courses session recovery (all of them)", e);
        } finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return returnedList;
    }
}
