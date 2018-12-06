package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import fr.utbm.TeachMe.entity.CourseSession;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class CourseSessionDao {

    public void saveCourseSession(CourseSession cs){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(cs);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    private void deleteCourseSession (CourseSession cs ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(cs);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    private void updateCourseSession (CourseSession cs ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(cs);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    public List<CourseSession> getAllCourseSession (){
        List<CourseSession> allCourseSession = null;
        Session mySession = HibernateUtils.openSession();
        try{
            CriteriaQuery<CourseSession> critQuery = mySession.getCriteriaBuilder().createQuery(CourseSession.class);
            critQuery.from(CourseSession.class);
            allCourseSession = mySession.createQuery(critQuery).getResultList();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        } finally {
            mySession.close();
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
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
        return selectedCourseSession;
    }
}
