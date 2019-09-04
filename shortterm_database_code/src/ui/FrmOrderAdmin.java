package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.FramePeer;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsOrderManager;
import kitchen.control.IngredientsProcurementManager;
import kitchen.control.RecipeManager;
import kitchen.model.BeanIngredientsOrder;
import kitchen.model.BeanIngredientsProcurement;
import kitchen.model.BeanOrderDetail;
import kitchen.model.BeanRecipeEvaluation;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
import kitchen.model.BeanRecipeStep;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;
import java.awt.Font;

public class FrmOrderAdmin extends JFrame implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JButton btnChangeStatus = new JButton("修改状态");
	private JComboBox cmbOrderStatus = new JComboBox(new String[] { "下单", "配送", "送达" });

//	用户
	private Object tblUserTitle[] = BeanUser.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel = new DefaultTableModel();
	private JTable dataTableUser = new JTable(tabUserModel);
	private BeanUser curUser = null;
	List<BeanUser> allUsers = null;

//	订单信息
	private Object tblOrderTitle[] = BeanIngredientsOrder.tblOrderTitle;
	private Object tblOrderData[][];
	DefaultTableModel tabOrderModel = new DefaultTableModel();
	private JTable dataTableOrder = new JTable(tabOrderModel);
	private BeanIngredientsOrder curOrder = null;
	List<BeanIngredientsOrder> allOrders = null;

//	订单详情
	private Object tblDetailsTitle[] = BeanOrderDetail.tblDetailTitle;
	private Object tblDetailData[][];
	DefaultTableModel tabDetailModel = new DefaultTableModel();
	private JTable dataTableDetails = new JTable(tabDetailModel);
	private BeanOrderDetail curDetail = null;
	List<BeanOrderDetail> allDetails = null;

//	刷新订单
	private void reloadOrdersTable() {// 这是测试数据，需要用实际数替换
		IngredientsOrderManager orderManager = new IngredientsOrderManager();
		curUser = BeanUser.currentUser;

		try {
			allOrders = orderManager.loadAllOrders();// 根据用户loadall菜谱
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrderData = new Object[allOrders.size()][BeanIngredientsOrder.tblOrderTitle.length];
		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < BeanIngredientsOrder.tblOrderTitle.length; j++)
				tblOrderData[i][j] = allOrders.get(i).getCell(j);
		}
		tabOrderModel.setDataVector(tblOrderData, tblOrderTitle);
		this.dataTableOrder.validate();
		this.dataTableOrder.repaint();
	}

//	刷新订单详情
	private void reloadDetailsTable(int order_number) {// 这是测试数据，需要用实际数替换
		IngredientsOrderManager orderManager = new IngredientsOrderManager();
		curOrder = allOrders.get(order_number);
		try {
			allDetails = orderManager.loadAllDetails(curOrder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDetailData = new Object[allDetails.size()][BeanOrderDetail.tblDetailTitle.length];
		for (int i = 0; i < allDetails.size(); i++) {
			for (int j = 0; j < BeanOrderDetail.tblDetailTitle.length; j++)
				tblDetailData[i][j] = allDetails.get(i).getCell(j);
		}
		tabDetailModel.setDataVector(tblDetailData, tblDetailsTitle);
		this.dataTableDetails.validate();
		this.dataTableDetails.repaint();
	}

	public FrmOrderAdmin(JFrame f, String s, boolean b) {

		super();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		cmbOrderStatus.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(cmbOrderStatus);
		btnChangeStatus.setFont(new Font("宋体", Font.BOLD, 20));
		toolBar.add(btnChangeStatus);

//		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setSize(1000, 700);
		this.setTitle("订单统计");

		this.getContentPane().add(toolBar, BorderLayout.NORTH);

//		订单
		JScrollPane paneOrder = new JScrollPane(this.dataTableOrder);
		paneOrder.setPreferredSize(new Dimension(500, 0));
		this.getContentPane().add(paneOrder, BorderLayout.WEST);

//		鼠标点击 刷新界面 订单/ 订单详情
		this.dataTableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmOrderAdmin.this.dataTableOrder.getSelectedRow();
				if (i < 0) {
					return;
				}
				curOrder = allOrders.get(i);
				FrmOrderAdmin.this.reloadDetailsTable(i);
			}

		});
		this.reloadOrdersTable();

//		步骤
		JScrollPane paneDetail = new JScrollPane(this.dataTableDetails);
//		paneDetail.setPreferredSize(new Dimension(60, 0));
		this.getContentPane().add(paneDetail, BorderLayout.CENTER);

		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnChangeStatus.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		修改订单状态, 当订单送达时, 将食材的数量减去订单的食材数量
		if (e.getSource() == this.btnChangeStatus) {
//			int i = this.dataTableOrder.getSelectedRow();
			if (curOrder == null) {
				JOptionPane.showMessageDialog(null, "请选择订单", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			IngredientsOrderManager orderManager = new IngredientsOrderManager();
//			curOrder = allOrders.get(i);
			if (JOptionPane.showConfirmDialog(this, "是否确认修改订单状态?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
//				修改状态
					orderManager.changeIngredientsOrderStatus(curOrder,
							this.cmbOrderStatus.getSelectedItem().toString());
//				如果配送中,数量减去
					if (this.cmbOrderStatus.getSelectedItem().toString() == "配送") {
						IngredientsManager ingredientsManager = new IngredientsManager();
						ingredientsManager.deleteIngredientsQuantityByOrder(curOrder);
//					FrmMain.this.reloadIngredientsTable(BeanIngredientsProcurement.currentProcurement.getIngredients_number());
					
					}

				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.reloadOrdersTable();

//			this.setVisible(false);
			}

		}
	}
}
