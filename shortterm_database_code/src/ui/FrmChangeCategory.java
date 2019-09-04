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
import javax.swing.JTextField;

import kitchen.control.AdministratorManager;
import kitchen.control.IngredientsManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.util.BaseException;
import java.awt.Font;

public class FrmChangeCategory extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelDescription = new JLabel("新的描述");
	private JTextField edtDescription = new JTextField();

	public BeanIngredientsCategory currentIngredientCategory = null;

	public FrmChangeCategory(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOk.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(this.btnOk);
		btnCancel.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelDescription.setFont(new Font("宋体", Font.BOLD, 20));
		labelDescription.setBounds(14, 36, 87, 34);
		workPane.add(labelDescription);
		edtDescription.setBounds(115, 41, 116, 24);
		workPane.add(edtDescription);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(302, 205);
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
			if (JOptionPane.showConfirmDialog(this, "是否确认修改食材类别的描述?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				IngredientsManager ingredientsManager = new IngredientsManager();
				ingredientsManager.changIngredientsCategory(currentIngredientCategory, edtDescription.getText());
				this.setVisible(false);
			}

		}

	}
}
