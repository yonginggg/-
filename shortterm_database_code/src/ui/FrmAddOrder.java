package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kitchen.control.IngredientsOrderManager;
import kitchen.control.RecipeManager;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;

public class FrmAddOrder extends JDialog implements ActionListener {
//	static int order_num = 3;//全局常量
	BeanRecipeInformation currentRecipe = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("退出");
	private JLabel labelTime = new JLabel("时间：");
	private JLabel labelAddress = new JLabel("地址：");
	private JLabel labelPhone = new JLabel("手机：");
	private JTextField edtTime = new JTextField(20);
	private JTextField edtAddress = new JTextField(20);
	private JTextField edtPhone = new JTextField(20);

	public FrmAddOrder(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelTime.setBounds(22, 8, 45, 18);
		workPane.add(labelTime);
		edtTime.setBounds(105, 5, 166, 24);
		workPane.add(edtTime);
		labelAddress.setBounds(22, 39, 45, 18);
		workPane.add(labelAddress);
		edtAddress.setBounds(105, 36, 166, 24);
		workPane.add(edtAddress);
		labelPhone.setBounds(22, 70, 45, 18);
		workPane.add(labelPhone);
		edtPhone.setBounds(105, 67, 166, 24);
		workPane.add(edtPhone);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(329, 193);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnOk) {
//			order_num++;
			IngredientsOrderManager orderManager = new IngredientsOrderManager();
			String address = this.edtAddress.getText();
			String time = this.edtTime.getText();
			int phone = Integer.parseInt(this.edtPhone.getText());
			RecipeManager recipeManager = new RecipeManager();
			try {
//				添加订单
				orderManager.addIngredientsOrder(BeanUser.currentUser, time, address, phone);
//				添加订单详情
				int maxOrderNumber = orderManager.maxOrder();
				List<BeanRecipeMaterial> materials = recipeManager.loadAllMaterials(currentRecipe);
				for(int i=0; i<materials.size(); i++) {
					orderManager.addDeatil(maxOrderNumber, materials.get(i), 0.9);
				}
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			

			
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		}
	}

}
