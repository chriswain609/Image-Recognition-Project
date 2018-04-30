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
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JTextField;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	public JTable imagesTable;
	private JTable analysisTable;
	private JTextField filterTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AdminPage();
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
		
		/* *************** DATABASE TABLE ******************* */
	
		Vector<String> columnNamesDB = new Vector<String>();
        Vector<Object> dataDB = new Vector<Object>();
        contentPane = new JPanel();   //
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
                columnNamesDB.addElement(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                Vector<Object> row = new Vector<Object>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                dataDB.addElement(row);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable databaseTable = new JTable(dataDB, columnNamesDB);
        TableColumn column;
        for (int i = 0; i < databaseTable.getColumnCount(); i++) {
            column = databaseTable.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
        contentPane.setLayout(null);
        JScrollPane tablePane = new JScrollPane(databaseTable);        
        contentPane.add(tablePane);
        tablePane.setBounds(50, 70, 300, 350);
        JFrame frame = new JFrame();
        frame.setTitle("Admin Page");
        frame.getContentPane().add(contentPane);         				//adding panel to the frame
        frame.setBounds(300, 300, 642, 580); 							//setting frame size
        frame.setVisible(true);  										//setting visibility true
        
        /* ***************** END OF TABLE ******************* */
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			Home.getLogin().setVisible(true);
        			frame.dispose();
        		}catch(Exception e1) {
        			JOptionPane.showMessageDialog(null, e1);
        		}
        	}
        });
        btnLogOut.setBounds(26, 12, 98, 23);
        contentPane.add(btnLogOut);
        
        /* *************** ANALYSIS TABLE ******************* */
        
        JScrollPane analysisPane = new JScrollPane();
        analysisPane.setBounds(402, 124, 202, 296);
        contentPane.add(analysisPane);
        // Sets the row header.
        int data [] = null;
        String[]  rowNames = {"No. of images", "No. correct", "Average Confidence", "Av. conf. of correct", 
        		"Av. conf. of incorrect", "Percentage correct"};
        
        int row1 = 0;
        int row2 = 0;
        int row3 = 0;
        int row4 = 0;
        int row5 = 0;
        int row6 = 0;
        
        int numImages = 0;
        int numCorrect = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://mysql.csc.liv.ac.uk:3306/u5af", "u5af", "");
            
            String sqlRow1 = "SELECT * FROM images";
            String sqlRow2 = "SELECT * FROM images WHERE correct = 'Y'";
            String sqlRow3 = "SELECT confidence FROM images";
            String sqlRow4 = "SELECT confidence FROM images WHERE correct = 'Y'";
            String sqlRow5 = "SELECT confidence FROM images WHERE correct = 'N'";
            
            
            Statement statement = con.createStatement();
            int columns;
            ResultSet resultSet;
            ResultSetMetaData metaData;
            
            resultSet = statement.executeQuery(sqlRow1);
            metaData = resultSet.getMetaData();
            columns = metaData.getColumnCount();
            for (int i = 0; i <= columns; i++) {
               numImages++;
            }
            row1 = numImages;
            
            resultSet = statement.executeQuery(sqlRow2);
            metaData = resultSet.getMetaData();
            columns = metaData.getColumnCount();
            for (int i = 0; i <= columns; i++) {
               numCorrect++;
            }
            row2 = numCorrect;
        
         
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        analysisTable = new JTable();
        analysisTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{rowNames[0], row1},
        		{rowNames[1], row2},
        		{rowNames[2], row3},
        		{rowNames[3], row4},
        		{rowNames[4], row5},
        		{rowNames[5], row6},
        	},
        	new String[] {
        		"Descriptives", "Data"
        	}
        ));
        analysisTable.getColumnModel().getColumn(0).setPreferredWidth(118);
        analysisTable.getColumnModel().getColumn(0).setMinWidth(120);
        analysisTable.setRowHeight(0, 50);
        analysisTable.setRowHeight(1, 50);
        analysisTable.setRowHeight(2, 50);
        analysisTable.setRowHeight(3, 50);
        analysisTable.setRowHeight(4, 50);
        analysisTable.setRowHeight(5, 50);
        
        analysisPane.setColumnHeaderView(analysisTable);
       
        /* *************** END OF TABLE ******************* */
        
        JLabel lblAnalysisBox = new JLabel("Analysis of selected images");
        lblAnalysisBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnalysisBox.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnalysisBox.setBounds(392, 87, 212, 26);
        contentPane.add(lblAnalysisBox);
        
        JLabel lblTagFilter = new JLabel("Tag filter:");
        lblTagFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTagFilter.setBounds(60, 431, 72, 23);
        contentPane.add(lblTagFilter);
        
        filterTextBox = new JTextField();
        filterTextBox.setBounds(131, 434, 106, 20);
        contentPane.add(filterTextBox);
        filterTextBox.setColumns(10);
        
        JButton btnAnalyse = new JButton("Analyse");
        btnAnalyse.setBounds(261, 433, 89, 23);
        contentPane.add(btnAnalyse);
        
	}
}

