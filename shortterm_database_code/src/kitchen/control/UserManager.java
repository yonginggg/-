package kitchen.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import kitchen.util.BusinessException;
import kitchen.util.HibernateUtil;
public class UserManager {
	public static BeanUser currentUser=null;
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	public static Session getSession(){
		Session session = sessionFactory.openSession();
        return session;
	}
	
	public BeanUser reg(String user_name, String user_sex, String user_id,
		String user_password1, String user_password2, int user_phone_number,
		String user_email, String user_city) throws BaseException{
		if(!user_password1.equals(user_password2)) {
			throw new BusinessException("两次输入的密码不一致");
		}
		else if (user_password1==null||"".equals(user_password1)) {
			throw new BusinessException("密码不能为空");
		}
		else if (user_sex!="men"&&user_sex!="women") {
			throw new BusinessException("性别只能为men或women");
		}
		Session session = getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		BeanUser beanUser = new BeanUser();
		try {
			//查找用户是否已经存在
			List<BeanUser> users = session.createQuery("from BeanUser").list();
			for (BeanUser u:users) {
				if(user_id.equals(u.getUser_id())) {
					throw new BusinessException("用户已经存在"); 
				}
			}
			
			//插入用户
			
			beanUser.setUser_city(user_city);
			beanUser.setUser_email(user_email);
			beanUser.setUser_id(user_id);
			beanUser.setUser_name(user_name);
			beanUser.setUser_password(user_password1);
			beanUser.setUser_phone_number(user_phone_number);
			beanUser.setUser_register(new Timestamp(System.currentTimeMillis()));
			beanUser.setUser_sex(user_sex);
			
			session.save(beanUser);
			transaction.commit();
		}catch (SessionException e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
		return beanUser;
		
	}
	
	public static BeanUser login(String user_id, String user_password) throws BaseException {
		if (user_password==null||"".equals(user_password)) {
			throw new BusinessException("密码不能为空");
		}
		else if (user_id==null||"".equals(user_id)) {
			throw new BusinessException("用户名不能为空");
		}
		
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		BeanUser beanUser = new BeanUser();
		
		try {
			String hql = "from BeanUser u where u.user_id=:user_id";
			Query query = session.createQuery(hql);
			query.setString("user_id",user_id);
			beanUser = (BeanUser) query.uniqueResult();
			if(beanUser==null) {
				throw new BaseException("用户不存在");
			}
			//比较密码
			if(beanUser.getUser_password().equals(user_password)==false) {
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

		return beanUser;
	}
	
	public void changePwd(BeanUser user, String oldPwd, String newPwd,String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd)) throw new BusinessException("新的密码不能为空");
		if(newPwd2==null || "".equals(newPwd2)) throw new BusinessException("新的密码2不能为空");
		if(!newPwd.equals(newPwd2) ) throw new BusinessException("两次输入的密码不一致");
		BeanUser beanUser = new BeanUser();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			beanUser = session.get(BeanUser.class, user.getUser_number());
			if(beanUser==null) {
				throw new BusinessException("登陆账号不 存在");
			}
			if(!oldPwd.equals(beanUser.getUser_password())) {
				throw new BusinessException("原始密码错误");
			}

			beanUser.setUser_password(newPwd);
			session.update(beanUser);
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
	
	public void deleteUser(BeanUser user)throws BaseException{
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		try {
			//删除语句
			String hql = "delete BeanUser u where u.user_id =:user_id";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setString("user_id", user.getUser_id());
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
	
	public List<BeanUser> loadAll()throws BaseException{
		List<BeanUser> users=new ArrayList<BeanUser>();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql ="from BeanUser";
			org.hibernate.query.Query query = session.createQuery(hql);
			users = query.list();
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
		return users;
	}
	
	public BeanUser loadUser(String user_id) throws BaseException{
		BeanUser user = new BeanUser();
		Session session = HibernateUtil.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			String hql = "from BeanUser where user_id =:"+user_id;
			Query query = session.createQuery(hql);
			if(query.uniqueResult()!=null) {
				user = (BeanUser) query.uniqueResult();
			}
		}catch (Exception e) {
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
		return user;
		
	}
}
