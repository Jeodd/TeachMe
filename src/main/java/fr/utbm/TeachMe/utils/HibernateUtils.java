package fr.utbm.TeachMe.utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(HibernateUtils.class);

    static {
        try {
            PropertyConfigurator.configure("Log4J.properties");
            PropertyConfigurator.configureAndWatch("Log4j.properties", 10000);

            //Absolute paths for log4J activation
            //PropertyConfigurator.configure("C:\\Users\\boxof\\IdeaProjects\\TeachMe\\src\\main\\resources\\Log4j.properties");
            //PropertyConfigurator.configureAndWatch("C:\\Users\\boxof\\IdeaProjects\\TeachMe\\src\\main\\resources\\Log4j.properties", 10000);
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            logger.fatal("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
    public static void logSessionClose(){
        logger.log(Level.INFO, "Session closed successfully");
    }
    public static void logErrorDuringTransaction( String transactionName, Boolean isError){
        if (isError){
            logger.log(Level.ERROR, "Error during "+ transactionName);
        }
        else {
            logger.log(Level.ERROR, " Successfully "+ transactionName);
        }
    }
}
