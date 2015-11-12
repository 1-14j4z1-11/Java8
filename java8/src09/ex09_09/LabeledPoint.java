package ex09_09;

public class LabeledPoint implements ILabeledPoint
{
	private final String label;
	private final int x;
	private final int y;
	
	public LabeledPoint(String label, int x, int y)
	{
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public final String getLabel()
	{
		return this.label;
	}

	@Override
	public final int getX()
	{
		return this.x;
	}
	
	@Override
	public final int getY()
	{
		return this.y;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		
		LabeledPoint other = (LabeledPoint)obj;
		
		if(label == null)
		{
			if(other.label != null)
				return false;
		}
		else if(!label.equals(other.label))
			return false;
		if(x != other.x)
			return false;
		if(y != other.y)
			return false;
		return true;
	}
}
