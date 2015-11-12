package test08_06;

import static org.junit.Assert.*;
import java.util.*;
import javafx.geometry.*;
import org.junit.Test;
import ex08_06.ComparatorFactory;

public class ComparatorFactoryTest
{
	private final Comparator<Point2D> pointComparator = ComparatorFactory.getPoint2DComparator();
	private final Comparator<Rectangle2D> rectangleComparator = ComparatorFactory.getRectangle2DComparator();
	
	@Test
	public void compareRectangles()
	{
		assertEquals(0, compR(rect(0, 0, 0, 0), rect(0, 0, 0, 0)));
		assertEquals(1, compR(rect(0, 0, 0, 1), rect(0, 0, 0, 0)));
		assertEquals(1, compR(rect(0, 0, 2, 0), rect(0, 0, 0, 3)));
		assertEquals(1, compR(rect(0, 1, 0, 0), rect(0, 0, 4, 9)));
		assertEquals(1, compR(rect(1, 0, 0, 0), rect(0, 2, 2, 2)));
		assertEquals(1, compR(rect(1, 1, 0, 0), rect(1, 0, 1, 4)));
		assertEquals(1, compR(rect(1, -2, 3, 4), rect(-1, 2, 3, 4)));
		assertEquals(1, compR(rect(-2, -3, 4, 5), rect(-3, -4, 5, 6)));
	}
	
	@Test
	public void comparePoints()
	{
		assertEquals(0, compP(point(0, 0), point(0, 0)));
		assertEquals(1, compP(point(0, 3), point(0, 0)));
		assertEquals(1, compP(point(1, 0), point(0, 9)));
		assertEquals(1, compP(point(-1, -1), point(-2, -2)));
	}
	
	private int compP(Point2D p1, Point2D p2)
	{
		int comp = this.pointComparator.compare(p1, p2);
		int rev_comp = this.pointComparator.compare(p2, p1);
		
		if(comp == 0)
		{
			assertEquals(0, rev_comp);
		}
		else
		{
			// 元の比較結果と反転した比較結果の符号が不一致でないといけない
			assertTrue(comp * rev_comp < 0);
		}
		
		return comp;
	}
	
	private int compR(Rectangle2D r1, Rectangle2D r2)
	{
		int comp = this.rectangleComparator.compare(r1, r2);
		int rev_comp = this.rectangleComparator.compare(r2, r1);
		
		if(comp == 0)
		{
			assertEquals(0, rev_comp);
		}
		else
		{
			// 元の比較結果と反転した比較結果の符号が不一致でないといけない
			assertTrue(comp * rev_comp < 0);
		}
		
		return comp;
	}
	
	private static Rectangle2D rect(double x, double y, double w, double h)
	{
		return new Rectangle2D(x, y, w, h);
	}
	
	private static Point2D point(double x, double y)
	{
		return new Point2D(x, y);
	}
}
