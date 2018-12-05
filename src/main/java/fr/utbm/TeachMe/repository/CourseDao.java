package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Course;
import fr.utbm.TeachMe.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class CourseDao {

    public void saveCourse(Course c){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(c);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    private void deleteCourse (Course c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(c);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    private void updateCourse (Course c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(c);
            mySession.getTransaction().commit();
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
    }

    public List<Course> getAllCourse (){
        List<Course> allCourses = null;
        Session mySession = HibernateUtils.openSession();
        try{

        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        } finally {
            mySession.close();
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
        }catch (Exception e){
            //TODO : Log4J implementation
            //le type d'exception doit etre adapté
        }finally {
            mySession.close();
        }
        return selectedCourse;
    }
}
