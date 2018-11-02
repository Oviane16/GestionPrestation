package config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;
	private static Session session = null;

	private static SessionFactory buildSessionFactory() {
	
		try{
			Configuration configuration = new Configuration();
			configuration.configure("config/hibernate.cfg.xml");
			
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			
			return configuration.buildSessionFactory(serviceRegistry);
		    
		    }catch (Throwable ex) {
		    	System.err.println("Failed to ceate sessionFactory object" + ex);
		    	throw new ExceptionInInitializerError(ex);
	        }
    
	}  
	 
	// des méthodes utils
	
	public static SessionFactory geSessionFactory(){
		return sessionFactory;
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}

	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public static void close() {
		if (sessionFactory != null){
			sessionFactory.close();
		}
	    sessionFactory = null;
	}
}

	


