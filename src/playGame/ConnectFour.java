package playGame;

import java.awt.EventQueue;

import gameGUI.GUI;

public class ConnectFour {	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}	
}
