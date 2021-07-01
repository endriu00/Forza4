package gameGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.filechooser.FileNameExtensionFilter;

import customException.*;
import gameComponents.*;
import saveAndLoad.LoadInterface;
import saveAndLoad.LoadMatch;
import saveAndLoad.SaveMatch;

/**
 * This class represents the GUI (Graphical User Interface), from which a user can control and play the game. 
 */
public class GUI extends JFrame implements ActionListener {
	     
	private JFrame biggestFrame;	//Frame containing the UI
    private JPanel mainPanel;		//mainPanel
    private JPanel colorsTopPanel;	//Panel containing the colors
    
    private Container gameContainer;	 // Container 

    private JPanel topPanel;		// Panel containing players and turn
    private JPanel boardPanel;		// Panel containing the board cells
    private JPanel[][] boardCellsPanel;	 // Panel (array) representing the game board
    private JPanel turnPanel;		// Panel containing the turn info 
    private JPanel bottomButtonPanel;		 // Panel containing reset, quit and save buttons 
    private JPanel columnsButtonPanel;		 // Panel containing the column buttons
    private JPanel gameBoardPanel;		// Panel containing the game board
    private JPanel playerPanel;			// Panel containing players
    private JPanel endGamePanel;			// Panel containing end label
    private JPanel informativePanel;	//Panel containing information about the match
    private JPanel downPanel;			//Panel containing bottomButtonPanel and informativePanel
    
    private JLabel playerOneLabel;	 // Label for player one
    private JLabel playerTwoLabel;	// Label for player two
    private JLabel turnLabel;		// Label displaying the turn
    private JLabel informativeLabel;	//Label containing information about the match
    private JLabel welcomeLabel;	//Label welcoming user

    private JButton startGameButton; // Start button
    private JButton startMatchButton; // Start match button
    private JButton quitButton;		// Quit button
    private JButton resetButton;	 // Reset button
    private JButton saveButton;		// Save button
    private JButton loadButton;		// Load button
    private JButton[] columnButtons;	 // Buttons for each column
    private JButton[][] colors = new JButton[6][6];	//Button matrix for color chooser functionality


    private JTextField playerOneField;		// Field for player one's name
    private JTextField playerTwoField;		// Field for player two's name
        
    private Match match;
    private Token firstPlayerToken; 
    private Token secondPlayerToken;
    private Player firstPlayer;
    private Player secondPlayer;
    
    private boolean isEnded;			//If match is ended, isEnded is set to true, false otherwise
    private int playerOneFieldCounter;	//Counter for letting disappear text on JTextField playerOneField
    private int playerTwoFieldCounter;  //Counter for letting disapper text on JTextField playerTwoField
    
    /**
     * A custom font, namely gamingFont because it reminds old pixel graphics games.
     */
    private Font gamingFont;
    
    /**
     * Constants for custom font size.
     * Every component of the UI will have a size that is one of these values.
     */
    private static final float SMALLER_SIZE = 13.f;
    private static final float MEDIUM_SIZE = 24.f;
    private static final float LARGER_SIZE = 34.f;
    private static final float SMALLEST_SIZE = 11.0f;
    
    /**
     * Border variable that deletes components border
     */
    private Border emptyBorder = BorderFactory.createEmptyBorder();

    /**
     * Loading file resources.
     * Interface is used to apply Observer design pattern.
     */
    private File loadingFile;
    private LoadInterface loadListener;
    
    /**
     * Variable used as a mutex in the color grid shared resource 
     */
    private Player mutexPlayer;
    
    /**
     * Gets the dimension of the screen on which the application is displayed
     */
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = (int) screenSize.getWidth();
    private final int HEIGHT = (int) screenSize.getHeight();
    
    /**
     * Palette colors used in the interface colors.
     */
    private static final Color PALETTE_COLOR_1 = new Color(235, 245, 247);
    private static final Color PALETTE_COLOR_2 = new Color(193, 91, 120);
    private static final Color PALETTE_COLOR_3 = new Color(91, 176, 186);
    private static final Color PALETTE_COLOR_4 = new Color(255, 200, 230);
    
    /**
     * Colors a player can select for his token
     */
    private static final Color TOKEN_COLOR_1 = new Color(20, 20, 20);
    private static final Color TOKEN_COLOR_2 = new Color(205, 245, 247);
    private static final Color TOKEN_COLOR_3 = new Color(193, 91, 120);
    private static final Color TOKEN_COLOR_4 = new Color(91, 176, 186);
    private static final Color TOKEN_COLOR_5 = new Color(255, 200, 230);
    private static final Color TOKEN_COLOR_6 = new Color(175, 43, 118);
    private static final Color TOKEN_COLOR_7 = new Color(255, 0, 255);
    private static final Color TOKEN_COLOR_8 = new Color(90, 90, 90);
    private static final Color TOKEN_COLOR_9 = new Color(120, 120, 120);
    private static final Color TOKEN_COLOR_10 = new Color(150, 150, 150);
    private static final Color TOKEN_COLOR_11 = new Color(180, 180, 180);
    private static final Color TOKEN_COLOR_12 = new Color(210, 210, 210);
    private static final Color TOKEN_COLOR_13 = new Color(0, 205, 155);
    private static final Color TOKEN_COLOR_14 = new Color(110, 0, 0);
    private static final Color TOKEN_COLOR_15 = new Color(140, 0, 0);
    private static final Color TOKEN_COLOR_16 = new Color(170, 0, 0);
    private static final Color TOKEN_COLOR_17 = new Color(220, 0, 0);
    private static final Color TOKEN_COLOR_18 = new Color(255, 0, 0);
    private static final Color TOKEN_COLOR_19 = new Color(0, 255, 71);
    private static final Color TOKEN_COLOR_20 = new Color(0, 110, 0);
    private static final Color TOKEN_COLOR_21 = new Color(0, 140, 0);
    private static final Color TOKEN_COLOR_22 = new Color(0, 170, 0);
    private static final Color TOKEN_COLOR_23 = new Color(0, 220, 0);
    private static final Color TOKEN_COLOR_24 = new Color(0, 255, 0);
    private static final Color TOKEN_COLOR_25 = new Color(212, 220, 80);
    private static final Color TOKEN_COLOR_26 = new Color(0, 0, 110);
    private static final Color TOKEN_COLOR_27 = new Color(0, 0, 140);
    private static final Color TOKEN_COLOR_28 = new Color(0, 0, 170);
    private static final Color TOKEN_COLOR_29 = new Color(0, 0, 220);
    private static final Color TOKEN_COLOR_30 = new Color(0, 0, 255);
    private static final Color TOKEN_COLOR_31 = new Color(170, 190, 30);
    private static final Color TOKEN_COLOR_32 = new Color(80, 150, 70);
    private static final Color TOKEN_COLOR_33 = new Color(110, 110, 100);
    private static final Color TOKEN_COLOR_34 = new Color(110, 30, 106);
    private static final Color TOKEN_COLOR_35 = new Color(205, 110, 170);
    private static final Color TOKEN_COLOR_36 = new Color(103, 215, 122);  
     
	/**
	 * Creates the GUI
	 */
	public GUI() {	
		super("Connect Four");
		addFont();
		displayStartGameFrame();
	}
	
	/**
	 * Initialize the actors of the game.
	 */
	public void initializeComponents() {
		isEnded = false;

		firstPlayerToken = new Token(new Color(0, 250, 0));
		secondPlayerToken = new Token(new Color(255, 0, 0));
		firstPlayer = new Player("", firstPlayerToken);
		secondPlayer = new Player("", secondPlayerToken);
		match = new Match(firstPlayer, secondPlayer);

		playerOneFieldCounter = 0;
		playerTwoFieldCounter = 0;		
	}
	
