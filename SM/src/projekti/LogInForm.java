package projekti;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;
import java.util.Calendar;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogInForm {

	public JFrame frmLogIn;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					LogInForm window = new LogInForm();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public LogInForm() { 
		initialize();
	}

	/**\
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		setFrame(new JFrame());
		getFrame().setUndecorated(true);
		getFrame().getContentPane().setBackground(Color.WHITE);
		getFrame().setBackground(Color.WHITE);
		getFrame().setBounds(550, 130, 337, 379);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		JTextField txtEmail = new JTextField();
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});

		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setToolTipText("");
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 155, 204, 23);
		getFrame().getContentPane().add(txtEmail);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(107, 214, 204, 24);
		getFrame().getContentPane().add(txtPassword);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(35, 158, 83, 20);
		getFrame().getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(35, 214, 83, 20);
		getFrame().getContentPane().add(lblPassword);

		JLabel lblUnvalid = new JLabel("x");
		lblUnvalid.setForeground(Color.WHITE);
		lblUnvalid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnvalid.setBackground(Color.WHITE);
		lblUnvalid.setBounds(319, 180, 18, 20);
		frmLogIn.getContentPane().add(lblUnvalid);

		JLabel lblEmail1 = new JLabel("");
		lblEmail1.setBounds(107, 189, 204, 14);
		frmLogIn.getContentPane().add(lblEmail1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/logo.png")));
		lblNewLabel.setBounds(107, 64, 118, 76);
		frmLogIn.getContentPane().add(lblNewLabel);

		JLabel lblPassword1 = new JLabel("");
		lblPassword1.setBounds(107, 249, 204, 14);
		frmLogIn.getContentPane().add(lblPassword1);

		Button button_1 = new Button("Log in");
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					SignUpForm sgf = new SignUpForm();
					sgf.setVisible(true);
				}
			}
		});
		button_1.setActionCommand("Log in");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Connection conn;
				try {
					conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
							"");
					Statement stm = conn.createStatement();
					Statement stm1= conn.createStatement();
					
					String s = "SELECT * FROM Users WHERE email='" + txtEmail.getText() + "' and password='"
							+ txtPassword.getText() + "'";
					
					String s1 = "SELECT * FROM TeDhenatPersonalePuntorët WHERE email='" + txtEmail.getText() + "' and Password='"
							+ txtPassword.getText() + "'";

					ResultSet rs = stm.executeQuery(s);
					ResultSet rs1 = stm1.executeQuery(s1);
					lblUnvalid.setForeground(Color.WHITE);

					if (rs.next() || rs1.next())
					{
						frmLogIn.dispose();
						 StudentManagement sm=new StudentManagement();
						 
						 sm.lblUser.setText(txtEmail.getText());
						 sm.setVisible(true);
					}

					if (txtEmail.getText().isEmpty()) {
						lblEmail1.setForeground(Color.RED);
						lblEmail1.setText("Please enter email!");
					}

					else
					{
						lblEmail1.setText("");
					}
					
					if (txtPassword.getText().isEmpty()) 
					{
						lblPassword1.setForeground(Color.RED);
						lblPassword1.setText("Please enter password!");
					} 
					else
				    {
						
						lblPassword1.setText("");
						if (!(rs.next())) 
						{
							lblPassword1.setForeground(Color.RED);
							lblPassword1.setText("Wrong email or password");
						}

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBackground(SystemColor.textHighlight);
		button_1.setBounds(35, 284, 276, 38);
		getFrame().getContentPane().add(button_1);

		JLabel lblLogInTo = new JLabel("               Log in to FIEK");
		lblLogInTo.setBackground(new Color(240, 240, 240));
		lblLogInTo.setFont(new Font("Footlight MT Light", Font.BOLD, 25));
		lblLogInTo.setForeground(Color.WHITE);
		lblLogInTo.setBounds(340, 11, 348, 53);
		getFrame().getContentPane().add(lblLogInTo);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 337, 53);
		frmLogIn.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmLogIn.dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ADMIN-01\\Downloads\\icons8-multiply-26.png"));
		lblNewLabel_1.setBounds(301, 11, 26, 29);
		panel.add(lblNewLabel_1);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcome.setBounds(22, 21, 112, 14);
		panel.add(lblWelcome);

		JLabel lblNewLabel_2 = new JLabel("Log In");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(41, 6, 71, 23);

	}

	public JFrame getFrame() {
		return frmLogIn;
	}

	public void setFrame(JFrame frame) {
		this.frmLogIn = frame;
		frmLogIn.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN-01\\Desktop\\icons8-customer-64.png"));
		frmLogIn.setTitle("Log In");
	}
}