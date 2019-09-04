package kitchen.control;

import org.hibernate.Session;
import org.hibernate.SessionException;

import kitchen.model.BeanDiscount;
import kitchen.model.BeanIngredientsCategory;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class DiscountManager {
	public BeanDiscount load() throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		BeanDiscount beanDiscount = (BeanDiscount)session.get(BeanDiscount.class,1);
		return beanDiscount;
	}
	public BeanDiscount addDiscount(double discount)throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		BeanDiscount beanDiscount = (BeanDiscount)session.get(BeanDiscount.class,1);
		
		try {
			beanDiscount.setDiscount(discount);

			session.saveOrUpdate(beanDiscount);
			transaction.commit();
		} catch (SessionException e) {
			throw new BusinessException("添加折扣失败");
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

//		BeanDiscount.currentDiscount = beanDiscount;
		return beanDiscount;
	}
	
}
