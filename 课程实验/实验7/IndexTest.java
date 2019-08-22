package cn.edu.zucc.booklib;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.booklib.util.DBUtil;

public class IndexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			long begin=System.currentTimeMillis();
			//��д���룬����ѡ��һ�����ߣ���ѯ�ö��ߵĽ��ĵ�ͼ�������������
			
			System.out.println("��ʱ��"+(System.currentTimeMillis()-begin)+"����");
			
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
