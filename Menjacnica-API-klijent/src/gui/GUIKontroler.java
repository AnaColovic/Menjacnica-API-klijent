package gui;

import java.awt.EventQueue;
import java.util.LinkedList;

import menjacnica.CurrencyConverterCommunication;
import menjacnica.Zemlja;

public class GUIKontroler {
	private static GlavniProzor glavniProzor;
	private static CurrencyConverterCommunication communication;
	private static LinkedList<String> zemlje;
	
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
	
	public static LinkedList<String> vratiZemlje(){
		return zemlje;
	}
	
}
