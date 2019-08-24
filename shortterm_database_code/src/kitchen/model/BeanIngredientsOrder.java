package kitchen.model;

import java.sql.Timestamp;

public class BeanIngredientsOrder {
//	  `order_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
//	  `user_number` int(11) NOT NULL COMMENT '用户编号',
//	  `order_required_time` datetime NOT NULL COMMENT '要求送达时间',
//	  `order_delivery_address` varchar(50) NOT NULL COMMENT '配送地址',
//	  `user_phone_number` int(11) NOT NULL COMMENT '联系电话',
//	  `order_status` varchar(50) NOT NULL COMMENT '订单状态',
	
	private int order_number;
	private int user_number;
	private Timestamp order_required_time;
	private String order_delivery_address;
	private int user_phone_number;
	private String order_status;
	
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public Timestamp getOrder_required_time() {
		return order_required_time;
	}
	public void setOrder_required_time(Timestamp order_required_time) {
		this.order_required_time = order_required_time;
	}
	public String getOrder_delivery_address() {
		return order_delivery_address;
	}
	public void setOrder_delivery_address(String order_delivery_address) {
		this.order_delivery_address = order_delivery_address;
	}
	public int getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(int user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	
}
