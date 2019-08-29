package kitchen.model;

public class BeanRecipeInformation {
//	  `recipe_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜谱编号',
//	  `recipe_name` varchar(50) NOT NULL COMMENT '菜谱名称',
//	  `user_number` int(11) NOT NULL COMMENT '贡献用户',
//	  `recipe_description` varchar(255) NOT NULL COMMENT '菜谱描述',
//	  `recipe_overall_rating` double NOT NULL COMMENT '综合评分',
//	  `recipe_collection_number` int(11) NOT NULL COMMENT '收藏数量',
//	  `recipe_views_number` int(11) NOT NULL COMMENT '浏览次数',
	
	public static BeanRecipeInformation currentRecipe= null;
	public static final String[] tblRecipeTitle = { "类别编号", "菜谱名称", "菜谱描述",
			"综合评分","收藏数量","浏览次数"};
	public String getCell(int col) {
		if (col == 0)
			return String.valueOf(getRecipe_number());
		else if (col == 1)
			return getRecipe_name();
		else if (col == 2)
			return getRecipe_description();
		else if (col == 3)
			return String.valueOf(getRecipe_overall_rating());
		else if (col == 4)
			return String.valueOf(getRecipe_collection_number());
		else if (col == 5)
			return String.valueOf(getRecipe_views_number());
		else
			return "";
		
	}
	
	private int recipe_number;
	private String recipe_name;
	private int user_number;
	private String recipe_description;
	private double recipe_overall_rating;
	private int recipe_collection_number;
	private int recipe_views_number;
	
	public int getRecipe_number() {
		return recipe_number;
	}
	public void setRecipe_number(int recipe_number) {
		this.recipe_number = recipe_number;
	}
	public String getRecipe_name() {
		return recipe_name;
	}
	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public String getRecipe_description() {
		return recipe_description;
	}
	public void setRecipe_description(String recipe_description) {
		this.recipe_description = recipe_description;
	}
	public double getRecipe_overall_rating() {
		return recipe_overall_rating;
	}
	public void setRecipe_overall_rating(double recipe_overall_rating) {
		this.recipe_overall_rating = recipe_overall_rating;
	}
	public int getRecipe_collection_number() {
		return recipe_collection_number;
	}
	public void setRecipe_collection_number(int recipe_collection_number) {
		this.recipe_collection_number = recipe_collection_number;
	}
	public int getRecipe_views_number() {
		return recipe_views_number;
	}
	public void setRecipe_views_number(int recipe_views_number) {
		this.recipe_views_number = recipe_views_number;
	}

}
