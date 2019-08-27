package kitchen.model;

import java.sql.Timestamp;

public class BeanUser {
	
	public static BeanUser currentUser = null;
	public static final String[] tblUserTitle = { "用户id", "用户密码", "用户姓名", "用户性别", "用户手机","用户邮箱", "用户城市","注册时间"};
	public String getCell(int col) {
		if (col == 0)
			return getUser_id();
		else if (col == 1)
			return getUser_password();
		else if (col == 2)
			return getUser_name();
		else if(col == 3)
			return getUser_sex();
		else if(col == 4)
			return String.valueOf(getUser_phone_number());
		else if(col == 5)
			return getUser_email();
		else if(col == 6)
			return getUser_city();
		else if(col == 7)
			return String.valueOf(getUser_register());
		else
			return "";
	}
	
	private String user_id;
	private int user_number;
	private String user_name;
	private String user_sex;
	private String user_password;
	private int user_phone_number;
	private String user_email;
	private String user_city;
	private Timestamp user_register;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(int user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Timestamp getUser_register() {
		return user_register;
	}
	public void setUser_register(Timestamp user_register) {
		this.user_register = user_register;
	}

	
}
