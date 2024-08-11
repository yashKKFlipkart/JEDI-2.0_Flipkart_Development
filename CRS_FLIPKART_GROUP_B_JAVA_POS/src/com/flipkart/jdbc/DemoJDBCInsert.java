package com.flipkart.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DemoJDBCInsert {
	public static void main(String args[])
	{ 
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_flipfit","root","sanjeev-flipkart");
		PreparedStatement stmt=con.prepareStatement("insert into employee values(?,?,?)");											
		stmt.setInt(1,101);//1 specifies the first parameter in the query
		stmt.setString(2,"Ratan"); stmt.setString(3, "Delhi"); int
		i=stmt.executeUpdate(); System.out.println(i+" records inserted");
		con.close(); }
		catch(Exception e)
		{
		System.out.println(e);}
	}
}

