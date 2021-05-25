package gameGUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PureGUI 
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
  	{
		EventQueue.invokeLater(new Runnable() 
   		 {
			public void run() 
     			 {
				try 
       				 {
					PureGUI window = new PureGUI();
					window.frame.setVisible(true);
				} 
        catch (Exception e) 
        {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PureGUI() 
 	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				initializeBoardComponents();

			}
		});
		frame.setVisible(true);
		startGameButton.setBounds(69, 92, 306, 75);
		frame.getContentPane().add(startGameButton);
	}
	
	
	/**
	 * Initialize the contents of the second frame, representing the board.
	 */
	void initializeBoardComponents() 
	{
		frame = new JFrame("Connect Four");
		frame.getContentPane().setName("contentPane");
		frame.getContentPane().setForeground(UIManager.getColor("ToolBar.shadow"));
		frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().setBackground(UIManager.getColor("ToolBar.floatingBackground"));
		frame.getContentPane().setLayout(null);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(21, 17, 1, 1);
		frame.getContentPane().add(horizontalBox);
		
		JButton col1Button = new JButton("1");
		col1Button.setBounds(25, 55, 45, 45);
		frame.getContentPane().add(col1Button);
		
		JButton col2Button = new JButton("2");
		col2Button.setBounds(77, 55, 45, 45);
		frame.getContentPane().add(col2Button);
		
		JButton col3Button = new JButton("3");
		col3Button.setBounds(129, 55, 45, 45);
		frame.getContentPane().add(col3Button);
		
		JButton col4Button = new JButton("5");
		col4Button.setBounds(233, 55, 45, 45);
		frame.getContentPane().add(col4Button);
		
		JButton col5Button = new JButton("4");
		col5Button.setBounds(181, 55, 45, 45);
		frame.getContentPane().add(col5Button);
		
		JButton col6Button = new JButton("6");
		col6Button.setBounds(285, 55, 45, 45);
		frame.getContentPane().add(col6Button);
		
		JButton col7Button = new JButton("7");
		col7Button.setBounds(337, 55, 45, 45);
		frame.getContentPane().add(col7Button);
		
		JLayeredPane panel = new JLayeredPane();
		panel.setForeground(new Color(0, 0, 0));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(21, 102, 367, 259);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel61 = new JPanel();
		panel61.setBackground(UIManager.getColor("ToolBar.highlight"));
		panel61.setBounds(7, 6, 40, 36);
		panel.add(panel61);
		
		JPanel panel51 = new JPanel();
		panel51.setBackground(Color.WHITE);
		panel51.setBounds(7, 48, 40, 36);
		panel.add(panel51);
		
		JPanel panel41 = new JPanel();
		panel41.setBackground(Color.WHITE);
		panel41.setBounds(7, 90, 40, 36);
		panel.add(panel41);
		
		JPanel panel31 = new JPanel();
		panel31.setBackground(Color.WHITE);
		panel31.setBounds(7, 132, 40, 36);
		panel.add(panel31);
		
		JPanel panel21 = new JPanel();
		panel21.setBackground(Color.WHITE);
		panel21.setBounds(7, 174, 40, 36);
		panel.add(panel21);
		
		JPanel panel11 = new JPanel();
		panel11.setBackground(Color.WHITE);
		panel11.setBounds(7, 216, 40, 36);
		panel.add(panel11);
		
		JPanel panel62 = new JPanel();
		panel62.setBackground(Color.WHITE);
		panel62.setBounds(59, 6, 40, 36);
		panel.add(panel62);
		
		JPanel panel52 = new JPanel();
		panel52.setBackground(Color.WHITE);
		panel52.setBounds(59, 48, 40, 36);
		panel.add(panel52);
		
		JPanel panel42 = new JPanel();
		panel42.setBackground(Color.WHITE);
		panel42.setBounds(59, 90, 40, 36);
		panel.add(panel42);
		
		JPanel panel32 = new JPanel();
		panel32.setBackground(Color.WHITE);
		panel32.setBounds(59, 132, 40, 36);
		panel.add(panel32);
		
		JPanel panel22 = new JPanel();
		panel22.setBackground(Color.WHITE);
		panel22.setBounds(59, 174, 40, 36);
		panel.add(panel22);
		
		JPanel panel12 = new JPanel();
		panel12.setBackground(Color.WHITE);
		panel12.setBounds(59, 216, 40, 36);
		panel.add(panel12);
		
		JPanel panel63 = new JPanel();
		panel63.setBackground(Color.WHITE);
		panel63.setBounds(111, 6, 40, 36);
		panel.add(panel63);
		
		JPanel panel53 = new JPanel();
		panel53.setBackground(Color.WHITE);
		panel53.setBounds(111, 48, 40, 36);
		panel.add(panel53);
		
		JPanel panel43 = new JPanel();
		panel43.setBackground(Color.WHITE);
		panel43.setBounds(111, 90, 40, 36);
		panel.add(panel43);
		
		JPanel panel33 = new JPanel();
		panel33.setBackground(Color.WHITE);
		panel33.setBounds(111, 132, 40, 36);
		panel.add(panel33);
		
		JPanel panel23 = new JPanel();
		panel23.setBackground(Color.WHITE);
		panel23.setBounds(111, 174, 40, 36);
		panel.add(panel23);
		
		JPanel panel13 = new JPanel();
		panel13.setBackground(Color.WHITE);
		panel13.setBounds(111, 216, 40, 36);
		panel.add(panel13);
		
		JPanel panel64 = new JPanel();
		panel64.setBackground(Color.WHITE);
		panel64.setBounds(163, 6, 40, 36);
		panel.add(panel64);
		
		JPanel panel54 = new JPanel();
		panel54.setBackground(Color.WHITE);
		panel54.setBounds(163, 48, 40, 36);
		panel.add(panel54);
		
		JPanel panel44 = new JPanel();
		panel44.setBackground(Color.WHITE);
		panel44.setBounds(163, 90, 40, 36);
		panel.add(panel44);
		
		JPanel panel34 = new JPanel();
		panel34.setBackground(Color.WHITE);
		panel34.setBounds(163, 132, 40, 36);
		panel.add(panel34);
		
		JPanel panel24 = new JPanel();
		panel24.setBackground(Color.WHITE);
		panel24.setBounds(163, 174, 40, 36);
		panel.add(panel24);
		
		JPanel panel14 = new JPanel();
		panel14.setBackground(Color.WHITE);
		panel14.setBounds(163, 216, 40, 36);
		panel.add(panel14);
		
		JPanel panel65 = new JPanel();
		panel65.setBackground(Color.WHITE);
		panel65.setBounds(215, 6, 40, 36);
		panel.add(panel65);
		
		JPanel panel55 = new JPanel();
		panel55.setBackground(Color.WHITE);
		panel55.setBounds(215, 48, 40, 36);
		panel.add(panel55);
		
		JPanel panel45 = new JPanel();
		panel45.setBackground(Color.WHITE);
		panel45.setBounds(215, 90, 40, 36);
		panel.add(panel45);
		
		JPanel panel35 = new JPanel();
		panel35.setBackground(Color.WHITE);
		panel35.setBounds(215, 132, 40, 36);
		panel.add(panel35);
		
		JPanel panel25 = new JPanel();
		panel25.setBackground(Color.WHITE);
		panel25.setBounds(215, 174, 40, 36);
		panel.add(panel25);
		
		JPanel panel15 = new JPanel();
		panel15.setBackground(Color.WHITE);
		panel15.setBounds(215, 216, 40, 36);
		panel.add(panel15);
		
		JPanel panel66 = new JPanel();
		panel66.setBackground(Color.WHITE);
		panel66.setBounds(267, 6, 40, 36);
		panel.add(panel66);
		
		JPanel panel56 = new JPanel();
		panel56.setBackground(Color.WHITE);
		panel56.setBounds(267, 48, 40, 36);
		panel.add(panel56);
		
		JPanel panel46 = new JPanel();
		panel46.setBackground(Color.WHITE);
		panel46.setBounds(267, 90, 40, 36);
		panel.add(panel46);
		
		JPanel panel36 = new JPanel();
		panel36.setBackground(Color.WHITE);
		panel36.setBounds(267, 132, 40, 36);
		panel.add(panel36);
		
		JPanel panel26 = new JPanel();
		panel26.setBackground(Color.WHITE);
		panel26.setBounds(267, 174, 40, 36);
		panel.add(panel26);
		
		JPanel panel16 = new JPanel();
		panel16.setBackground(Color.WHITE);
		panel16.setBounds(267, 216, 40, 36);
		panel.add(panel16);
		
		JPanel panel67 = new JPanel();
		panel67.setBackground(Color.WHITE);
		panel67.setBounds(319, 6, 40, 36);
		panel.add(panel67);
		
		JPanel panel57 = new JPanel();
		panel57.setBackground(Color.WHITE);
		panel57.setBounds(319, 48, 40, 36);
		panel.add(panel57);
		
		JPanel panel47 = new JPanel();
		panel47.setBackground(Color.WHITE);
		panel47.setBounds(319, 90, 40, 36);
		panel.add(panel47);
		
		JPanel panel37 = new JPanel();
		panel37.setBackground(Color.WHITE);
		panel37.setBounds(319, 132, 40, 36);
		panel.add(panel37);
		
		JPanel panel27 = new JPanel();
		panel27.setBackground(Color.WHITE);
		panel27.setBounds(319, 174, 40, 36);
		panel.add(panel27);
		
		JPanel panel17 = new JPanel();
		panel17.setBackground(Color.WHITE);
		panel17.setBounds(319, 216, 40, 36);
		panel.add(panel17);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(98, 371, 105, 29);
		frame.getContentPane().add(resetButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(202, 371, 105, 29);
		frame.getContentPane().add(quitButton);
		
		JLabel playerOneLabel = new JLabel("Player one");
		playerOneLabel.setBounds(21, 6, 73, 16);
		frame.getContentPane().add(playerOneLabel);
		
		JLabel playerTwoLabel = new JLabel("Player two");
		playerTwoLabel.setBounds(21, 27, 73, 16);
		frame.getContentPane().add(playerTwoLabel);
		
		frame.setBounds(100, 100, 413, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}

}
