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
import kitchen.control.RecipeManager;
import kitchen.control.UserManager;
import kitchen.model.BeanAdministratorInformation;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanRecipeEvaluation;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
import kitchen.model.BeanRecipeStep;
import kitchen.model.BeanUser;
import kitchen.util.*;

public class FrmMain extends JFrame implements ActionListener {

	private JMenuBar menubar = new JMenuBar();

//	管理员 按钮信息
	private JMenu menu_Manager = new JMenu("用户管理");
	private JMenu menu_Ingredients = new JMenu("食材管理");
	private JMenu menu_Procurement = new JMenu("采购管理");
	private JMenu menu_AllRecipeAdmin = new JMenu("所有菜谱");
	private JMenu menu_OrderAdmin = new JMenu("用户订单");
	private JMenu menu_Discount = new JMenu("折扣");

	private JMenuItem menuItem_AddIngredientsCategories = new JMenuItem("添加食材类别");
	private JMenuItem menuItem_DeleteIngredientsCategories = new JMenuItem("删除食材类别");
	private JMenuItem menuItem_ChangeIngredientsCategories = new JMenuItem("修改食材类别描述");
	private JMenuItem menuItem_AddIngredientsInformation = new JMenuItem("增加食材信息");
	private JMenuItem menuItem_DeleteIngredientsInformation = new JMenuItem("删除食材信息");
	private JMenuItem menuItem_ChangeIngredientsInformation = new JMenuItem("修改食材信息");

	private JMenuItem menuItem_AddProcurement = new JMenuItem("采购食材");
	private JMenuItem menuItem_ProcurementStatic = new JMenuItem("采购统计");

	private JMenuItem menuItem_AddUser = new JMenuItem("添加用户");
	private JMenuItem menuItem_DeleteUser = new JMenuItem("删除用户");
	private JMenuItem menuItem_ReloadUserPwd = new JMenuItem("重置用户密码");
	private JMenuItem menuItem_ChangeAdminPwd = new JMenuItem("修改管理员密码");

	private JMenuItem menuItem_allRecipeAdmin = new JMenuItem("查看所有菜谱");

	private JMenuItem menuItem_OrderAdmin = new JMenuItem("订单管理");
	private JMenuItem menuItem_Discount = new JMenuItem("设置折扣");
//	用户界面信息

	private JMenu menu_AllRecipe = new JMenu("所有菜谱");
	private JMenu menu_Recipe = new JMenu("我的菜谱管理");
	private JMenu menu_Step = new JMenu("菜谱步骤管理");
	private JMenu menu_Material = new JMenu("菜谱用料管理");
	private JMenu menu_Order = new JMenu("订单管理");
	private JMenu menu_Information = new JMenu("个人信息管理");

	private JMenuItem menuItem_AddRecipe = new JMenuItem("添加菜谱");
	private JMenuItem menuItem_DeleteRecipe = new JMenuItem("删除菜谱");
	private JMenuItem menuItem_ChangeRecipe = new JMenuItem("修改菜谱");
	private JMenuItem menuItem_AddStep = new JMenuItem("添加菜谱步骤");
	private JMenuItem menuItem_DeleteStep = new JMenuItem("删除菜谱步骤");
	private JMenuItem menuItem_ChangeStep = new JMenuItem("修改菜谱步骤");
	private JMenuItem menuItem_AddMaterial = new JMenuItem("添加食材");
	private JMenuItem menuItem_DeleteMaterial = new JMenuItem("删除食材");
	private JMenuItem menuItem_ChangeMaterial = new JMenuItem("修改食材");

	private JMenuItem menuItem_changePwd = new JMenuItem("修改密码");

	private JMenuItem menuItem_allRecipe = new JMenuItem("查看所有菜谱");

