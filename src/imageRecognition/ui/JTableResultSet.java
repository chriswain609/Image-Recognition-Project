package imageRecognition.mySQL;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTableResultSet {
    public static void main(String[] args) {
        Vector<String> columnNames = new Vector<String>();
        Vector<Object> data = new Vector<Object>();
        JPanel panel = new JPanel();   //
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://mysql.csc.liv.ac.uk:3306/u5af", "u5af", "");
            String sql = "Select label, tag, confidence, correct from images";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                data.addElement(row);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable table = new JTable(data, columnNames);
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
        JScrollPane scrollPane = new JScrollPane(table);        panel.add(scrollPane);               
        JFrame frame = new JFrame();
        frame.add(panel);         //adding panel to the frame
        frame.setSize(600, 400); //setting frame size
        frame.setVisible(true);  //setting visibility true
    }
}
