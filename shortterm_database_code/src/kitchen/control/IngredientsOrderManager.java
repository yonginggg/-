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

import kitchen.model.BeanDiscount;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanOrderDetail;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
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
	
	public void changeIngredientsOrderStatus(BeanIngredientsOrder ingredientsOrder, 
			String newStatus) throws BaseException {
		// TODO Auto-generated method stub
		if(newStatus==null || "".equals(newStatus)) throw new BusinessException("新的状态不能为空");
		
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanIngredientsOrder newOrder = new BeanIngredientsOrder();
		try {
			newOrder = session.get(BeanIngredientsOrder.class, ingredientsOrder.getOrder_number());
			if(newOrder==null) {
				throw new BusinessException("订单不存在");
			}

			newOrder.setOrder_status(newStatus);
			session.update(newOrder);
			transaction.commit();
			
		} catch (SessionException e) {
			e.printStackTrace();
			throw new BaseException("修改失败");
		}
		finally{
			if(session!=null)
				try {
					session.close();
				} catch (SessionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
//	显示所有订单
	public List<BeanIngredientsOrder> loadAllOrders()throws BaseException{
		List<BeanIngredientsOrder> ingredientsOrders=new ArrayList<BeanIngredientsOrder>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
			String hql ="from BeanIngredientsOrder";
			org.hibernate.query.Query query = session.createQuery(hql);
			ingredientsOrders = query.list();
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
		return ingredientsOrders;
	}
	
//	根据用户显示各自的订单
	public List<BeanIngredientsOrder> loadAllOrdersByUser(BeanUser beanUser)throws BaseException{
		List<BeanIngredientsOrder> ingredientsOrders=new ArrayList<BeanIngredientsOrder>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
			String hql ="from BeanIngredientsOrder where user_number=:num";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setInteger("num", beanUser.getUser_number());
			ingredientsOrders = query.list();
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
		return ingredientsOrders;
	}
	
	public BeanOrderDetail addDeatil(int order_number,  
			BeanRecipeMaterial material, double discount) throws BaseException{
		
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanOrderDetail orderDetail = new BeanOrderDetail();
		
		BeanIngredientsInformation information = (BeanIngredientsInformation)session.get(BeanIngredientsInformation.class, material.getIngredients_number());
		try {
			orderDetail.setOrder_number(order_number);
			orderDetail.setIngredients_number(material.getIngredients_number());
			orderDetail.setQuantity(material.getQuantity());
			orderDetail.setPrice(information.getIngredients_price());
			orderDetail.setOrder_discount(discount);
			session.save(orderDetail);
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
	
		return orderDetail;
	}
	
	public List<BeanOrderDetail> loadAllDetails(BeanIngredientsOrder order)throws BaseException{
		List<BeanOrderDetail> orderderDetails=new ArrayList<BeanOrderDetail>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
			String hql ="from BeanOrderDetail where order_number=:num";
			Query query = session.createQuery(hql);
			query.setInteger("num", order.getOrder_number());
			orderderDetails = query.list();
		} catch (SessionException e) {
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
		return orderderDetails;
	}
	
////	判断菜谱食材数量是否小于总食材数量,用于添加订单
//	public void judgeQuantity(BeanRecipeInformation recipe) throws BaseException {
////		Session session = HibernateUtil.getSession();
////		org.hibernate.Transaction transaction = session.beginTransaction();
//		List<BeanRecipeMaterial> materials = new RecipeManager().loadAllMaterials(recipe);
//		for(int i=0; i<materials.size();i++) {
//			BeanIngredientsInformation ingredientsInformation = new IngredientsManager().loadIngredient(materials.get(i).getIngredients_number());
//			if(ingredientsInformation.getIngredients_quantity()<materials.get(i).getQuantity()) {
//				throw new BusinessException("食材数量不够,无法创建订单");
//			}
//		}
//	}
	
//	查询最大order值,用于添加订单
	public int maxOrder() {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		int maxOrderNumber = (Integer)session.createQuery("select max(o.order_number) from BeanIngredientsOrder o " ).uniqueResult();
		return maxOrderNumber;
		
	}
	
	
}
