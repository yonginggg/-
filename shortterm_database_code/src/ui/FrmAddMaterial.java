package ui;

import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JDialog;

import kitchen.control.IngredientsManager;
import kitchen.control.RecipeManager;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeStep;
import kitchen.util.*;

public class FrmAddMaterial extends JDialog implements ActionListener {

	public BeanRecipeInformation curRecipe = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("食材");
	private JLabel labelquantity = new JLabel("数量");

	List<String> ingredientsInformations = new IngredientsManager().loadAllIngredientsName();
	String[] ingredientName = ingredientsInformations.toArray(new String[ingredientsInformations.size()]);
	private JComboBox cmbMaterial = new JComboBox(ingredientName);

	
	private JTextField edtQuantity = new JTextField(20);

	public FrmAddMaterial(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelName.setBounds(14, 8, 30, 18);
		workPane.add(labelName);
		cmbMaterial.setBounds(109, 5, 166, 24);
		workPane.add(cmbMaterial);
		labelquantity.setBounds(14, 46, 30, 18);
		workPane.add(labelquantity);
		edtQuantity.setBounds(109, 43, 166, 24);
		workPane.add(edtQuantity);
		String ingredientUnit=null;
		try {
			ingredientUnit = new IngredientsManager().loadIngredient(this.cmbMaterial.getSelectedItem().toString()).getIngredients_specification();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(336, 198);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			String name = this.cmbMaterial.getSelectedItem().toString();
			int quantity = Integer.parseInt(this.edtQuantity.getText());
			String unit = null;
			try {
				unit = new IngredientsManager().loadIngredient(name).getIngredients_specification();
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			RecipeManager recipeManager = new RecipeManager();
			IngredientsManager ingredientsManager = new IngredientsManager();
			try {
				recipeManager.addRecipeMaterial(curRecipe,
						ingredientsManager.loadIngredient(name).getIngredients_number(), quantity, unit);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
