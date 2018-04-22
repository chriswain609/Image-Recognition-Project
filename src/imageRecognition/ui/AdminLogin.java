package imageRecognition.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame //implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private static JFrame frame, nextFrame;
	
	JTextField username, password;
	JButton login, back;
	JLabel userLabel, passwordLabel, errorLabel;


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminLogin() 
	{
		setTitle("Admin Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Initialise();
	}
	
	public void Initialise()
	{
		Username();
		Password();
		LoginButton();
		BackButton();
	}
	
	public void Username() 
	{
		userLabel = new JLabel("Username");
		userLabel.setText("Username:");
		userLabel.setBounds(160, 160, 100, 20);
		add(userLabel);
		setLayout(null);
		username = new JTextField(5);
		username.setBounds(230, 161, 100, 20);
		add(username);
	}
	
	public void Password()
	{
		passwordLabel = new JLabel("Password");
		passwordLabel.setText("Password:");
		passwordLabel.setBounds(160, 190, 100, 20);
		add(passwordLabel);
		setLayout(null);
		password = new JTextField(5);
		password.setBounds(230, 191, 100, 20);
		add(password);
	}
	
	public void ErrorMessage()
	{
		errorLabel = new JLabel("Error");
		errorLabel.setText("Incorrect username and/or password.");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(170, 220, 400, 20);
		add(errorLabel);
	}
	
	public void LoginButton()
	{
		login = new JButton("Login");
		login.setBounds(230, 250, 100, 40);
		login.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (username.getText() == "admin" && password.getText() == "password")
					{
//						nextFrame = new AdminPage();
						nextFrame.setVisible(true);
						frame.dispose();
					} else 
					{
						ErrorMessage();
						revalidate();
						repaint();
					}
				} catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
			
		});
		add(login);
	}
	
	public void BackButton()
	{
		back = new JButton("Back");
		back.setBounds(20, 20, 100, 20);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nextFrame = new Home();
					nextFrame.setVisible(true);
					frame.dispose();
					JOptionPane.setRootFrame(nextFrame);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		add(back);
	}
}
