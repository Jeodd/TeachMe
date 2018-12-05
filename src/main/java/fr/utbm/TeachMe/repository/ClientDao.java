package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Client;
import org.hibernate.Session;
import fr.utbm.TeachMe.utils.HibernateUtils;


public class ClientDao {

    public ClientDao() {
    }


    private void saveClient(Client c){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.save(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }

    private void deleteClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.delete(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }

    private void updateClient (Client c ){
        Session mySession = HibernateUtils.openSession();
        try{
            mySession.beginTransaction();
            mySession.update(c);
            mySession.getTransaction().commit();

        }finally {
            mySession.close();
        }
    }
}
