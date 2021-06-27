package gameGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.text.*;

import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import customException.*;
import gameComponents.*;
import playGame.*;
import saveAndLoad.LoadMatch;
import saveAndLoad.SaveMatch;
import utilities.LoadInterface;

public class GUI extends JFrame implements ActionListener 
{
	     
	private JFrame biggestFrame;
	private JPanel boardFrame;
    private JPanel mainPanel;
    
    private Container gameContainer;	 // Container 

    private JPanel topPanel;		// Panel containing players and turn
    private JPanel boardPanel;		// Panel containing the board cells
    private JPanel[][] boardCellsPanel;	 // Panel (array) representing the game board
    private JPanel turnPanel;		// Panel containing the turn ** Panel containing turn info and games played */
    private JPanel bottomButtonPanel;		 // Panel containing reset, quit and save buttons 
    private JPanel columnsButtonPanel;		 // Panel containing the column buttons
    private JPanel gameBoardPanel;		// Panel containing the game board
    private JPanel playerPanel;			// Panel containing players
    private JPanel endPanel;			// Panel containing end label
    private JPanel informativePanel;
    private JPanel downPanel;
    
    private JLabel playerOneLabel;	 // Label for player one
    private JLabel playerTwoLabel;	// Label for player two
    private JLabel turnLabel;		// Label displaying the turn
    private JLabel informativeLabel;
    private JLabel welcomeLabel;

    private JButton startGameButton; // Start button
    private JButton startMatchButton; // Start match button
    private JButton quitButton;		// Quit button
    private JButton resetButton;	 // Reset button
    private JButton saveButton;		// Save button
    private JButton loadButton;		// Load button
    private JButton[] columnButtons;	 // Buttons for each column

    private JTextField playerOneField;		// Field for player one's name
    private JTextField playerTwoField;		// Field for player two's name
        
    private ConnectFour connectFour;	 
    private Match match;
    private Token firstPlayerToken; 
    private Token secondPlayerToken;
    private Player firstPlayer;
    private Player secondPlayer;
    
    private boolean isEnded;
    private int playerOneFieldCounter;
    private int playerTwoFieldCounter;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = (int) screenSize.getWidth();
    private final int HEIGHT = (int) screenSize.getHeight();
    
    private static final Color PALETTE_COLOR_1 = new Color(235, 245, 247);
    private static final Color PALETTE_COLOR_2 = new Color(193, 91, 120);
    private static final Color PALETTE_COLOR_3 = new Color(91, 176, 186);
    private static final Color PALETTE_COLOR_4 = new Color(255, 200, 230);

    private Font gamingFont;
    
    private static final float SMALLER_SIZE = 13.f;
    private static final float MEDIUM_SIZE = 24.f;
    private static final float LARGER_SIZE = 34.f;
    private static final float SMALLEST_SIZE = 11.0f;
    
    private Border emptyBorder = BorderFactory.createEmptyBorder();

    private File loadingFile;
    private LoadInterface loadListener;
    
    public void registerEventListener(LoadInterface load) {
		loadListener = load;
	}
 
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
					
					GUI window = new GUI();
					window.biggestFrame.setVisible(true);
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
	public GUI() 
	{	
		super("Connect Four");
		addFont();
		isEnded = false;
		displayStartGameFrame();
			
	}
	

