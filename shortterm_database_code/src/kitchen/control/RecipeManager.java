package kitchen.control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class RecipeManager {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public BeanRecipeInformation addRecipe(String recipe_name, BeanUser user, String  recipe_description) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanRecipeInformation recipeInformation = new BeanRecipeInformation();
		try {
			recipeInformation.setRecipe_name(recipe_name);
			recipeInformation.setRecipe_collection_number(0);
			recipeInformation.setRecipe_description(recipe_description);
			recipeInformation.setRecipe_overall_rating(0);
			recipeInformation.setRecipe_views_number(0);
			recipeInformation.setUser_number(user.getUser_number());
			
			session.save(recipeInformation);
			transaction.commit();
		}catch (SessionException e) {
			throw new BusinessException("添加菜谱失败");
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
	
		return recipeInformation;
	}
	
	
	public void deleRecipe(BeanRecipeInformation recipeInformation) throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
					
			String hql = "delete BeanRecipeInformation r where r.recipe_number =:recipe_number";
			Query query = session.createQuery(hql);
			query.setInteger("recipe_number", recipeInformation.getRecipe_number());
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
	
	public BeanRecipeInformation searchRecipe(String recipe_name) throws BaseException {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		BeanRecipeInformation recipeInformation = new BeanRecipeInformation();
		try {
			String hql = "from BeanRecipeInformation r where r.recipe_name=:recipe_name";
			Query query = session.createQuery(hql);
			query.setString("recipe_name","%"+recipe_name+"%");
			if(query.uniqueResult() != null) {
				recipeInformation = (BeanRecipeInformation)query.uniqueResult();
			}
		}catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return recipeInformation;
	}
	
	public List<BeanRecipeInformation> loadAll()throws BaseException{
		List<BeanRecipeInformation> recipeInformations=new ArrayList<BeanRecipeInformation>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql ="from BeanRecipeInformation";
			org.hibernate.query.Query query = session.createQuery(hql);
			recipeInformations = query.list();
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
		return recipeInformations;
	}
}
