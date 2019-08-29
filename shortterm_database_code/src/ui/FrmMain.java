package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import kitchen.*;
import kitchen.control.AdministratorManager;
import kitchen.control.IngredientsManager;
import kitchen.control.UserManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanUser;
import kitchen.util.*;

public class FrmMain extends JFrame implements ActionListener {

	private JMenuBar menubar = new JMenuBar();

//	按钮信息
	private JMenu menu_Manager = new JMenu("用户管理");
	private JMenu menu_Ingredients = new JMenu("食材管理");
	private JMenu menu_Procurement = new JMenu("采购管理");

	private JMenu menu_Recipe = new JMenu("菜谱管理");
	private JMenu menu_Order = new JMenu("订单管理");
	private JMenu menu_Information = new JMenu("个人信息管理");

	private JMenuItem menuItem_AddIngredientsCategories = new JMenuItem("添加食材类别");
	private JMenuItem menuItem_DeleteIngredientsCategories = new JMenuItem("删除食材类别");
	private JMenuItem menuItem_ChangeIngredientsCategories = new JMenuItem("修改食材类别描述");
	private JMenuItem menuItem_AddIngredientsInformation = new JMenuItem("增加食材信息");
	private JMenuItem menuItem_DeleteIngredientsInformation = new JMenuItem("删除食材信息");
	private JMenuItem menuItem_ChangeIngredientsInformation = new JMenuItem("修改食材信息描述");

	private JMenuItem menuItem_AddProcurement = new JMenuItem("采购食材");
	private JMenuItem menuItem_ProcurementStatic = new JMenuItem("采购统计");

