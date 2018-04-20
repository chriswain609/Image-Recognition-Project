// Credit: StackOverflow - https://stackoverflow.com/questions/35410171/how-to-insert-image-to-mysql-database-in-netbeans-java

import java.sql.*;
import java.io.*;

public class InsertImagesMysql{

	public static void main(String[] args){
		System.out.println("Insert Image Example!");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.csc.liv.ac.uk:3306/";
		String dbName = "u5af";
		String userName = "u5af";
		String password = "";
		Connection con = null;
		try{
		   Class.forName(driverName);
		   con = DriverManager.getConnection(url+dbName,userName,password);
		   Statement st = con.createStatement();
		   File imgfile = new File("H:/Pictures/maxresdefault.jpg");

		  FileInputStream fin = new FileInputStream(imgfile);

		   PreparedStatement pre =
		   con.prepareStatement("insert into images values(?,?,?,?,?)");

		   pre.setString(1,"789");
		   pre.setInt(2,3);
		   pre.setBinaryStream(3,(InputStream)fin,(int)imgfile.length());
		   pre.setInt(4,69);
		   pre.setString(5,"Y");
		   pre.executeUpdate();
		   System.out.println("Successfully inserted the file into the database!");

		   pre.close();
		   con.close(); 
		}catch (Exception e1){
			System.out.println(e1.getMessage());
		}
	}
}