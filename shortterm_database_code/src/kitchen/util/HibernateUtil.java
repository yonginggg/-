package kitchen.util;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kitchen.*;
import kitchen.control.AdministratorManager;
import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsOrderManager;
import kitchen.control.RecipeManager;
import kitchen.control.UserManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
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
//		userManager.reg("mike", "men", "4", "4", "4", 4, "4@qq.com", "南京");
//		userManager.reg("mike", "男", "5", "5", "5", 5, "5@qq.com", "南京");
		
		BeanUser beanUser=userManager.login("1", "1");
//		System.out.println(beanUser.getUser_city());
		
//		userManager.changePwd(beanUser, "123", "321", "321");
		
//		userManager.deleteUser(beanUser);
		
//		System.out.println(userManager.loadAll());
		
		RecipeManager recipeManager = new RecipeManager();
//		recipeManager.addRecipe("番茄炒蛋", beanUser, "番茄炒鸡蛋");
		
//		List<BeanRecipeInformation> recipeInformations=recipeManager.searchRecipe("炒蛋");
//		BeanRecipeInformation recipeInformation=recipeManager.searchRecipe("茄炒");
//		System.out.println(recipeInformations);
		
//		System.out.println(recipeManager.loadAllRecipe());
		
		IngredientsManager ingredientsManager = new IngredientsManager();
//		ingredientsManager.addIngredientsCategory("肉类", "鸡鸭鱼羊");
		
		BeanIngredientsCategory ingredientsCategory = new BeanIngredientsCategory();
//		ingredientsCategory.setCategory_number(1);
//		ingredientsManager.deleIngredientsCategory(ingredientsCategory);
		
//		ingredientsManager.addIngredientsInformation("鸡肉", 2, "鸡肉味,嘎嘣脆", "斤", 2);
		
//		System.out.println(ingredientsManager.loadAll());
		
		IngredientsOrderManager ingredientsOrderManager = new IngredientsOrderManager();
//		ingredientsOrderManager.addIngredientsOrder(beanUser, "2019-02-03 12:30:00", "zucc", 123456789);
		
//		BeanIngredientsOrder ingredientsOrder = new BeanIngredientsOrder();
//		ingredientsOrder.setOrder_number(1);
//		ingredientsOrderManager.changeIngredientsOrderStatus(ingredientsOrder, "途中");
		
//		System.out.println(ingredientsOrderManager.loadAll());
		
		
//		recipeManager.addRecipeMaterial(recipeInformations.get(0), 2, 1, "斤");
		
//		BeanUser beanUser2 = userManager.loadUser("1");
//		System.out.println(beanUser2);
		
//		ingredientsCategory = ingredientsManager.loadCategory(2);
//		BeanIngredientsInformation information = ingredientsManager.loadAllIngredients(ingredientsCategory).get(0);
//		System.out.println(information);

//		ingredientsCategory.setCategory_number(1);
//		BeanIngredientsInformation ingredientsInformation = ingredientsManager.addIngredientsInformation("2", 2.0, "2", "2", ingredientsCategory);
		
//		ingredientsCategory.setCategory_number(1);
//		BeanIngredientsInformation information = ingredientsManager.loadAllIngredients(ingredientsCategory).get(0);
//		ingredientsManager.addBeanIngredientsQuantity(information, 3);
//		System.out.println(information.getIngredients_quantity());
		
//		AdministratorManager administratorManager = new AdministratorManager();
//		System.out.println(administratorManager.loadAll());
		
//		userManager.reloadUserPassword();
		
//		BeanRecipeInformation recipeInformation =recipeManager.searchRecipe("炒蛋").get(0);
////		System.out.println(recipeInformation.getRecipe_name());
//		recipeManager.addRecipeStep(recipeInformation, 2, "2");
		
//		List<String> ingredientsInformations = new IngredientsManager().loadAllIngredients();
////		String[] ingredientName =(String[])ingredientsInformations.toArray(new String[ingredientsInformations.size()]);
//		String[] ingredientName =ingredientsInformations.toArray(new String[ingredientsInformations.size()]);
//		System.out.println(ingredientName);
		
		BeanIngredientsInformation information = new IngredientsManager().loadIngredient("1");
//		System.out.println(information.getIngredients_number());
//		BeanRecipeInformation recipeInformation = recipeManager.loadAllRecipe().get(0);
//		System.out.println(recipeInformation.getRecipe_number());
//		recipeManager.addRecipeMaterial(recipeInformation, information.getIngredients_number(), 2, "2");
		
//		BeanIngredientsInformation information2 = (BeanIngredientsInformation)session.get(BeanIngredientsInformation.class, information.getIngredients_number());
//		System.out.println(information2.getIngredients_name());
		
		BeanRecipeInformation recipeInformation = new RecipeManager().searchRecipe("番茄炒蛋").get(0);
//		System.out.println(recipeInformation.getRecipe_name());
		new RecipeManager().addRecipeView(recipeInformation);
	}
	

}
