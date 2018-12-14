package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Client;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import fr.utbm.TeachMe.utils.HibernateUtils;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            HibernateUtils.logErrorDuringTransaction("save client", false);

        }catch (Exception e ){
            HibernateUtils.logErrorDuringTransaction("save client", true);
            logger.log(Level.DEBUG,"Error during save client\n Stack trace :\n" + e.getMessage());
        }finally {
            mySession.close();
            HibernateUtils.logSessionClose();
        }
    }

    public void deleteClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(c);
            mySession.getTransaction().commit();
            HibernateUtils.logErrorDuringTransaction("delete client", false);

        }catch (Exception e ){
            HibernateUtils.logErrorDuringTransaction("delete client", true);
            logger.log(Level.DEBUG,"Error during delete client\n Stack trace :\n" + e.getMessage());
        }
        finally {
            mySession.close();
            HibernateUtils.logSessionClose();
        }
    }

    public void updateClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(c);
            mySession.getTransaction().commit();
            HibernateUtils.logErrorDuringTransaction("Update client", false);

        }catch (Exception e ){
            HibernateUtils.logErrorDuringTransaction("Update client", true);
            logger.log(Level.DEBUG,"Error during Update client\n Stack trace :\n" + e.getMessage());
        }
        finally {
            mySession.close();
            HibernateUtils.logSessionClose();
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
            HibernateUtils.logSessionClose();
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
            HibernateUtils.logSessionClose();
        }
        return selectedClient;

    }

    public List<Client> getClientBySessionId(Integer sessionId){
        List<Client> retrievedClient = null;
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            String hqlStr ="FROM Client c WHERE c.courseSession.id = :_id";
            Query q = mySession.createQuery(hqlStr);
            q.setInteger("_id", sessionId);
            retrievedClient = q.getResultList();
            mySession.getTransaction().commit();
            logger.log(Level.INFO, "Successfully retrieved client by sessionId  : "+ sessionId.toString());
        }catch (Exception e){
            logger.log(Level.WARN, "Error during getClientBySessionId()");
            logger.log(Level.DEBUG,"Error during getClientBySessionId()\n Stack trace :\n" + e.getMessage());
        }finally {
            mySession.close();
            HibernateUtils.logSessionClose();
        }
        return retrievedClient;
    }
}
