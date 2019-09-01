package kitchen.model;

public class BeanOrderDetail {
//	  `order_detail_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情表编号',
//	  `order_number` int(11) NOT NULL COMMENT '订单编号',
//	  `ingredients_number` int(11) NOT NULL COMMENT '食材编号',
//	  `quantity` int(50) NOT NULL COMMENT '数量',
//	  `price` double NOT NULL COMMENT '价格',
//	  `order_discount` double NOT NULL COMMENT '折扣',
	
	public static BeanOrderDetail currentDetail = null;
	public static final String[] tblDetailTitle = { "食材编号", "数量","价格","折扣"};
	public String getCell(int col) {
		if (col == 0)
			return String.valueOf(getIngredients_number());
		else if (col == 1)
			return String.valueOf(getQuantity());
		else if (col == 2)
			return String.valueOf(getPrice());
		else if (col == 3)
			return String.valueOf(getOrder_discount());
		else
			return "";
	}
	private int order_detail_number;
	private int order_number;
	private int ingredients_number;
	private int quantity;
	private double price;
	private double order_discount;
	
	public int getOrder_detail_number() {
		return order_detail_number;
	}
	public void setOrder_detail_number(int order_detail_number) {
		this.order_detail_number = order_detail_number;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getOrder_discount() {
		return order_discount;
	}
	public void setOrder_discount(double order_discount) {
		this.order_discount = order_discount;
	}

	
	
}
