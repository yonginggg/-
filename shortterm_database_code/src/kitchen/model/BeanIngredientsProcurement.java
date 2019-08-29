package kitchen.model;

public class BeanIngredientsProcurement {
//	  `procurement_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购单编号',
//	  `ingredients_number` int(11) NOT NULL COMMENT '食材编号',
//	  `quantity` int(50) NOT NULL COMMENT '数量',
//	  `procurement_status` varchar(50) NOT NULL COMMENT '状态',
//	  `administrator_number` int(11) NOT NULL COMMENT '员工编号',
	public static BeanIngredientsProcurement currentProcurement = null;
	private int procurement_number;
	private int ingredients_number;
	private int quantity;
	private String procurement_status;
	private int administrator_number;
	
	public int getProcurement_number() {
		return procurement_number;
	}
	public void setProcurement_number(int procurement_number) {
		this.procurement_number = procurement_number;
	}
	public int getIngredients_number() {
		return ingredients_number;
	}
	public void setIngredients_number(int ingredients_number) {
		this.ingredients_number = ingredients_number;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProcurement_status() {
		return procurement_status;
	}
	public void setProcurement_status(String procurement_status) {
		this.procurement_status = procurement_status;
	}
	public int getAdministrator_number() {
		return administrator_number;
	}
	public void setAdministrator_number(int administrator_number) {
		this.administrator_number = administrator_number;
	}

}
