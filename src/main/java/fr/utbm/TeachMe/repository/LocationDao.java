package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.utils.HibernateUtils;
import fr.utbm.TeachMe.entity.Location;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LocationDao {
    private static final Logger logger = Logger.getLogger(CourseSessionDao.class);
    public List<Location> getAllLocations (){
        List<Location> allCourses = null;
        Session mySession = HibernateUtils.openSession();
        try{
            CriteriaQuery<Location> critQuery = mySession.getCriteriaBuilder().createQuery(Location.class);
            critQuery.from(Location.class);
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
}