	private JMenuItem menuItem_AddUser = new JMenuItem("添加用户");
	private JMenuItem menuItem_DeleteUser = new JMenuItem("删除用户");
	private JMenuItem menuItem_ReloadUserPwd = new JMenuItem("重置用户密码");
	private JMenuItem menuItem_ChangeAdminPwd = new JMenuItem("修改管理员密码");
	

//	登录界面
	private FrmLogin dlgLogin = null;

//	状态栏
	private JPanel statusBar = new JPanel();

//	用户
	private Object tblUserTitle[] = BeanUser.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel = new DefaultTableModel();
	private JTable dataTableUser = new JTable(tabUserModel);
	private BeanUser curUser = null;
	List<BeanUser> allUsers = null;

//	食材类别
	private Object tblCategoryTitle[] = BeanIngredientsCategory.tblCategoryTitle;
	private Object tblCategoryData[][];
	DefaultTableModel tabCategoryModel = new DefaultTableModel();
	private JTable dataTableCategory = new JTable(tabCategoryModel);
	private BeanIngredientsCategory curCategory = null;
	List<BeanIngredientsCategory> allCategorys = null;

//	食材信息
	private Object tblIngredientsTitle[] = BeanIngredientsInformation.tblIngredientsTitle;
	private Object tblIngredientsData[][];
	DefaultTableModel tabIngredientsModel = new DefaultTableModel();
	private JTable dataTableIngredients = new JTable(tabIngredientsModel);
	private BeanIngredientsInformation curIngredients = null;
	List<BeanIngredientsInformation> allIngredients = null;

//	刷新用户
	private void reloadUserTable() {// 这是测试数据，需要用实际数替换
		UserManager userManager = new UserManager();
		try {
			allUsers = userManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData = new Object[allUsers.size()][BeanUser.tblUserTitle.length];
		for (int i = 0; i < allUsers.size(); i++) {
			for (int j = 0; j < BeanUser.tblUserTitle.length; j++)
				tblUserData[i][j] = allUsers.get(i).getCell(j);
		}
		tabUserModel.setDataVector(tblUserData, tblUserTitle);
		this.dataTableUser.validate();
		this.dataTableUser.repaint();
	}

//	刷新食材类别
	private void reloadCategoryTable() {// 这是测试数据，需要用实际数替换
		IngredientsManager ingredientsManager = new IngredientsManager();
		try {
			allCategorys = ingredientsManager.loadAllCategory();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCategoryData = new Object[allCategorys.size()][BeanIngredientsCategory.tblCategoryTitle.length];
		for (int i = 0; i < allCategorys.size(); i++) {
			for (int j = 0; j < BeanIngredientsCategory.tblCategoryTitle.length; j++)
				tblCategoryData[i][j] = allCategorys.get(i).getCell(j);
		}
		tabCategoryModel.setDataVector(tblCategoryData, tblCategoryTitle);
		this.dataTableCategory.validate();
		this.dataTableCategory.repaint();
	}

//	刷新食材信息
	private void reloadIngredientsTable(int category_number) {// 这是测试数据，需要用实际数替换
		IngredientsManager ingredientsManager = new IngredientsManager();
		curCategory = allCategorys.get(category_number);

		try {
			allIngredients = ingredientsManager.loadAllIngredients(curCategory);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblIngredientsData = new Object[allIngredients.size()][BeanIngredientsInformation.tblIngredientsTitle.length];
		for (int i = 0; i < allIngredients.size(); i++) {
			for (int j = 0; j < BeanIngredientsInformation.tblIngredientsTitle.length; j++)
				tblIngredientsData[i][j] = allIngredients.get(i).getCell(j);
		}
		tabIngredientsModel.setDataVector(tblIngredientsData, tblIngredientsTitle);
		this.dataTableIngredients.validate();
		this.dataTableIngredients.repaint();
	}

//	主函数
	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("厨房小帮手");
		dlgLogin = new FrmLogin(this, "登陆", true);
		dlgLogin.setVisible(true);
//	     菜单
//		管理员界面 按钮
		if ("管理员".equals(FrmLogin.userType)) {
//			添加按钮
			menu_Manager.add(menuItem_AddUser);
			menuItem_AddUser.addActionListener(this);
			menu_Manager.add(menuItem_DeleteUser);
			menuItem_DeleteUser.addActionListener(this);
			menu_Manager.add(menuItem_ReloadUserPwd);
			menuItem_ReloadUserPwd.addActionListener(this);
			menu_Manager.add(menuItem_ChangeAdminPwd);
			menuItem_ChangeAdminPwd.addActionListener(this);
			
			menu_Ingredients.add(menuItem_AddIngredientsCategories);
			menuItem_AddIngredientsCategories.addActionListener(this);
			menu_Ingredients.add(menuItem_DeleteIngredientsCategories);
			menuItem_DeleteIngredientsCategories.addActionListener(this);
			menu_Ingredients.add(menuItem_ChangeIngredientsCategories);
			menuItem_ChangeIngredientsCategories.addActionListener(this);
			
			menu_Ingredients.add(menuItem_AddIngredientsInformation);
			menuItem_AddIngredientsInformation.addActionListener(this);
			menu_Ingredients.add(menuItem_DeleteIngredientsInformation);
			menuItem_DeleteIngredientsInformation.addActionListener(this);
			menu_Ingredients.add(menuItem_ChangeIngredientsInformation);
			menuItem_ChangeIngredientsInformation.addActionListener(this);
					
			menu_Procurement.add(menuItem_AddProcurement);
			menuItem_AddProcurement.addActionListener(this);
			menu_Procurement.add(menuItem_ProcurementStatic);
			menuItem_ProcurementStatic.addActionListener(this);

			menubar.add(menu_Manager);
			menubar.add(menu_Ingredients);
			menubar.add(menu_Procurement);

		}

//		管理员界面 布局
		if ("管理员".equals(FrmLogin.userType)) {
			// 用户
			JScrollPane paneUser = new JScrollPane(this.dataTableUser);
			paneUser.setPreferredSize(new Dimension(1000, 10));
			this.getContentPane().add(paneUser, BorderLayout.WEST);
//			this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.WEST);

//			鼠标点击 刷新界面
			this.dataTableUser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableUser.getSelectedRow();
					if (i < 0) {
						return;
					}
//					FrmMain.this.reloadUserTable();
				}

			});
			this.reloadUserTable();

			// 食材类别
			JScrollPane paneCategory = new JScrollPane(this.dataTableCategory);
			paneCategory.setPreferredSize(new Dimension(150, 0));
			this.getContentPane().add(paneCategory, BorderLayout.CENTER);
			this.dataTableCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableCategory.getSelectedRow();
					if (i < 0) {
						return;
					}
					FrmMain.this.reloadIngredientsTable(i);
				}

			});

