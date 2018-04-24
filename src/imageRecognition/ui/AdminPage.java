package imageRecognition.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public JTable imagesTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Home.getLogin().setVisible(true);
					AdminLogin.getAdmin().setVisible(false);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnBack.setBounds(26, 12, 98, 23);
		contentPane.add(btnBack);
		
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
        JScrollPane scrollPane = new JScrollPane(table);        
        panel.add(scrollPane);               
//        JFrame frame = new JFrame();
//        frame.add(panel);         //adding panel to the frame
//        frame.setSize(600, 400); //setting frame size
//        frame.setVisible(true);  //setting visibility true
	}
}
