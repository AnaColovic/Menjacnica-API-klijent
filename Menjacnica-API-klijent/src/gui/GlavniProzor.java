package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import menjacnica.Zemlja;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JButton btnKonvertuj;
	LinkedList<Zemlja> zemlje = GUIKontroler.vratiZemlje();
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
		
		
		
		lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
		lblIzValuteZemlje.setBounds(30, 53, 102, 14);
		panel.add(lblIzValuteZemlje);
		
		lblUValutuZemlje = new JLabel("U valutu zemlje:");
		lblUValutuZemlje.setBounds(249, 53, 102, 14);
		panel.add(lblUValutuZemlje);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(30, 78, 143, 20);
		panel.add(comboBox);
		for(int i=0;i<zemlje.size();i++){
			comboBox.addItem(zemlje.get(i).getName());
		}
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(249, 78, 143, 20);
		panel.add(comboBox_1);
		for(int i=0;i<zemlje.size();i++){
			comboBox_1.addItem(zemlje.get(i).getName());
		}
		
		lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(30, 120, 46, 14);
		panel.add(lblIznos);
		
		lblIznos_1 = new JLabel("Iznos:");
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
		
		btnKonvertuj = new JButton("Konvertuj");
		btnKonvertuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double kurs = GUIKontroler.vratiKurs(preuzmiIzComboBox());
				System.out.println(kurs);
				int iznos = Integer.parseInt(textField.getText());
				if(kurs==0){
					GUIKontroler.ispisi();
				} else {
				textField_1.setText(""+iznos*kurs);
				}
			}
		});
		btnKonvertuj.setBounds(158, 196, 112, 29);
		panel.add(btnKonvertuj);
	}
	
	private String preuzmiIzComboBox(){
		String q = "";
		for(int i=0;i<zemlje.size();i++){
			if(comboBox.getSelectedItem().equals(zemlje.get(i).getName())){
				q += zemlje.get(i).getCurrencyId()+"_";
			}
			if(comboBox_1.getSelectedItem().equals(zemlje.get(i).getName())){
				q +=zemlje.get(i).getCurrencyId();
			}
		}
		return q;
	}
}
