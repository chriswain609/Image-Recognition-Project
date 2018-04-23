package imageRecognition.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	
	private static JFrame frame;
	
	private static AdminLogin login;
	
	public static AdminLogin get() {
		return login;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Home();
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
	public Home() {
		setTitle("Image Recognition Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login = new AdminLogin();
				login.setVisible(true);
				frame.dispose();
			}
		});
		btnAdmin.setBounds(78, 178, 146, 32);
		contentPane.add(btnAdmin);
		
		JButton btnImageRecognition = new JButton("Image Recognition");
		btnImageRecognition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UploadImage imageUp = new UploadImage();
					imageUp.setVisible(true);
					frame.dispose();
				}catch(Exception e1) {
					//JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnImageRecognition.setBounds(244, 178, 146, 32);
		contentPane.add(btnImageRecognition);
	}
}
