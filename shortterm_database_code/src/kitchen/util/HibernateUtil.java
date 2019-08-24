package kitchen.util;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kitchen.*;
import kitchen.model.BeanAdministratorInformation;


public class HibernateUtil {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public static void main(String[] args){
		Session session=getSession();
		List<BeanAdministratorInformation> pubs=session.createQuery("from BeanAdministratorInformation").list();
		System.out.println(pubs.size());
	}
}
