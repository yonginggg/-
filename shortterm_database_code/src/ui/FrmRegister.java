package ui;

import java.awt.BorderLayout;
//import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import kitchen.control.*;
import kitchen.model.*;
import kitchen.util.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("注册");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelUser = new JLabel("用户名：");
	private JLabel labelPwd = new JLabel("密码1：");
	private JLabel labelPwd2 = new JLabel("密码2：");
	private JLabel labelName = new JLabel("姓名：");
	private JLabel labelSex = new JLabel("性别：");

	private JLabel labelPhoneNumber = new JLabel("手机：");
	private JLabel labelEmail = new JLabel("邮箱：");
	private JLabel labelCity = new JLabel("城市：");
	
//	private JLabel labelUserType = new JLabel("是否为管理员(yes/no):");// (是/否)
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField edtUserName = new JTextField(20);
//	private JTextField edtUserSex = new JTextField(20);
	private JComboBox cmbUserSex = new JComboBox(new String[] { "men", "women" });
	private JTextField edtUserPhoneNumber = new JTextField(20);
	private JTextField edtUserEmail = new JTextField(20);
	private JTextField edtUserCity = new JTextField(20);
	
//	private JPasswordField edtUserType = new JPasswordField(20);// 是否管理员

	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
//		workPane.add(labelUserType);// 新增按钮
//		workPane.add(edtUserType);// 新增按钮
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		workPane.setLayout(null);
		labelName.setBounds(6, 103, 45, 18);
		workPane.add(labelName);
		edtUserName.setBounds(119, 100, 166, 24);
		workPane.add(edtUserName);
		labelPwd2.setBounds(6, 72, 53, 18);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(119, 69, 166, 24);
		workPane.add(edtPwd2);
		labelCity.setBounds(6, 227, 45, 18);
		workPane.add(labelCity);
		edtUserCity.setBounds(119, 224, 166, 24);
		workPane.add(edtUserCity);
		labelEmail.setBounds(6, 196, 45, 18);
		workPane.add(labelEmail);
		edtUserEmail.setBounds(119, 193, 166, 24);
		workPane.add(edtUserEmail);
		labelPhoneNumber.setBounds(6, 165, 45, 18);
		workPane.add(labelPhoneNumber);
		labelSex.setBounds(6, 134, 45, 18);
		workPane.add(labelSex);
		cmbUserSex.setBounds(119, 131, 166, 24);
		workPane.add(cmbUserSex);
		edtUserPhoneNumber.setBounds(119, 162, 166, 24);
		workPane.add(edtUserPhoneNumber);
		labelPwd.setBounds(6, 43, 53, 18);
		workPane.add(labelPwd);
		edtPwd.setBounds(119, 40, 166, 24);
		workPane.add(edtPwd);
		labelUser.setBounds(6, 11, 60, 18);
		workPane.add(labelUser);
		edtUserId.setBounds(119, 8, 166, 24);
		workPane.add(edtUserId);
//		this.setSize(300, 180);
		this.setSize(367, 350);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);

		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk) {
			String user_id = this.edtUserId.getText();
			String pwd1 = new String(this.edtPwd.getPassword());
			String pwd2 = new String(this.edtPwd2.getPassword());
			String user_name = this.edtUserName.getText();
			String user_sex = this.cmbUserSex.getSelectedItem().toString();
			int user_phone_number = new Integer(this.edtUserPhoneNumber.getText());
			String user_email = this.edtUserEmail.getText();
			String user_city = this.edtUserCity.getText();
			try {
				UserManager userManager = new UserManager();
				BeanUser user = userManager.reg(user_name, user_sex, user_id, pwd1 , pwd2, user_phone_number, user_email, user_city);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}

	}

}
