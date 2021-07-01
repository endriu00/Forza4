package playGame;

import java.awt.EventQueue;

import gameGUI.GUI;

/**
 * Class for playing the game (read, run the application).
 * 
 * The GUI, as java Swing creators stated, must be inserted in the EventQueue, that is a Queue
 * in which threads run in a continuous way. This is particularly useful for events detection and triggering,
 * so buttons clicked, mouse events and so forth. This is to run the application in a so called "thread safety"
 * 
 * @author andre
 *
 */
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
