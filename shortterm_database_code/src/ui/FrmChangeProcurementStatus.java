package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

////	食材类别
//	private Object tblCategoryTitle[] = BeanIngredientsCategory.tblCategoryTitle;
//	private Object tblCategoryData[][];
//	DefaultTableModel tabCategoryModel = new DefaultTableModel();
//	private JTable dataTableCategory = new JTable(tabCategoryModel);
//	private BeanIngredientsCategory curCategory = null;
//	List<BeanIngredientsCategory> allCategorys = null;
////	食材信息
//	private Object tblIngredientsTitle[] = BeanIngredientsInformation.tblIngredientsTitle;
//	private Object tblIngredientsData[][];
//	DefaultTableModel tabIngredientsModel = new DefaultTableModel();
//	private JTable dataTableIngredients = new JTable(tabIngredientsModel);
//	private BeanIngredientsInformation curIngredients = null;
//	List<BeanIngredientsInformation> allIngredients = null;
//
////	刷新食材信息-管理员
//	private void reloadIngredientsTable(int ingredients_number) throws BaseException {// 这是测试数据，需要用实际数替换
//		IngredientsManager ingredientsManager = new IngredientsManager();
//		curIngredients = new IngredientsManager().loadIngredient(ingredients_number);
//		curCategory = new IngredientsManager().loadCategory(curIngredients.getCategory_number());
//		try {
//			allIngredients = ingredientsManager.loadAllIngredients(curCategory);
//		} catch (BaseException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tblIngredientsData = new Object[allIngredients.size()][BeanIngredientsInformation.tblIngredientsTitle.length];
//		for (int i = 0; i < allIngredients.size(); i++) {
//			for (int j = 0; j < BeanIngredientsInformation.tblIngredientsTitle.length; j++)
//				tblIngredientsData[i][j] = allIngredients.get(i).getCell(j);
//		}
//		tabIngredientsModel.setDataVector(tblIngredientsData, tblIngredientsTitle);
//		this.dataTableIngredients.validate();
//		this.dataTableIngredients.repaint();
//	}

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
			if (JOptionPane.showConfirmDialog(this, "是否确认修改状态?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
//				修改状态
				procurementManager.changeStatus(BeanIngredientsProcurement.currentProcurement,
						this.cmbStauts.getSelectedItem().toString());
//				如果入库,食材数量增加
				if (this.cmbStauts.getSelectedItem().toString() == "入库") {
					new IngredientsManager().addBeanIngredientsQuantity(
							new IngredientsManager().loadIngredient(
									BeanIngredientsProcurement.currentProcurement.getIngredients_number()),
							BeanIngredientsProcurement.currentProcurement.getQuantity());

//					FrmMain.this.reloadIngredientsTable(BeanIngredientsProcurement.currentProcurement.getIngredients_number());
				}
			

			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
			}
			
		}

	}
}
