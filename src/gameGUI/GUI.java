package GUI;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.text.*;

import javax.swing.event.*;

import customException.*;
import gameComponents.*;
import playGame.*;

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
    
    private JLabel playerOneLabel;	 // Label for player one
    private JLabel playerTwoLabel;	// Label for player two
    private JLabel turnLabel;		// Label displaying the turn

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
	
	private boolean playGame;
    
    private ConnectFour connectFour;	 
    private Match match;
    private Board gameBoard;
    private Token token; 
    private Player firstPlayer;
    private Player secondPlayer;
    
   
    
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
		
		initializeStartGameFrame();
		
		connectFour = new ConnectFour();
		gameBoard = new Board();
		token = new Token(getBackground());
		firstPlayer = new Player(getName(), token);
		secondPlayer = new Player(getName(), token);
		match = new Match(gameBoard, firstPlayer, secondPlayer);
		
		playGame = false;
		
	}

	
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeStartGameFrame() 
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
     * Initialize the contents of the game board.
     */
    public void initializeBoardComponents() 
    {	
    	setBounds(300, 300, 600, 700);
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
				initializeMatchPlayers();
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

  	  startMatchButton.setBounds(120, 80, 200, 50);
  	  frame.getContentPane().add(startMatchButton);
  	  frame.add(startMatchButton, BorderLayout.SOUTH);
  	  frame.getContentPane().add(panel);
  	  frame.setVisible(true);
  	  
  	  playGame = true;
  	  
  	  match.executeTurn();
  	  if(match.firstPlayerTurn)
  	  {
    	turnLabel.setText("It is " + match.getFirstPlayer().getName() + " turn.");
  	  }
  	  else
  	  {
    	turnLabel.setText("It is " + match.getSecondPlayer().getName() + " turn.");
  	  }
    }
    
    
    
    
    
    /**
     * Initialize the contents of the match.
     */
    public void initializeMatchPlayers() 
    {	
    	Player playerOne = match.getFirstPlayer();
        Player playerTwo = match.getSecondPlayer();

        String playerOneText = String.format(playerOne.getName()); 
        String playerTwoText = String.format(playerTwo.getName()); 

        if(match.firstPlayerTurn)
        {
        	turnLabel.setText("It is " + match.getFirstPlayer().getName() + " turn.");
        }
        else
        {
        	turnLabel.setText("It is " + match.getSecondPlayer().getName() + " turn.");
        }
        playerOneLabel.setText(playerOneText);
        playerTwoLabel.setText(playerTwoText);
    }
    

    
    /** 
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, COLUMNS, 10, 10));
        columnButtons = new JButton[COLUMNS];

        for (int j = 0; j < COLUMNS; j++) 
        {
            columnButtons[j] = new JButton("" + j + "");
            columnButtons[j].addActionListener(this);
            columnsButtonPanel.add(columnButtons[j]);
        }

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
    	
    	//connectFour.play(match);
    	//match.executeTurn();
    	match.whoPlaysFirst(); 
	    //Board.printBoard();
	    //Scanner inSave = new Scanner(System.in);
    	int turn = match.getTurn();
    	
	    for ( ; turn <= 42; turn++)
	    {
	    	if (!match.isEndGame(gameBoard))
	    	{
	    		if (match.firstPlayerTurn)
	    		{
	    			for (int j = 0; j < COLUMNS; j++) 
	    			 {  
	    				turnLabel.setText("It is " + match.getFirstPlayer().getName() + " (first player) turn.");
	    				Player currentPlayer = match.getFirstPlayer();
	    				Token token = currentPlayer.getToken();
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
	    		    		
	    		    		if(match.getBoard().isDraw())
	    		    		{
	    		    			//System.out.print(" The match ended in a draw.");
	    		    		}
	    		    		else if (match.getBoard().isWin())   
	    		    		{
	    		    		    //System.out.print(" The match ended with a win.");
	    		    	    }	
	    		        }
	    		        
	    			 }
	    			//match.setTurn(turn);  	
	    			 }
	    			else if (!match.firstPlayerTurn)
	    			{
	    				for (int j = 0; j < COLUMNS; j++) 
	    				 {  
	    					turnLabel.setText("It is " + match.getSecondPlayer().getName() + " (second player) turn.");
	    					Player currentPlayer = match.getSecondPlayer();
	    					Token token = currentPlayer.getToken();
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
	    			    		
	    			    		if(match.getBoard().isDraw() || match.getBoard().isWin())
	    			    		{
	    			    			//System.out.print(" The match ended in a draw.");
	    			    			
	    			    			//initializeEndGameFrame();
	    			    		}
	    			    		
	    			        }
	    				 }	
	    			  }
	    		match.setTurn(turn); 
		        match.firstPlayerTurn = !match.firstPlayerTurn;
		        //Board.printBoard();
	    	}
	    }
		
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
            match.saveGameAndQuit();
        }
        
        else if (e.getSource() == loadButton) 
        {
        	connectFour.restartMatch(getName());
        }
        
        
        if (match.firstPlayerTurn)
		{
			for (int j = 0; j < COLUMNS; j++) 
			 {  
				turnLabel.setText("It is " + match.getFirstPlayer().getName() + " (first player) turn.");
				Player currentPlayer = match.getFirstPlayer();
				Token token = currentPlayer.getToken();
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
		    		
		    		if(match.getBoard().isDraw())
		    		{
		    			//System.out.print(" The match ended in a draw.");
		    		}
		    		else if (match.getBoard().isWin())   
		    		{
		    		    //System.out.print(" The match ended with a win.");
		    	    }	
		        }
		        	
		     }
		}
		else if (!match.firstPlayerTurn)
		{
			for (int j = 0; j < COLUMNS; j++) 
			 {  
				turnLabel.setText("It is " + match.getSecondPlayer().getName() + " (second player) turn.");
				Player currentPlayer = match.getSecondPlayer();
				Token token = currentPlayer.getToken();
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
		    		
		    		if(match.getBoard().isDraw() || match.getBoard().isWin())
		    		{
		    			//System.out.print(" The match ended in a draw.");
		    			
		    			//initializeEndGameFrame();
		    		}
		    		
		        }
			 }	
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
      	initializeStartGameFrame();
      
      }

}
