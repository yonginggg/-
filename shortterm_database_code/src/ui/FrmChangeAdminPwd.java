package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import kitchen.util.*;
import kitchen.model.*;
import kitchen.control.*;
import java.awt.Font;

public class FrmChangeAdminPwd extends JDialog implements ActionListener {

	public BeanAdministratorInformation currentAdministrator = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelPwdOld = new JLabel("原密码：");
	private JLabel labelPwd = new JLabel("新密码：");
	private JLabel labelPwd2 = new JLabel("新密码：");
	private JPasswordField edtPwdOld = new JPasswordField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);

	public FrmChangeAdminPwd(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOk.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(this.btnOk);
		btnCancel.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelPwdOld.setFont(new Font("宋体", Font.BOLD, 20));
		labelPwdOld.setBounds(25, 40, 98, 18);
		workPane.add(labelPwdOld);
		edtPwdOld.setBounds(155, 40, 166, 24);
		workPane.add(edtPwdOld);
		labelPwd.setFont(new Font("宋体", Font.BOLD, 20));
		labelPwd.setBounds(25, 88, 98, 18);
		workPane.add(labelPwd);
		edtPwd.setBounds(155, 88, 166, 24);
		workPane.add(edtPwd);
		labelPwd2.setFont(new Font("宋体", Font.BOLD, 20));
		labelPwd2.setBounds(25, 144, 98, 18);
		workPane.add(labelPwd2);
		edtPwd2.setBounds(155, 144, 166, 24);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(366, 332);
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
			if (JOptionPane.showConfirmDialog(this, "是否确认修改管理员密码", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					AdministratorManager administratorManager = new AdministratorManager();
					administratorManager.changAdministratorPwd(currentAdministrator,
							new String(edtPwdOld.getPassword()), new String(edtPwd.getPassword()),
							new String(edtPwd2.getPassword()));
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}

	}

}
