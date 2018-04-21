package imageRecognition.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadImage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadImage frame = new UploadImage();
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
	public UploadImage() {
		setTitle("Image Recognition Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(26, 47, 378, 329);
		contentPane.add(lblImage);
		
		JButton btnChooseImage = new JButton("Choose Image");
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images','jpg','gif","png");
				file.addChoosableFileFilter(filter);;
				int result = file.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					lblImage.setIcon(ResizeImage(path));
				} else if(result == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null, "No File Selected");
				}
			}

			private ImageIcon ResizeImage(String ImagePath) {
				ImageIcon MyImage = new ImageIcon(ImagePath);
				Image img = MyImage.getImage();
				Image newImg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(newImg);
				return image;
			}
		});
		btnChooseImage.setBounds(60, 404, 117, 42);
		contentPane.add(btnChooseImage);
	}
}
