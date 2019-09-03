package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.jmx.snmp.Timestamp;

import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsOrderManager;
import kitchen.control.IngredientsProcurementManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsInformation;
import kitchen.util.BaseException;

public class FrmAddProcurement extends JDialog implements ActionListener{
	public BeanIngredientsInformation currentIngredients = null;
	public BeanAdministratorInformation currentAdministrator = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelQuantity = new JLabel("数量:");
	
	private JTextField edtQuantity = new JTextField(20);
	
	public FrmAddProcurement(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelQuantity);
		workPane.add(edtQuantity);

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
			double quantity = Double.parseDouble(this.edtQuantity.getText());
			IngredientsProcurementManager ingredientsProcurementManager = new IngredientsProcurementManager();
			try {
				ingredientsProcurementManager.addIngredientsProcurement(currentIngredients, quantity, currentAdministrator);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}

	}
}
