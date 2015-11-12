package test09_09;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import ex09_09.ILabeledPoint;

public abstract class LabeledPointTestBase
{
	@Test
	public void test()
	{
		for(int i = 0; i < 10000; i++)
		{
			testEqualCase(i);
			testNotEqualCase(i);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testEqualCase(int seed)
	{
		Random random = new Random(seed);
		
		String label = createLabel(seed);
		int x = random.nextInt();
		int y = random.nextInt();
		
		ILabeledPoint point1 = this.createInstance(label, x, y);
		ILabeledPoint point2 = this.createInstance(label, x, y);
		
		assertEquals(point1.getLabel(), point2.getLabel());
		assertEquals(point1.getX(), point2.getX());
		assertEquals(point1.getY(), point2.getY());
		assertTrue(point1.equals(point2));
		assertEquals(point1.hashCode(), point2.hashCode());
		
		if((point1 instanceof Comparable) && (point2 instanceof Comparable))
		{
			Comparable comp1 = (Comparable)point1;
			Comparable comp2 = (Comparable)point2;
			assertEquals(0, comp1.compareTo(comp2));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testNotEqualCase(int seed)
	{
		Random random = new Random(seed);
		
		String label = createLabel(seed);
		String label2 = createLabel(seed ^ 0xFFFF);
		int x = random.nextInt();
		int y = random.nextInt();
		
		ILabeledPoint point1 = this.createInstance(label, x, y);
		ILabeledPoint point2 = this.createInstance(label, x ^ 0xFFFF, y);
		ILabeledPoint point3 = this.createInstance(label, x, y ^ 0xFFFF);
		ILabeledPoint point4 = this.createInstance(label2, x, y);
		
		assertFalse(point1.equals(point2));
		assertFalse(point2.equals(point3));
		assertFalse(point3.equals(point4));
		assertFalse(point4.equals(point1));
		
		if((point1 instanceof Comparable)
			&& (point2 instanceof Comparable)
			&& (point3 instanceof Comparable)
			&& (point4 instanceof Comparable))
		{
			Comparable comp1 = (Comparable)point1;
			Comparable comp2 = (Comparable)point2;
			Comparable comp3 = (Comparable)point3;
			Comparable comp4 = (Comparable)point4;
			
			assertEquals(ComparePoints(point1, point2), normalizeCompareResult(comp1.compareTo(comp2)));
			assertEquals(ComparePoints(point2, point3), normalizeCompareResult(comp2.compareTo(comp3)));
			assertEquals(ComparePoints(point3, point4), normalizeCompareResult(comp3.compareTo(comp4)));
			assertEquals(ComparePoints(point4, point1), normalizeCompareResult(comp4.compareTo(comp1)));
		}
	}
	
	private static int ComparePoints(ILabeledPoint point1, ILabeledPoint point2)
	{
		if(point1.getLabel().compareTo(point2.getLabel()) != 0)
		{
			return normalizeCompareResult(point1.getLabel().compareTo(point2.getLabel()));
		}
		else if(Integer.compare(point1.getX(), point2.getX()) != 0)
		{
			return normalizeCompareResult(Integer.compare(point1.getX(), point2.getX()));
		}
		else if(Integer.compare(point1.getY(), point2.getY()) != 0)
		{
			return normalizeCompareResult(Integer.compare(point1.getY(), point2.getY()));
		}
		
		return 0;
	}
	
	private static int normalizeCompareResult(int result)
	{
		return (result == 0) ? 0 : result / Math.abs(result);
	}
	
	private static String createLabel(int seed)
	{
		Random random = new Random(seed);
		int length = seed & 0x1F;
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < length; i++)
		{
			builder.append((char)(random.nextInt(0x7f - 0x20) + 0x20));
		}
		
		return builder.toString();
	}
	
	protected abstract ILabeledPoint createInstance(String label, int x, int y);		
}
