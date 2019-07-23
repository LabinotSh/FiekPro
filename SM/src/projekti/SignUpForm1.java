package projekti;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.regex.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUpForm1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JLabel lblUsername_Err;
	private JLabel lblEmri_Err;
	private JLabel lblMbiemri_Err;
	private JLabel lblPassword_Err;
	private JLabel lblGjinia_Err;
	private JLabel lblEmail_Err;
	private JLabel lblPerkatesia_Err;
	private JLabel lblViti_Err;
	private JLabel lblKomuna_Err;
	private JLabel lblDrejtimi_Err;
	public JLabel lbllabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm1 frame = new SignUpForm1();
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

	public SignUpForm1() {
		setResizable(false);
		setBounds(352, 105, 567, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		Color color = new Color(39, 117, 239);
		panel.setBackground(new Color(39, 117, 239));
		panel.setBounds(0, 0, 600, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Regjistro studentët");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(75, 11, 200, 34);
		panel.add(lblNewLabel);

		JLabel lblNewLabel1 = new JLabel("");
		panel.add(lblNewLabel1);
		lblNewLabel1.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/Main.png")));
		lblNewLabel1.setBounds(10, 0, 63, 60);
		
		lbllabela = new JLabel("");
		lbllabela.setBounds(432, 23, 95, 14);
		panel.add(lbllabela);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 70, 600, 513);
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(null);

		JLabel lblEmri = new JLabel("Emri :");
		lblEmri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmri.setBounds(45, 25, 144, 25);
		panel_1.add(lblEmri);

		txtEmri = new JTextField();
		txtEmri.setColumns(10);
		txtEmri.setBounds(147, 27, 195, 25);
		panel_1.add(txtEmri);

		JLabel lblMbiemri = new JLabel("Mbiemri :");
		lblMbiemri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMbiemri.setBounds(45, 66, 144, 25);
		panel_1.add(lblMbiemri);

		txtMbiemri = new JTextField();
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(147, 63, 195, 25);
		panel_1.add(txtMbiemri);

		JLabel lblPasswordi = new JLabel("Fjalkalimi :");
		lblPasswordi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswordi.setBounds(45, 102, 144, 25);
		panel_1.add(lblPasswordi);

		JLabel lblGjinia = new JLabel("Gjinia :");
		lblGjinia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGjinia.setBounds(45, 138, 144, 25);
		panel_1.add(lblGjinia);

		JRadioButton rdbM = new JRadioButton("M");
		buttonGroup.add(rdbM);
		rdbM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbM.setBounds(147, 138, 37, 27);
		panel_1.add(rdbM);

		JRadioButton rdbF = new JRadioButton("F");
		buttonGroup.add(rdbF);
		rdbF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbF.setBounds(203, 137, 37, 27);
		panel_1.add(rdbF);

		JRadioButton rdbTjeter = new JRadioButton("Tjeter");
		buttonGroup.add(rdbTjeter);
		rdbTjeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbTjeter.setBounds(264, 137, 78, 27);
		panel_1.add(rdbTjeter);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(45, 174, 144, 25);
		panel_1.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(147, 174, 195, 25);
		panel_1.add(txtEmail);

		JLabel lblVitiILindjes = new JLabel("Viti i lindjes :");
		lblVitiILindjes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitiILindjes.setBounds(45, 246, 144, 25);
		panel_1.add(lblVitiILindjes);

		JLabel lblPerkatesiaEtnike = new JLabel("Perkatesia :");
		lblPerkatesiaEtnike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPerkatesiaEtnike.setBounds(45, 210, 144, 25);
		panel_1.add(lblPerkatesiaEtnike);

		JComboBox cmbPerkatesia = new JComboBox();
		cmbPerkatesia
				.setModel(new DefaultComboBoxModel(new String[] { "Perkatesia Etnike", "Shqiptar", "Serb", "Tjeter" }));
		cmbPerkatesia.setBounds(147, 210, 195, 25);
		panel_1.add(cmbPerkatesia);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(147, 102, 195, 25);
		panel_1.add(txtPassword);

		JLabel lblEmail_Err = new JLabel("");
		lblEmail_Err.setBounds(351, 185, 187, 14);
		lblEmail_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblEmail_Err);

		JLabel lblUsername_Err = new JLabel("");
		lblUsername_Err.setBounds(352, 41, 206, 14);
		lblUsername_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblUsername_Err);

		JLabel lblEmri_Err = new JLabel("");
		lblEmri_Err.setBounds(352, 36, 172, 14);
		lblEmri_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblEmri_Err);

		JLabel lblMbiemri_Err = new JLabel("");
		lblMbiemri_Err.setBounds(352, 66, 172, 14);
		lblMbiemri_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblMbiemri_Err);

		JLabel lblPassword_Err = new JLabel("");
		lblPassword_Err.setBounds(352, 102, 172, 14);
		lblPassword_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPassword_Err);

		JLabel lblGjinia_Err = new JLabel("");
		lblGjinia_Err.setBounds(348, 138, 172, 14);
		lblGjinia_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblGjinia_Err);

		JLabel lblPerkatesia_Err = new JLabel("");
		lblPerkatesia_Err.setBounds(352, 221, 172, 14);
		lblPerkatesia_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblPerkatesia_Err);

		JLabel lblViti_Err = new JLabel("");
		lblViti_Err.setBounds(352, 258, 172, 14);
		panel_1.add(lblViti_Err);

		JLabel lblKomuna_Err = new JLabel("");
		lblKomuna_Err.setBounds(351, 293, 172, 14);
		lblKomuna_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblKomuna_Err);

		JLabel lblDrejtimi_Err = new JLabel("");
		lblDrejtimi_Err.setBounds(352, 331, 172, 14);
		lblDrejtimi_Err.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblDrejtimi_Err);

		JComboBox cmbDrejtimi = new JComboBox();
		cmbDrejtimi.setModel(new DefaultComboBoxModel(
				new String[] { "Drejtimi\t\t", "Elektroenergjetik", "Automatik e Kompjuterizuar dhe Robotik",
						"Elektronik", "Inxhinieri Kompjuterike", "Telekomunikacion" }));

		JComboBox cmbKomuna = new JComboBox();
		cmbKomuna.setModel(new DefaultComboBoxModel(new String[] { "Prishtin\u00EB", "Pej\u00EB", "Prizren",
				"Mitrovica", "Gjakova", "Gjilani ", "Ferizaji" }));
		cmbKomuna.setBounds(147, 282, 195, 25);
		panel_1.add(cmbKomuna);

		cmbDrejtimi.setBounds(147, 320, 195, 25);
		panel_1.add(cmbDrejtimi);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 246, 195, 20);
		panel_1.add(dateChooser);

		JButton btnRegjistro = new JButton("Regjistro");
		btnRegjistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
				String date1=sd.format(dateChooser.getDate());
				String s = "";
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
									"pass123");
					Statement stm = conn.createStatement();

					if (rdbM.isSelected()) {
						s = "M";
					} else if (rdbF.isSelected()) {
						s = "F";
					} else if (rdbTjeter.isSelected()) {
						s = "Tjeter";
					}
					String s1 = "insert into TeDhenatStudent(Emri,Mbiemri,Email,Drejtimi,Gjinia,VitiLindjes,Komuna,PerkatesiaEtnike) "
							+ "values('" + txtEmri.getText() + "','" + txtMbiemri.getText() + "','"+ txtEmail.getText() + "','" + cmbDrejtimi.getSelectedItem() + "','" + s + "','" + date1+ "','"
							+ cmbKomuna.getSelectedItem() + "','" + cmbPerkatesia.getSelectedItem() + "')";
					String s2 = "insert into TeDhenatPersonaleStudent(Email,Password) values('"
							+ txtEmail.getText() + "','" + txtPassword.getText() + "')";

					stm.executeUpdate(s1);
					stm.executeUpdate(s2);

					JOptionPane.showMessageDialog(null, "Të dhënat u regjistruan me sukses!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}


				

			}
		});
		btnRegjistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
				String date1=sd.format(dateChooser.getDate());
				String s = "";
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
									"pass123");
					Statement stm = conn.createStatement();

					if (rdbM.isSelected()) {
						s = "M";
					} else if (rdbF.isSelected()) {
						s = "F";
					} else if (rdbTjeter.isSelected()) {
						s = "Tjeter";
					}
					String s1 = "insert into TeDhenatStudent(Emri,Mbiemri,Email,Drejtimi,Gjinia,VitiLindjes,Komuna,PerkatesiaEtnike) "
							+ "values('" + txtEmri.getText() + "','" + txtMbiemri.getText() + "','"+ txtEmail.getText() + "','" + cmbDrejtimi.getSelectedItem() + "','" + s + "','" + date1+ "','"
							+ cmbKomuna.getSelectedItem() + "','" + cmbPerkatesia.getSelectedItem() + "')";
					String s2 = "insert into TeDhenatPersonaleStudent(Email,Password) values('"
							+ txtEmail.getText() + "','" + txtPassword.getText() + "')";

					stm.executeUpdate(s1);
					stm.executeUpdate(s2);

					JOptionPane.showMessageDialog(null, "Të dhënat u regjistruan me sukses!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}


				if (txtEmri.getText().isEmpty()) {
					lblEmri_Err.setForeground(Color.RED);
					lblEmri_Err.setText("Emri duhet shënuar!");
				} else {
					char[] strEmri = txtEmri.getText().toCharArray();
					for (int i = 0; i < strEmri.length; i++) {
						if (Character.isDigit(strEmri[i])) {
							lblEmri_Err.setText("Nuk duhet të përmbajë numra!");
							break;
						} else {
							lblEmri_Err.setText("");

						}
					}
				}

				if (txtMbiemri.getText().isEmpty()) {
					lblMbiemri_Err.setForeground(Color.RED);
					lblMbiemri_Err.setText("Mbiemri duhet shënuar!");
				} else {
					char[] strMbiemri = txtMbiemri.getText().toCharArray();
					for (int i = 0; i < strMbiemri.length; i++) {
						if (Character.isDigit(strMbiemri[i])) {
							lblMbiemri_Err.setText("Nuk duhet të përmbajë numra!");
							break;
						} else {
							lblMbiemri_Err.setText("");

						}
					}
				}
				if (txtPassword.getText().isEmpty()) {
					lblPassword_Err.setForeground(Color.RED);
					lblPassword_Err.setText("Passwordi duhet shënuar!");
				} else {
					if (txtPassword.getText().length() < 8) {
						lblPassword_Err.setForeground(Color.RED);
						lblPassword_Err.setText("8 ose më shumë karaktere!");

					} else {
						lblPassword_Err.setText("");

					}
				}

				if ((rdbM.isSelected() == true) || (rdbF.isSelected() == true) || (rdbTjeter.isSelected() == true)) {
					lblGjinia_Err.setText("");
				} else {
					lblGjinia_Err.setForeground(Color.RED);
					lblGjinia_Err.setText("Duhet të zgjedhet një opsion!");
				}

				if (txtEmail.getText().isEmpty()) {
					lblEmail_Err.setForeground(Color.RED);
					lblEmail_Err.setText("Email duhet shënuar!");
				}

				else {
					if (txtEmail.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}")) {
						lblEmail_Err.setForeground(Color.RED);
						lblEmail_Err.setText("Email nuk është në rregull");
					} else {
						lblEmail_Err.setText("");
					}
				}
				if ((cmbPerkatesia.getSelectedItem() == "Shqiptar") || (cmbPerkatesia.getSelectedItem() == "Serb")
						|| (cmbPerkatesia.getSelectedItem() == "Tjeter")) {
					lblPerkatesia_Err.setText("");
				} else {
					lblPerkatesia_Err.setForeground(Color.RED);
					lblPerkatesia_Err.setText("Duhet të zgjedhet një opsion!");
				}

				if ((cmbKomuna.getSelectedItem() == "Prishtinë") || (cmbKomuna.getSelectedItem() == "Pejë") || (cmbKomuna.getSelectedItem() == "Prizren")
						|| (cmbKomuna.getSelectedItem() == "Mitrovica") || (cmbKomuna.getSelectedItem() == "Gjakova") || (cmbKomuna.getSelectedItem() == "Gjilani")|| (cmbKomuna.getSelectedItem() == "Ferizaji")) {
					lblKomuna_Err.setText("");
				} else {
					lblKomuna_Err.setForeground(Color.RED);
					lblKomuna_Err.setText("Duhet të zgjedhet një opsion!");
				}

				if ((cmbDrejtimi.getSelectedItem() == "Elektroenergjetik") || (cmbDrejtimi.getSelectedItem() == "Automatik e Kompjuterizuar dhe Robotik")
						|| (cmbDrejtimi.getSelectedItem() == "Elektronik")|| (cmbDrejtimi.getSelectedItem() == "Inxhinieri Kompjuterike") || (cmbDrejtimi.getSelectedItem() == "Telekomunikacion")) {
					lblDrejtimi_Err.setText("");
				} else {
					lblDrejtimi_Err.setForeground(Color.RED);
					lblDrejtimi_Err.setText("Duhet të zgjedhet një opsion!");
				}
				
				
				Date date;
				date = dateChooser.getDate();
				if(date == null)
				{
					lblViti_Err.setVisible(true);
					
				}else
				{
					lblViti_Err.setVisible(false);
				}


			}

		});

		btnRegjistro.setForeground(Color.WHITE);
		btnRegjistro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegjistro.setBackground(new Color(39, 117, 239));
		btnRegjistro.setBounds(69, 425, 264, 31);
		panel_1.add(btnRegjistro);

		JLabel lblKomuna = new JLabel("Komuna :");
		lblKomuna.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKomuna.setBounds(45, 282, 144, 25);
		panel_1.add(lblKomuna);

		JLabel lblDrejtimi = new JLabel("Drejtimi :");
		lblDrejtimi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrejtimi.setBounds(45, 317, 144, 25);
		panel_1.add(lblDrejtimi);
		
		
		if(Gjuha.gj=="Anglisht")
		{
			lblEmri.setText("Name");
			lblMbiemri.setText("Lastname");
			lblPasswordi.setText("Password");
			lblEmail.setText("Email");
			lblGjinia.setText("Gender");
			lblVitiILindjes.setText("Date of birth");
			lblDrejtimi.setText("Department");
			lblKomuna.setText("Municipality");
			lblPerkatesiaEtnike.setText("Ethnicity");
			btnRegjistro.setText("Add");
			lblNewLabel.setText("Register student");
			
		}
		else
		{
			lblEmri.setText("Emri");
			lblMbiemri.setText("Mbiemri");
			lblPasswordi.setText("Fjalkalimi");
			lblEmail.setText("Email");
			lblGjinia.setText("Gjinia");
			lblVitiILindjes.setText("Viti i lindjes");
			lblDrejtimi.setText("Drejtimi");
			lblKomuna.setText("Komuna");
			lblPerkatesiaEtnike.setText("Perkatesia");
			btnRegjistro.setText("Regjistro");
			lblNewLabel.setText("Regjistro studentet");
			
		}
		

	}
}