package kitchen.model;

public class BeanRecipeEvaluation {
//	  `recipe_evaluation_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜谱评价表编号',
//	  `recipe_number` int(11) NOT NULL COMMENT '菜谱编号',
//	  `user_number` int(11) NOT NULL COMMENT '用户编号',
//	  `evaluation_conten` varchar(50) NOT NULL COMMENT '评价内容',
//	  `evaluation_browse_sign` int(11) NOT NULL COMMENT '浏览标志',
//	  `evaluation_collection_sign` int(11) NOT NULL COMMENT '收藏标志',
//	  `evaluation_grade` double NOT NULL COMMENT '评分',
	
	public static BeanRecipeEvaluation currentEvaluation = null;
	public static final String[] tblEvaluationsTitle = { "评价用户编号", "评价内容", "浏览标志",
			"收藏标志","评分"};
	public String getCell(int col) {
		if (col == 0)
			return String.valueOf(getUser_number());
		else if (col == 1)
			return getEvaluation_conten();
		else if (col == 2)
			return String.valueOf(getEvaluation_browse_sign());
		else if(col == 3)
			return getEvaluation_collection_sign();
		else if (col == 4) {
			return String.valueOf(getEvaluation_grade());
		}
		else
			return "";
	}
	
	private int recipe_evaluation_number;
	private int recipe_number;
	private int user_number;
	private String evaluation_conten;
	private int evaluation_browse_sign;
	private String evaluation_collection_sign;
	private double evaluation_grade;
	
	public int getRecipe_evaluation_number() {
		return recipe_evaluation_number;
	}
	public void setRecipe_evaluation_number(int recipe_evaluation_number) {
		this.recipe_evaluation_number = recipe_evaluation_number;
	}
	public int getRecipe_number() {
		return recipe_number;
	}
	public void setRecipe_number(int recipe_number) {
		this.recipe_number = recipe_number;
	}
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public String getEvaluation_conten() {
		return evaluation_conten;
	}
	public void setEvaluation_conten(String evaluation_conten) {
		this.evaluation_conten = evaluation_conten;
	}
	public int getEvaluation_browse_sign() {
		return evaluation_browse_sign;
	}
	public void setEvaluation_browse_sign(int evaluation_browse_sign) {
		this.evaluation_browse_sign = evaluation_browse_sign;
	}
	public String getEvaluation_collection_sign() {
		return evaluation_collection_sign;
	}
	public void setEvaluation_collection_sign(String evaluation_collection_sign) {
		this.evaluation_collection_sign = evaluation_collection_sign;
	}
	public double getEvaluation_grade() {
		return evaluation_grade;
	}
	public void setEvaluation_grade(double evaluation_grade) {
		this.evaluation_grade = evaluation_grade;
	}

}
