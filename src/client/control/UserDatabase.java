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
	        con.close();
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
}