	private JMenuItem menuItem_OrderStatic = new JMenuItem("订单统计");
//	private JMenuItem menuItem_allRecipe = new JMenuItem("查看所有菜谱");
//	private JMenuItem menuItem_allRecipe = new JMenuItem("查看所有菜谱");

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

//	菜谱
	private Object tblRecipesTitle[] = BeanRecipeInformation.tblRecipeTitle;
	private Object tblRecipesData[][];
	DefaultTableModel tabRecipesModel = new DefaultTableModel();
	private JTable dataTableRecipes = new JTable(tabRecipesModel);
	private BeanRecipeInformation curRecipes = null;
	List<BeanRecipeInformation> allRecipes = null;

//	步骤
	private Object tblStepsTitle[] = BeanRecipeStep.tblStepsTitle;
	private Object tblStepsData[][];
	DefaultTableModel tabStepsModel = new DefaultTableModel();
	private JTable dataTableSteps = new JTable(tabStepsModel);
	private BeanRecipeStep curStep = null;
	List<BeanRecipeStep> allSteps = null;

//	菜谱用料
	private Object tblMaterialsTitle[] = BeanRecipeMaterial.tblMaterialsTitle;
	private Object tblMaterialsData[][];
	DefaultTableModel tabMaterialsModel = new DefaultTableModel();
	private JTable dataTableMaterials = new JTable(tabMaterialsModel);
	private BeanRecipeMaterial curMaterial = null;
	List<BeanRecipeMaterial> allMaterials = null;

//	菜谱评价
	private Object tblEvaluationsTitle[] = BeanRecipeEvaluation.tblEvaluationsTitle;
	private Object tblEvaluationsData[][];
	DefaultTableModel tabEvaluationsModel = new DefaultTableModel();
	private JTable dataTableEvaluations = new JTable(tabEvaluationsModel);
	private BeanRecipeEvaluation curEvaluation = null;
	List<BeanRecipeEvaluation> allEvaluations = null;

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

//	刷新食材类别-管理员
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

//	刷新食材信息-管理员
	private void reloadIngredientsTable(int category_number) {// 这是测试数据，需要用实际数替换
		IngredientsManager ingredientsManager = new IngredientsManager();
//		curCategory = allCategorys.get(category_number);

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

//	刷新菜谱信息-用户
	private void reloadRecipesTable() {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();
//		curUser = allUsers.get(user_number);
		curUser = BeanUser.currentUser;

		try {
			allRecipes = recipeManager.loadAllRecipe(curUser);// 根据用户loadall菜谱
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRecipesData = new Object[allRecipes.size()][BeanRecipeInformation.tblRecipeTitle.length];
		for (int i = 0; i < allRecipes.size(); i++) {
			for (int j = 0; j < BeanRecipeInformation.tblRecipeTitle.length; j++)
				tblRecipesData[i][j] = allRecipes.get(i).getCell(j);
		}
		tabRecipesModel.setDataVector(tblRecipesData, tblRecipesTitle);
		this.dataTableRecipes.validate();
		this.dataTableRecipes.repaint();
	}

//	刷新步骤信息-用户
	private void reloadStepsTable(int recipe_number) {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();

		try {
			allSteps = recipeManager.loadAllSteps(curRecipes);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStepsData = new Object[allSteps.size()][BeanRecipeStep.tblStepsTitle.length];
		for (int i = 0; i < allSteps.size(); i++) {
			for (int j = 0; j < BeanRecipeStep.tblStepsTitle.length; j++)
				tblStepsData[i][j] = allSteps.get(i).getCell(j);
		}
		tabStepsModel.setDataVector(tblStepsData, tblStepsTitle);
		this.dataTableSteps.validate();
		this.dataTableSteps.repaint();
	}

//	刷新菜谱用料信息-用户
	private void reloadMaterialsTable(int recipe_number) {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();

		try {
			allMaterials = recipeManager.loadAllMaterials(curRecipes);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMaterialsData = new Object[allMaterials.size()][BeanRecipeMaterial.tblMaterialsTitle.length];
		for (int i = 0; i < allMaterials.size(); i++) {
			for (int j = 0; j < BeanRecipeMaterial.tblMaterialsTitle.length; j++)
				tblMaterialsData[i][j] = allMaterials.get(i).getCell(j);
		}
		tabMaterialsModel.setDataVector(tblMaterialsData, tblMaterialsTitle);
		this.dataTableMaterials.validate();
		this.dataTableMaterials.repaint();
	}

//	刷新菜谱评价信息-用户
	private void reloadEvaluationsTable(int recipe_number) {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();
//		curRecipes = allRecipes.get(recipe_number);

		try {
			allEvaluations = recipeManager.loadAllEvaluations(curRecipes);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblEvaluationsData = new Object[allEvaluations.size()][BeanRecipeEvaluation.tblEvaluationsTitle.length];
		for (int i = 0; i < allEvaluations.size(); i++) {
			for (int j = 0; j < BeanRecipeEvaluation.tblEvaluationsTitle.length; j++)
				tblEvaluationsData[i][j] = allEvaluations.get(i).getCell(j);
		}
		tabEvaluationsModel.setDataVector(tblEvaluationsData, tblEvaluationsTitle);
		this.dataTableEvaluations.validate();
		this.dataTableEvaluations.repaint();
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

			menu_AllRecipeAdmin.add(menuItem_allRecipeAdmin);
			menuItem_allRecipeAdmin.addActionListener(this);

			menu_OrderAdmin.add(menuItem_OrderAdmin);
			menuItem_OrderAdmin.addActionListener(this);

			menu_Discount.add(menuItem_Discount);
			menuItem_Discount.addActionListener(this);

			menubar.add(menu_Manager);
			menubar.add(menu_Ingredients);
			menubar.add(menu_Procurement);
			menubar.add(menu_AllRecipeAdmin);
			menubar.add(menu_OrderAdmin);
			menubar.add(menu_Discount);

		}
//		用户界面按钮
		else if ("用户".equals(FrmLogin.userType)) {
			menu_Recipe.add(menuItem_AddRecipe);
			menuItem_AddRecipe.addActionListener(this);
			menu_Recipe.add(menuItem_DeleteRecipe);
			menuItem_DeleteRecipe.addActionListener(this);
			menu_Recipe.add(menuItem_ChangeRecipe);
			menuItem_ChangeRecipe.addActionListener(this);

			menu_Step.add(menuItem_AddStep);
			menuItem_AddStep.addActionListener(this);
			menu_Step.add(menuItem_DeleteStep);
			menuItem_DeleteStep.addActionListener(this);
			menu_Step.add(menuItem_ChangeStep);
			menuItem_ChangeStep.addActionListener(this);

			menu_Material.add(menuItem_AddMaterial);
			menuItem_AddMaterial.addActionListener(this);
			menu_Material.add(menuItem_DeleteMaterial);
			menuItem_DeleteMaterial.addActionListener(this);
			menu_Material.add(menuItem_ChangeMaterial);
			menuItem_ChangeMaterial.addActionListener(this);

			menu_Information.add(menuItem_changePwd);
			menuItem_changePwd.addActionListener(this);

			menu_AllRecipe.add(menuItem_allRecipe);
			menuItem_allRecipe.addActionListener(this);

			menu_Order.add(menuItem_OrderStatic);
			menuItem_OrderStatic.addActionListener(this);

			menubar.add(menu_Information);
			menubar.add(menu_Recipe);
			menubar.add(menu_Step);
			menubar.add(menu_Material);
			menubar.add(menu_Order);
			menubar.add(menu_AllRecipe);
		}

//		管理员界面 布局
		if ("管理员".equals(FrmLogin.userType)) {
			// 用户
			JScrollPane paneUser = new JScrollPane(this.dataTableUser);
			paneUser.setPreferredSize(new Dimension(1100, 10));
			this.getContentPane().add(paneUser, BorderLayout.WEST);
//			this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.WEST);

//			鼠标点击 刷新界面 用户
			this.dataTableUser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableUser.getSelectedRow();
					if (i < 0) {
						return;
					}
					curUser = allUsers.get(i);
//					FrmMain.this.reloadUserTable();
				}

			});
			this.reloadUserTable();

			// 食材类别
			JScrollPane paneCategory = new JScrollPane(this.dataTableCategory);
			paneCategory.setPreferredSize(new Dimension(120, 0));
			this.getContentPane().add(paneCategory, BorderLayout.CENTER);

			this.dataTableCategory.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableCategory.getSelectedRow();
					if (i < 0) {
						return;
					}
					curCategory = allCategorys.get(i);
//					BeanIngredientsCategory.currentCategory = curCategory;
					FrmMain.this.reloadIngredientsTable(i);
				}

			});

//			食材
			this.getContentPane().add(new JScrollPane(this.dataTableIngredients), BorderLayout.EAST);
			this.dataTableIngredients.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableIngredients.getSelectedRow();
					if (i < 0) {
						return;
					}
					curIngredients = allIngredients.get(i);
					BeanIngredientsInformation.currentIngredients = curIngredients;
				}

			});

			this.reloadCategoryTable();

