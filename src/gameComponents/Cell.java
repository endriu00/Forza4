package gameComponents;

public class Cell 
{
	Token token = new Token(null);
	boolean isFilled;
	boolean firstPlayer;
	boolean secondPlayer;
	
	public Cell(Token token, boolean isFilled, boolean firstPlayer, boolean secondPlayer)
	{
		this.token = token;
		isFilled = false;
		firstPlayer = false;
		secondPlayer = false;
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
		firstPlayer = true;
	}
	
	public boolean isPlayer1()
	{
		return firstPlayer;
	}
	
	public void setPlayer2()
	{
		secondPlayer = true;
	}
	
	public boolean isPlayer2()
	{
		return secondPlayer;
	}
	
}
