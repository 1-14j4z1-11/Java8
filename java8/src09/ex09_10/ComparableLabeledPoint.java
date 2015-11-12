package ex09_10;

import ex09_09.ILabeledPoint;
import ex09_09.LabeledPoint;

public class ComparableLabeledPoint implements ILabeledPoint, Comparable<ComparableLabeledPoint>
{
	private final LabeledPoint labeledPoint;
	
	public ComparableLabeledPoint(String label, int x, int y)
	{
		this.labeledPoint = new LabeledPoint(label, x, y);
	}
	
	@Override
	public final String getLabel()
	{
		return this.labeledPoint.getLabel();
	}
	
	@Override
	public final int getX()
	{
		return this.labeledPoint.getX();
	}
	
	@Override
	public final int getY()
	{
		return this.labeledPoint.getY();
	}
	
	@Override
	public int hashCode()
	{
		return this.labeledPoint.hashCode();
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
		
		ComparableLabeledPoint other = (ComparableLabeledPoint) obj;
		
		return this.labeledPoint.equals(other.labeledPoint);
	}
	
	@Override
	public int compareTo(ComparableLabeledPoint o)
	{
		if(this.labeledPoint.getLabel().compareTo(o.labeledPoint.getLabel()) != 0)
		{
			return this.labeledPoint.getLabel().compareTo(o.labeledPoint.getLabel());
		}
		else if(Integer.compare(this.labeledPoint.getX(), o.labeledPoint.getX()) != 0)
		{
			return Integer.compare(this.labeledPoint.getX(), o.labeledPoint.getX());
		}
		else if(Integer.compare(this.labeledPoint.getY(), o.labeledPoint.getY()) != 0)
		{
			return Integer.compare(this.labeledPoint.getY(), o.labeledPoint.getY());
		}
		
		return 0;
	}
}
