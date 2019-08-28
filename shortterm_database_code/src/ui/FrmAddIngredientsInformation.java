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
import kitchen.model.BeanIngredientsCategory;
import kitchen.util.*;

public class FrmAddIngredientsInformation extends JDialog implements ActionListener{
	
	public BeanIngredientsCategory ingredientsCategory = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("名称：");
	private JLabel labelPrice = new JLabel("价格：");
	private JLabel labelDescription = new JLabel("描述：");
	private JLabel labelSpecification = new JLabel("单位：");

	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtDescription = new JTextField(20);
	private JTextField edtSpecification = new JTextField(20);
	
	public FrmAddIngredientsInformation(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelPrice);
		workPane.add(edtPrice);	
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		workPane.add(labelSpecification);
		workPane.add(edtSpecification);
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
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			String name = this.edtName.getText();
			double price = Double.parseDouble(this.edtPrice.getText());
			String description = this.edtDescription.getText();
			String specification = this.edtSpecification.getText();
			
			IngredientsManager ingredientsManager = new IngredientsManager();
			
			try {
				ingredientsManager.addIngredientsInformation(name, price, description, specification, ingredientsCategory);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}

