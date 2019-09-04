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
import kitchen.util.*;
import java.awt.Font;

public class FrmAddCategory extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("名称：");
	private JLabel labelDescription = new JLabel("描述：");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtDescription = new JTextField(20);

	public FrmAddCategory(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOk.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(btnOk);
		btnCancel.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelName.setFont(new Font("宋体", Font.BOLD, 20));
		labelName.setBounds(27, 8, 70, 18);
		workPane.add(labelName);
		edtName.setBounds(140, 2, 166, 24);
		workPane.add(edtName);
		labelDescription.setFont(new Font("宋体", Font.BOLD, 20));
		labelDescription.setBounds(27, 67, 70, 18);
		workPane.add(labelDescription);
		edtDescription.setBounds(140, 61, 166, 24);
		workPane.add(edtDescription);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(338, 236);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			if (JOptionPane.showConfirmDialog(this, "是否确认增加食材类别?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String name = this.edtName.getText();
			String description = this.edtDescription.getText();
			IngredientsManager ingredientsManager = new IngredientsManager();
			try {
				ingredientsManager.addIngredientsCategory(name, description);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			}
			
		}

	}

}
