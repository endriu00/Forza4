package gameGUI;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import java.util.*;
import javax.swing.text.*;

import javax.swing.event.*;

import customException.*;
import gameComponents.*;
import playGame.*;
import saveAndLoad.LoadMatch;
import saveAndLoad.SaveMatch;

public class GUI2 extends JFrame implements ActionListener 
{
     
    private JFrame frame;
    
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
    
    private JLabel playerOneLabel;	 // Label for player one
    private JLabel playerTwoLabel;	// Label for player two
    private JLabel turnLabel;		// Label displaying the turn
    private JLabel endLabel;

    private JButton startGameButton; // Start button
    private JButton startMatchButton; // Start match button
    private JButton quitButton;		// Quit button
    private JButton resetButton;	 // Reset button
    private JButton saveButton;		// Save button
    private JButton loadButton;		// Load button
    private JButton[] columnButtons;	 // Buttons for each column

    private JTextField playerOneField;		// Field for player one's name
    private JTextField playerTwoField;		// Field for player two's name
    
    private static final int ROWS = 6;
	private static final int COLUMNS = 7;
    
    private ConnectFour connectFour;	 
    private Match match;
    private Board gameBoard;
    private Token firstPlayerToken; 
    private Token secondPlayerToken;
    private Player firstPlayer;
    private Player secondPlayer;
    
    private SaveMatch savedMatch;
    private LoadMatch loadedMatch;
    
   
    
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
					GUI2 window = new GUI2();
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
	public GUI2() 
	{	
		super("Connect Four");
		
		displayStartGameFrame();
		
		connectFour = new ConnectFour();
		gameBoard = new Board();
		firstPlayerToken = new Token(getBackground());
		secondPlayerToken = new Token(getBackground());
		firstPlayer = new Player(getName(), firstPlayerToken);
		secondPlayer = new Player(getName(), secondPlayerToken);
		match = new Match(gameBoard, firstPlayer, secondPlayer);		
	}

	
	


