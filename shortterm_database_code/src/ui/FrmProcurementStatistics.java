package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Array;

import kitchen.control.IngredientsManager;
import kitchen.control.IngredientsProcurementManager;
import kitchen.model.BeanIngredientsCategory;
import kitchen.model.BeanIngredientsInformation;
import kitchen.model.BeanIngredientsProcurement;
import kitchen.model.BeanRecipeInformation;
import kitchen.util.*;

public class FrmProcurementStatistics  extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnChangeStatus = new JButton("修改状态");
	private Object tblTitle[]={"采购单编号","食材编号","数量","状态","员工编号"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	List<BeanIngredientsProcurement> records = new ArrayList<BeanIngredientsProcurement>();
	private void reloadTable(){
		try {
			records=(new IngredientsProcurementManager()).loadAll();
			tblData =new Object[records.size()][5];
			for(int i=0;i<records.size();i++){
				tblData[i][0]=records.get(i).getProcurement_number();
				tblData[i][1]=records.get(i).getIngredients_number();
				tblData[i][2]=records.get(i).getQuantity();
				tblData[i][3]=records.get(i).getProcurement_status();
				tblData[i][4]=records.get(i).getAdministrator_number();
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public FrmProcurementStatistics(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnChangeStatus);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnChangeStatus.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnChangeStatus){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择采购单","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanIngredientsProcurement.currentProcurement = this.records.get(i);
			FrmChangeProcurementStatus changeProcurementStatus = new FrmChangeProcurementStatus(this, "修改状态",true);
			changeProcurementStatus.setVisible(true);
			this.reloadTable();
			
		}
	}
} 
