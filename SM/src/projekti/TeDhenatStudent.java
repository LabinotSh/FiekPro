package projekti;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.SingleSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class TeDhenatStudent extends JFrame {

	private JPanel contentPane;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBoxSelection;
	private JSeparator separator;
	private JTextPane textFieldSearch;
	public JLabel lblNewLabel;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeDhenatStudent frame = new TeDhenatStudent();
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
   
	public TeDhenatStudent() {
		setResizable(false);
		setBounds(300, 100, 680, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		Color color = new Color(39, 117, 239);
		panel.setBackground(new Color(39, 117, 239));
		panel.setBounds(0, 0, 680, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Të dhënat e studentëve të regjistruar");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 280, 34);
		panel.add(lblNewLabel);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(StudentManagement.class.getResource("/img/2222_16.png")));
		lblSearch.setBounds(448, 17, 22, 22);
		panel.add(lblSearch);

		comboBoxSelection = new JComboBox();
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] { "Id ", "Emri", "Mbiemri" }));
		comboBoxSelection.setBounds(378, 17, 60, 28);
		panel.add(comboBoxSelection);

		separator = new JSeparator();
		separator.setBounds(458, 43, 169, 2);
		panel.add(separator);

		textFieldSearch = new JTextPane();

		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				try {
					String selection = (String) comboBoxSelection.getSelectedItem();
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
							"pass123");
					String sql = "select * from TeDhenatStudent where " + selection + "=? ";
					pst = conn.prepareStatement(sql);
					// objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
					pst.setString(1, textFieldSearch.getText());
					rs = pst.executeQuery();
					// duhet te behet import rs2xml libraria.
					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te table." + e.getMessage());
				}

			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSearch.setBorder(null);
		textFieldSearch.setBackground(new Color(45, 118, 232));
		textFieldSearch.setBounds(472, 21, 155, 21);
		panel.add(textFieldSearch);
		
		 label = new JLabel("");
		label.setBounds(282, 23, 60, 14);
		panel.add(label);
		
		

		JButton btnNewButton = new JButton("Shfaq te dhenat");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjektiKnk?allowPublicKeyRetrieval=true&useSSL=false", "root",
							"pass123");
					String sql = "select * from TeDhenatStudent";
					pst = conn.prepareStatement(sql);
					// objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
					rs = pst.executeQuery();
					// duhet te behet import rs2xml libraria.
					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te table." + e.getMessage());
				}
			}

		});
		btnNewButton.setBounds(404, 286, 227, 31);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(39, 117, 239));
		contentPane.add(btnNewButton);
		
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 83, 588, 192);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);			
		
		if(Gjuha.gj == "Anglisht") {
			lblNewLabel.setText("Data student register");
			btnNewButton.setText("Display data");
		}
		else {
			lblNewLabel.setText("Të dhënat e studentëve të regjistruar");
			btnNewButton.setText("Shfaq te dhenat");
		}
		
}
}
