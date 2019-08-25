package kitchen.control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class IngredientsManager {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public BeanIngredientsCategory addIngredientsCategory(String category_name,String  category_description) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanIngredientsCategory ingredientsCategory = new BeanIngredientsCategory();
		try {
			ingredientsCategory.setCategory_name(category_name);
			ingredientsCategory.setCategory_description(category_description);
			
			session.save(ingredientsCategory);
			transaction.commit();
		}catch (SessionException e) {
			throw new BusinessException("添加食材类别失败");
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
	
		return ingredientsCategory;
	}
	
	public void deleIngredientsCategory(BeanIngredientsCategory ingredientsCategory) throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
					
			String hql = "delete BeanIngredientsCategory i where i.category_number =:category_number";
			Query query = session.createQuery(hql);
			query.setInteger("category_number", ingredientsCategory.getCategory_number());
			query.executeUpdate();
			
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
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
	}
	
	public BeanIngredientsInformation addIngredientsInformation(String ingredients_name,double ingredients_price,
			String ingredients_description, String ingredients_specification, int category_number) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanIngredientsInformation ingredientsInformation = new BeanIngredientsInformation();
		try {
			ingredientsInformation.setIngredients_name(ingredients_name);
			ingredientsInformation.setIngredients_price(ingredients_price);
			ingredientsInformation.setIngredients_quantity(0);
			ingredientsInformation.setIngredients_description(ingredients_description);
			ingredientsInformation.setIngredients_specification(ingredients_specification);
			ingredientsInformation.setCategory_number(category_number);
			session.save(ingredientsInformation);
			transaction.commit();
		}catch (SessionException e) {
			throw new BusinessException("添加食材信息失败");
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
	
		return ingredientsInformation;
	}
	
	public List<BeanIngredientsInformation> loadAll()throws BaseException{
		List<BeanIngredientsInformation> ingredientsInformations=new ArrayList<BeanIngredientsInformation>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql ="from BeanIngredientsInformation";
			org.hibernate.query.Query query = session.createQuery(hql);
			ingredientsInformations = query.list();
		} catch (Exception e) {
			// TODO: handle exception
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
		return ingredientsInformations;
	}
}
