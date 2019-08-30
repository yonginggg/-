package kitchen.model;

public class BeanRecipeMaterial { 
	public static BeanIngredientsInformation currentIngredients = null;
	public static final String[] tblMaterialsTitle = { "食材编号", "数量", "单位"};
	public String getCell(int col) {
		if (col == 0)
			return String.valueOf(getIngredients_number());
		else if (col == 1)
			return String.valueOf(getQuantity());
		else if (col == 2)
			return getUnit();
		else if(col == 3)
			return getUnit();
		else
			return "";
	}
	
	private int recipe_material_number;
	private int recipe_number;
	private int ingredients_number;
	private int quantity;
	private String unit;
	
	public int getRecipe_material_number() {
		return recipe_material_number;
	}
	public void setRecipe_material_number(int recipe_material_number) {
		this.recipe_material_number = recipe_material_number;
	}
	public int getRecipe_number() {
		return recipe_number;
	}
	public void setRecipe_number(int recipe_number) {
		this.recipe_number = recipe_number;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
