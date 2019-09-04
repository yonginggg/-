package kitchen.control;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.query.Query;

import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;

public class AdministratorManager {
//	当前管理员
//	public static BeanAdministratorInformation currentAdministrator = null;

//	管理员登录
	public static BeanAdministratorInformation login(int administrator_number, String administrator_password)
			throws BaseException {
		if (administrator_password == null || "".equals(administrator_password)) {
			throw new BusinessException("密码不能为空");
		} else if ("".equals(administrator_number)) {
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
			if (administratorInformation == null) {
				throw new BaseException("管理员不存在");
			}
			// 比较密码
			if (administratorInformation.getAdministrator_password().equals(administrator_password) == false) {
				throw new BaseException("密码错误");
			}

			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("登录失败");
		} finally {
			if (session != null) {
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

	public static BeanAdministratorInformation login(String administrator_number, String administrator_password)
			throws BaseException {
		if (administrator_password == null || "".equals(administrator_password)) {
			throw new BusinessException("密码不能为空");
		} else if ("".equals(administrator_number)||administrator_password==null) {
			throw new BusinessException("用户名不能为空");
		}

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		BeanAdministratorInformation administratorInformation = new BeanAdministratorInformation();
		try {
			String hql = "from BeanAdministratorInformation where administrator_number=:num";
			Query query = session.createQuery(hql);
			query.setString("num", administrator_number);
			administratorInformation = (BeanAdministratorInformation) query.uniqueResult();
			if (administratorInformation == null) {
				throw new BaseException("管理员不存在");
			}
			// 比较密码
			if (administratorInformation.getAdministrator_password().equals(administrator_password) == false) {
				throw new BaseException("密码错误");
			}

			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("登录失败");
		} finally {
			if (session != null) {
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
	
//	查询管理员
	public BeanAdministratorInformation loadAdministrator(int administrator_number) throws BaseException {
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		BeanAdministratorInformation administratorInformation = new BeanAdministratorInformation();
		try {
			String hql = "from BeanAdministratorInformation where administrator_number=:num";
			Query query = session.createQuery(hql);
			query.setInteger("num", administrator_number);
			administratorInformation = (BeanAdministratorInformation) query.uniqueResult();
			if (administratorInformation == null) {
				throw new BaseException("管理员不存在");
			}

			transaction.commit();
		} catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException("查询失败");
		} finally {
			if (session != null) {
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

//	查询表中所有管理员
	public List<BeanAdministratorInformation> loadAll() throws BaseException {
		List<BeanAdministratorInformation> administrators = new ArrayList<BeanAdministratorInformation>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanAdministratorInformation";
			org.hibernate.query.Query query = session.createQuery(hql);
			administrators = query.list();
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
		return administrators;
	}

	public BeanAdministratorInformation changAdministratorPwd(BeanAdministratorInformation administratorInformation,
			String oldPwd, String newPwd, String newPwd2) throws BaseException {

		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			if("".equals(oldPwd)||"".equals(newPwd)||"".equals(newPwd2)||oldPwd==null||newPwd==null||newPwd2==null) {
				throw new BusinessException("输入不能为空");
			}
			if (!oldPwd.equals(administratorInformation.getAdministrator_password())) {
				throw new BusinessException("原始密码错误");
			}
			if(!newPwd.equals(newPwd2)) {
				throw new BusinessException("两次密码不相同");
			}

			administratorInformation.setAdministrator_password(newPwd);
			session.update(administratorInformation);
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
		return administratorInformation;
	}
}
