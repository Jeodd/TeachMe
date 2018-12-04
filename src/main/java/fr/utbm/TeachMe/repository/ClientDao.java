package fr.utbm.TeachMe.repository;

import fr.utbm.TeachMe.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ClientDao {

    private SessionFactory hbtSession = null;
    private Session mySession = null;

    public ClientDao() {
        this.buildSession();
    }

    private void buildSession(){
        this.hbtSession = new Configuration()
                .configure()
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();
        this.mySession = this.hbtSession.getCurrentSession();
    }

    private void saveClient(Client c){
        if (this.mySession == null || this.hbtSession == null){
            this.buildSession();
        }
        try{
            this.mySession.beginTransaction();
            this.mySession.save(c);
            this.mySession.getTransaction().commit();

        }finally {
            this.hbtSession.close();
        }
    }

    private void deleteClient (Client c ){
            if (this.mySession == null || this.hbtSession == null){
                this.buildSession();
            }
            try{
                this.mySession.beginTransaction();
                this.mySession.delete(c);
                this.mySession.getTransaction().commit();

            }finally {
                this.hbtSession.close();
            }
    }

    private void updateClient (Client c ){
        if (this.mySession == null || this.hbtSession == null){
            this.buildSession();
        }
        try{
            this.mySession.beginTransaction();
            this.mySession.update(c);
            this.mySession.getTransaction().commit();

        }finally {
            this.hbtSession.close();
        }
    }
}
