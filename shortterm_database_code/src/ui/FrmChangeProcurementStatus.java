package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kitchen.control.AdministratorManager;
import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsProcurementManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanIngredientsProcurement;
import kitchen.util.BaseException;

public class FrmChangeProcurementStatus extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelDescription = new JLabel("状态");
//	private JTextField edtDescription = new JTextField();
	
	private JComboBox cmbStauts = new JComboBox(new String[] { "下单", "途中", "入库" });

	public FrmChangeProcurementStatus(FrmProcurementStatistics frmProcurementStatistics, String s, boolean b) {
		super(frmProcurementStatistics, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelDescription.setBounds(14, 36, 87, 34);
		workPane.add(labelDescription);
//		edtDescription.setBounds(115, 41, 116, 24);
//		workPane.add(edtDescription);
		cmbStauts.setBounds(92, 41, 166, 24);
		workPane.add(cmbStauts);
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
		if (this.cmbStauts.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(null, "请选择状态", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk) {
			IngredientsProcurementManager procurementManager = new IngredientsProcurementManager();
			try {
				procurementManager.changeStatus(BeanIngredientsProcurement.currentProcurement, this.cmbStauts.getSelectedItem().toString());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		}

	}
}
