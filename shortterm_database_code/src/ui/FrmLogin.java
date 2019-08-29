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

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kitchen.model.*;
import kitchen.util.*;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import kitchen.control.*;
import kitchen.model.*;
import kitchen.util.*;

public class FrmLogin extends JDialog implements ActionListener {
	static String userType = "用户属性";// 用户/ 管理员
	static String id="id";
	
	private BeanUser user = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("登陆");
	private JButton btnCancel = new JButton("退出");
	private JButton btnRegister = new JButton("注册");

	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JComboBox cmbUsertype = new JComboBox(new String[] { "管理员", "用户" });
	private final JLabel label = new JLabel("类型:");

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);

		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelUser.setBounds(8, 8, 45, 18);
		workPane.add(labelUser);
		edtUserId.setBounds(108, 5, 166, 24);
		workPane.add(edtUserId);
		labelPwd.setBounds(8, 52, 45, 18);
		workPane.add(labelPwd);
		edtPwd.setBounds(108, 49, 166, 24);
		workPane.add(edtPwd);
		cmbUsertype.setBounds(108, 107, 166, 24);
		workPane.add(cmbUsertype);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		label.setBounds(8, 110, 72, 18);
		
		workPane.add(label);
		this.setSize(323, 274);

		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnLogin) {
			UserManager userManager = new UserManager();
			AdministratorManager administratorManager = new AdministratorManager();

			if (this.cmbUsertype.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "请选择账号类别", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String userid = this.edtUserId.getText();
			id = userid;
			String pwd = this.edtPwd.getText();
			String usertype = this.cmbUsertype.getSelectedItem().toString();
			userType = usertype;
			try {
				if (usertype == "用户") {
					BeanUser user = userManager.login(userid, pwd);
//					userManager.currentUser = user;
					BeanUser.currentUser = user;
					setVisible(false);
				} else if (usertype == "管理员") {
					BeanAdministratorInformation administratorInformation = administratorManager.login(Integer.parseInt(userid), pwd);
//					administratorManager.currentAdministrator = administratorInformation;
					BeanAdministratorInformation.currentAdministrator = administratorInformation;
					setVisible(false);
				}
			} catch (BaseException e1) {
				this.user = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if (e.getSource() == this.btnRegister) {
			FrmRegister dlg = new FrmRegister(this, "注册", true);
			dlg.setVisible(true);
		}
	}

}
