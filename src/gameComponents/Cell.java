public class Cell 
{
	Token token = new Token(null);
	boolean isFilled;
	boolean player1;
	boolean player2;
	
	public Cell(Token token, boolean isFilled, boolean player1, boolean player2)
	{
		this.token = token;
		isFilled = false;
		player1 = false;
		player2 = false;
	}
	
	public void setFilled()
	{
		isFilled = true;
	}
	
	public boolean isFilled()
	{
		return isFilled;
	}
	
	public void setPlayer1()
	{
		player1 = true;
	}
	
	public boolean isPlayer1()
	{
		return player1;
	}
	
	public void setPlayer2()
	{
		player2 = true;
	}
	
	public boolean isPlayer2()
	{
		return player2;
	}
	
}