//			状态栏
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
//		用户界面布局
		else if ("用户".equals(FrmLogin.userType)) {
//			菜谱
			JScrollPane paneRecipe = new JScrollPane(this.dataTableRecipes);
			paneRecipe.setPreferredSize(new Dimension(1100, 10));
			this.getContentPane().add(paneRecipe, BorderLayout.WEST);

//			鼠标点击 刷新界面 菜谱
			this.dataTableRecipes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableRecipes.getSelectedRow();
					if (i < 0) {
						return;
					}
					curRecipes = allRecipes.get(i);
					FrmMain.this.reloadStepsTable(i);
					FrmMain.this.reloadMaterialsTable(i);
					FrmMain.this.reloadEvaluationsTable(i);
				}

			});
			this.reloadRecipesTable();

//			步骤
			JScrollPane paneSteps = new JScrollPane(this.dataTableSteps);
			paneSteps.setPreferredSize(new Dimension(60, 0));
			this.getContentPane().add(paneSteps, BorderLayout.CENTER);
//			鼠标点击 步骤
			this.dataTableSteps.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableSteps.getSelectedRow();
					if (i < 0) {
						return;
					}
					curStep = allSteps.get(i);
//					FrmMain.this.reloadStepsTable(i);
//					FrmMain.this.reloadMaterialsTable(i);
//					FrmMain.this.reloadEvaluationsTable(i);
				}

			});

