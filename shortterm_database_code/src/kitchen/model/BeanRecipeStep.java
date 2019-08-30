package kitchen.model;

public class BeanRecipeStep {
	
	public static BeanRecipeInformation currentRecipe= null;
	public static final String[] tblStepsTitle = { "步骤序号", "步骤描述"};
	public String getCell(int col) {
		if (col == 0)
			return String.valueOf(getStep_number());
		else if (col == 1)
			return getStep_description();
		else
			return "";
		
	}
	
	
	private int recipe_step_number;
	private int recipe_number;
	private int step_number;
	private String step_description;
	
	public int getRecipe_step_number() {
		return recipe_step_number;
	}
	public void setRecipe_step_number(int recipe_step_number) {
		this.recipe_step_number = recipe_step_number;
	}
	public int getRecipe_number() {
		return recipe_number;
	}
	public void setRecipe_number(int recipe_number) {
		this.recipe_number = recipe_number;
	}
	public int getStep_number() {
		return step_number;
	}
	public void setStep_number(int step_number) {
		this.step_number = step_number;
	}
	public String getStep_description() {
		return step_description;
	}
	public void setStep_description(String step_description) {
		this.step_description = step_description;
	}

	
	
}
