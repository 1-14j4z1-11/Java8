package test04_03;

import static org.junit.Assert.*;
import javafx.geometry.Point2D;
import org.junit.Test;
import ex04_03.DelayPropertySample;

public class DelayPropertySampleTest
{
	@Test
	public void test()
	{
		DelayPropertySample sample = new DelayPropertySample();

		assertEquals("", sample.getText());
		assertEquals(new Point2D(0, 0), sample.getLocation());
		assertEquals(new Point2D(10, 10), sample.getSize());
		
		sample.textProperty().setValue("Changed");
		sample.locationProperty().setValue(new Point2D(5, 5));
		sample.sizeProperty().setValue(new Point2D(100, 100));

		assertEquals("Changed", sample.getText());
		assertEquals(new Point2D(5, 5), sample.getLocation());
		assertEquals(new Point2D(100, 100), sample.getSize());
	}
}