//			食材
			JScrollPane paneMaterial = new JScrollPane(this.dataTableMaterials);
//			paneMaterial.setPreferredSize(new Dimension(0, 0));
			this.getContentPane().add(paneMaterial, BorderLayout.EAST);
//			鼠标点击  食材
			this.dataTableMaterials.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i = FrmMain.this.dataTableMaterials.getSelectedRow();
					if (i < 0) {
						return;
					}
					curMaterial = allMaterials.get(i);
//					FrmMain.this.reloadStepsTable(i);
//					FrmMain.this.reloadMaterialsTable(i);
//					FrmMain.this.reloadEvaluationsTable(i);
				}

			});

//			评价
			JScrollPane paneEvaluations = new JScrollPane(this.dataTableEvaluations);
			paneEvaluations.setPreferredSize(new Dimension(1920, 300));
			this.getContentPane().add(paneEvaluations, BorderLayout.SOUTH);

//			// 状态栏
			this.setJMenuBar(menubar);

//			statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//			JLabel label = new JLabel("您好!" + FrmLogin.userType);
//			statusBar.add(label);
//			this.getContentPane().add(statusBar, BorderLayout.SOUTH);

			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			this.setVisible(true);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		添加食材类别
		if (e.getSource() == this.menuItem_Discount) {
			FrmAddDiscount addDiscount = new FrmAddDiscount(this, "设置折扣", true);
			addDiscount.setVisible(true);
			this.reloadCategoryTable();

		}

//		添加食材类别
		if (e.getSource() == this.menuItem_AddIngredientsCategories) {

			FrmAddCategory addCategory = new FrmAddCategory(this, "添加食材类别", true);
			addCategory.setVisible(true);
			this.reloadCategoryTable();

		}
