package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Client;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import fr.utbm.TeachMe.utils.HibernateUtils;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ClientDao {

    private static final Logger logger = Logger.getLogger(CourseDao.class);

    public ClientDao() {
    }

    public void saveClient(Client c){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }

    public void deleteClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }

    public void updateClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }

    public List<Client> getAllClients() {
        List<Client> allClients = null;
        Session mySession = HibernateUtils.openSession();
        try {
            CriteriaQuery<Client> criteriaQuery = mySession.getCriteriaBuilder().createQuery(Client.class);
            criteriaQuery.from(Client.class);
            allClients = mySession.createQuery(criteriaQuery).getResultList();
            logger.log(Level.INFO, "Getting all clients : OK");
        }catch(Exception e) {
            logger.fatal("Error during clients recovery (all of them)", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return allClients;
    }

    public Client getClient(Client c) {
        Client selectedClient = null;
        Session mySession = HibernateUtils.openSession();
        try {
            mySession.beginTransaction();
            selectedClient = mySession.get(Client.class, c.getId());
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Getting a client : OK");
        }catch (Exception e) {
            logger.fatal("Error during client recovery", e);
        }finally {
            mySession.close();
            logger.log(Level.INFO, "Session closed successfully");
        }
        return selectedClient;

    }
}
