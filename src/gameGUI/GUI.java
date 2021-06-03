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

public class GUI extends JFrame implements ActionListener 
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
    private JButton quitButton;		// Quit button
    private JButton resetButton;	 // Reset button
    private JButton saveButton;		// Save button
    private JButton loadButton;		// Load button
    private JButton[] columnButtons;	 // Buttons for each column

    //private JTextField playerOneField;		// Field for player one's name
    //private JTextField playerTwoField;		// Field for player two's name
    
    private ConnectFour connectFour;	 
    private Match match;
    private Board gameBoard;
    private Token token; 
    private Player player;
    
   
    
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
		
		initializeStartGameFrame();
		
		connectFour = new ConnectFour();
		gameBoard = new Board();
		token = new Token(getBackground());
		player = new Player(getName(), token);
		match = new Match(gameBoard, player, player);
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
				initializeBoardComponents();

			}
		});
		
		loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				initializeBoardComponents();
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
     * Creates the game board.
     */
    public void createBoard() 
    {
        columnsButtonPanel = new JPanel(new GridLayout(1, 7, 10, 10));
        columnButtons = new JButton[7];

        for (int j = 0; j < 7; j++) 
        {
            columnButtons[j] = new JButton("" + j  + "");
            columnButtons[j].addActionListener(this);
            columnsButtonPanel.add(columnButtons[j]);
        }

        boardPanel = new JPanel(new GridLayout(7, 6, 20, 20));
        boardCellsPanel = new JPanel[7][6];

        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < boardCellsPanel[i].length; j++) 
            {
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
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
    	
    	match.executeTurn();
		
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
        
 
        
        //connectFour.play(match);
        
        if (match.firstPlayerTurn)
		{
			turnLabel.setText("Turn: " + match.getFirstPlayer().getName());
			Player currentPlayer = match.getFirstPlayer();
			Token token = currentPlayer.getToken();
			for (int i = 0; i < boardCellsPanel.length; i++) 
			 {     	
		        		if (e.getSource().equals(columnButtons[i]) && !gameBoard.isColumnFull(i))
		        		{	
		        			int k = getClickedButton(e);
		        			//gameBoard.insert(token, k);
		        			boardCellsPanel[k][i].setBackground(token.getTokenColor());
		        		}
		        	
		     }
		}
		else
		{
			turnLabel.setText("Turn: " + match.getSecondPlayer().getName());
			Player currentPlayer = match.getSecondPlayer();
			Token token = currentPlayer.getToken();
			for (int i = 0; i < boardCellsPanel.length; i++) 
			{
		        		if (e.getSource().equals(columnButtons[i]) && !gameBoard.isColumnFull(i))
		        		{	
		        			int k = getClickedButton(e);
		        			//gameBoard.insert(token, k);
		        			boardCellsPanel[k][i].setBackground(token.getTokenColor());
		        		}
		        }
		     }
        }
		
    
	/**
    * Returns the index of the cliked button.
    */
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

