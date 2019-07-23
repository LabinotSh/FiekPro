package projekti;
import java.awt.*;
import javax.swing.*;

import javafx.scene.control.ComboBox;

import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.net.ServerSocket;
import java.net.Socket;


///

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//import net.proteanit.sql.DbUtils;
import projekti1.KlientGUI;


public class StudentManagement extends JFrame {

	private JPanel contentPane;
	public JLabel lblUser;
	private JLabel lblKeepTo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement frame = new StudentManagement();
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
	public StudentManagement() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(280, 65, 750, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1000, 600);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(45, 118, 232));
		panel_1.setBounds(0, 0, 739, 220);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		

		 lblKeepTo = new JLabel("Regjistro studentet");
		lblKeepTo.setFont(new Font("Verdana", Font.BOLD, 16));
		lblKeepTo.setForeground(Color.WHITE);
		lblKeepTo.setBounds(128, 42, 238, 50);
		panel_1.add(lblKeepTo);

		JLabel lblStudentManagement_1 = new JLabel("Menaxho studentet");
		lblStudentManagement_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblStudentManagement_1.setForeground(Color.WHITE);
		lblStudentManagement_1.setBounds(128, 86, 100, 14);
		panel_1.add(lblStudentManagement_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Main.png")));
		lblNewLabel.setBounds(55, 44, 63, 74);
		panel_1.add(lblNewLabel);
		
		 lblUser = new JLabel("");
		lblUser.setBounds(579, 11, 150, 19);
		panel_1.add(lblUser);
		
	
		JPanel panel_2 = new JPanel();
		panel_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Cursor cs = new Cursor(Cursor.HAND_CURSOR);
				panel_2.setCursor(cs);

			}
		});
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				
				if(lblUser.getText().matches("ardiana@hotmail.com"))
				{
				  
				  SignUpForm rgj1 = new SignUpForm();
				
				   rgj1.setVisible(true);
				
				}
				else
				{
					if(Gjuha.gj=="Shqip")
					{
					JOptionPane.showMessageDialog(null, "Nuk keni të drejtë të hyni!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You have no right to log in!");
					}
				}
			}

		});
		panel_2.setBounds(82, 294, 132, 114);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Add User Male.png")));
		label.setBounds(46, 11, 52, 52);
		panel_2.add(label);

		JLabel lblAdminRegister = new JLabel("Regjistro adminet");
		lblAdminRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdminRegister.setForeground(new Color(45, 118, 232));
		lblAdminRegister.setBounds(10, 74, 112, 20);
		panel_2.add(lblAdminRegister);

		JPanel panel_3 = new JPanel();
		panel_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Cursor cs1 = new Cursor(Cursor.HAND_CURSOR);
				panel_3.setCursor(cs1);
			}
		});
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SignUpForm1 sgn1 = new SignUpForm1();
				sgn1.setVisible(true);
			}
		});
		panel_3.setLayout(null);
		panel_3.setBounds(242, 294, 132, 114);
		panel.add(panel_3);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Add User Group.png")));
		label_1.setBounds(46, 11, 52, 52);
		panel_3.add(label_1);

		JLabel lblStudentRegister = new JLabel("Regjistro studentet");
		lblStudentRegister.setForeground(new Color(45, 118, 232));
		lblStudentRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentRegister.setBounds(10, 74, 122, 20);
		panel_3.add(lblStudentRegister);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Cursor cs2 = new Cursor(Cursor.HAND_CURSOR);
				panel_4.setCursor(cs2);
			}
		});
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
				Settings st = new Settings();
				
				st.lblUser1.setText(lblUser.getText());
				st.setVisible(true);
			}
		});
		panel_4.setLayout(null);
		panel_4.setBounds(401, 294, 132, 114);
		panel.add(panel_4);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Settings.png")));
		label_2.setBounds(43, 11, 52, 52);
		panel_4.add(label_2);

		JLabel lblSettings = new JLabel("Rregullo");
		lblSettings.setForeground(new Color(45, 118, 232));
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSettings.setBounds(43, 74, 52, 20);
		panel_4.add(lblSettings);

		JPanel panel_5 = new JPanel();
		panel_5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Cursor cs = new Cursor(Cursor.HAND_CURSOR);
				panel_5.setCursor(cs);
			}
		});
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TeDhenatAdmin tda = new TeDhenatAdmin();
			
				tda.setVisible(true);
			}
		});
		panel_5.setLayout(null);
		panel_5.setBounds(166, 432, 132, 114);
		panel.add(panel_5);

		JLabel lblStafManagment = new JLabel("Menaxho stafin");
		lblStafManagment.setForeground(new Color(45, 118, 232));
		lblStafManagment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStafManagment.setBounds(10, 74, 122, 20);
		panel_5.add(lblStafManagment);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(41, 11, 52, 52);
		panel_5.add(label_3);
		label_3.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Client Company.png")));

		JPanel panel_6 = new JPanel();
		panel_6.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cs = new Cursor(Cursor.HAND_CURSOR);
				panel_6.setCursor(cs);
			}
		});
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				TeDhenatStudent tds = new TeDhenatStudent();
			   tds.setVisible(true);
			}
		});
		panel_6.setLayout(null);
		panel_6.setBounds(321, 432, 132, 114);
		panel.add(panel_6);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/List.png")));
		label_4.setBounds(46, 11, 52, 52);
		panel_6.add(label_4);

		JLabel lblStudentManagement = new JLabel("Menaxho studentet");
		lblStudentManagement.setForeground(new Color(45, 118, 232));
		lblStudentManagement.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentManagement.setBounds(0, 74, 132, 20);
		panel_6.add(lblStudentManagement);

		JPanel panel_7 = new JPanel();
		panel_7.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cs = new Cursor(Cursor.HAND_CURSOR);
				panel_7.setCursor(cs);
			}
		});
		panel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{    

				new projekti1.KlientGUI("localhost", 1500, lblUser.getText());
			}
			
		    
		});
		panel_7.setLayout(null);
		panel_7.setBounds(475, 432, 132, 114);
		panel.add(panel_7);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Undo_50px.png")));
		label_5.setBounds(46, 11, 52, 52);
		panel_7.add(label_5);
		
		JLabel lblChat_1 = new JLabel("Largohu");
		lblChat_1.setForeground(SystemColor.textHighlight);
		lblChat_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChat_1.setBounds(46, 77, 52, 14);
		panel_7.add(lblChat_1);
		
		
		JButton btnShqip = new JButton("Alb");
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuha.gj = "Shqip";
				
				lblKeepTo.setText("Regjistrimi i studentave");
				lblStudentManagement_1.setText("Menaxhimi i studentave");
				lblAdminRegister.setText("Regjistro adminat");
				lblStudentRegister.setText("Regjistro studentat");
				lblStafManagment.setText("Menaxho stafin");
				lblStudentManagement.setText("Menaxho studentat");
				lblSettings.setText("Rregullo");
			}
		});
		btnShqip.setBounds(593, 59, 63, 23);
		panel_1.add(btnShqip);
			
			
		
		
		
		lblStudentManagement.setText("Menaxho studentat");
		
		
		JButton btnAnglisht = new JButton("Eng");
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuha.gj = "Anglisht";
				lblKeepTo.setText("Student register");
				lblStudentManagement_1.setText("Student management");
				lblAdminRegister.setText("Admin register");
				lblStudentRegister.setText("Student register");
				lblStafManagment.setText("Staff management");
				lblStudentManagement.setText("Student management");
				lblSettings.setText("Settings");
				//lblLargohu.setText("Sign out");
			}
		});
		btnAnglisht.setBounds(666, 59, 63, 23);
		panel_1.add(btnAnglisht);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(560, 294, 132, 114);
		panel.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(31, 11, 75, 63);
		panel_8.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("Chat");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(52, 85, 38, 14);
		panel_8.add(lblNewLabel_1);
	}
}
