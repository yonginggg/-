package kitchen.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanIngredientsProcurement;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class IngredientsProcurementManager {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public BeanIngredientsProcurement addIngredientsProcurement(BeanIngredientsInformation ingredientsInformation,
			int quantity, BeanAdministratorInformation administratorInformation) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanIngredientsProcurement ingredientsProcurement = new BeanIngredientsProcurement();
		
		try {
			ingredientsProcurement.setAdministrator_number(administratorInformation.getAdministrator_number());
			ingredientsProcurement.setIngredients_number(ingredientsInformation.getIngredients_number());
			ingredientsProcurement.setQuantity(quantity);
			ingredientsProcurement.setProcurement_status("下单");
			session.save(ingredientsProcurement);
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
	
		return ingredientsProcurement;
	}
	
	public List<BeanIngredientsProcurement> loadAll() throws BaseException{
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		List<BeanIngredientsProcurement> procurements = new ArrayList<BeanIngredientsProcurement>();
		try {
			String hql ="from BeanIngredientsProcurement";
			Query query = session.createQuery(hql);
			procurements = query.list();
			
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("查询失败");
		}finally {
			if(session!=null) {
				try {
					session.close();
				} catch (SessionException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return procurements;
		
	}
	
	public BeanIngredientsProcurement changeStatus(BeanIngredientsProcurement procurement,
			String status) throws BaseException{
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
			procurement.setProcurement_status(status);
			session.update(procurement);
			
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("查询失败");
		}finally {
			if(session!=null) {
				try {
					session.close();
				} catch (SessionException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return procurement;
	}
}