//			食材
			this.getContentPane().add(new JScrollPane(this.dataTableIngredients), BorderLayout.EAST);
			this.reloadCategoryTable();
		}

		// 状态栏
		this.setJMenuBar(menubar);

		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("您好!" + FrmLogin.userType);
		statusBar.add(label);
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		添加食材类别
		if (e.getSource() == this.menuItem_AddIngredientsCategories) {
			FrmAddCategory addCategory = new FrmAddCategory(this, "添加食材类别", true);
			addCategory.setVisible(true);
			this.reloadCategoryTable();
		}
//		删除食材类别
		if (e.getSource() == this.menuItem_DeleteIngredientsCategories) {
			if (this.curCategory == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				IngredientsManager ingredientsManager = new IngredientsManager();
				ingredientsManager.deleteIngredientsCategory(this.curCategory);
				this.reloadCategoryTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
//		添加食材信息
		else if (e.getSource() == this.menuItem_AddIngredientsInformation) {
			FrmAddIngredientsInformation addIngredients = new FrmAddIngredientsInformation(this, "添加食材信息", true);
			addIngredients.ingredientsCategory = this.curCategory;
			addIngredients.setVisible(true);
			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
		} 
//		删除食材信息
		else if (e.getSource() == this.menuItem_DeleteIngredientsInformation) {
			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				IngredientsManager ingredientsManager = new IngredientsManager();
				ingredientsManager.deleteIngredientsInformation(this.allIngredients.get(i));
				this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} 
//		采购食材
		else if (e.getSource() == this.menuItem_AddProcurement) {
			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
//			if (i < 0) {
//				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
			AdministratorManager administratorManager = new AdministratorManager();
			FrmAddProcurement addProcurement = new FrmAddProcurement(this, "食材采购", true);
			addProcurement.currentIngredients = this.allIngredients.get(i);
			try {
				addProcurement.currentAdministrator = administratorManager
						.loadAdministrator(Integer.parseInt(FrmLogin.id));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addProcurement.setVisible(true);
			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
		} 
//		采购统计
		else if (e.getSource() == this.menuItem_ProcurementStatic) {
			FrmProcurementStatistics procurementStatistics = new FrmProcurementStatistics(this, "采购管理", true);
			procurementStatistics.setVisible(true);
		}
//		添加用户
		else if(e.getSource()==this.menuItem_AddUser){
			FramAddUser addUser = new FramAddUser(this, "注册", true);
			addUser.setVisible(true);
		}
//		删除用户
		else if(e.getSource()==this.menuItem_DeleteUser){
			int i = FrmMain.this.dataTableUser.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				UserManager userManager = new UserManager();
				userManager.deleteUser(this.allUsers.get(i));
				this.reloadUserTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
//		重置用户密码
		else if(e.getSource()==this.menuItem_ReloadUserPwd){
			UserManager userManager = new UserManager();
			try {
				userManager.reloadUserPassword();
				this.reloadUserTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
//		修改管理员密码
		else if(e.getSource()==this.menuItem_ChangeAdminPwd){
			FrmChangeAdminPwd changeAdminPwd=new FrmChangeAdminPwd(this,"修改管理员密码",true);
			changeAdminPwd.currentAdministrator = BeanAdministratorInformation.currentAdministrator;
			changeAdminPwd.setVisible(true);
		}
		//修改食材类别描述
		else if(e.getSource()==this.menuItem_ChangeIngredientsCategories){
			int i = FrmMain.this.dataTableCategory.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeCategory changeCategory=new FrmChangeCategory(this,"修改食材类别描述",true);
			changeCategory.currentIngredientCategory = this.allCategorys.get(i);
			changeCategory.setVisible(true);
			this.reloadCategoryTable();
			
		}
//		修改食材描述
		else if(e.getSource()==this.menuItem_ChangeIngredientsInformation){
			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeIngredients changeIngredients=new FrmChangeIngredients(this,"修改食材类别描述",true);
			changeIngredients.currentIngredient = this.allIngredients.get(i);
			changeIngredients.setVisible(true);
			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
		}
	}
}
