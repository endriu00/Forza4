package gameGUI;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.text.*;

import javax.swing.event.*;

import customException.*;
import gameComponents.*;
import playGame.*;
import saveAndLoad.LoadMatch;
import saveAndLoad.SaveMatch;

public class GUI extends JFrame implements ActionListener 
{
	     
	private JFrame biggestFrame;
	private JPanel boardFrame;
    private JPanel frame;
    
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
	public GUI() 
	{	
		super("Connect Four");
		
		isEnded = false;
		displayStartGameFrame();
			
	}

	/**
	 * Display the start/load game frame.
	 */
	private void displayStartGameFrame() 
	{		
		
		firstPlayerToken = new Token(new Color(0, 123, 123));
		secondPlayerToken = new Token(new Color(255, 184, 0));
		firstPlayer = new Player(getName(), firstPlayerToken);
		secondPlayer = new Player(getName(), secondPlayerToken);
		match = new Match(firstPlayer, secondPlayer);

		biggestFrame = new JFrame("Connect Four");
		
		biggestFrame.setMinimumSize(new Dimension(700, 800));
		biggestFrame.setResizable(false);
		biggestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = new JPanel();
		frame = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				initializeMatch();
			}
		});
		
		loadButton = new JButton("    Load     ");
        loadButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				initializeBoardComponents();
				initializeMatch();
				try {
					connectFour.restartMatch(getName());
				} catch (FullColumnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
       
        
        //Sets the button height to a bigger value
        constraints.ipady = 40;
        constraints.ipadx = 180;
        constraints.gridx = 3;
        constraints.gridy = 0;
		constraints.weighty = 0.6; 
    	constraints.insets = new Insets(40, 0, 0, 0);

		frame.add(startGameButton, constraints);
		
		constraints.weighty = 0.2;
		constraints.ipady = 40;
        constraints.ipadx = 180;
        constraints.gridx = 3;
        constraints.gridy = 1;
    	constraints.insets = new Insets(0 ,0 , 80, 0);

		frame.add(loadButton, constraints);
		
		frame.setVisible(true);
		
		frame.setBackground(new Color(0, 231, 103));
	    biggestFrame.add(frame);
		biggestFrame.setVisible(true);
	}
	
	
	
	/**
	 * Initialize the end game frame.
	 */
	private void displayEndGameFrame(String winnerName) 
	{

		JPanel endGamePanel = new JPanel(new GridLayout(2, 2, 10, 10));
		JPanel endGamePanelButtons = new JPanel();
		if (match.getBoard().isDraw())    // Se è finita con un pareggio
		{
			informativeLabel.setText("The match ended with a draw.");
			//JLabel tempLabel = new JLabel("Thanks for playing!");
		}
		else if (match.getBoard().isWin())    // Se è finita con una vincita
	    {
			informativeLabel.setText(winnerName + " " + "won! Thanks for playing!");
			//JLabel tempLabel = new JLabel("Thanks for playing!");
	    }	
		

  		startGameButton = new JButton("Start a new Game");
  		startGameButton.addActionListener(new ActionListener() 
 		{
			public void actionPerformed(ActionEvent e) 
			{
				
				isEnded = false;
        
				repaintPanels(Color.pink);
		        bottomButtonPanel.removeAll();
		        informativePanel.removeAll();
				biggestFrame.dispose();
				displayStartGameFrame();
				
				endGamePanel.setVisible(false);
				gameContainer.setVisible(false);
			}
		});
       
          downPanel.setVisible(false);
//        
//        //To refresh the view and delete old buttons
          biggestFrame.repaint();
          endGamePanelButtons.add(startGameButton);
          endGamePanelButtons.add(quitButton);
          endGamePanel.add(informativePanel);
          endGamePanel.add(endGamePanelButtons);

  		 // gameContainer.setBackground(new Color(0, 231, 63));

          gameContainer.add(endGamePanel, BorderLayout.SOUTH);
          endGamePanel.setVisible(true);
		
	}

    public void repaintPanels(Color color) {
    	biggestFrame.repaint();

    	boardCellsPanel[0][0].setBackground(color);
		boardCellsPanel[0][1].setBackground(color);
		boardCellsPanel[0][2].setBackground(color);
		boardCellsPanel[0][3].setBackground(color);
		boardCellsPanel[0][4].setBackground(color);
		boardCellsPanel[0][5].setBackground(color);
		boardCellsPanel[0][6].setBackground(color);
		                                    
		boardCellsPanel[1][0].setBackground(color);
		boardCellsPanel[1][1].setBackground(color);
		boardCellsPanel[1][2].setBackground(color);
		boardCellsPanel[1][3].setBackground(color);
		boardCellsPanel[1][4].setBackground(color);
		boardCellsPanel[1][5].setBackground(color);
		boardCellsPanel[1][6].setBackground(color);
		                                    
		boardCellsPanel[2][0].setBackground(color);
		boardCellsPanel[2][1].setBackground(color);
		boardCellsPanel[2][2].setBackground(color);
		boardCellsPanel[2][3].setBackground(color);
		boardCellsPanel[2][4].setBackground(color);
		boardCellsPanel[2][5].setBackground(color);
		boardCellsPanel[2][6].setBackground(color);
		                                    
		boardCellsPanel[3][0].setBackground(color);
		boardCellsPanel[3][1].setBackground(color);
		boardCellsPanel[3][2].setBackground(color);
		boardCellsPanel[3][3].setBackground(color);
		boardCellsPanel[3][4].setBackground(color);
		boardCellsPanel[3][5].setBackground(color);
		boardCellsPanel[3][6].setBackground(color);
		                                    
		boardCellsPanel[4][0].setBackground(color);
		boardCellsPanel[4][1].setBackground(color);
		boardCellsPanel[4][2].setBackground(color);
		boardCellsPanel[4][3].setBackground(color);
		boardCellsPanel[4][4].setBackground(color);
		boardCellsPanel[4][5].setBackground(color);
		boardCellsPanel[4][6].setBackground(color);
		                                    
		boardCellsPanel[5][0].setBackground(color);
		boardCellsPanel[5][1].setBackground(color);
		boardCellsPanel[5][2].setBackground(color);
		boardCellsPanel[5][3].setBackground(color);
		boardCellsPanel[5][4].setBackground(color);
		boardCellsPanel[5][5].setBackground(color);
		boardCellsPanel[5][6].setBackground(color);

    }
    
    
    /**
     * Initialize the contents of the game board.
     */
    public void initializeBoardComponents() 
    {	
        gameContainer = getContentPane();
        gameContainer.setVisible(true);
        
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BorderLayout());
        
        //Panel containing playerPanel and turnPanel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        
        playerOneLabel = new JLabel("Player One: " + match.getFirstPlayer().getName());
        playerTwoLabel = new JLabel("Player Two: " + match.getSecondPlayer().getName());
        playerPanel.add(playerOneLabel);
        playerPanel.add(playerTwoLabel);
  
        turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 1, 10, 10));

        turnLabel = new JLabel("Whose turn is it?");
        //turnLabel.setHorizontalAlignment(JLabel.RIGHT);
        turnPanel.add(turnLabel);

        topPanel.add(playerPanel, BorderLayout.WEST);
        topPanel.add(turnPanel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        boardCellsPanel = new JPanel[Board.getNumberOfRows()][Board.getNumberOfColumns()];
        createBoard();

        gameContainer.add(topPanel, BorderLayout.NORTH);
        gameContainer.add(gameBoardPanel, BorderLayout.CENTER);
        
        downPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        bottomButtonPanel = new JPanel();
        
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        
        bottomButtonPanel.remove(startGameButton);
        bottomButtonPanel.remove(quitButton);
        
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(quitButton);
        bottomButtonPanel.add(saveButton);
        bottomButtonPanel.add(loadButton);

        informativePanel = new JPanel();
        informativeLabel = new JLabel("Playing match...");
        informativePanel.add(informativeLabel);
        informativePanel.setVisible(true);
        
        downPanel.add(informativePanel, BorderLayout.NORTH);
        downPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        downPanel.setVisible(true);
        
        gameContainer.add(downPanel, BorderLayout.SOUTH);
        downPanel.repaint();
        gameContainer.setBackground(new Color(0, 231, 63));
        bottomButtonPanel.setBackground(new Color(0, 231, 63));
        informativePanel.setBackground(new Color(0, 231, 103));
		downPanel.setBackground(new Color(0, 231, 63));
		playerPanel.setBackground(new Color(0, 231, 103));
		turnPanel.setBackground(new Color(0, 231, 103));
		topPanel.setBackground(new Color(0, 231, 63));

        biggestFrame.add(gameContainer);

    }
    
    
    
    /**
     * Initialize match players. 
     */
    private void initializeMatch() 
    {
  	  
  	  frame = new JPanel(new GridBagLayout());
  	  JPanel panel = new JPanel(new GridBagLayout());
 	  GridBagConstraints constraints = new GridBagConstraints();
	  
  	  startMatchButton = new JButton("Start");
  	  startMatchButton.addActionListener(new ActionListener() 
	  {
  		  public void actionPerformed(ActionEvent e) 
		  {
				frame.setVisible(false);
				panel.setVisible(false);
				initializeBoardComponents();

				match.whoPlaysFirst();
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

  	  panel.add(new JLabel("Player one:"), constraints);
  	  
  	  //Player one text preferences in GUI representation
  	  constraints.ipady = 40;
	  constraints.ipadx = 200;
	  constraints.gridx = 3;
	  constraints.gridy = 1;
  	  panel.add(playerOneField, constraints);
  	  
  	  //Player two label preferences in GUI representation
  	  constraints.ipadx = 20;
  	  constraints.ipady = 40;
	  constraints.gridx = 3;
	  constraints.gridy = 2;
  	  panel.add(new JLabel("Player two:"), constraints);
  	  
  	  //Player two text preferences in GUI representation
  	  constraints.ipady = 40;
	  constraints.ipadx = 180;
	  constraints.gridx = 3;
	  constraints.gridy = 3;
  	  panel.add(playerTwoField, constraints);
  	  
  	  constraints.ipady = 40;
  	  constraints.ipadx = 180;
  	  constraints.gridx = 3;
  	  constraints.gridy = 4;
  	  constraints.insets = new Insets(0,0,20,0);
  	  frame.add(startMatchButton, constraints);
  	  
  	  panel.setBackground(new Color(0, 231, 103));
  	  frame.setBackground(new Color(0, 231, 103));
  	  
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

  	  biggestFrame.add(panel, BorderLayout.CENTER);
  	  biggestFrame.add(frame, BorderLayout.SOUTH);
  	 
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
			if(boardCellsPanel[Board.getNumberOfRows()-1][column].getBackground().equals(Color.pink)) {
				
				turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn.");
				
				isFirstRow = true;
        		boardCellsPanel[Board.getNumberOfRows()-1][column].setBackground(match.getFirstPlayer().getToken().getTokenColor());
    		}	
    		
			if(!isFirstRow) {
				int tempRow = 0;
    		
	    		if(!match.getBoard().isColumnFull(column)) {
	    			turnLabel.setText("It is " + match.getFirstPlayer().getName() + "'s turn.");
					
	    			if (!boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
	    				boardCellsPanel[tempRow][column].setBackground(match.getFirstPlayer().getToken().getTokenColor());
	    				try {
	    					match.insertInBoard(firstPlayerToken, column);
	    		       		checkResults(match.getFirstPlayer().getName());
	    					match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
							return;
						} catch (FullColumnException e1) {
							turnLabel.setText(e1.getError());

							e1.printStackTrace();
						}	
	    			}
	    			while(boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
	    				tempRow++;
	    			}
	    			boardCellsPanel[tempRow][column].setBackground(match.getFirstPlayer().getToken().getTokenColor());		    		
	    		}
    		}
		
       		
       		try {
				match.insertInBoard(firstPlayerToken, column);
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;
			}
    		checkResults(match.getFirstPlayer().getName());
			match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
		}
		
		else {	
			
			if(boardCellsPanel[Board.getNumberOfRows()-1][column].getBackground().equals(Color.pink)) {
				turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn.");
				
				isFirstRow = true;
        		boardCellsPanel[Board.getNumberOfRows()-1][column].setBackground(match.getSecondPlayer().getToken().getTokenColor());
    		}	
    		
			if(!isFirstRow){
				int tempRow = 0;
    		
    				if(!match.getBoard().isColumnFull(column)) {
    					turnLabel.setText("It is " + match.getSecondPlayer().getName() + "'s turn.");
    					if (!boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
    						boardCellsPanel[tempRow][column].setBackground(match.getSecondPlayer().getToken().getTokenColor());
    						try {
    							match.insertInBoard(secondPlayerToken, column);
    				       		checkResults(match.getSecondPlayer().getName());
    				    		match.setFirstPlayerTurn(!match.getFirstPlayerTurn());
								return;
							} catch (FullColumnException e1) {
								turnLabel.setText(e1.getError());
								e1.printStackTrace();
							}	
    					}
    			
    					while(boardCellsPanel[tempRow+1][column].getBackground().equals(Color.pink)) {
    						tempRow++;
    					}
    			
    					boardCellsPanel[tempRow][column].setBackground(match.getSecondPlayer().getToken().getTokenColor());		    		
    				}
    		}
		
       		try {
				match.insertInBoard(secondPlayerToken, column);
				
			} catch (FullColumnException e) {
				fullColumnHandler(e);			
				return;		
			}
       		checkResults(match.getSecondPlayer().getName());
    		match.setFirstPlayerTurn(!match.getFirstPlayerTurn());		
		}
		
    }
    
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
    
    public void fullColumnHandler(FullColumnException e) {
    	JFrame errorFrame = new JFrame();
		JButton errorButton = new JButton("Understood");
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biggestFrame.setEnabled(true);
				errorFrame.dispose();
			}
		});
		errorFrame.setSize(650, 150);
		JLabel errorLabel = new JLabel(e.getError());
		
		biggestFrame.setEnabled(false);

		errorFrame.add(errorButton, BorderLayout.SOUTH);
		errorFrame.add(errorLabel, BorderLayout.CENTER);
		errorFrame.setVisible(true);
    }
 
    /** 
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, Board.getNumberOfColumns(), 10, 10));
        columnButtons = new JButton[Board.getNumberOfColumns()];
        
        columnButtons[0] = new JButton(1 + "");
        columnButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(0);
				}
			}});
        
        columnButtons[1] = new JButton(2 + "");
        columnButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(!isEnded) {		
					changer(1);
				}
			}});
        
        columnButtons[2] = new JButton(3 + "");
        columnButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(2);
				
				}
			}});
        
        columnButtons[3] = new JButton(4 + "");
        columnButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					changer(3);
					
				}
			}});
        
        columnButtons[4] = new JButton(5 + "");
        columnButtons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					
					changer(4);
					
				}
			}});
        
        columnButtons[5] = new JButton(6 + "");
        columnButtons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isEnded) {
					
					changer(5);
					
				}
			}});
        
        columnButtons[6] = new JButton(7 + "");
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


	public void repaintBoard() {
		
		boardPanel = new JPanel(new GridLayout(Board.getNumberOfRows(), Board.getNumberOfColumns(), 20, 20));
        boardCellsPanel = new JPanel[Board.getNumberOfRows()][Board.getNumberOfColumns()];

        for (int i = 0; i < Board.getNumberOfRows(); i++) 
        {
            for (int j = 0; j < Board.getNumberOfColumns(); j++) 
            {
                JPanel panel = new JPanel();
                //panel.setBackground(Color.pink);
                boardCellsPanel[i][j] = panel;
                boardCellsPanel[i][j].setBackground(Color.pink);
                boardPanel.add(boardCellsPanel[i][j]);
            }
        }

	}
	
	/**
     * Performs actions based on clicked buttons.
     */
    public void actionPerformed(ActionEvent e) 
    {	
        if (e.getSource() == resetButton) 
        {
			repaintPanels(Color.pink);
        	reloadGame();
        }
        
        else if (e.getSource() == quitButton) 
        {
            System.exit(0);
        }
        
        else if (e.getSource() == saveButton) 
        {
        	isEnded = true;
        	saveGame();	
        }
        
        else if (e.getSource() == loadButton) 
        {
        	try {
				connectFour.restartMatch(getName());
			} catch (FullColumnException e1) {
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
    
       
	public void reloadGame() {
		biggestFrame.dispose();
		isEnded = false;
      	displayStartGameFrame(); 
    }
	
	public void saveGame() {
			
		JPanel panel = new JPanel(new GridLayout(5, 3, 10, 10));
    	
		JLabel whereToSaveText = new JLabel("Please give a name to this match, we'll store it in a file for you with this name!");
		JTextField whereToSave = new JTextField();
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = "";
				if(!whereToSave.getText().equals("")) {
					fileName = whereToSave.getText();
				}
				else {
					Random r = new Random();
					fileName = "match" + r.nextInt(100);
				}
				SaveMatch saver = new SaveMatch(fileName, match);
				
				JLabel confirmLabel = new JLabel("Saved!");
				panel.remove(whereToSaveText);
				panel.add(confirmLabel, BorderLayout.CENTER);
				confirmLabel.setVisible(true);
				confirmButton.setVisible(false);
				whereToSave.setVisible(false);
				
				//want to continue JLabel, two buttons: yes displaystartgame, no quit
				
			}
		});
		
		panel.add(whereToSaveText, BorderLayout.NORTH);
		panel.add(whereToSave, BorderLayout.CENTER);
		panel.add(confirmButton, BorderLayout.SOUTH);
		gameContainer.setVisible(false);
		panel.setVisible(true);

    	biggestFrame.add(panel);
    	//maybe add a choice panel: want to continue? if yes displayStartGameFrame() else close
    	//displayStartGameFrame();
    	
    	
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
