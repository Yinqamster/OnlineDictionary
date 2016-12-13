package client.control;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDatabase {
	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/dictionary";
	private final String userName = "root";
	private final String userPassword = "mysql12345";
	
//	static int user_id = 0;
	
	Connection con = null;
	Statement stmt = null;
	
	
	public boolean createConnection() {
		try{
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, userName, userPassword);
	//		insert();
			System.out.println("数据库连接成功");
			return true;
		}
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		
		
	}
	
	public void insert(String name, String password) {
	    String sql = "insert into user_information (user_name,password) values(?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, password);
	        pstmt.executeUpdate();
	        pstmt.close();
	//        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void setStateOn(String name) {
		String sql = "update user_information set state = 1 where user_name = " + "'" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	//        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void setStateOff(String name) {
		String sql = "update user_information set state = 0 where user_name = " + "'" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	//        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean nameExists(String name) {
		String sql = "select user_name from user_information where user_name = '" + name + "'";
		PreparedStatement pstmt;
		try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rset = pstmt.executeQuery();
	        
	     /*   while (rset.next()) {
	        	if (name.equals(rset.getString(1))) {
	        		return true;
	        	}
	        	else {
	        		return false;
	        	}
	        }*/
	        
	        if (rset.next()) {
	        	return true;
	        }
	        else {
	        	return false;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}
	
	public boolean passwordCorrectly(String name, String password) {
		String sql = "select password from user_information where user_name = '" + name + "'";
		PreparedStatement pstmt;
		try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rset = pstmt.executeQuery();
	        
	        if (rset.next()) {
	        	if (rset.getString(1).equals(password)) {
	        		return true;
	        	}
	        	else {
	        		return false;
	        	}
	        }
	        else {
	        	return false;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}
	
	/*
	public static void main(String[] args) {
		UserDatabase ud = new UserDatabase();
		ud.createConnection();
		ud.insert();
	}*/
	public String getOnlineUser() {
		String sql = "select user_name from user_information where state = 1";
	    PreparedStatement pstmt;
	    String user = "";
	    try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rset = pstmt.executeQuery();
             
	        while (rset.next()) {
	        	user = user + rset.getString(1) + " ";
	        }
	//        System.out.println(user);
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
	
	public String getOfflineUser() {
		String sql = "select user_name from user_information where state = 0";
	    PreparedStatement pstmt;
	    String user = "";
	    try {
	        pstmt = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rset = pstmt.executeQuery();
             
	        while (rset.next()) {
	        	user = user + rset.getString(1) + " ";
	        }
	//        System.out.println(user);
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
	
	public String getZan(String ap, String word) {
		String numLike = "";
		PreparedStatement pstmt;
		String sql = "select " + ap + " from likeNum where word = " + "'" + word + "'";
//		String sql = "select youdao from likeNum where word = '" + word + "'";
		try{
			
			pstmt = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rset = pstmt.executeQuery();
	        if (rset.next()) {
	        	numLike = rset.getString(1);
	        }
	        else {
	        	numLike = "0";
	        }
	        pstmt.close();
	/*		if (ap.equals("baidu")) {
				String sql = "select baidu from user_like where word = " + "'" + word + "'";
				pstmt = (PreparedStatement) con.prepareStatement(sql);
		        ResultSet rset = pstmt.executeQuery();
		        numLike = rset.getString(1);
			}
			else if (ap.equals("youdao")) {
				String sql = "select youdao from user_like where word = " + "'" + word + "'";
				pstmt = (PreparedStatement) con.prepareStatement(sql);
		        ResultSet rset = pstmt.executeQuery();
		        numLike = rset.getString(1);
			}
			else if (ap.equals("bing")) {
				String sql = "select bing from user_like where word = " + "'" + word + "'";
				pstmt = (PreparedStatement) con.prepareStatement(sql);
		        ResultSet rset = pstmt.executeQuery();
		        numLike = rset.getString(1);
			}*/
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }	
		return numLike;
	}
	
	public void writeZan(String ap, String word, String user) {
		
		String sql0 = "select word from likeNum where word = " + "'" + word + "'";
		String sql1 = "update likeNum set " + ap + " = " + ap + " + 1 where word = " + "'" + word + "'";
		String sql2 = "insert into likeNum (word) values(" + "'" + word + "'" + ")";
		
		String sql3 = "select word from user_like where word = " + "'" + word + "'" + " and user_name = " + "'" + user + "'";
		String sql4 = "update user_like set " + ap + " = 1 where user_name = " + "'" + user + "'" + " and word = " + "'" + word + "'";
		String sql5 = "insert into user_like (user_name, word) values(" + "'" + user + "' , '" + word + "'" + ")";
		String sql6 = "select " + ap + " from user_like where word = " + "'" + word + "'" + " and user_name = " + "'" + user + "'";
		
		PreparedStatement pstmt0;
		
	    
	    PreparedStatement pstmt3;
	    PreparedStatement pstmt4;
	    
	    try {
	    	//user_like 表中数据
	    	int zan = 0;
	    	pstmt3 = (PreparedStatement) con.prepareStatement(sql3);
	        ResultSet rset3 = pstmt3.executeQuery();
	        if (!rset3.next()) {
	        	PreparedStatement pstmt5;
	        	pstmt5 = (PreparedStatement) con.prepareStatement(sql5);
	        	pstmt5.executeUpdate();
	        	pstmt5.close();
	        }
	        else {
	        	PreparedStatement pstmt5;
	        	pstmt5 = (PreparedStatement) con.prepareStatement(sql6);
	        	ResultSet rset5 = pstmt5.executeQuery();
	        	String res = "";
	        	if (rset5.next()) {
	        		res = rset5.getString(1);
	        	}
	        	if(res.equals("1")) {
	        		zan = 1;
	        	}
	        }
	        pstmt4 = (PreparedStatement) con.prepareStatement(sql4);
	        pstmt4.executeUpdate();
	    	
	    	
	    	pstmt0 = (PreparedStatement) con.prepareStatement(sql0);
	        ResultSet rset0 = pstmt0.executeQuery();
	        if (!rset0.next()) {
	    //    	numLike = rset.getString(1);
	        	PreparedStatement pstmt2;
	        	pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
		        pstmt2.executeUpdate();
		        pstmt2.close();
	        }
	        if (zan == 0) {
	        	PreparedStatement pstmt1;
		        pstmt1 = (PreparedStatement) con.prepareStatement(sql1);
		        pstmt1.executeUpdate();
		        pstmt1.close();
	        }
	        
	        
	        
	        
	        pstmt0.close();
	        
	        
	        pstmt3.close();
	        pstmt4.close();
	        
	//        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
