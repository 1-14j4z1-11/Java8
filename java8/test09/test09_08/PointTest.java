package test09_08;

import static org.junit.Assert.*;
import org.junit.Test;
import ex09_08.Point;

public class PointTest
{
	@Test
	public void test()
	{
		testSingleCase(0, 0x8000_0000, 0, 0x8000_0000);
		testSingleCase(0, 0x8000_0001, 0, 0x8000_0000);
		testSingleCase(0x8000_0000, 0x8000_0001, 0x8000_0000, 0x8000_0000);
		testSingleCase(0x8000_0000, 0x8000_0000, 0x8000_0000, 0x8000_0000);
		testSingleCase(0x8000_0000, 0x8000_0002, 0x8000_0000, 0x8000_0000);
		testSingleCase(1, 1, 1, 2);
	}
	
	private static void testSingleCase(int x1, int y1, int x2, int y2)
	{
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		
		assertEquals(x1, p1.getX());
		assertEquals(y1, p1.getY());
		assertEquals(x2, p2.getX());
		assertEquals(y2, p2.getY());

		assertEquals(comparePoints(p1, p2), normalizeCompareResult(p1.compareTo(p2)));
		assertEquals(comparePoints(p2, p1), normalizeCompareResult(p2.compareTo(p1)));
	}
	
	private static int comparePoints(Point p1, Point p2)
	{
		int resultX = Integer.compare(p1.getX(), p2.getX());
		int resultY = Integer.compare(p1.getY(), p2.getY());
		
		return normalizeCompareResult((resultX != 0) ? resultX : resultY);
	}
	
	private static int normalizeCompareResult(int result)
	{
		return (result == 0) ? 0 : result / Math.abs(result);
	}
	
}
