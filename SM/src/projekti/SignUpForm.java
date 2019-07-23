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
import java.util.regex.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class SignUpForm extends JFrame 
{

private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	public JLabel lblGjuha;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm frame = new SignUpForm();
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
	public boolean isItValidEmailAddress(String email) {
        String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public boolean isValidEmri(String emri) {
        String ePattern = "^[a-zA-Z]*$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emri);
        return m.matches();
	}
	
	public boolean isValidMbiemri(String mbiemri) {
        String ePattern = "^[a-zA-Z]*$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(mbiemri);
        return m.matches();
	}
	
	public boolean isValidUsername(String username) {
        String ePattern = "^[a-zA-Z0-9]*$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(username);
        return m.matches();
	}
	public boolean isValidVitiLindjes(String vitiLindjes) {
        String ePattern = "^[0-9]*$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(vitiLindjes);
        return m.matches();
	}
	
	public SignUpForm() 
	{
		setResizable(false);
		setBounds(400, 60, 600, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		Color color = new Color(39,117,239);
		panel.setBackground(new Color(39, 117, 239));
		panel.setBounds(0, 0, 600, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regjistro adminët");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 300, 34);
		panel.add(lblNewLabel);
		
	    lblGjuha = new JLabel("");
		lblGjuha.setBounds(465, 23, 87, 14);
		
		panel.add(lblGjuha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 70, 555, 513);
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(null);
		
		JLabel lblEmri = new JLabel("Emri :");
		lblEmri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmri.setBounds(72, 30, 144, 25);
		panel_1.add(lblEmri);
		
		

		
		JLabel lblName = new JLabel("Name is invalid !");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(391, 36, 130, 14);
		panel_1.add(lblName);
		
		txtEmri = new JTextField();
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(isValidEmri(txtEmri.getText()))) {
					lblName.setForeground(Color.RED);
				}
				else {
					lblName.setForeground(Color.WHITE);
				}
			}
		});
		txtEmri.setColumns(10);
		txtEmri.setBounds(175, 32, 195, 25);
		panel_1.add(txtEmri);
		
		JLabel lblMbiemri = new JLabel("Mbiemri :");
		lblMbiemri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMbiemri.setBounds(72, 66, 144, 25);
		panel_1.add(lblMbiemri);
		
		
		JLabel lblLastName = new JLabel("Last name is invalid !");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(391, 72, 130, 14);
		panel_1.add(lblLastName);
		
		txtMbiemri = new JTextField();
		txtMbiemri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(isValidMbiemri(txtMbiemri.getText()) != true) {
					lblLastName.setForeground(Color.RED);
				}
				else {
					lblLastName.setForeground(Color.WHITE);
				}
			}
		});
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(175, 66, 195, 25);
		panel_1.add(txtMbiemri);
		
		JLabel lblPasswordi = new JLabel("Passwordi :");
		lblPasswordi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswordi.setBounds(72, 102, 144, 25);
		panel_1.add(lblPasswordi);
		
		JLabel lblGjinia = new JLabel("Gjinia :");
		lblGjinia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGjinia.setBounds(72, 138, 90, 25);
		panel_1.add(lblGjinia);
		
		JRadioButton rdbM = new JRadioButton("M");
		buttonGroup.add(rdbM);
		rdbM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbM.setBounds(175, 137, 37, 27);
		panel_1.add(rdbM);
		
		JRadioButton rdbF = new JRadioButton("F");
		buttonGroup.add(rdbF);
		rdbF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbF.setBounds(226, 137, 37, 27);
		panel_1.add(rdbF);
		
		JLabel lblGjini = new JLabel("You must select one !");
		lblGjini.setForeground(Color.WHITE);
		lblGjini.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGjini.setBounds(391, 144, 130, 14);
		panel_1.add(lblGjini);
		
		JRadioButton rdbTjeter = new JRadioButton("Tjeter");
		buttonGroup.add(rdbTjeter);
		rdbTjeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbTjeter.setBounds(292, 137, 78, 27);
		panel_1.add(rdbTjeter);
		
		
		JLabel lblEmail_1 = new JLabel("Email :");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail_1.setBounds(72, 174, 144, 25);
		panel_1.add(lblEmail_1);
		
		JLabel lblEmail = new JLabel("Email is invalid !");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(391, 180, 119, 14);
		panel_1.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(isItValidEmailAddress(txtEmail.getText()) != true) {
					lblEmail.setForeground(Color.RED);
				}
				else {
					lblEmail.setForeground(Color.WHITE);
				}
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 177, 195, 25);
		panel_1.add(txtEmail);
		
		JLabel lblVitiILindjes = new JLabel("Viti i lindjes :");
		lblVitiILindjes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVitiILindjes.setBounds(72, 246, 144, 25);
		panel_1.add(lblVitiILindjes);
		
		JLabel lblBirthYear = new JLabel("Birth year is invalid !");
		lblBirthYear.setForeground(Color.WHITE);
		lblBirthYear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBirthYear.setBounds(391, 252, 119, 14);
		panel_1.add(lblBirthYear);
		
		JLabel lblPerkatesiaEtnike = new JLabel("Perkatesia :");
		lblPerkatesiaEtnike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPerkatesiaEtnike.setBounds(72, 210, 144, 25);
		panel_1.add(lblPerkatesiaEtnike);
		
		JComboBox cmbPerkatesia = new JComboBox();
		cmbPerkatesia.setModel(new DefaultComboBoxModel(new String[] {"Perkatesia Etnike", "Shqiptarë", "Serb", "Tjeter"}));
		cmbPerkatesia.setBounds(175, 210, 195, 25);
		panel_1.add(cmbPerkatesia);
		
		
		JLabel lblNewLabel_1 = new JLabel("Password must have 8 and more characters");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(391, 109, 130, 14);
		panel_1.add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtPassword.getText().length() < 8) {
					lblNewLabel_1.setForeground(Color.RED);
				}
				else {
					lblNewLabel_1.setForeground(Color.WHITE);
				}
			}
		});
		txtPassword.setBounds(175, 102, 195, 25);
		panel_1.add(txtPassword);
		
		JComboBox cmbDrejtimi = new JComboBox();
		cmbDrejtimi.setModel(new DefaultComboBoxModel(new String[] {"Drejtimi\t\t", "Elektroenergjetik", "Automatik e Kompjuterizuar dhe Robotik", "Elektronik", "Inxhinieri Kompjuterike", "Telekomunikacion"}));
		
		cmbDrejtimi.setBounds(175, 282, 195, 25);
		panel_1.add(cmbDrejtimi);
		
		JLabel lblDrejtimi = new JLabel("Drejtimi :");
		lblDrejtimi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrejtimi.setBounds(72, 282, 144, 25);
		panel_1.add(lblDrejtimi);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(175, 246, 195, 20);
		panel_1.add(dateChooser);
		

		
		JButton btnRegjistro = new JButton("Regjistro");
		btnRegjistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
					String date=sd.format(dateChooser.getDate());
					if(isValidEmri(txtEmri.getText()) && isValidMbiemri(txtMbiemri.getText()) && isItValidEmailAddress(txtEmail.getText())) {
					
					
				
					String s="";
					try
					{
				    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
							"pass123");
				    Statement stm=conn.createStatement();
				    if(rdbM.isSelected())
				    {
				       s="M";
				    }
				    else if (rdbF.isSelected())
				    {
				    	s="F";
				    }
				    else if(rdbTjeter.isSelected())
				    {
				    	s="Tjeter";
				    }
				    String s1="insert into TeDhenatPuntorët(Emri,Mbiemri,Drejtimi,Gjinia,VitiLindjes,PerkatesiaEtnike) "
				    		+ "values('"+txtEmri.getText()+"','"+txtMbiemri.getText()+"','"+cmbDrejtimi.getSelectedItem()+"','"+s+"','"+date+"','"+cmbPerkatesia.getSelectedItem()+"')";
				    String s2="insert into TeDhenatPersonalePuntorët(Email,Password) values('"+txtEmail.getText()+"','"+txtPassword.getText()+"')";
				    
				    
				    
				    stm.executeUpdate(s1);
				    stm.executeUpdate(s2);
				    
				    JOptionPane.showMessageDialog(null,"Te dhenat u insetuan!");
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null,e.getMessage());
					}
					
					
						
					}
					else
					{	
						JOptionPane.showMessageDialog(null, "Te gjitha fushat duhet te plotesohen!");
					}
				}
			}
		});
		btnRegjistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			
			{
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
				String date=sd.format(dateChooser.getDate());
				if(isValidEmri(txtEmri.getText()) && isValidMbiemri(txtMbiemri.getText()) && isItValidEmailAddress(txtEmail.getText())) {
				
				
			
				String s="";
				try
				{
			    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
						"pass123");
			    Statement stm=conn.createStatement();
			    if(rdbM.isSelected())
			    {
			       s="M";
			    }
			    else if (rdbF.isSelected())
			    {
			    	s="F";
			    }
			    else if(rdbTjeter.isSelected())
			    {
			    	s="Tjeter";
			    }
			    String s1="insert into TeDhenatPuntorët(Emri,Mbiemri,Email,Drejtimi,Gjinia,VitiLindjes,PerkatesiaEtnike) "
			    		+ "values('"+txtEmri.getText()+"','"+txtMbiemri.getText()+"','"+txtEmail.getText()+"','"+cmbDrejtimi.getSelectedItem()+"','"+s+"','"+date+"','"+cmbPerkatesia.getSelectedItem()+"')";
			    String s2="insert into TeDhenatPersonalePuntorët(Email,Password) values('"+txtEmail.getText()+"','"+txtPassword.getText()+"')";
			    
			    
			    
			    stm.executeUpdate(s1);
			    stm.executeUpdate(s2);
			    
			    JOptionPane.showMessageDialog(null,"Te dhenat u insetuan!");
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
				
				
					
				}
				else
				{	
					JOptionPane.showMessageDialog(null, "Te gjitha fushat duhet te plotesohen!");
				}
				
			}
				
			
		});
		
		
		btnRegjistro.setForeground(Color.WHITE);
		btnRegjistro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegjistro.setBackground(new Color(39, 117, 239));
		btnRegjistro.setBounds(98, 386, 255, 31);
		panel_1.add(btnRegjistro);
		
		JLabel label = new JLabel("Email is invalid !");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(388, 108, 133, 14);
		panel_1.add(label);
		
		
		if(Gjuha.gj=="Anglisht")
		{
			lblEmri.setText("Name");
			lblMbiemri.setText("Lastname");
			lblPasswordi.setText("Password");
			lblEmail.setText("Email");
			lblGjinia.setText("Gender");
			lblVitiILindjes.setText("Date of birth");
			lblDrejtimi.setText("Department");
			lblPerkatesiaEtnike.setText("Ethnicity");
			btnRegjistro.setText("Add");
			lblNewLabel.setText("Admin register");
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
			lblPerkatesiaEtnike.setText("Perkatesia");
			btnRegjistro.setText("Regjistro");
			lblNewLabel.setText("Regjistro adminet");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
}