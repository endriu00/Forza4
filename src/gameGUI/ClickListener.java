package gameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ClickListener implements ActionListener {
	
	JFrame fr; 
	public ClickListener(JFrame frame) {
		fr = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		fr.dispose();
	}
}