	/**
	 * Display the start/load game frame.
	 */
	private void displayStartGameFrame() 
	{
		frame = new JFrame("Connect Four");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				//initializeBoardComponents();
				initializeMatch();
			}
		});
		
		loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				initializeBoardComponents();
				initializeMatch();
				connectFour.restartMatch(getName());
			}
		});
        
		startGameButton.setBounds(120, 80, 200, 50);
		loadButton.setBounds(120, 140, 200, 50);
		frame.getContentPane().add(startGameButton);
		frame.getContentPane().add(loadButton);
		frame.setVisible(true);
	}
	
	
	
	/**
	 * Initialize the end game frame.
	 */
	private void displayEndGameFrame() 
	{
		frame = new JFrame("Game over");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		endPanel = new JPanel();
	    endPanel.setLayout(new GridLayout(2, 1, 10, 10));
		
		//endLabel = new JLabel("The match ended.");
		//endPanel.add(endLabel);
		
		if (match.getBoard().isDraw())    // Se è finita con un pareggio
		{
			endLabel = new JLabel("The match ended with a draw.");
			endPanel.add(endLabel);
		}
		else if (match.getBoard().isWin())    // Se è finita con una vincita
	    {
			endLabel = new JLabel("The match ended with a win.");
			endPanel.add(endLabel);
	    }	
		
		startGameButton = new JButton("Start a new Game");
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				
				initializeMatch();
				
				connectFour = new ConnectFour();
				gameBoard = new Board();
				firstPlayerToken = new Token(getBackground());
				secondPlayerToken = new Token(getBackground());
				firstPlayer = new Player(getName(), firstPlayerToken);
				secondPlayer = new Player(getName(), secondPlayerToken);
				match = new Match(gameBoard, firstPlayer, secondPlayer);	
			}
		});
		
		quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
        
		startGameButton.setBounds(120, 80, 200, 50);
		quitButton.setBounds(120, 140, 200, 50);
		endPanel.setBounds(130, 40, 200, 50);
		frame.getContentPane().add(startGameButton);
		frame.getContentPane().add(quitButton);
		frame.getContentPane().add(endPanel);
		frame.setVisible(true);	
	}

    
    
    
    /**
     * Initialize the contents of the game board.
     */
    public void initializeBoardComponents() 
    {	
    	setBounds(300, 300, 700, 700);
        gameContainer = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1, 10, 10));

        playerOneLabel = new JLabel("Player One");
        playerTwoLabel = new JLabel("Player Two");
        playerPanel.add(playerOneLabel);
        playerPanel.add(playerTwoLabel);
        playerPanel.add(playerOneField);
        playerPanel.add(playerTwoField);

        turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 1, 10, 10));

        turnLabel = new JLabel("Whose turn is it?");
        turnLabel.setHorizontalAlignment(JLabel.RIGHT);
        turnPanel.add(turnLabel);

        topPanel.add(playerPanel);
        topPanel.add(turnPanel);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        createBoard();

        gameContainer.add(topPanel, BorderLayout.NORTH);
        gameContainer.add(gameBoardPanel, BorderLayout.CENTER);
        
        bottomButtonPanel = new JPanel();
        
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(quitButton);
        bottomButtonPanel.add(saveButton);
        bottomButtonPanel.add(loadButton);
        gameContainer.add(bottomButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    
    
    /**
     * Initialize match players. 
     */
    private void initializeMatch() 
    {
  	  
  	  frame = new JFrame("Connect Four");
	  frame.setBounds(300, 300, 400, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
  	  startMatchButton = new JButton("Start");
  	  startMatchButton.addActionListener(new ActionListener() 
	  {
  		  public void actionPerformed(ActionEvent e) 
		  {
				frame.setVisible(false);
				initializeBoardComponents();
		
				match.whoPlaysFirst();
		  }
	  });

  	  playerOneField = new JTextField();
  	  playerTwoField = new JTextField();
  	  
  	  JPanel panel = new JPanel(new GridLayout(0, 1));
  	  panel.add(startMatchButton);
  	  panel.add(new JLabel("Player one:"));
  	  panel.add(playerOneField);
  	  panel.add(new JLabel("Player two:"));
  	  panel.add(playerTwoField);
  	  
  	  String playerOneText = playerOneField.getText();
  	  String playerTwoText = playerTwoField.getText();
  	  
  	  firstPlayer.setName(playerOneText);
  	  secondPlayer.setName(playerTwoText);
  	  
  	  firstPlayer = new Player(playerOneText, firstPlayerToken);
  	  secondPlayer = new Player(playerTwoText, secondPlayerToken);
  	  
  	  match.setFirstPlayer(firstPlayer);
	  match.setSecondPlayer(secondPlayer);

  	  startMatchButton.setBounds(120, 80, 200, 50);
  	  frame.getContentPane().add(startMatchButton);
  	  frame.add(startMatchButton, BorderLayout.SOUTH);
  	  frame.getContentPane().add(panel);
  	  frame.setVisible(true);
  	 
    }
    
    

    
    /** 
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, COLUMNS, 10, 10));
        columnButtons = new JButton[COLUMNS];
        
        columnButtons[0] = new JButton(0 + "");
        columnButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][0].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][0].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(0)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][0].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][0].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 0);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][0].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][0].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 0);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][0].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][0].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(0)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][0].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][0].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 0);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][0].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][0].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 0);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[1] = new JButton(1 + "");
        columnButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][1].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][1].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(1)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][1].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][1].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 1);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][1].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][1].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 1);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][1].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][1].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(1)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][1].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][1].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 1);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][1].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][1].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 1);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[2] = new JButton(2 + "");
        columnButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][2].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][2].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 2;
		    		
		    				if(!gameBoard.isColumnFull(2)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][2].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][2].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 2);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][2].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][2].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 2);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][2].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][2].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(2)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][2].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][2].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 2);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][2].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][2].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 2);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[3] = new JButton(3 + "");
        columnButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][3].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][3].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(3)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][3].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][3].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 3);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][3].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][3].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 3);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][3].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][3].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(3)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][3].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][3].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 3);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][3].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][3].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 3);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[4] = new JButton(4 + "");
        columnButtons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][4].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][4].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(4)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][4].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][4].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 4);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][4].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][4].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 4);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][4].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][4].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(4)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][4].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][4].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 4);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][4].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][4].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 4);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[5] = new JButton(5 + "");
        columnButtons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][5].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][5].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(5)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][5].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][5].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 5);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][5].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][5].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 5);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][5].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][5].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(5)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][5].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][5].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 5);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][5].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][5].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 5);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        columnButtons[6] = new JButton(6 + "");
        columnButtons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean isFirstRow = false;
				if (match.firstPlayerTurn)
				{ 
					if(boardCellsPanel[ROWS-1][6].getBackground().equals(Color.PINK)) 	
		    			{
						firstPlayer = match.getFirstPlayer();
						turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][6].setBackground(Color.RED);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(6)) 
		    				{
		    					firstPlayer = match.getFirstPlayer();
							turnLabel.setText("It is " + match.getFirstPlayer() + " (first player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][6].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][6].setBackground(Color.RED);
		    						gameBoard.insert(firstPlayerToken, 6);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][6].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][6].setBackground(Color.RED);		    		
						}
		    			}
				
		       			gameBoard.insert(firstPlayerToken, 6);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player one won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
				
				else
				{	
					if(boardCellsPanel[ROWS-1][6].getBackground().equals(Color.PINK)) 	
		    			{
						secondPlayer = match.getSecondPlayer();
						turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
						isFirstRow = true;
		        			//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
		        			boardCellsPanel[ROWS-1][6].setBackground(Color.BLUE);
		    			}	
		    		
					if(!isFirstRow){
						int tempRow = 0;
		    		
		    				if(!gameBoard.isColumnFull(6)) 
		    				{
							secondPlayer = match.getSecondPlayer();
							turnLabel.setText("It is " + match.getSecondPlayer() + " (second player) turn.");
						
		    					if (!boardCellsPanel[tempRow+1][6].getBackground().equals(Color.PINK))
		    					{
		    						boardCellsPanel[tempRow][6].setBackground(Color.BLUE);
		    						gameBoard.insert(secondPlayerToken, 6);	
		    					}
		    			
		    					while(boardCellsPanel[tempRow+1][6].getBackground().equals(Color.PINK)) 	
		    					{
		    						tempRow++;
		    					}
		    			
		    					boardCellsPanel[tempRow][6].setBackground(Color.BLUE);		    		
						}
		    			}
				
		       			gameBoard.insert(secondPlayerToken, 6);	

		    			if (match.getBoard().isDraw())    // Se è finita con un pareggio
		    			{
		    		    		System.out.print(" The match ended in a draw.");
		    		  		displayEndGameFrame();
		    			}
		    			else if (match.getBoard().isWin())    // Se è finita con una vincita
		    	   		{
		    	    			System.out.print(" Player two won. ");
		    	    			displayEndGameFrame();
		    	   		}
					match.firstPlayerTurn = !match.firstPlayerTurn;
				}
		});
        
        match.firstPlayerTurn = !match.firstPlayerTurn;
        
        columnsButtonPanel.add(columnButtons[0]);
        columnsButtonPanel.add(columnButtons[1]);
        columnsButtonPanel.add(columnButtons[2]);
        columnsButtonPanel.add(columnButtons[3]);
        columnsButtonPanel.add(columnButtons[4]);
        columnsButtonPanel.add(columnButtons[5]);
        columnsButtonPanel.add(columnButtons[6]);
        
        
        boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS, 20, 20));
        boardCellsPanel = new JPanel[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLUMNS; j++) 
            {
                JPanel panel = new JPanel();
                panel.setBackground(Color.PINK);
                boardCellsPanel[i][j] = panel;
                boardPanel.add(panel);
            }
        }
        gameBoardPanel.add(columnsButtonPanel, BorderLayout.NORTH);
        gameBoardPanel.add(boardPanel,BorderLayout.CENTER);   
    }


	
	
	/**
     * Performs actions based on clicked buttons.
     */
    public void actionPerformed(ActionEvent e) 
    {
    	/*//connectFour.play(match);
    	//match.executeTurn();
    	match.whoPlaysFirst(); 
	    //Board.printBoard();
	    //Scanner inSave = new Scanner(System.in);
    	int turn = match.getTurn();
    	
	    for (turn = match.getTurn(); turn <= 42; turn++)
	    {
	    	if (!match.isEndGame(gameBoard))
	    	{
	    		if (match.firstPlayerTurn)
	    		{
	    			turnLabel.setText("It is " + match.getFirstPlayer().getName() + " (first player) turn.");
    				Player currentPlayer = match.getFirstPlayer();
    				Token token = currentPlayer.getToken();
	    			for (int j = 0; j < COLUMNS; j++) 
	    			{  
	    				//match.grantPlayerMove(currentPlayer);
	    		        if (e.getSource().equals(columnButtons[j]) && !gameBoard.isColumnFull(j))
	    		        {	
	    		        	int k = getClickedButton(e);
	    		        	//match.grantPlayerMove(currentPlayer);
	    		        	
	    		        	gameBoard.insert(token, k);
	    		        			
	    		        	if(boardCellsPanel[ROWS-1][k].getBackground().equals(Color.PINK)) 	
	    		    		{
	    		        		//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
	    		        		boardCellsPanel[ROWS-1][k].setBackground(Color.RED);
	    		    			return;	
	    		    		}	
	    		    			
	    		    		int tempRow = 0;
	    		    		if(!gameBoard.isColumnFull(k)) 
	    		    		{
	    		    			while(boardCellsPanel[tempRow+1][k].getBackground().equals(Color.PINK)) 	
	    		    			{
	    		    				tempRow++;
	    		    			}
	    		    		}
	    		    		//boardCellsPanel[tempRow][k].setBackground(token.getTokenColor());
	    		    		boardCellsPanel[tempRow][k].setBackground(Color.RED);    	
	    		        }     
	    			 } 	
	    		}
	    		else
	    		{
	    			turnLabel.setText("It is " + match.getSecondPlayer().getName() + " (second player) turn.");
					Player currentPlayer = match.getSecondPlayer();
					Token token = currentPlayer.getToken();
	    			for (int j = 0; j < COLUMNS; j++) 
	    			{  
	    				//match.grantPlayerMove(currentPlayer);
	    			    if (e.getSource().equals(columnButtons[j]) && !gameBoard.isColumnFull(j))
	    			    {	
	    			    	int k = getClickedButton(e);
	    			        //match.grantPlayerMove(currentPlayer);
	    			        	
	    			        gameBoard.insert(token, k);
	    			        			
	    			        if(boardCellsPanel[ROWS-1][k].getBackground().equals(Color.PINK)) 	
	    			    	{
	    			        	//boardCellsPanel[ROWS-1][k].setBackground(token.getTokenColor());
	    			    		boardCellsPanel[ROWS-1][k].setBackground(Color.BLUE);
	    			    		return;	
	    			    	}	
	    			    			
	    			    	int tempRow = 0;
	    			    	if(!gameBoard.isColumnFull(k)) 
	    			    	{
	    			    		while(boardCellsPanel[tempRow+1][k].getBackground().equals(Color.PINK)) 	
	    			    		{
	    			    			tempRow++;
	    			    		}
	    			    	}
	    			    	//boardCellsPanel[tempRow][k].setBackground(token.getTokenColor());
	    			    	boardCellsPanel[tempRow][k].setBackground(Color.BLUE);		    		
	    			     }
	    			 }	
	    		} 
	    		if(match.getBoard().isDraw() || match.getBoard().isWin())
	    		{
	    			displayEndGameFrame();
	    		}
	    		match.setTurn(turn);
		        match.firstPlayerTurn = !match.firstPlayerTurn;
	    	}    	
	    }*/
		
        if (e.getSource() == resetButton) 
        {
        	reloadGame();
        }
        
        else if (e.getSource() == quitButton) 
        {
            System.exit(0);
        }
        
        else if (e.getSource() == saveButton) 
        {
        	//savedMatch = new File();	
            match.saveGameAndQuit();
            
        }
        
        else if (e.getSource() == loadButton) 
        {
        	connectFour.restartMatch(getName());
        }
    }  
        
       
	public static int getClickedButton(ActionEvent e)
    {
    	JButton button = (JButton)e.getSource();
        int clickedColumn = Integer.parseInt(button.getActionCommand());
        return clickedColumn;	
    }
    
       
        
      public void reloadGame()
      {
    	frame.setVisible(false);
      	displayStartGameFrame(); 
      }
     

}