	public void initializeComponents() {
		//add functionality, let user  choose color
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
	  	JPanel panel = new JPanel(new GridBagLayout());
	  	GridBagConstraints constraints = new GridBagConstraints();
		
	  	startMatchButton = new JButton("Start");
	  	startMatchButton.setBackground(PALETTE_COLOR_4);
	  	startMatchButton.setBorder(emptyBorder);
	  	startMatchButton.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			mainPanel.setVisible(false);
			panel.setVisible(false);
			match.whoPlaysFirst();
			initializeBoardComponents();
	  		}
	  	});
	  	
	  	//Useful for user
	  	playerOneField = new JTextField("Insert the name of the first player here!");
	  	//in this JTextField it has been added a mouse listener that deletes its content 
	  	//when user clicks the field, letting him insert his name.
	  	playerOneField.addMouseListener(new DeleteTextOneMouseListener());
	
	  	//Useful for user
	  	playerTwoField = new JTextField("Insert the name of the second player here!");
	  	//in this JTextField it has been added a mouse listener that deletes its content 
	  	//when user clicks the field, letting him insert his name.
	  	playerTwoField.addMouseListener(new DeleteTextTwoMouseListener());
	  	
	  	//Player one label preferences in GUI representation
	  	
	  	constraints.ipadx = 20;
	  	constraints.ipady = 40;
		constraints.gridx = 3;
		constraints.gridy = 0;
	
		JLabel playerOneLabel = new JLabel("Player one:");
		playerOneLabel.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
	  	panel.add(playerOneLabel, constraints);
	  	
	  	//Player one text preferences in GUI representation
	  	constraints.ipady = 40;
		constraints.ipadx = 200;
		constraints.gridx = 3;
		constraints.gridy = 1;
		playerOneField.setFont(gamingFont.deriveFont(SMALLER_SIZE));
	  	panel.add(playerOneField, constraints);
	  	
	  	//Player two label preferences in GUI representation
	  	constraints.ipadx = 20;
	  	constraints.ipady = 40;
		constraints.gridx = 3;
		constraints.gridy = 2;
		JLabel playerTwoLabel = new JLabel("Player two:");
		playerTwoLabel.setFont(gamingFont.deriveFont(MEDIUM_SIZE));
	  	panel.add(playerTwoLabel, constraints);
	  	
	  	//Player two text preferences in GUI representation
	  	constraints.ipady = 40;
		constraints.ipadx = 180;
		constraints.gridx = 3;
		constraints.gridy = 3;
		playerTwoField.setFont(gamingFont.deriveFont(SMALLER_SIZE));
	  	panel.add(playerTwoField, constraints);
	  	
	  	constraints.ipady = 40;
	  	constraints.ipadx = 180;
	  	constraints.gridx = 3;
	  	constraints.gridy = 4;
	  	constraints.insets = new Insets(0, 0, 110, 0);
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
		
		panel.setBackground(PALETTE_COLOR_1);
	  	mainPanel.setBackground(PALETTE_COLOR_1);
	
	  	biggestFrame.add(panel, BorderLayout.CENTER);
	  	biggestFrame.add(mainPanel, BorderLayout.SOUTH);
  	 
    }
    
    /**
     * Initialize the contents of the game board.
     */
    public void initializeBoardComponents() 
    {	
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
        if(match.getFirstPlayerTurn()) turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn");
        else turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn");
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
        quitButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        resetButton.setBackground(PALETTE_COLOR_1);
        resetButton.addActionListener(this);
        
        saveButton = new JButton("Save");
        saveButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        saveButton.setBackground(PALETTE_COLOR_1);
        saveButton.addActionListener(this);
        
        loadButton = new JButton("Load");
        loadButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
        loadButton.setBackground(PALETTE_COLOR_1);
        loadButton.addActionListener(this);
        
        bottomButtonPanel.remove(startGameButton);
        bottomButtonPanel.remove(quitButton);
        
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(quitButton);
        bottomButtonPanel.add(saveButton);
        bottomButtonPanel.add(loadButton);

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

        gameContainer.add(downPanel, constraints);
        
        gameContainer.setBackground(PALETTE_COLOR_1);
        bottomButtonPanel.setBackground(PALETTE_COLOR_3);
        informativePanel.setBackground(PALETTE_COLOR_2);
		downPanel.setBackground(PALETTE_COLOR_3);
		playerPanel.setBackground(PALETTE_COLOR_3);
		turnPanel.setBackground(PALETTE_COLOR_3);
		topPanel.setBackground(PALETTE_COLOR_3);

        biggestFrame.add(gameContainer);

    }
    
    /** 
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, Board.getNumberOfColumns(), 10, 20));
        columnButtons = new JButton[Board.getNumberOfColumns()];
        columnsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        columnButtons[0] = new JButton(1 + "");
        columnButtons[0].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[0].setBackground(PALETTE_COLOR_3);
        columnButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(0);
				}
			}});
        
        columnButtons[1] = new JButton(2 + "");
        columnButtons[1].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[1].setBackground(PALETTE_COLOR_3);
        columnButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(!isEnded) {		
					changer(1);
				}
			}});
        
        columnButtons[2] = new JButton(3 + "");
        columnButtons[2].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[2].setBackground(PALETTE_COLOR_3);
        columnButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(2);
				}
			}});
        
        columnButtons[3] = new JButton(4 + "");
        columnButtons[3].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[3].setBackground(PALETTE_COLOR_3);
        columnButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(3);
				}
			}});
        
        columnButtons[4] = new JButton(5 + "");
        columnButtons[4].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[4].setBackground(PALETTE_COLOR_3);
        columnButtons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(4);
				}
			}});
        
        columnButtons[5] = new JButton(6 + "");
        columnButtons[5].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[5].setBackground(PALETTE_COLOR_3);
        columnButtons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(5);
				}
			}});
        
        columnButtons[6] = new JButton(7 + "");
        columnButtons[6].setFont(gamingFont.deriveFont(SMALLEST_SIZE));
        columnButtons[6].setBackground(PALETTE_COLOR_3);
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
	 */
	private void displayEndGameFrame(String winnerName) 
	{
		JPanel endGamePanel = new JPanel(new GridLayout(2, 2, 10, 10));
		JPanel endGamePanelButtons = new JPanel();
		if (match.getBoard().isDraw())    // Se è finita con un pareggio
		{
			informativeLabel.setText("The match ended with a draw. Thanks for playing!");
		}
		else if (match.getBoard().isWin())    // Se è finita con una vincita
	    {
			informativeLabel.setText(winnerName + " " + "won! Thanks for playing!");
	    }	

  		startGameButton = new JButton("Start a new Game");
  		startGameButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
  		startGameButton.setBackground(PALETTE_COLOR_1);
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
        constraints.ipadx = 20;
      	constraints.ipady = 40;
    	constraints.gridx = 0;
    	constraints.gridy = 8;
    	constraints.anchor = GridBagConstraints.PAGE_END;
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	
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
     */
    public void changer(int column) {
    	    	
    	boolean isFirstRow = false;
		if (match.getFirstPlayerTurn()) { 
			
			//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
			//accordingly, but it is necessary to specify whose turn is next when triggering the event.
			turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn.");
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
	    					match.insertInBoard(firstPlayerToken, column);
	    					match.setTurn(match.getTurn()+1);
	    		       		checkResults(match.getFirstPlayer().getName());
	    					match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
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
				match.insertInBoard(firstPlayerToken, column);
				match.setTurn(match.getTurn()+1);
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;
			}
    		checkResults(match.getFirstPlayer().getName());

			match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
		}
		
		else {	
			//When pressing a button, the turn owner changes, so if it was first player's turn, these code changes the board
			//accordingly, but it is necessary to specify whose turn is next when triggering the event.
			turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn.");
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
							match.insertInBoard(secondPlayerToken, column);
							match.setTurn(match.getTurn()+1);
							checkResults(match.getSecondPlayer().getName());
							match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
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
				match.insertInBoard(secondPlayerToken, column);
				match.setTurn(match.getTurn()+1);
				
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;		
			}
       		checkResults(match.getSecondPlayer().getName());

			match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
		}	
    }
    
    /**
     * Method for checking if a match has ended in a win or in a draw.
     * @param winnerName is the name of the winning player. If it is a draw, winnerName is an empty string
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
     */
    public void fullColumnHandler(FullColumnException e) {
    	JFrame errorFrame = new JFrame("Column is FULL");
		JButton errorButton = new JButton("I will choose another column");
		errorButton.setFont(gamingFont.deriveFont(SMALLER_SIZE));
		errorButton.setBackground(PALETTE_COLOR_2);
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
     * Changes the color of the board according to the color of the token passed as input.
     * Position of the board is specified as row, column.
     * 
     * @param token is the token with its color
     * @param row is the row of the board
     * @param col is the column of the board
     */
    public void changeColorInBoard(Token token, int row, int col) {
    	boardCellsPanel[row][col].setBackground(token.getTokenColor());
    }
	
	/**
     * Performs actions based on clicked buttons.
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
        	try {
				loadGame();
			} catch (FullColumnException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }       
       
	public static int getClickedButton(ActionEvent e)
    {
    	JButton button = (JButton)e.getSource();
        int clickedColumn = Integer.parseInt(button.getActionCommand());
        return clickedColumn;	
    }
    
	//For synchronous task
	public void doTask() {
		if(loadListener != null) {
			try {
				match = loadListener.loadMatch(loadingFile.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public File selectSavedFileInterface() {
		loadingFile = null;
		JFileChooser chooser = new JFileChooser("savedGames" + File.separator);
		
		//Forbids the user to select multiple files
		chooser.setMultiSelectionEnabled(false);
		
		//Lets the user select only .txt files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int status = chooser.showOpenDialog(biggestFrame);

		
		if(status == JFileChooser.APPROVE_OPTION) {
			loadingFile = chooser.getSelectedFile();
			System.out.println(loadingFile);		
			
			LoadInterface loader = new LoadMatch();
			this.registerEventListener(loader);
			this.doTask();
			System.out.println(match.getFirstPlayer().getName() + " " + match.getSecondPlayer().getName() + " " + match.getFirstPlayer().getToken().getTokenColor());
			
		}

		return loadingFile;
	}
	
	public void loadGame() throws FullColumnException, IOException {
		loadingFile = selectSavedFileInterface();
	}
	
	
       
	public void reloadGame() {
		biggestFrame.dispose();
		isEnded = false;	
      	displayStartGameFrame(); 
    }
	
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
				SaveMatch saver = new SaveMatch(fileName, match);
				
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
	 * Simple class for deleting the informative text 
	 * shown in the text field where the user can insert his name. 
	 * 
	 * Note that the bigger part of the methods implementation
	 * is empty: the only necessary thing is implement
	 * the method executing an action when listening to mouse click.
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
	 * the method executing an action when listening to mouse click.
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

}
