package kitchen.model;

public class BeanAdministratorInformation {
//	  `administrator_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
//	  `administrator_name` int(11) NOT NULL COMMENT '员工姓名',
//	  `administrator_password` int(50) NOT NULL COMMENT '密码',
	
	private int administrator_number;
	private int administrator_name;
	private int administrator_password;
	
	public int getAdministrator_number() {
		return administrator_number;
	}
	public void setAdministrator_number(int administrator_number) {
		this.administrator_number = administrator_number;
	}
	public int getAdministrator_name() {
		return administrator_name;
	}
	public void setAdministrator_name(int administrator_name) {
		this.administrator_name = administrator_name;
	}
	public int getAdministrator_password() {
		return administrator_password;
	}
	public void setAdministrator_password(int administrator_password) {
		this.administrator_password = administrator_password;
	}

}
