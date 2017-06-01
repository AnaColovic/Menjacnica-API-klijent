package gui;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import menjacnica.CurrencyConverterCommunication;
import menjacnica.Zemlja;

public class GUIKontroler {
	private static GlavniProzor glavniProzor;
	private static CurrencyConverterCommunication communication;
	private static LinkedList<Zemlja> zemlje;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					communication = new CurrencyConverterCommunication();
					zemlje = communication.getZemlje();
					glavniProzor = new GlavniProzor();
					glavniProzor.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static LinkedList<Zemlja> vratiZemlje(){
		return zemlje;
	}
	
	public static double vratiKurs(String q){
		return communication.konverzija(q);
	}
	
	public static void ispisi(){
		JOptionPane.showMessageDialog(glavniProzor, "Ne postoje podaci o konverziji izabranih valuta");
	}
	
	public static void sacuvaj(String izValuta, String uValuta, double kurs){
		communication.sacuvajUFajl(izValuta, uValuta, kurs);
	}
}
