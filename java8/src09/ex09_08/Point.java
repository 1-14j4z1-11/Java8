package ex09_08;

public class Point implements Comparable<Point>
{
	private final int x;
	private final int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/** X座標を取得します */
	public int getX()
	{
		return x;
	}

	/** Y座標を取得します */
	public int getY()
	{
		return y;
	}
	
	@Override
	public int compareTo(Point o)
	{
		int compX = Integer.valueOf(this.x).compareTo(o.x);
		
		if(compX != 0)
			return compX;
		else
			return Integer.valueOf(this.y).compareTo(o.y);
	}
}