	/**
	 * Display the start/load game frame.
	 */
	private void displayStartGameFrame() {
		
		initializeComponents();
		biggestFrame = new JFrame("Connect Four");
		
		biggestFrame.setMinimumSize(new Dimension(WIDTH/2, HEIGHT - 20));
		biggestFrame.setResizable(false);
		biggestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();	
		
		startGameButton = new JButton("Start Game");
		startGameButton.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		startGameButton.setBackground(PALETTE_COLOR_4);
		startGameButton.setBorder(emptyBorder);
	  	startGameButton.addMouseListener(new EnableHoverOnButton(startGameButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainPanel.setVisible(false);
				initializeMatch();
			}
		});
		
		loadButton = new JButton("     Load      ");
		loadButton.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		loadButton.setBackground(PALETTE_COLOR_4);
		loadButton.setBorder(emptyBorder);
	  	loadButton.addMouseListener(new EnableHoverOnButton(loadButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
        loadButton.addActionListener(this);
               
        welcomeLabel = new JLabel("Welcome to ConnectFour!");
        welcomeLabel.setFont(gamingFont.deriveFont(LARGER_SIZE));
        constraints.ipady = 40;
        constraints.ipadx = 0;
        constraints.gridx = 3;
        constraints.gridy = 0;
		constraints.weighty = 0.6; 
    	constraints.insets = new Insets(50, 20, 0, 10);

        mainPanel.add(welcomeLabel, constraints);
        
        //Sets the button height to a bigger value
        constraints.ipady = 40;
        constraints.ipadx = 180;
        constraints.gridx = 3;
        constraints.gridy = 1;
		constraints.weighty = 0.6; 
    	constraints.insets = new Insets(40, 0, 0, 0);

		mainPanel.add(startGameButton, constraints);
		
		constraints.weighty = 0.2;
		constraints.ipady = 40;
        constraints.ipadx = 180;
        constraints.gridx = 3;
        constraints.gridy = 2;
    	constraints.insets = new Insets(0 ,0 , 80, 0);

		mainPanel.add(loadButton, constraints);
		
		mainPanel.setVisible(true);
		
		mainPanel.setBackground(PALETTE_COLOR_1);
	    biggestFrame.add(mainPanel);
		biggestFrame.setVisible(true);
	}

    /**
     * Initialize match players. 
     */
    private void initializeMatch() {
    	    	
    	mainPanel = new JPanel(new GridBagLayout());
	  	playerPanel = new JPanel(new GridBagLayout());
	  	GridBagConstraints constraints = new GridBagConstraints();
	  	
		createColorChooserGrid();

		
	  	startMatchButton = new JButton("Start");
	  	startMatchButton.setBackground(PALETTE_COLOR_4);
	  	startMatchButton.setBorder(emptyBorder);
	  	startMatchButton.addMouseListener(new EnableHoverOnButton(startMatchButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
	  	startMatchButton.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			mainPanel.setVisible(false);
	  			playerPanel.setVisible(false);
	  			match.whoPlaysFirst();
	  			initializeBoardComponents(biggestFrame);
	  		}
	  	});
	  	
	  	//Useful for user
	  	playerOneField = new JTextField("Insert the name of the first player here!");
	  	//in this JTextField it has been added a mouse listener that deletes its content 
	  	//when user clicks the field, letting him insert his name.
	  	playerOneField.setForeground(PALETTE_COLOR_2);
	  	playerOneField.addMouseListener(new DeleteTextOneMouseListener());
	
	  	//Useful for user
	  	playerTwoField = new JTextField("Insert the name of the second player here!");
	  	//in this JTextField it has been added a mouse listener that deletes its content 
	  	//when user clicks the field, letting him insert his name.
	  	playerTwoField.setForeground(PALETTE_COLOR_2);
	  	playerTwoField.addMouseListener(new DeleteTextTwoMouseListener());
	  	
	  	//Player one label preferences in GUI representation
	  	
	  	constraints.ipadx = 20;
	  	constraints.ipady = 40;
		constraints.gridx = 3;
		constraints.gridy = 0;
		//constraints.insets = new Insets(40, 0, 0, 0);
		JLabel playerOneLabel = new JLabel("Player one:");
		playerOneLabel.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		playerPanel.add(playerOneLabel, constraints);
	  	
	  	//Player one text preferences in GUI representation
	  	constraints.ipady = 40;
		constraints.ipadx = 480;
		constraints.gridx = 1;
		constraints.gridy = 1;
		
		constraints.gridwidth = 3;
		playerOneField.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		playerPanel.add(playerOneField, constraints);
		
		//Player one can choose color with this button
		JButton firstPlayerColor = new JButton("Choose a color");
	  	firstPlayerColor.setFont(gamingFont.deriveFont(SMALLER_SIZE));
	  	firstPlayerColor.setBackground(PALETTE_COLOR_3);
	  	firstPlayerColor.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
		firstPlayerColor.addMouseListener(new EnableHoverOnButton(firstPlayerColor, PALETTE_COLOR_2, PALETTE_COLOR_3));
	  	firstPlayerColor.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			firstPlayerColor.setEnabled(false);
	  			playerPanel.setVisible(false);
	  	        mainPanel.setVisible(false);
	  	        colorsTopPanel.setVisible(true);
	  	        biggestFrame.add(colorsTopPanel);
	  	        showColorGrid(match.getFirstPlayer());
	  		}
	  	});
	  	
	  	constraints.ipady = 0;
	  	constraints.ipadx = 0;
	  	constraints.gridx = 3;
	  	constraints.gridy = 2;
	  	constraints.insets = new Insets(20, 0, 20, 0);
	  	playerPanel.add(firstPlayerColor, constraints);
	  	
	  	//Player two label preferences in GUI representation
	  	constraints.ipadx = 20;
	  	constraints.ipady = 0;
		constraints.gridx = 3;
		constraints.gridy = 3;
		JLabel playerTwoLabel = new JLabel("Player two:");
		playerTwoLabel.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		playerPanel.add(playerTwoLabel, constraints);
	  	
	  	//Player two text preferences in GUI representation
	  	constraints.ipady = 40;
		constraints.ipadx = 480;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(-3, 0, 0, 0);
		playerTwoField.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		playerPanel.add(playerTwoField, constraints);
		
		//Player two can choose color with this button
		JButton secondPlayerColor = new JButton("Choose a color");
		secondPlayerColor.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		secondPlayerColor.setBackground(PALETTE_COLOR_3);
		secondPlayerColor.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
		secondPlayerColor.addMouseListener(new EnableHoverOnButton(secondPlayerColor, PALETTE_COLOR_2, PALETTE_COLOR_3));

		secondPlayerColor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			secondPlayerColor.setEnabled(false);
			playerPanel.setVisible(false);
	        mainPanel.setVisible(false);
	        colorsTopPanel.setVisible(true);
  	        biggestFrame.add(colorsTopPanel);
			showColorGrid(match.getSecondPlayer());
		}
		});
		constraints.ipady = 0;
		constraints.ipadx = 0;
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.insets = new Insets(20, 0, 0, 0);
		playerPanel.add(secondPlayerColor, constraints);
	  	
		//Start match button section continues
	  	constraints.ipady = 40;
	  	constraints.ipadx = 180;
	  	constraints.gridx = 3;
	  	constraints.gridy = 6;
	  	constraints.anchor = GridBagConstraints.PAGE_END;
	  	constraints.insets = new Insets(0, 0, 60, 0);
	  	startMatchButton.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
	  	mainPanel.add(startMatchButton, constraints);
	  	
	  	startMatchButton.addActionListener(new ActionListener() {
	  	  public void actionPerformed(ActionEvent e) {
	  		  String playerOneText = playerOneField.getText();
	  	  	  
	  	  	  //if name was set, so if it is not equal to an empty string
	  	  	  if(!playerOneText.equals("") && !playerOneText.equals("Insert the name of the first player here!")) {
	  	  		  firstPlayer.setName(playerOneText);
	  	  		  firstPlayer = new Player(playerOneText, firstPlayerToken);
	  	  	  }
	  	  	  //set player name to a default string
	  	  	  else {
	  	  		  firstPlayer.setName("first player");
	  	  		  firstPlayer = new Player("first player", firstPlayerToken);
	  	  	  }
	  	  }
	  	});
  	  
	  	startMatchButton.addActionListener(new ActionListener() {
	  	  public void actionPerformed(ActionEvent e) {
	  	  	  String playerTwoText = playerTwoField.getText();
	  	  	  
	  	  	  //if name was set, so if it is not equal to an empty string
	  	  	  if(!playerTwoText.equals("") && !playerTwoText.equals("Insert the name of the second player here!")) {
	  	  		  secondPlayer.setName(playerTwoText);
	  	  		  secondPlayer = new Player(playerTwoText, secondPlayerToken);
	  	  	  }
	  	  	  //set player name to a default string
	  	  	  else {
	  	  		  secondPlayer.setName("second player");
	  	  		  secondPlayer = new Player("second player", secondPlayerToken); 
	  	  	  }
	  	  }
	  	});

	  	match.setFirstPlayer(firstPlayer);
		match.setSecondPlayer(secondPlayer);
		
		playerPanel.setBackground(PALETTE_COLOR_1);
	  	mainPanel.setBackground(PALETTE_COLOR_1);
	
	  	biggestFrame.add(playerPanel, BorderLayout.CENTER);
	  	biggestFrame.add(mainPanel, BorderLayout.SOUTH);
  	 
    }
    
    /**
     * Show the interface from which a player can choose a color for his token.
     * 
     * Note that, for accessing the color grid (that is properly a shared resource), an additional variable is used as a mutex, mutexPlayer.
     * 
     * @param p is the player calling the interface.
     * 
     * @author andre
     */
    public void showColorGrid(Player p) {
    	mutexPlayer = p;
        colorsTopPanel.setVisible(true);    	
    }
    
    /**
     * Sets player's token to the color of the chosen button
     * In order to shorten the code, this method is also concerned with setting visibility
     * of elements in the biggestFrame JFrame (biggestFrame is the main frame of the application).
     * 
     * @param colorButton is the button pressed by the user
     * @param player is the player who has pressed it
     * 
     * @author andre
     */
    public void setColorCorrespondingToButton(JButton colorButton, Player player) {
    	player.setToken(new Token(colorButton.getBackground()));
    	colorButton.setEnabled(false);
    	colorButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
    	colorButton.add(new JLabel("  X ")).setFont(gamingFont.deriveFont(LARGER_SIZE));
		colorsTopPanel.setVisible(false);
		mainPanel.setVisible(true);
		playerPanel.setVisible(true);
    }  
    
    /**
     * Creates the color chooser interface.
     * This view wants to be a reference to game interfaces when choosing a player.
     * It is created a matrix of buttons, each one filled with a different color 
     * (taken from a pool of constants in the field section).
     * Player clicking on a button selects it.
     * Once selected, the player will have his token colored like the clicked button.
     *  
     * @author andre
     */
    public void createColorChooserGrid() {
    	
    	colorsTopPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints constraints = new GridBagConstraints();
    	
    	JPanel labelPanel = new JPanel(new GridBagLayout());	
    	labelPanel.setBackground(PALETTE_COLOR_3);
    	
    	JLabel colorLabel = new JLabel("Click on a color to choose it! You can only choose once");
    	colorLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
    	constraints.gridx = 3;
    	constraints.ipady = 34;
    	labelPanel.add(colorLabel, constraints);
    	
    	constraints.ipadx = 0;
    	constraints.ipady = 0;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 0;
  	  	constraints.weightx = 0.5;
  	    constraints.insets = new Insets(30,10,10,10); 
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
    	colorsTopPanel.add(labelPanel, constraints);
    	    	
    	JPanel colorPanel = new JPanel(new GridLayout(6, 6, 10, 10));
    	    	
    	constraints.ipadx = 20;
    	constraints.ipady = 400;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 1;
  	  	constraints.weighty = 1;
  	    constraints.insets = new Insets(10,10,10,10); 
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
    	colorsTopPanel.add(colorPanel, constraints);
        
        colors[0][0] = new JButton();
        
        colors[0][0].setBackground(TOKEN_COLOR_1);
        colors[0][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][0]);

        colors[0][1] = new JButton();

        colors[0][1].setBackground(TOKEN_COLOR_2);
        colors[0][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][1]);

        colors[0][2] = new JButton();

        colors[0][2].setBackground(TOKEN_COLOR_3);
        colors[0][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][2]);
        
        colors[0][3] = new JButton();
        
        colors[0][3].setBackground(TOKEN_COLOR_4);
        colors[0][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][3]);
        
        colors[0][4] = new JButton();
        
        colors[0][4].setBackground(TOKEN_COLOR_5);
        colors[0][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][4]);
        
        colors[0][5] = new JButton();
        
        colors[0][5].setBackground(TOKEN_COLOR_6);
        colors[0][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[0][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[0][5]);
        
        colors[1][0] = new JButton();
        
        colors[1][0].setBackground(TOKEN_COLOR_7);
        colors[1][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][0]);

        colors[1][1] = new JButton();

        colors[1][1].setBackground(TOKEN_COLOR_8);
        colors[1][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][1]);

        colors[1][2] = new JButton();

        colors[1][2].setBackground(TOKEN_COLOR_9);
        colors[1][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][2]);
        
        colors[1][3] = new JButton();

        colors[1][3].setBackground(TOKEN_COLOR_10);
        colors[1][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][3]);
        
        colors[1][4] = new JButton();
        
        colors[1][4].setBackground(TOKEN_COLOR_11);
        colors[1][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][4]);
        
        colors[1][5] = new JButton();
        
        colors[1][5].setBackground(TOKEN_COLOR_12);
        colors[1][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[1][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[1][5]);
        
        colors[2][0] = new JButton();

        colors[2][0].setBackground(TOKEN_COLOR_13);
        colors[2][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][0]);
        
        colors[2][1] = new JButton();

        colors[2][1].setBackground(TOKEN_COLOR_14);
        colors[2][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][1]);

        colors[2][2] = new JButton();

        colors[2][2].setBackground(TOKEN_COLOR_15);
        colors[2][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][2]);
        
        colors[2][3] = new JButton();

        colors[2][3].setBackground(TOKEN_COLOR_16);
        colors[2][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][3]);

        colors[2][4] = new JButton();
        
        colors[2][4].setBackground(TOKEN_COLOR_17);
        colors[2][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][4]);
        
        colors[2][5] = new JButton();
        
        colors[2][5].setBackground(TOKEN_COLOR_18);
        colors[2][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[2][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[2][5]);        
        
        colors[3][0] = new JButton();

        colors[3][0].setBackground(TOKEN_COLOR_19);
        colors[3][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][0]);
        
        colors[3][1] = new JButton();

        colors[3][1].setBackground(TOKEN_COLOR_20);
        colors[3][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][1]);
        
        colors[3][2] = new JButton();

        colors[3][2].setBackground(TOKEN_COLOR_21);
        colors[3][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][2]);
        
        colors[3][3] = new JButton();

        colors[3][3].setBackground(TOKEN_COLOR_22);
        colors[3][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][3]);
        
        colors[3][4] = new JButton();
        
        colors[3][4].setBackground(TOKEN_COLOR_23);
        colors[3][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][4]);
        
        colors[3][5] = new JButton();
        
        colors[3][5].setBackground(TOKEN_COLOR_24);
        colors[3][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[3][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[3][5]);
        
        colors[4][0] = new JButton();
        
        colors[4][0].setBackground(TOKEN_COLOR_25);
        colors[4][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][0]);
        
        colors[4][1] = new JButton();

        colors[4][1].setBackground(TOKEN_COLOR_26);
        colors[4][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][1]);
        
        colors[4][2] = new JButton();

        colors[4][2].setBackground(TOKEN_COLOR_27);
        colors[4][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][2]);
        
        colors[4][3] = new JButton();

        colors[4][3].setBackground(TOKEN_COLOR_28);
        colors[4][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][3]);
        
        colors[4][4] = new JButton();
        
        colors[4][4].setBackground(TOKEN_COLOR_29);
        colors[4][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][4]);
        
        colors[4][5] = new JButton();
        
        colors[4][5].setBackground(TOKEN_COLOR_30);
        colors[4][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[4][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[4][5]);
        
        colors[5][0] = new JButton();
        
        colors[5][0].setBackground(TOKEN_COLOR_31);
        colors[5][0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][0], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][0]);
        
        colors[5][1] = new JButton();

        colors[5][1].setBackground(TOKEN_COLOR_32);
        colors[5][1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][1], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][1]);
        
        colors[5][2] = new JButton();

        colors[5][2].setBackground(TOKEN_COLOR_33);
        colors[5][2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][2], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][2]);
        
        colors[5][3] = new JButton();

        colors[5][3].setBackground(TOKEN_COLOR_34);
        colors[5][3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][3], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][3]);
        
        colors[5][4] = new JButton();
        
        colors[5][4].setBackground(TOKEN_COLOR_35);
        colors[5][4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][4], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][4]);
        
        colors[5][5] = new JButton();
        
        colors[5][5].setBackground(TOKEN_COLOR_36);
        colors[5][5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setColorCorrespondingToButton(colors[5][5], mutexPlayer);
        	}
        });
        colorPanel.add(colors[5][5]);
       
        colorsTopPanel.setBackground(PALETTE_COLOR_1);
    }
    
    /**
     * Initialize the content of the game board.
     * 
     * @param frame is the frame in which initialize the components
     */
    public void initializeBoardComponents(JFrame frame) 
    {	
    	frame.setVisible(true);
    	gameContainer = new JPanel(new GridBagLayout());
   	  	GridBagConstraints constraints = new GridBagConstraints();

        gameContainer.setVisible(true);
        
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BorderLayout());
        
        //Panel containing playerPanel and turnPanel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        
        playerOneLabel = new JLabel("Player One: " + match.getFirstPlayer().getName());
        playerOneLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        playerPanel.add(playerOneLabel);

        playerTwoLabel = new JLabel("Player Two: " + match.getSecondPlayer().getName());
        playerTwoLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        playerPanel.add(playerTwoLabel);
  
        turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 1, 10, 10));

        turnLabel = new JLabel();
        if(match.getIsFirstPlayerPlaying()) turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn. Turn " + match.getTurn());
        else turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn. Turn " + match.getTurn());
        turnLabel.setHorizontalAlignment(JLabel.RIGHT);
        turnLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        turnPanel.add(turnLabel);

        topPanel.add(playerPanel, BorderLayout.WEST);
        topPanel.add(turnPanel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        createBoard();

        constraints.ipadx = 0;
    	constraints.ipady = 0;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 0;
  	  	constraints.weightx = 0.5;
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
        gameContainer.add(topPanel, constraints);
        
        constraints.ipadx = 20;
    	constraints.ipady = 320;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 1;
  	  	constraints.weighty = 1;
  	    constraints.insets = new Insets(10,10,10,10); 
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
        gameContainer.add(gameBoardPanel, constraints);
        
        /**
         * downPanel section.
         * downPanel contains bottomButtonPanel and informativePanel.
         */
        downPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        //bottomButtonPanel section. It contains the buttons.
        bottomButtonPanel = new JPanel();
        
        quitButton = new JButton("Quit");
        quitButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        quitButton.setBackground(PALETTE_COLOR_1);
        quitButton.setBorder(BorderFactory.createEmptyBorder(5, 22, 5, 22));
		quitButton.addMouseListener(new EnableHoverOnButton(quitButton, PALETTE_COLOR_2, PALETTE_COLOR_1));
        quitButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        resetButton.setBackground(PALETTE_COLOR_1);
        resetButton.setBorder(BorderFactory.createEmptyBorder(5, 22, 5, 22));
		resetButton.addMouseListener(new EnableHoverOnButton(resetButton, PALETTE_COLOR_2, PALETTE_COLOR_1));
        resetButton.addActionListener(this);
        
        saveButton = new JButton("Save");
        saveButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        saveButton.setBackground(PALETTE_COLOR_1);
        saveButton.setBorder(BorderFactory.createEmptyBorder(5, 22, 5, 22));
		saveButton.addMouseListener(new EnableHoverOnButton(saveButton, PALETTE_COLOR_2, PALETTE_COLOR_1));
        saveButton.addActionListener(this);
          
        bottomButtonPanel.remove(startGameButton);
        bottomButtonPanel.remove(quitButton);
        
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(quitButton);
        bottomButtonPanel.add(saveButton);

        //InformativePanel section. It contains the information of the match.
        informativePanel = new JPanel();
        
        informativeLabel = new JLabel("Playing match...");
        informativeLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        informativePanel.add(informativeLabel);
        informativePanel.setVisible(true);
        
        downPanel.add(informativePanel, BorderLayout.NORTH);
        downPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        downPanel.setVisible(true);
        
        constraints.ipadx = 20;
    	constraints.ipady = 30;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 8;
  	  	constraints.anchor = GridBagConstraints.PAGE_END;
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
  	  	constraints.insets = new Insets(10, 10, 10, 10);

        gameContainer.add(downPanel, constraints);
        
        gameContainer.setBackground(PALETTE_COLOR_1);
        bottomButtonPanel.setBackground(PALETTE_COLOR_3);
        informativePanel.setBackground(PALETTE_COLOR_2);
		downPanel.setBackground(PALETTE_COLOR_3);
		playerPanel.setBackground(PALETTE_COLOR_3);
		turnPanel.setBackground(PALETTE_COLOR_3);
		topPanel.setBackground(PALETTE_COLOR_3);

        frame.add(gameContainer);

    }
    
    /** 
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, Board.getNumberOfColumns(), 20, 20));
        columnButtons = new JButton[Board.getNumberOfColumns()];
        columnsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        columnButtons[0] = new JButton(1 + "");
        columnButtons[0].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[0].setBackground(PALETTE_COLOR_3);
        columnButtons[0].setBorder(BorderFactory.createEmptyBorder(5, -1, 5, -1));
        columnButtons[0].addMouseListener(new EnableHoverOnButton(columnButtons[0], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(0);
				}
			}});
        
        columnButtons[1] = new JButton(2 + "");
        columnButtons[1].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[1].setBackground(PALETTE_COLOR_3);
        columnButtons[1].setBorder(emptyBorder);
        columnButtons[1].addMouseListener(new EnableHoverOnButton(columnButtons[1], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(!isEnded) {		
					changer(1);
				}
			}});
        
        columnButtons[2] = new JButton(3 + "");
        columnButtons[2].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[2].setBackground(PALETTE_COLOR_3);
        columnButtons[2].setBorder(emptyBorder);
        columnButtons[2].addMouseListener(new EnableHoverOnButton(columnButtons[2], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(2);
				}
			}});
        
        columnButtons[3] = new JButton(4 + "");
        columnButtons[3].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[3].setBackground(PALETTE_COLOR_3);
        columnButtons[3].setBorder(emptyBorder);
        columnButtons[3].addMouseListener(new EnableHoverOnButton(columnButtons[3], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(3);
				}
			}});
        
        columnButtons[4] = new JButton(5 + "");
        columnButtons[4].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[4].setBackground(PALETTE_COLOR_3);
        columnButtons[4].setBorder(emptyBorder);
        columnButtons[4].addMouseListener(new EnableHoverOnButton(columnButtons[4], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(4);
				}
			}});
        
        columnButtons[5] = new JButton(6 + "");
        columnButtons[5].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[5].setBackground(PALETTE_COLOR_3);
        columnButtons[5].setBorder(emptyBorder);
        columnButtons[5].addMouseListener(new EnableHoverOnButton(columnButtons[5], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(5);
				}
			}});
        
        columnButtons[6] = new JButton(7 + "");
        columnButtons[6].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[6].setBackground(PALETTE_COLOR_3);
        columnButtons[6].setBorder(emptyBorder);
        columnButtons[6].addMouseListener(new EnableHoverOnButton(columnButtons[6], PALETTE_COLOR_4, PALETTE_COLOR_3));
        columnButtons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {			
					changer(6);
				}
			}});
                
        columnsButtonPanel.add(columnButtons[0]);
        columnsButtonPanel.add(columnButtons[1]);
        columnsButtonPanel.add(columnButtons[2]);
        columnsButtonPanel.add(columnButtons[3]);
        columnsButtonPanel.add(columnButtons[4]);
        columnsButtonPanel.add(columnButtons[5]);
        columnsButtonPanel.add(columnButtons[6]);
        
        repaintBoard();
        gameBoardPanel.add(columnsButtonPanel, BorderLayout.NORTH);
        gameBoardPanel.add(boardPanel,BorderLayout.CENTER);   
    }

    /**
     * Method that creates the board view and paints every panel (where the token will be placed in) in a pink color.
     */
	public void repaintBoard() {
		
		boardPanel = new JPanel(new GridLayout(Board.getNumberOfRows(), Board.getNumberOfColumns(), 20, 20));
        boardCellsPanel = new JPanel[Board.getNumberOfRows()][Board.getNumberOfColumns()];
        for (int i = 0; i < Board.getNumberOfRows(); i++) 
        {
            for (int j = 0; j < Board.getNumberOfColumns(); j++) 
            {
                JPanel panel = new JPanel();
                boardCellsPanel[i][j] = panel;
                boardCellsPanel[i][j].setBackground(Color.pink);
                boardPanel.add(boardCellsPanel[i][j]);
            }
        }
	} 
	
	/**
	 * Initialize the end game mainPanel.
	 * 
	 * @param winnerName is the name of the winner, if there was a winner at all. In case of draw, an empty string is passed.
	 */
	private void displayEndGameFrame(String winnerName) 
	{
		endGamePanel = new JPanel(new GridLayout(2, 2, 10, 10));
		JPanel endGamePanelButtons = new JPanel();
		//If the match ended in a draw
		if (match.getBoard().isDraw()) {
			informativeLabel.setText("The match ended in a draw. Thanks for playing!");
			turnLabel.setText("Congratulations both!");
			turnLabel.setForeground(PALETTE_COLOR_1);
			informativeLabel.setForeground(PALETTE_COLOR_1);
		}
		//If someone won the match
		else if (match.getBoard().isWin()) {
			informativeLabel.setText(winnerName + " " + "won! Thanks for playing!");
			turnLabel.setText("Congratulations " + winnerName + "!");
			turnLabel.setForeground(PALETTE_COLOR_1);
			informativeLabel.setForeground(PALETTE_COLOR_1);
	    }	
		
  		startGameButton = new JButton("Start a new Game");
  		startGameButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
  		startGameButton.setBackground(PALETTE_COLOR_1);
  		startGameButton.setBorder(BorderFactory.createEmptyBorder(5, 22, 5, 22));
  		startGameButton.addMouseListener(new EnableHoverOnButton(startGameButton, PALETTE_COLOR_2, PALETTE_COLOR_1));
  		startGameButton.addActionListener(new ActionListener() 
 		{
			public void actionPerformed(ActionEvent e) 
			{	
				isEnded = false;
        
		        bottomButtonPanel.removeAll();
		        informativePanel.removeAll();
				biggestFrame.dispose();
				displayStartGameFrame();
				
				endGamePanel.setVisible(false);
				gameContainer.setVisible(false);
			}
		});
       
        downPanel.setVisible(true);
        bottomButtonPanel.setVisible(false);
        
        //To refresh the view and delete old buttons
        biggestFrame.repaint();
        endGamePanelButtons.add(startGameButton);
        endGamePanelButtons.add(quitButton);
        endGamePanel.add(informativePanel);
        endGamePanel.add(endGamePanelButtons);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 0;
    	constraints.ipady = 0;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 7;
  	  	constraints.anchor = GridBagConstraints.PAGE_END;
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
  	  	constraints.insets = new Insets(10, 10, 10, 10);
    	
    	gameContainer.remove(downPanel);
        gameContainer.add(endGamePanel, constraints);
        endGamePanelButtons.setBackground(PALETTE_COLOR_3);
        endGamePanel.setBackground(PALETTE_COLOR_3);
        endGamePanel.setVisible(true);
		
	}
  
    /**
     * Adds the font used in the whole game.
     * The font used is a custom font, downloaded here: https://www.dafont.com/retro-gaming.font#null
     * Credits to its creator: Daymarius
     * 
     * This method adds the font to the local GraphicsEnvironment.
     * Note that adding this new font is completely system-independent, since the file Retro Gaming.tff 
     * is located in a res directory of this project and there is no dependence to a linux or windows or unix system:
     * the file is located using the File.separator String, that implicitly uses the system file separator.
     * 
     * Last but not least, the font is registered in the GraphicsEnvironment.
     * The font can be used calling component.setFont(gamingFont);
     * 
     * @author andre
     */
    public void addFont(){
    	gamingFont = null;
		try {
			gamingFont = Font.createFont(Font.TRUETYPE_FONT, new File("res" + File.separator + "Retro Gaming.ttf"));
		} catch (FontFormatException | IOException e1) {
		}
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(gamingFont);		    
    }
    

    /**
     * changer is the core method for developing and shipping the functionality of the game.
     * It decides what color to place in what position depending on the player playing the turn,
     * determines if there was a victory when inserting a token in a column and so on.
     * 
     * Note that it does what it is supposed to do connecting itself strictly with the logic
     * of the game, that is separated. It calls gameComponents package methods in order
     * to insert a token in a column, to determine a win or a draw and to get players' names, 
     * just to make some examples.
     * 
     * @param column is the column the changer has to change, or where the action has taken place in the UI.
     * 
     * @author andre
     */
    public void changer(int column) {    	    	
    	boolean isFirstRow = false;
		if (match.getIsFirstPlayerPlaying()) { 
			
			if(boardCellsPanel[Board.getNumberOfRows()-1][column].getBackground().equals(Color.pink)) {
				isFirstRow = true;
				changeColorInBoard(match.getFirstPlayer().getToken(), Board.getNumberOfRows()-1, column);
    		}	
    		
			if(!isFirstRow) {
				int tempRow = 0;
	    		if(!match.getBoard().isColumnFull(column)) {					
	    			if (!boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
	    				changeColorInBoard(match.getFirstPlayer().getToken(), tempRow, column);
	    				//boardCellsPanel[tempRow][column].setBackground(match.getFirstPlayer().getToken().getTokenColor());
	    				try {
	    					match.insertInBoard(match.getFirstPlayer().getToken(), column);
	    					//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
	    					//accordingly, but it is necessary to specify whose turn is next when triggering the event.
	    					match.setTurn(match.getTurn()+1);
	    					turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn. Turn " + match.getTurn());
	    		       		checkResults(match.getFirstPlayer().getName());
	    					match.setFirstPlayerPlaying(!match.getIsFirstPlayerPlaying());
							return;
						} catch (FullColumnException e1) {
							turnLabel.setText(e1.getError());
						}	
	    			}
	    			while(boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
	    				tempRow++;
	    			}
    				changeColorInBoard(match.getFirstPlayer().getToken(), tempRow, column);
	    		}
    		}   		
       		try {
				match.insertInBoard(match.getFirstPlayer().getToken(), column);
				//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
				//accordingly, but it is necessary to specify whose turn is next when triggering the event.
				match.setTurn(match.getTurn()+1);
				turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn. Turn " + match.getTurn());
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;
			}
    		checkResults(match.getFirstPlayer().getName());
			match.setFirstPlayerPlaying(!match.getIsFirstPlayerPlaying());
		}
		
		else {	
			if(boardCellsPanel[Board.getNumberOfRows()-1][column].getBackground().equals(Color.pink)) {				
				isFirstRow = true;
				changeColorInBoard(match.getSecondPlayer().getToken(), Board.getNumberOfRows()-1, column);
    		}	
    		
			if(!isFirstRow){
				int tempRow = 0;
				if(!match.getBoard().isColumnFull(column)) {
					if (!boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
	    				changeColorInBoard(match.getSecondPlayer().getToken(), tempRow, column);
						try {
							match.insertInBoard(match.getSecondPlayer().getToken(), column);
							//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
							//accordingly, but it is necessary to specify whose turn is next when triggering the event.
							match.setTurn(match.getTurn()+1);
							turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn. Turn " + match.getTurn());
							checkResults(match.getSecondPlayer().getName());
							match.setFirstPlayerPlaying(!match.getIsFirstPlayerPlaying());
							return;
						} catch (FullColumnException e1) {
							turnLabel.setText(e1.getError());
						}	
					}
    			while(boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
    				tempRow++;
    			}
    			
				changeColorInBoard(match.getSecondPlayer().getToken(), tempRow, column);
				}
    		}
		
       		try {
				match.insertInBoard(match.getSecondPlayer().getToken(), column);
				//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
				//accordingly, but it is necessary to specify whose turn is next when triggering the event.
				match.setTurn(match.getTurn()+1);
				turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn. Turn " + match.getTurn());
				
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;		
			}
       		checkResults(match.getSecondPlayer().getName());
			match.setFirstPlayerPlaying(!match.getIsFirstPlayerPlaying());
		}	
    }
    
    /**
     * Method for checking if a match has ended in a win or in a draw.
     * @param winnerName is the name of the winning player. If it is a draw, winnerName is an empty string
     * 
     * @author andre
     */
    public void checkResults(String winnerName) {
    	if (match.getBoard().isDraw()) {
	    	isEnded = true;
		  	displayEndGameFrame("");
		}
		else if (match.getBoard().isWin()) {
	    	isEnded = true;
	    	displayEndGameFrame(winnerName);
	   	}
    }
    
	/**
     * Performs actions based on clicked buttons.
     * 
     * @param e is the event triggered by clicking the button
     */
    public void actionPerformed(ActionEvent e) 
    {	
        if (e.getSource() == resetButton) 
        {
        	reloadGame();
        }
        
        else if (e.getSource() == quitButton) 
        {
            biggestFrame.dispose();
        }
        
        else if (e.getSource() == saveButton) 
        {
        	//Prevents player from continue playing
        	isEnded = true;
        	saveGame();	
        }
        
        else if (e.getSource() == loadButton) 
        {
			loadGame();     	
        }
    }       
       
    /**
     * Gets the clicked button.
     * @param e is the event
     * @return an integer representing the clicked column
     */
	public static int getClickedButton(ActionEvent e)
    {
    	JButton button = (JButton)e.getSource();
        int clickedColumn = Integer.parseInt(button.getActionCommand());
        return clickedColumn;	
    }	
	 
    /**
     * Changes the color of the board according to the color of the token passed as input.
     * Position of the board is specified as row, column.
     * 
     * @param token is the token with its color
     * @param row is the row of the board
     * @param col is the column of the board
     * 
     * @author andre
     */
    public void changeColorInBoard(Token token, int row, int col) {
    	boardCellsPanel[row][col].setBackground(token.getTokenColor());
    }
	
	/**
	 * Paints the board with the correct color, basing on the state of the board.
	 * This method is fundamentally the method used when loading a match,
	 * because board coloring is triggered by clicking the column buttons 
	 * of the board, not reproducible when loading a file.
	 * 
	 * @param gameBoard is the gameBoard of the match.
	 * 
	 * @author andre
	 */
	public void paintBoardWithColors(Board gameBoard) {
		for(int r = Board.getNumberOfRows() - 1; r >= 0; r--) {
			for(int c = 0; c < Board.getNumberOfColumns(); c++) {
				if(gameBoard.getTokenInPosition(r, c) != null) {
					changeColorInBoard(gameBoard.getTokenInPosition(r,  c), r, c);
				}
			}
		}
	}
	
	/**
	 * Customizing method for a JFileChooser object.
	 * This method deletes every extension but .txt, in order to let user choose only this type of file.
	 * It disables the multi-choice selection.
	 * 
	 * @param fileChooser is the fileChooser to change the characteristics of 
	 * @return fileChooser the JFileChooser instance
	 * 
	 * @author andre
	 */
	public static JFileChooser customizeFileChooser(JFileChooser fileChooser) {
		//Forbids the user to select multiple files
		fileChooser.setMultiSelectionEnabled(false);
				
		//Lets the user select only .txt files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		return fileChooser;
	}
	
	/**
	 * Necessary in order to adhere to the Observer design pattern
	 * @param load is the interface observing
	 * 
	 * @author andre
	 */
	public void registerEventListener(LoadInterface load) {
		loadListener = load;
	}
    
	/**
	 * For synchronous loading task.
	 * Part of the implementation of the Observer design pattern.
	 * @throws FileNotFoundException if the file is not found
	 * @throws CorruptedFileException if the file is corrupted
	 * 
	 * @author andre
	 */
	public void doTask() throws FileNotFoundException, CorruptedFileException {
		if(loadListener != null) {
			match = loadListener.loadMatch(loadingFile.getName());
		}
	}
	
	/**
	 * Displays the JFileChooser frame, reconfiguring it, customizing what user can do in this view
	 * When the user chooses a file, this file is stored in the variable loadingFile.
	 * Next, the Observer Pattern is applied: 
	 * 		- it is called a new LoadMatch instance as an interface
	 * 		- the GUI registers itself to this instance
	 * 		- synchronous task of loading the selected file begins
	 * 		- WHEN it ends, the match is finally initialized to the correct match stored in the given file
	 * Now it is possible to play a stored match.
	 * 
	 * @param chooser is the JFileChooser object
	 * @return the status of the showOpenDialog method. Status can be APPROVE_OPTION if user pressed the continue button or CANCEL_OPTION if pressed the cancel.
	 */
	public int selectSavedFileInterface(JFileChooser chooser) {
		loadingFile = null;
		int status = chooser.showOpenDialog(biggestFrame);
		return status;
	}
	
	/**
	 * Method for loading a match stored in a .txt file.
	 * 
	 * @author andre
	 */
	public void loadGame() {
		JFileChooser fileChooser = new JFileChooser("savedGames" + File.separator);
		fileChooser = customizeFileChooser(fileChooser);
		
		try {
			int status = selectSavedFileInterface(fileChooser);
			if(status == JFileChooser.APPROVE_OPTION) {
				loadingFile = fileChooser.getSelectedFile();
				
				LoadInterface loader = new LoadMatch();
				this.registerEventListener(loader);
				this.doTask();	
				
				biggestFrame.dispose();
				JFrame loadingFrame = new JFrame();
				loadingFrame.setMinimumSize(new Dimension(WIDTH/2, HEIGHT - 20));
				loadingFrame.setResizable(false);
				loadingFrame = biggestFrame;
				mainPanel.setVisible(false);
				loadingFrame.setVisible(false);
				
				//Reinitialize components of the match
				firstPlayerToken = match.getFirstPlayer().getToken();
				secondPlayerToken = match.getSecondPlayer().getToken();
				initializeBoardComponents(biggestFrame);
				paintBoardWithColors(match.getBoard());		
			}
			else {
				biggestFrame.dispose();
				displayStartGameFrame();
			}
		} catch (CorruptedFileException e) {
			corruptedFileHandler(e);
			return;
		} catch (FileNotFoundException e) {
			fileNotFoundHandler(e);
		}
	}	
	
    /**
     * Method for resetting the match that is being played.
     */
	public void reloadGame() {
		biggestFrame.dispose();
		isEnded = false;	
      	displayStartGameFrame(); 
    }
	
	/**
	 * Method for saving the match that is being played.
	 * Simply calls the SaveMatch class to save the file and creates and displays UI.
	 * 
	 * @author andre
	 */
	public void saveGame() {
			
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel notePanel = new JPanel(new GridLayout(2, 1, 40, 40));

		GridBagConstraints constraints = new GridBagConstraints();
    	
		JLabel whereToSaveText = new JLabel("Please give a name to this match, we'll store it in a file for you!");
		whereToSaveText.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		constraints.ipadx = 0;
    	constraints.ipady = 0;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 0;
  	  	constraints.anchor = GridBagConstraints.PAGE_START;

		panel.add(whereToSaveText, constraints);
		
		JTextField whereToSave = new JTextField();
		constraints.ipadx = 0;
    	constraints.ipady = 40;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 1;
  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
  	  	constraints.insets = new Insets(20, 20, 20, 20);
		panel.add(whereToSave, constraints);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		confirmButton.setBackground(PALETTE_COLOR_4);
		confirmButton.setBorder(emptyBorder);
		confirmButton.addMouseListener(new EnableHoverOnButton(confirmButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = "";
				if(!whereToSave.getText().equals("")) {
					fileName = whereToSave.getText();
				}
				else {
					Random r = new Random();
					fileName = "match" + match.getFirstPlayer().getName() + "VS" + match.getSecondPlayer().getName() + r.nextInt(100);
				}
				try {
					SaveMatch saver = new SaveMatch(fileName, match);
				} catch (SavingFileException e2) {
					savingFileErrorHandler(e2);
					return;
				}
				
				//Introduce a little delay (0.4 seconds) to let user see there is a change in the interface
				try {
					TimeUnit.MILLISECONDS.sleep(400);
				} catch (InterruptedException e1) {
				}
				
				JLabel confirmLabel = new JLabel("Saved!");
				confirmLabel.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
				constraints.ipadx = 0;
		    	constraints.ipady = 40;
		  	  	constraints.gridx = 0;
		  	  	constraints.gridy = 0;
		  	  	constraints.anchor = GridBagConstraints.PAGE_START;
		  	  	constraints.fill = GridBagConstraints.HORIZONTAL;
		  	  	panel.add(confirmLabel, constraints);
		    	
		  	  	JPanel prettier = new JPanel();
				prettier.setBackground(PALETTE_COLOR_3);
				
		    	JLabel continueLabel = new JLabel("Do you want to play again?");
				continueLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
				continueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));	
		    	constraints.ipadx = 0;
		    	constraints.ipady = 0;
		  	  	constraints.gridx = 0;
		  	  	constraints.gridy = 1;
		  	  	prettier.add(continueLabel);
		    	panel.add(prettier, constraints);
		    	
		    	JButton yesButton = new JButton("Play again!");
		    	yesButton.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		    	yesButton.setBackground(PALETTE_COLOR_4);
		    	yesButton.setBorder(emptyBorder);
		    	yesButton.addMouseListener(new EnableHoverOnButton(yesButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
		    	yesButton.addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent e) {
		    			isEnded = false;
		    			biggestFrame.dispose();
		    			displayStartGameFrame();
		    		}
		    	});
		    	constraints.ipadx = 0;
		    	constraints.ipady = 40;
		  	  	constraints.gridx = 0;
		  	  	constraints.gridy = 2;
		  	  	constraints.anchor = GridBagConstraints.CENTER;
		    	panel.add(yesButton, constraints);
		    	
		    	JButton noButton = new JButton("No, thanks");
		    	noButton.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
		    	noButton.setBackground(PALETTE_COLOR_4);
		    	noButton.setBorder(emptyBorder);
		    	noButton.addMouseListener(new EnableHoverOnButton(noButton, PALETTE_COLOR_3, PALETTE_COLOR_4));
		    	noButton.addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent e) {
		    			biggestFrame.dispose();
		    		}
		    	});
		    	constraints.ipadx = 0;
		    	constraints.ipady = 40;
		  	  	constraints.gridx = 0;
		  	  	constraints.gridy = 3;
		  	  	constraints.anchor = GridBagConstraints.PAGE_END;
		    	panel.add(noButton, constraints); 	
				
		    	confirmLabel.setVisible(true);
		    	notePanel.setVisible(false);
				confirmButton.setVisible(false);
				whereToSave.setVisible(false);	  
				whereToSaveText.setVisible(false);
		    	
			}
		});	
		constraints.ipadx = 0;
    	constraints.ipady = 40;
  	  	constraints.gridx = 0;
  	  	constraints.gridy = 2;
  	  	constraints.anchor = GridBagConstraints.CENTER;
  	  	constraints.insets = new Insets(20, 20, 60, 20);
		panel.add(confirmButton, constraints);

		JLabel noteLabel1 = new JLabel("If you won't provide any name, we will call it");
		noteLabel1.setFont(gamingFont.deriveFont(SMALLEST_SIZE));
		noteLabel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));	
  	  	JLabel noteLabel2 = new JLabel("\"match" + match.getFirstPlayer().getName() + "VS" + match.getSecondPlayer().getName() + "RandomNumber\"");
		noteLabel2.setFont(gamingFont.deriveFont(SMALLEST_SIZE));
		noteLabel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
  	  	notePanel.setBackground(PALETTE_COLOR_3);
  	  	notePanel.add(noteLabel1);
  	  	notePanel.add(noteLabel2);
  	  	constraints.ipadx = 0;
  	  	constraints.ipady = 0;
	  	constraints.gridx = 0;
	  	constraints.gridy = 5;
	  	constraints.anchor = GridBagConstraints.PAGE_END;
  	  	panel.add(notePanel, constraints);
		
		gameContainer.setVisible(false);
		panel.setBackground(PALETTE_COLOR_1);
		panel.setVisible(true);

    	biggestFrame.add(panel);
	}
	
	/**
     * Handles the situation of a player clicking again and again at a button corresponding to a full column.
     * It is a modal view, because UI enters in a modal state in which he cannot do anything but pressing 
     * the button.
     * In order to catch his attention, it has been chosen to display this modal view as a brand new JFrame.
     * Over that, the button text has been set to a message displaying a summary of what the error says:
     * this choice is in line with Human-Computer interaction dogmas, because user usually does not read 
     * what it is written in a pop-up, so his attention locus (that is set to the button in the moment he's 
     * clicking it) sees what the button label says.
     * In order to focus his attention completely on the pop-up, clicking the main frame is disabled 
     * until he clicks the button in this view.
     * This prevents from generating n new JFrame, where n is the number of clicks on the full column button.
     * 
     * @param e is the exception FullColumnException
     * 
     * @author andre
     */
    public void fullColumnHandler(FullColumnException e) {
    	JFrame errorFrame = new JFrame("Column is full");
		JButton errorButton = new JButton("I will choose another column");
		errorButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		errorButton.setBackground(PALETTE_COLOR_4);
		errorButton.addMouseListener(new EnableHoverOnButton(errorButton, PALETTE_COLOR_2, PALETTE_COLOR_4));
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biggestFrame.setEnabled(true);
				errorFrame.dispose();
			}
		});
		errorFrame.setSize(750, 150);
		JLabel errorLabel = new JLabel(e.getError());
		errorLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		biggestFrame.setEnabled(false);

		errorFrame.setBackground(PALETTE_COLOR_1);
		errorFrame.add(errorButton, BorderLayout.SOUTH);
		errorFrame.add(errorLabel, BorderLayout.CENTER);
		errorFrame.setVisible(true);
    }
    
    /**
     * Handles the situation in which a user selects a file that is corrupted
     * (a file is said to be corrupted when the board is in an unsafe state. View IntegrityMatrix documentation).
     * In order to catch his attention, it has been chosen to display this modal view as a brand new JFrame.
     * Over that, the button text has been set to a message displaying a summary of what the error says:
     * this choice is in line with Human-Computer interaction dogmas, because user usually does not read 
     * what it is written in a pop-up, so his attention locus (that is set to the button in the moment he's 
     * clicking it) sees what the button label says.
     * In order to focus his attention completely on the pop-up, clicking the main frame is disabled 
     * until he clicks the button in this view.
     * 
     * @param e is the exception CorruptedFileException
     * 
     * @author andre
     */
    public void corruptedFileHandler(CorruptedFileException e) {
    	JFrame errorFrame = new JFrame("File is corrupted");
		JButton errorButton = new JButton("I will choose another file");
		errorButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		errorButton.setBackground(PALETTE_COLOR_2);
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biggestFrame.setEnabled(true);
				match = null;
				biggestFrame.dispose();
				displayStartGameFrame(); 
				errorFrame.dispose();
			}
		});
		errorFrame.setSize(WIDTH/2, 150);
		JLabel errorLabel = new JLabel(e.getError());
		errorLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		biggestFrame.setEnabled(false);

		errorFrame.setBackground(PALETTE_COLOR_1);
		errorFrame.add(errorButton, BorderLayout.SOUTH);
		errorFrame.add(errorLabel, BorderLayout.CENTER);
		errorFrame.setVisible(true);
    }
    
    /**
     * Handles the situation of an error occurring when saving a file.
     * In order to catch user's attention, it has been chosen to display this modal view as a brand new JFrame.
     * Over that, the button text has been set to a message displaying a summary of what the error says:
     * this choice is in line with Human-Computer interaction dogmas, because user usually does not read 
     * what it is written in a pop-up, so his attention locus (that is set to the button in the moment he's 
     * clicking it) sees what the button label says.
     * In order to focus his attention completely on the pop-up, clicking the main frame is disabled 
     * until he clicks the button in this view.
     * 
     * @param e is the exception SavingFileException
     * 
     * @author andre
     */
    public void savingFileErrorHandler(SavingFileException e) {
    	JFrame errorFrame = new JFrame("Error saving match");
		JButton errorButton = new JButton("Ok then");
		errorButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		errorButton.setBackground(PALETTE_COLOR_2);
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biggestFrame.setEnabled(true);
				biggestFrame.dispose();
				displayStartGameFrame(); 
				errorFrame.dispose();
			}
		});
		errorFrame.setSize(WIDTH/2, 150);
		JLabel errorLabel = new JLabel(e.getError());
		errorLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		biggestFrame.setEnabled(false);

		errorFrame.setBackground(PALETTE_COLOR_1);
		errorFrame.add(errorButton, BorderLayout.SOUTH);
		errorFrame.add(errorLabel, BorderLayout.CENTER);
		errorFrame.setVisible(true);
    }
    
    /**
     * Handles the situation of a file not found.
     * In order to catch user's attention, it has been chosen to display this modal view as a brand new JFrame.
     * Over that, the button text has been set to a message displaying a summary of what the error says:
     * this choice is in line with Human-Computer interaction dogmas, because user usually does not read 
     * what it is written in a pop-up, so his attention locus (that is set to the button in the moment he's 
     * clicking it) sees what the button label says.
     * In order to focus his attention completely on the pop-up, clicking the main frame is disabled 
     * until he clicks the button in this view.
     * 
     * @param e is the exception FileNotFoundException
     * 
     * @author andre
     */
    public void fileNotFoundHandler(FileNotFoundException e) {
    	JFrame errorFrame = new JFrame("File not found");
		JButton errorButton = new JButton("Ok, that's fine for me");
		errorButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		errorButton.setBackground(PALETTE_COLOR_2);
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biggestFrame.setEnabled(true);
				biggestFrame.dispose();
				displayStartGameFrame(); 
				errorFrame.dispose();
			}
		});
		errorFrame.setSize(WIDTH/2, 150);
		JLabel errorLabel = new JLabel("File chosen was not found");
		errorLabel.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		biggestFrame.setEnabled(false);

		errorFrame.setBackground(PALETTE_COLOR_1);
		errorFrame.add(errorButton, BorderLayout.SOUTH);
		errorFrame.add(errorLabel, BorderLayout.CENTER);
		errorFrame.setVisible(true);
    }
      
	/**
	 * Simple class for deleting the informative text 
	 * shown in the text field where the user can insert his name. 
	 * 
	 * Note that the bigger part of the methods implementation
	 * is empty: the only necessary thing is to implement
	 * the method executing an action when listening to mouse click
	 * or a mouse pressed event, because it could be possible for a user
	 * to select part of the text and then skip the Mouse Clicked trigger.
	 * 
	 * @author andre
	 */
    class DeleteTextOneMouseListener implements MouseListener {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		if(playerOneFieldCounter < 1) {
    			playerOneField.setText("");
    			playerOneFieldCounter++;
    		}
  		}

  		@Override
  		public void mousePressed(MouseEvent e) {
  			if(playerOneFieldCounter < 1) {
    			playerOneField.setText("");
    			playerOneFieldCounter++;
    		}
  		}

  		@Override
  		public void mouseReleased(MouseEvent e) {
  		}

  		@Override
  		public void mouseEntered(MouseEvent e) {
  		}

  		@Override
  		public void mouseExited(MouseEvent e) {
  		}
    }

    /**
	 * Simple class for deleting the informative text 
	 * shown in the text field where the user can insert his name. 
	 * 
	 * Note that the bigger part of the methods implementation
	 * is empty: the only necessary thing is implement
	 * the method executing an action when listening to mouse click
	 * or a mouse pressed event, because it could be possible for a user
	 * to select part of the text and then skip the Mouse Clicked trigger.
	 * 
	 * @author andre
	 */
    class DeleteTextTwoMouseListener implements MouseListener {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		if(playerTwoFieldCounter < 1) {
    			playerTwoField.setText("");
    			playerTwoFieldCounter++;
    		}
  		}

  		@Override
  		public void mousePressed(MouseEvent e) {
  			if(playerTwoFieldCounter < 1) {
    			playerTwoField.setText("");
    			playerTwoFieldCounter++;
    		}
  		}

  		@Override
  		public void mouseReleased(MouseEvent e) {
  		}

  		@Override
  		public void mouseEntered(MouseEvent e) {
  		}

  		@Override
  		public void mouseExited(MouseEvent e) {
  		}
    }
    
    /**
     * This class changes the color of a button when entering it, and changes it again when exiting it.
     * Useful to give the impression of a pressable button.
     * Must implement MouseListener interface, so implementing all of its methods.
     * For the purpose of the class only part of the implemented methods have a non-empty body.
     * 
     * @author andre
     *
     */
    class EnableHoverOnButton implements MouseListener {
    	
    	JButton button;
    	Color enteringColor;
    	Color exitingColor;

    	public EnableHoverOnButton(JButton button, Color enteringColor, Color exitingColor) {
    		this.button = button;
    		this.enteringColor = enteringColor;
    		this.exitingColor = exitingColor;
    	}
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			button.setBackground(enteringColor);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			button.setBackground(exitingColor);	
		}
    }
}
