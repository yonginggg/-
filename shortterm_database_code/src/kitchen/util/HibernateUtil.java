package kitchen.util;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.edu.zucc.personplan.model.*;


public class HibernateUtil {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
//	public static void main(String[] args){
//		Session session=getSession();
//		System.out.println(session);
//	}
	
//	public static void main(String[] args){
//		Session session=getSession();
//		List<BeanPlan> pubs=session.createQuery("from BeanPlan").list();
//		System.out.println(pubs.size());
//	}

//	public static void main(String[] args){
//		Session session=getSession();
//		List<BeanUser> pubs=session.createQuery("from BeanUser").list();
//		System.out.println(pubs.size());
//	}
	
	public static void main(String[] args){
		Session session=getSession();
		List<BeanStep> pubs=session.createQuery("from BeanStep").list();
		System.out.println(pubs.size());
	}
}
