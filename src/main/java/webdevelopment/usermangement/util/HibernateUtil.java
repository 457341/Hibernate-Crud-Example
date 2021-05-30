package webdevelopment.usermangement.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

//import net.javaguides.usermanagement.model.User;
import webdevelopment.usermangement.model.User;

/**
 * Java based configuration
 * @author Manzoor Hussain
 *
 */
public class HibernateUtil {
 private static SessionFactory sessionFactory;

 public static SessionFactory getSessionFactory() {
  if (sessionFactory == null) {
   try {
    Configuration configuration = new Configuration();

    // Hibernate settings equivalent to hibernate.cfg.xml's properties
    Properties settings = new Properties();
    settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");//It represents the JDBC driver class.
    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false");
//    settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT");
    settings.put(Environment.USER, "root");
    settings.put(Environment.PASS, "hussain");
    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");// says that which database will be used

    settings.put(Environment.SHOW_SQL, "true");//It is used to display the executed SQL statements to console.

    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");//It provides a custom strategy for the scoping of the "current" Session.

    settings.put(Environment.HBM2DDL_AUTO, "update");// Possible options create-drop,create,drop,create-only,validate,none
//    settings.put(Environment.HBM2DDL_AUTO, "create-drop");
	    configuration.setProperties(settings);
	    configuration.addAnnotatedClass(User.class); //hangi sinif uzerinde session olacagini
//A ServiceRegistry, at its most basic, hosts and manages Services.
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
      .applySettings(configuration.getProperties()).build();//SessionFactoryServiceRegistry is designed 
    //to hold Services which need access to the SessionFactory. SessionFactoryServiceRegistry, 
    //SessionFactory'ye erismesi gereken Hizmetleri tutmak için tasarlanmistir.
    System.out.println("Hibernate Java Config serviceRegistry created");
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    return sessionFactory;

   } catch (Exception e) {
    e.printStackTrace();
   }
   
  }
  return sessionFactory;
 }
}