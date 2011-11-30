package Utils;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	void setX(int x)
	{
		this.x=x;
	}
	
	void setY(int y)
	{
		this.y=y;
	}
	
	void setPos(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

	public String toString()
	{
		String s;
		s="Position -> X = "+getX()+" Y = "+getY();
		return s;
	}
	
}
