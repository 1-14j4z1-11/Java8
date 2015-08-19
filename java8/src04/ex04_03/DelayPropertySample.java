package ex04_03;

import javafx.beans.property.Property;
import javafx.geometry.Point2D;
import ex04_02.*;

public class DelayPropertySample
{
	private final DelayPropertyWrapper<String> text = new DelayPropertyWrapper<>("");
	private final DelayPropertyWrapper<Point2D> location = new DelayPropertyWrapper<>(new Point2D(0, 0));
	private final DelayPropertyWrapper<Point2D> size = new DelayPropertyWrapper<>(new Point2D(10, 10));
	
	public DelayPropertySample()
	{ }
	
	public String getText()
	{
		return this.text.getValue();
	}
	
	public Point2D getLocation()
	{
		return this.location.getValue();
	}
	
	public Point2D getSize()
	{
		return this.size.getValue();
	}
	
	public Property<String> textProperty()
	{
		return this.text.property();
	}
	
	public Property<Point2D> locationProperty()
	{
		return this.location.property();
	}
	
	public Property<Point2D> sizeProperty()
	{
		return this.size.property();
	}
}
