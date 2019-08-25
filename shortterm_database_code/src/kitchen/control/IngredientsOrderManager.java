package kitchen.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class IngredientsOrderManager {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public BeanIngredientsOrder addIngredientsOrder(BeanUser user, String order_required_time,
			String order_delivery_address, int user_phone_number) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanIngredientsOrder ingredientsOrder = new BeanIngredientsOrder();
		
		try {
			ingredientsOrder.setUser_number(user.getUser_number());
			ingredientsOrder.setOrder_required_time(Timestamp.valueOf(order_required_time) );
			ingredientsOrder.setOrder_delivery_address(order_delivery_address);
			ingredientsOrder.setUser_phone_number(user.getUser_phone_number());
			ingredientsOrder.setOrder_status("下单");
			session.save(ingredientsOrder);
			transaction.commit();
		}catch (SessionException e) {
			throw new BusinessException("下单失败");
		}finally {
			if(session!=null) {
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	
		return ingredientsOrder;
	}
}