package projekti;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Settings extends JFrame {
	private JPanel contentPane;
	private JPasswordField txtpassword2;
	private JPasswordField txtpassword3;
	private JPasswordField txtpassword1;
	public JLabel lblUser1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	public Settings() {
		setResizable(false);
		setTitle("Settings");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Settings.class.getResource("/img/Settings.png")));
		setBounds(340, 100, 601, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 118, 232));
		panel.setBounds(0, 0, 585, 75);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setBounds(26, 22, 111, 30);
		panel.add(lblSettings);
		
		lblUser1 = new JLabel("");
		lblUser1.setBounds(397, 33, 167, 14);
		panel.add(lblUser1);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.setForeground(new Color(0, 0, 128));
		panel_1.setBounds(59, 130, 447, 350);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChangePassword.setForeground(new Color(0, 0, 128));
		lblChangePassword.setBounds(35, 114, 207, 20);
		panel_1.add(lblChangePassword);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(35, 84, 361, 2);
		panel_1.add(separator);

		JLabel lblCurrentPass = new JLabel("Current Password");
		lblCurrentPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurrentPass.setBounds(35, 169, 132, 22);
		panel_1.add(lblCurrentPass);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewPassword.setBounds(35, 202, 107, 19);
		panel_1.add(lblNewPassword);

		JLabel lblConfirmation = new JLabel("Confirm password");
		lblConfirmation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmation.setBounds(35, 232, 132, 22);
		panel_1.add(lblConfirmation);

		txtpassword2 = new JPasswordField();
		txtpassword2.setBorder(null);
		txtpassword2.setBackground(SystemColor.menu);
		txtpassword2.setBounds(189, 202, 207, 15);
		panel_1.add(txtpassword2);

		txtpassword3 = new JPasswordField();
		txtpassword3.setBorder(null);
		txtpassword3.setBackground(SystemColor.menu);
		txtpassword3.setBounds(189, 235, 207, 15);
		panel_1.add(txtpassword3);
		
		txtpassword1 = new JPasswordField();
		txtpassword1.setBorder(null);
		txtpassword1.setBackground(SystemColor.menu);
		txtpassword1.setBounds(189, 169, 207, 15);
		panel_1.add(txtpassword1);
		

		JButton btnSave = new JButton("Save");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try
				{
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
						"pass123");
				Statement stm = conn.createStatement();
				Statement stm1 = conn.createStatement();
				
				String sql = "SELECT * FROM Users WHERE email='" + lblUser1.getText() + "' and password='"+ txtpassword1.getText() + "'";
				
				String sql1 = "SELECT * FROM TeDhenatPersonalePuntorët WHERE email='" + lblUser1.getText() + "' and password='"+ txtpassword1.getText() + "'";
				
				ResultSet rs=stm.executeQuery(sql);
				ResultSet rs1=stm1.executeQuery(sql1);
				
				  if(rs.next() || rs1.next())
				  {
					if(txtpassword2.getText().matches(txtpassword3.getText()) && !(txtpassword1.getText().matches(txtpassword2.getText())))
					{
						String sql3="update Users set password='"+txtpassword2.getText()+"' where email='"+lblUser1.getText()+"'";
						String sql4="update TeDhenatPersonalePuntorët set password='"+txtpassword2.getText()+"' where email='"+lblUser1.getText()+"'";
						
						Statement stm3 = conn.createStatement();
						Statement stm4 = conn.createStatement();
						
						stm3.executeUpdate(sql3);
						stm4.executeUpdate(sql4);
						
						JOptionPane.showMessageDialog(null,"Të dhënat u ndryshuan!");
						
					}
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(null,"Konfirmimi i passwordit është gabim!");
				  }
				
				
				}
				 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
				try
				{
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
						"pass123");
				Statement stm = conn.createStatement();
				Statement stm1 = conn.createStatement();
				
				String sql = "SELECT * FROM Users WHERE email='" + lblUser1.getText() + "' and password='"+ txtpassword1.getText() + "'";
				
				String sql1 = "SELECT * FROM TeDhenatPersonalePuntorët WHERE email='" + lblUser1.getText() + "' and password='"+ txtpassword1.getText() + "'";
				
				ResultSet rs=stm.executeQuery(sql);
				ResultSet rs1=stm1.executeQuery(sql1);
				
				  if(rs.next() || rs1.next())
				  {
					if(txtpassword2.getText().matches(txtpassword3.getText()) && !(txtpassword1.getText().matches(txtpassword2.getText())))
					{
						String sql3="update Users set password='"+txtpassword2.getText()+"' where email='"+lblUser1.getText()+"'";
						String sql4="update TeDhenatPersonalePuntorët set password='"+txtpassword2.getText()+"' where email='"+lblUser1.getText()+"'";
						
						Statement stm3 = conn.createStatement();
						Statement stm4 = conn.createStatement();
						
						stm3.executeUpdate(sql3);
						stm4.executeUpdate(sql4);
						
						JOptionPane.showMessageDialog(null,"Të dhënat u ndryshuan!");
						
					}
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(null,"Konfirmimi i passwordit është gabim!");
				  }
				
				
				}
				 catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
			}
				
		});
		btnSave.setIcon(new ImageIcon(Settings.class.getResource("/img/Checkmark_16.png")));

		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBackground(new Color(45, 118, 232));
		btnSave.setBounds(189, 309, 207, 30);
		panel_1.add(btnSave);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(190, 189, 207, 2);
		panel_1.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.GRAY);
		separator_2.setBounds(189, 219, 207, 2);
		panel_1.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBackground(Color.GRAY);
		separator_3.setBounds(189, 252, 207, 2);
		panel_1.add(separator_3);
		
		
		if(Gjuha.gj=="Anglisht")
		{
			lblSettings.setText("Settings");
			lblConfirmation.setText("Confirm password");
			lblChangePassword.setText("Change password");
			lblCurrentPass.setText("Current password");
			lblNewPassword.setText("New password");
			btnSave.setText("Save");
		}
		else
		{
			lblSettings.setText("Rregullo");
			lblConfirmation.setText("Konfirmo fjalkalimin");
			lblChangePassword.setText("Ndrysho fjalkalimin");
			lblCurrentPass.setText("Fjalkalimi aktual ");
			lblNewPassword.setText("Fjalkalimi i ri");
			btnSave.setText("Ruaj");
		}
		
		

	

	}
}