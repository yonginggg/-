package kitchen.model;

public class BeanIngredientsCategory {
	public static BeanIngredientsCategory currentCategory = null;
	public static final String[] tblCategoryTitle = { "类别名称", "类别描述"};
	public String getCell(int col) {
		if (col == 0)
			return getCategory_name();
		else if (col == 1)
			return getCategory_description();
		else
			return "";
	}
	
	private int category_number;
	private String category_name;
	private String category_description;
	
	public int getCategory_number() {
		return category_number;
	}
	public void setCategory_number(int category_number) {
		this.category_number = category_number;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	
}
