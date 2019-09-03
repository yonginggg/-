package ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeStep;
import kitchen.util.*;

public class FrmAddStep extends JDialog implements ActionListener {

	public BeanRecipeInformation curRecipe = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
//	private JLabel labelNumber = new JLabel("序号：");
	private JLabel labelDescription = new JLabel("描述：");

//	private JTextField edtNumber = new JTextField(20);
	private JTextField edtDescription = new JTextField(20);

	public FrmAddStep(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
//		workPane.add(labelNumber);
//		workPane.add(edtNumber);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
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
			if (JOptionPane.showConfirmDialog(this, "是否确认取消?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				return;
			}

		} else if (e.getSource() == this.btnOk) {
			if (JOptionPane.showConfirmDialog(this, "是否确认添加步骤?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//				int number = Integer.parseInt(this.edtNumber.getText());
				String description = this.edtDescription.getText();

				RecipeManager recipeManager = new RecipeManager();
				try {
//					recipeManager.addRecipeStep(curRecipe, number, description);
					recipeManager.addRecipeStep(curRecipe, description);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}

	}
}