//		删除食材类别
		if (e.getSource() == this.menuItem_DeleteIngredientsCategories) {
//			if (this.curCategory == null) {
//				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			int i = FrmMain.this.dataTableCategory.getSelectedRow();
			if (this.curCategory == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除食材类别?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					IngredientsManager ingredientsManager = new IngredientsManager();
					ingredientsManager.deleteIngredientsCategory(this.curCategory);
					this.reloadCategoryTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
//			try {
//				IngredientsManager ingredientsManager = new IngredientsManager();
//				ingredientsManager.deleteIngredientsCategory(this.curCategory);
//				this.reloadCategoryTable();
//			} catch (BaseException e1) {
//				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
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
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curIngredients == null) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除食材?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					IngredientsManager ingredientsManager = new IngredientsManager();
//				ingredientsManager.deleteIngredientsInformation(this.allIngredients.get(i));
					ingredientsManager.deleteIngredientsInformation(this.curIngredients);
					this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
//		采购食材
		else if (e.getSource() == this.menuItem_AddProcurement) {
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curIngredients == null) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			AdministratorManager administratorManager = new AdministratorManager();
			FrmAddProcurement addProcurement = new FrmAddProcurement(this, "食材采购", true);
			addProcurement.currentIngredients = this.curIngredients;
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
			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);

		}
//		添加用户
		else if (e.getSource() == this.menuItem_AddUser) {
			FramAddUser addUser = new FramAddUser(this, "注册", true);
			addUser.setVisible(true);
			this.reloadUserTable();
		}
