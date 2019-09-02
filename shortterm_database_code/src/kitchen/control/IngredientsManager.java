package kitchen.control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javafx.collections.ListChangeListener.Change;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanRecipeMaterial;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class IngredientsManager {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public static Session getSession() {
		Session session = sessionFactory.openSession();
		return session;
	}

//	添加食材类别
	public BeanIngredientsCategory addIngredientsCategory(String category_name, String category_description)
			throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		BeanIngredientsCategory ingredientsCategory = new BeanIngredientsCategory();
		try {
			ingredientsCategory.setCategory_name(category_name);
			ingredientsCategory.setCategory_description(category_description);

			session.save(ingredientsCategory);
			transaction.commit();
		} catch (SessionException e) {
			throw new BusinessException("添加食材类别失败");
		} finally {
			if (session != null) {
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

//	删除食材类别
	public void deleteIngredientsCategory(BeanIngredientsCategory ingredientsCategory) throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		try {
			List<BeanIngredientsInformation> informations = new ArrayList<BeanIngredientsInformation>();
			String hql = "from BeanIngredientsInformation  where category_number=:num";
			Query query = session.createQuery(hql);
			query.setInteger("num", ingredientsCategory.getCategory_number());
			informations = query.getResultList();
			if (informations != null && !informations.isEmpty()) {
				throw new BusinessException("该食材类别下有食材信息存在,无法删除");
			}

//			hql = "delete BeanIngredientsCategory i where i.category_number =:category_number";
//			query = session.createQuery(hql);
//			query.setInteger("category_number", ingredientsCategory.getCategory_number());
//			query.executeUpdate();
			session.delete(informations);
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	public BeanIngredientsCategory changIngredientsCategory(BeanIngredientsCategory ingredientsCategory,
			String newDescription) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			ingredientsCategory.setCategory_description(newDescription);
			session.update(ingredientsCategory);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

	public BeanIngredientsCategory loadCategory(int category_number) throws BaseException {
		BeanIngredientsCategory ingredientsCategory = new BeanIngredientsCategory();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanIngredientsCategory where category_number =:num";
			Query query = session.createQuery(hql);
			query.setInteger("num", category_number);
			if (query.uniqueResult() != null) {
				ingredientsCategory = (BeanIngredientsCategory) query.uniqueResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

	public List<BeanIngredientsCategory> loadAllCategory() throws BaseException {
		List<BeanIngredientsCategory> ingredientsCategories = new ArrayList<BeanIngredientsCategory>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanIngredientsCategory";
			org.hibernate.query.Query query = session.createQuery(hql);
			ingredientsCategories = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return ingredientsCategories;
	}

	// 添加食材信息
	public BeanIngredientsInformation addIngredientsInformation(String ingredients_name, double ingredients_price,
			String ingredients_description, String ingredients_specification,
			BeanIngredientsCategory ingredientsCategory) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		BeanIngredientsInformation ingredientsInformation = new BeanIngredientsInformation();
		try {
			ingredientsInformation.setIngredients_name(ingredients_name);
			ingredientsInformation.setIngredients_price(ingredients_price);
			ingredientsInformation.setIngredients_quantity(0);
			ingredientsInformation.setIngredients_description(ingredients_description);
			ingredientsInformation.setIngredients_specification(ingredients_specification);
			ingredientsInformation.setCategory_number(ingredientsCategory.getCategory_number());
			session.save(ingredientsInformation);
			transaction.commit();
		} catch (SessionException e) {
			throw new BusinessException("添加食材信息失败");
		} finally {
			if (session != null) {
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

	public void deleteIngredientsInformation(BeanIngredientsInformation ingredientsInformation) throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		try {
			if (ingredientsInformation.getIngredients_quantity() != 0) {
				throw new BusinessException("该食材还有余量,无法删除");
			}

			session.delete(ingredientsInformation);

			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	
	public BeanIngredientsInformation changIngredients(BeanIngredientsInformation information, String newDescription) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			information.setIngredients_description(newDescription);
			session.update(information);
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return information;
	}

//	通过名字找食材
	public BeanIngredientsInformation loadIngredient(String name) throws BaseException {
		BeanIngredientsInformation ingredientsInformations = new BeanIngredientsInformation();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanIngredientsInformation where ingredients_name=:name";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setString("name", name);
			if (query.uniqueResult() != null) {
				ingredientsInformations = (BeanIngredientsInformation) query.uniqueResult();
			}
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

//	通过编号找食材
	public BeanIngredientsInformation loadIngredient(int number) throws BaseException {
		BeanIngredientsInformation ingredientsInformations = new BeanIngredientsInformation();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanIngredientsInformation where ingredients_number=:number";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setInteger("number", number);
			if (query.uniqueResult() != null) {
				ingredientsInformations = (BeanIngredientsInformation) query.uniqueResult();
			}
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

	public List<String> loadAllIngredientsName() {
		List<String> ingredientsInformations = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "select ingredients_name from BeanIngredientsInformation";
			org.hibernate.query.Query query = session.createQuery(hql);
			ingredientsInformations = query.list();
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

	public List<BeanIngredientsInformation> loadAllIngredients(BeanIngredientsCategory ingredientsCategory)
			throws BaseException {
		List<BeanIngredientsInformation> ingredientsInformations = new ArrayList<BeanIngredientsInformation>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanIngredientsInformation where category_number=:num";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setInteger("num", ingredientsCategory.getCategory_number());
			ingredientsInformations = query.list();
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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

	public BeanIngredientsInformation addBeanIngredientsQuantity(BeanIngredientsInformation ingredientsInformation,
			int quantity) {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			quantity += ingredientsInformation.getIngredients_quantity();
			ingredientsInformation.setIngredients_quantity(quantity);
			session.saveOrUpdate(ingredientsInformation);
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
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
}
