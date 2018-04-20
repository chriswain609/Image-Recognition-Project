// Credit: StackOverflow - https://stackoverflow.com/questions/35410171/how-to-insert-image-to-mysql-database-in-netbeans-java

import java.io.*;
import java.sql.*;

public class RetriveImagesMysql{
	
	public static void main(String[] args){
		System.out.println("Retrive Image Example!");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.csc.liv.ac.uk:3306/";
		String dbName = "u5af";
		String userName = "u5af";
		String password = "";
		Connection con = null;
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select image from images");
			int i = 0;
			while (rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				OutputStream f = new FileOutputStream(new File("test"+i+".jpg"));
				i++;
				int c = 0;
				while ((c = in.read()) > -1) {
					f.write(c);
				}
				f.close();
				in.close();
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}