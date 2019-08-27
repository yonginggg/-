package kitchen.control;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.query.Query;

import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class AdministratorManager {
	public static BeanAdministratorInformation currentAdministrator=null;
	
	public static BeanAdministratorInformation login(int administrator_number, String administrator_password) throws BaseException {
		if (administrator_password==null||"".equals(administrator_password)) {
			throw new BusinessException("密码不能为空");
		}
		else if ("".equals(administrator_number)) {
			throw new BusinessException("用户名不能为空");
		}
		
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanAdministratorInformation administratorInformation = new BeanAdministratorInformation();
		try {
			String hql = "from BeanAdministratorInformation where administrator_number=:num";
			Query query = session.createQuery(hql);
			query.setInteger("num", administrator_number);
			administratorInformation = (BeanAdministratorInformation) query.uniqueResult();
			if(administratorInformation==null) {
				throw new BaseException("管理员不存在");
			}
			//比较密码
			if(administratorInformation.getAdministrator_password().equals(administrator_password)==false) {
				throw new BaseException("密码错误");
			}
			
			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("登录失败");
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

		return administratorInformation;
	}
	
	public BeanAdministratorInformation loadAdministrator(int administrator_number) throws BaseException{
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanAdministratorInformation administratorInformation = new BeanAdministratorInformation();
		try {
			String hql = "from BeanAdministratorInformation where administrator_number=:"+String.valueOf(administrator_number);
			Query query = session.createQuery(hql);
			administratorInformation = (BeanAdministratorInformation) query.uniqueResult();
			if(administratorInformation==null) {
				throw new BaseException("管理员不存在");
			}
			
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

		return administratorInformation;
		
	}
}
