package kitchen.util;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kitchen.*;
import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsOrderManager;
import kitchen.control.RecipeManager;
import kitchen.control.UserManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanUser;


public class HibernateUtil {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public static void main(String[] args) throws BaseException {
		Session session = getSession();
		UserManager userManager = new UserManager();
//		userManager.reg("tom", "men", "1", "1", "1", 1, "1@qq.com", "newyork");
//		userManager.reg("jack", "women", "2", "2", "2", 2, "2@qq.com", "pekking");
//		userManager.reg("merry", "women", "3", "3", "3", 3, "3@qq.com", "tokyo");
		
		BeanUser beanUser=userManager.login("1", "1");
//		System.out.println(beanUser.getUser_city());
		
//		userManager.changePwd(beanUser, "123", "321", "321");
		
//		userManager.deleteUser(beanUser);
		
//		System.out.println(userManager.loadAll());
		
		RecipeManager recipeManager = new RecipeManager();
//		recipeManager.addRecipe("番茄炒蛋", beanUser, "番茄炒鸡蛋");
		
//		BeanRecipeInformation recipeInformation=recipeManager.searchRecipe("番茄炒蛋");
//		BeanRecipeInformation recipeInformation=recipeManager.searchRecipe("茄炒");
//		System.out.println(recipeInformation);
		
//		System.out.println(recipeManager.loadAll());
		
		IngredientsManager ingredientsManager = new IngredientsManager();
//		ingredientsManager.addIngredientsCategory("肉类", "鸡鸭鱼羊");
		
//		BeanIngredientsCategory ingredientsCategory = new BeanIngredientsCategory();
//		ingredientsCategory.setCategory_number(1);
//		ingredientsManager.deleIngredientsCategory(ingredientsCategory);
		
//		ingredientsManager.addIngredientsInformation("鸡肉", 2, "鸡肉味,嘎嘣脆", "斤", 2);
		
//		System.out.println(ingredientsManager.loadAll());
		
		IngredientsOrderManager ingredientsOrderManager = new IngredientsOrderManager();
		ingredientsOrderManager.addIngredientsOrder(beanUser, "2019-2-3 12:30:00", "zucc", 123456789);
	}
	

}