//		删除用户
		else if (e.getSource() == this.menuItem_DeleteUser) {
//			int i = FrmMain.this.dataTableUser.getSelectedRow();
			if (curUser == null) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除用户?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					UserManager userManager = new UserManager();
					userManager.deleteUser(this.curUser);
					this.reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
//		重置用户密码
		else if (e.getSource() == this.menuItem_ReloadUserPwd) {
			UserManager userManager = new UserManager();
			if (JOptionPane.showConfirmDialog(this, "是否确认重置所有用户密码为1?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					userManager.reloadUserPassword();
					this.reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
//		修改管理员密码
		else if (e.getSource() == this.menuItem_ChangeAdminPwd) {
			FrmChangeAdminPwd changeAdminPwd = new FrmChangeAdminPwd(this, "修改管理员密码", true);
			changeAdminPwd.currentAdministrator = BeanAdministratorInformation.currentAdministrator;
			changeAdminPwd.setVisible(true);
		}
//		修改食材类别描述
		else if (e.getSource() == this.menuItem_ChangeIngredientsCategories) {
//			int i = FrmMain.this.dataTableCategory.getSelectedRow();
			if (curCategory == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeCategory changeCategory = new FrmChangeCategory(this, "修改食材类别描述", true);
//			changeCategory.currentIngredientCategory = this.allCategorys.get(i);
			changeCategory.currentIngredientCategory = this.curCategory;
			changeCategory.setVisible(true);
			this.reloadCategoryTable();

		}
//		修改食材描述
		else if (e.getSource() == this.menuItem_ChangeIngredientsInformation) {
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curIngredients == null) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeIngredients changeIngredients = new FrmChangeIngredients(this, "修改食材", true);
//			changeIngredients.currentIngredient = this.allIngredients.get(i);
//			changeIngredients.currentIngredient = this.curIngredients;
			changeIngredients.setVisible(true);
			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);
		}

//		新建菜谱
		else if (e.getSource() == this.menuItem_AddRecipe) {
			FrmAddRecipe addRecipe = new FrmAddRecipe(this, "添加菜谱", true);
			addRecipe.setVisible(true);
			this.reloadRecipesTable();
		}
//		删除菜谱
		else if (e.getSource() == this.menuItem_DeleteRecipe) {
//			int i = FrmMain.this.dataTableRecipes.getSelectedRow();
			if (curRecipes == null) {
				JOptionPane.showMessageDialog(null, "请选择菜谱", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除菜谱?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RecipeManager recipeManager = new RecipeManager();
//				recipeManager.deleRecipe(this.allRecipes.get(i));
					recipeManager.deleRecipe(this.curRecipes);
					this.reloadRecipesTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
//		修改菜谱描述
		else if (e.getSource() == this.menuItem_ChangeRecipe) {
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curRecipes == null) {
				JOptionPane.showMessageDialog(null, "请选择菜谱", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeRecipe changeRecipe = new FrmChangeRecipe(this, "修改菜谱", true);
//			changeIngredients.currentIngredient = this.allIngredients.get(i);
			changeRecipe.currentRecipe = this.curRecipes;
			changeRecipe.setVisible(true);
			this.reloadRecipesTable();
		}

//		添加步骤
		else if (e.getSource() == this.menuItem_AddStep) {
			FrmAddStep addStep = new FrmAddStep(this, "添加步骤信息", true);
			addStep.curRecipe = this.curRecipes;
			addStep.setVisible(true);
			this.reloadStepsTable(this.curRecipes.getRecipe_number() - 1);
		}
//		删除步骤
		else if (e.getSource() == this.menuItem_DeleteStep) {
//			int i = FrmMain.this.dataTableSteps.getSelectedRow();
			if (this.curStep == null) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除步骤?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RecipeManager recipeManager = new RecipeManager();
					recipeManager.deleteStep(this.curStep);
					this.reloadStepsTable(this.curRecipes.getRecipe_number() - 1);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
//		修改步骤描述
		else if (e.getSource() == this.menuItem_ChangeStep) {
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curStep == null) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeStep changeStep = new FrmChangeStep(this, "修改步骤", true);
//			changeIngredients.currentIngredient = this.allIngredients.get(i);
			changeStep.currentStep = this.curStep;
			changeStep.setVisible(true);
			this.reloadStepsTable(this.curRecipes.getRecipe_number() - 1);
		}

//		添加菜谱用料
		else if (e.getSource() == this.menuItem_AddMaterial) {
			FrmAddMaterial addMaterial = new FrmAddMaterial(this, "添加食材信息", true);
			addMaterial.curRecipe = this.curRecipes;
			addMaterial.setVisible(true);
			this.reloadMaterialsTable(this.curRecipes.getRecipe_number() - 1);
		}

//		删除菜谱用料
		else if (e.getSource() == this.menuItem_DeleteMaterial) {
//			int i = FrmMain.this.dataTableMaterials.getSelectedRow();
			if (this.curMaterial == null) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "是否确认删除食材?", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RecipeManager recipeManager = new RecipeManager();
					recipeManager.deleteMaterial(curMaterial);
					this.reloadMaterialsTable(this.curRecipes.getRecipe_number() - 1);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}

//		修改食材原料数量
		else if (e.getSource() == this.menuItem_ChangeMaterial) {
//			int i = FrmMain.this.dataTableIngredients.getSelectedRow();
			if (curMaterial == null) {
				JOptionPane.showMessageDialog(null, "请选择食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmChangeMaterial changeMaterial = new FrmChangeMaterial(this, "修改食材", true);
//			changeIngredients.currentIngredient = this.allIngredients.get(i);
			changeMaterial.currentMaterial = this.curMaterial;
			changeMaterial.setVisible(true);
			this.reloadMaterialsTable(this.curRecipes.getRecipe_number() - 1);
		}

//		修改用户个人信息
		else if (e.getSource() == this.menuItem_changePwd) {
			FrmChangeUserPwd changePwd = new FrmChangeUserPwd(this, "修改密码", true);
			changePwd.currentUser = BeanUser.currentUser;

			changePwd.setVisible(true);
		}

//		查看所有菜谱-user
		else if (e.getSource() == this.menuItem_allRecipe) {
			FrmAllRecipe allRecipe = new FrmAllRecipe(this, "所有菜谱", true);
			allRecipe.setVisible(true);
		}
//		查看所有菜谱-admin
		else if (e.getSource() == this.menuItem_allRecipeAdmin) {
			FrmAllRecipeAdmin allRecipeAdmin = new FrmAllRecipeAdmin(this, "所有菜谱", true);
			allRecipeAdmin.curRecipes = this.curRecipes;
			allRecipeAdmin.setVisible(true);
		}
//		订单统计-user
		else if (e.getSource() == this.menuItem_OrderStatic) {
			FrmOrderStatic orderStatic = new FrmOrderStatic(this, "订单统计", true);
			orderStatic.setVisible(true);
		}
//		订单管理-admin
		else if (e.getSource() == this.menuItem_OrderAdmin) {
			FrmOrderAdmin orderAdmin = new FrmOrderAdmin(this, "订单管理", true);
			orderAdmin.setVisible(true);
//			this.reloadIngredientsTable(this.curCategory.getCategory_number() - 1);			
		}
	}
}
