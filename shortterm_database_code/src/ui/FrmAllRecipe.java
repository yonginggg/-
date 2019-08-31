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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kitchen.control.RecipeManager;
import kitchen.model.BeanRecipeEvaluation;
import kitchen.model.BeanRecipeInformation;
import kitchen.model.BeanRecipeMaterial;
import kitchen.model.BeanRecipeStep;
import kitchen.model.BeanUser;
import kitchen.util.BaseException;

public class FrmAllRecipe extends JFrame implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JButton btnOrder = new JButton("生成订单");
	private JButton btnEvaluation = new JButton("评价");
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询");

//	用户
	private Object tblUserTitle[] = BeanUser.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel = new DefaultTableModel();
	private JTable dataTableUser = new JTable(tabUserModel);
	private BeanUser curUser = null;
	List<BeanUser> allUsers = null;
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

//	刷新菜谱信息-用户
	private void reloadRecipesTable() {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();
//		curUser = allUsers.get(user_number);
		curUser = BeanUser.currentUser;

		try {
			allRecipes = recipeManager.loadAllRecipe();// 根据用户loadall菜谱
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

	private void reloadTable(){
		try {
			allRecipes=(new RecipeManager()).searchRecipe(this.edtKeyword.getText());
			tblRecipesData =new Object[allRecipes.size()][6];
			for(int i=0;i<allRecipes.size();i++){
				tblRecipesData[i][0]=allRecipes.get(i).getRecipe_number();
				tblRecipesData[i][1]=allRecipes.get(i).getRecipe_name();
				tblRecipesData[i][2]=allRecipes.get(i).getRecipe_description();
				tblRecipesData[i][3]=allRecipes.get(i).getRecipe_overall_rating();
				tblRecipesData[i][4]=allRecipes.get(i).getRecipe_collection_number();
				tblRecipesData[i][5]=allRecipes.get(i).getRecipe_views_number();
				
			}
			tabRecipesModel.setDataVector(tblRecipesData,tblRecipesTitle);
			this.dataTableRecipes.validate();
			this.dataTableRecipes.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	刷新步骤信息-用户
	private void reloadStepsTable(int recipe_number) {// 这是测试数据，需要用实际数替换
		RecipeManager recipeManager = new RecipeManager();
		curRecipes = allRecipes.get(recipe_number);

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
		curRecipes = allRecipes.get(recipe_number);

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
		curRecipes = allRecipes.get(recipe_number);

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

	public FrmAllRecipe(JFrame f, String s, boolean b) {
		
		super();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnOrder);
		toolBar.add(btnEvaluation);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("所有菜谱");
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
//		菜谱
		JScrollPane paneRecipe = new JScrollPane(this.dataTableRecipes);
		paneRecipe.setPreferredSize(new Dimension(1100, 10));
		this.getContentPane().add(paneRecipe, BorderLayout.WEST);

//		鼠标点击 刷新界面 菜谱
		this.dataTableRecipes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmAllRecipe.this.dataTableRecipes.getSelectedRow();
				if (i < 0) {
					return;
				}
				FrmAllRecipe.this.reloadStepsTable(i);
				FrmAllRecipe.this.reloadMaterialsTable(i);
				FrmAllRecipe.this.reloadEvaluationsTable(i);
			}

		});
		this.reloadRecipesTable();

//		步骤
		JScrollPane paneSteps = new JScrollPane(this.dataTableSteps);
		paneSteps.setPreferredSize(new Dimension(60, 0));
		this.getContentPane().add(paneSteps, BorderLayout.CENTER);

//		食材
		JScrollPane paneMaterial = new JScrollPane(this.dataTableMaterials);
//		paneMaterial.setPreferredSize(new Dimension(0, 0));
		this.getContentPane().add(paneMaterial, BorderLayout.EAST);

//		评价
		JScrollPane paneEvaluations = new JScrollPane(this.dataTableEvaluations);
		paneEvaluations.setPreferredSize(new Dimension(1920, 300));
		this.getContentPane().add(paneEvaluations, BorderLayout.SOUTH);

		this.btnOrder.addActionListener(this);
		this.btnEvaluation.addActionListener(this);
		this.btnSearch.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				System.exit(0);
			}
		});
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.reloadTable();
	}
}
