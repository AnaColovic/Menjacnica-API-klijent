package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		LinkedList<String> zemlje = GUIKontroler.vratiZemlje();
		
		JLabel lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
		lblIzValuteZemlje.setBounds(30, 53, 102, 14);
		panel.add(lblIzValuteZemlje);
		
		JLabel lblUValutuZemlje = new JLabel("U valutu zemlje:");
		lblUValutuZemlje.setBounds(249, 53, 102, 14);
		panel.add(lblUValutuZemlje);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(30, 78, 143, 20);
		panel.add(comboBox);
		for(int i=0;i<zemlje.size();i++){
			comboBox.addItem(zemlje.get(i));
		}
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(249, 78, 143, 20);
		panel.add(comboBox_1);
		for(int i=0;i<zemlje.size();i++){
			comboBox_1.addItem(zemlje.get(i));
		}
		
		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(30, 120, 46, 14);
		panel.add(lblIznos);
		
		JLabel lblIznos_1 = new JLabel("Iznos:");
		lblIznos_1.setBounds(249, 120, 46, 14);
		panel.add(lblIznos_1);
		
		textField = new JTextField();
		textField.setBounds(30, 151, 143, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(249, 151, 143, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnKonvertuj = new JButton("Konvertuj");
		btnKonvertuj.setBounds(158, 196, 112, 29);
		panel.add(btnKonvertuj);
	}
}
