package cn.edu.zucc.booklib;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.booklib.util.DBUtil;

public class IndexTest_initData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			//��д���룬�����ݿ������1000�����ߣ�����idΪ:r1-r1000����������Ϊ������1-����1000�����������������ݿ���ȡһ��
			//��д���룬�����ݿ���������1000���������Զ���
			//��д���룬�������û����������鼮������returnDateҲ�õ�ǰʱ��
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
