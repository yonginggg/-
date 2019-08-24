package kitchen.model;

public class BeanRecipeStep {
	
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
