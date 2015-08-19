package test03_09;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import ex03_09.FieldComparatorFactory;

public class FieldComparatorFactoryTest
{
	@SuppressWarnings("unused")
	private static class SampleClass
	{
		private final String stringField;
		private final int intField;
		public final Double doubleField;
		
		public SampleClass(String s, int i, double d)
		{
			this.stringField = s;
			this.intField = i;
			this.doubleField = d;
		}
	}

	@Test
	public void compareWithMultipleComparableFields()
	{
		FieldComparatorFactory<SampleClass> factory = new FieldComparatorFactory<>(SampleClass.class);
		Comparator<SampleClass> comparator = factory.lexicographicComparator("stringField", "doubleField");
		
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("A", 0, 1.1)) < 0);
		assertTrue(comparator.compare(new SampleClass("B", 0, 1.0), new SampleClass("A", 0, 1.1)) > 0);
		assertTrue(comparator.compare(new SampleClass(" ", 0, 1.0), new SampleClass(" ", 0, 1.0)) == 0);
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("C", 0, 1.0)) < 0);
	}
	
	@Test
	public void compareWithSingleComparableField()
	{
		FieldComparatorFactory<SampleClass> factory = new FieldComparatorFactory<>(SampleClass.class);
		Comparator<SampleClass> comparator = factory.lexicographicComparator("stringField");
		
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("A", 0, 1.1)) == 0);
		assertTrue(comparator.compare(new SampleClass("B", 0, 1.0), new SampleClass("A", 0, 1.1)) > 0);
		assertTrue(comparator.compare(new SampleClass(" ", 0, 1.0), new SampleClass(" ", 0, 1.0)) == 0);
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("C", 0, 1.0)) < 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void compareWithNotComparableField()
	{
		FieldComparatorFactory<SampleClass> factory = new FieldComparatorFactory<>(SampleClass.class);
		Comparator<SampleClass> comparator = factory.lexicographicComparator("stringField", "intField");
		
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("A", 0, 1.1)) < 0);
		assertTrue(comparator.compare(new SampleClass("B", 0, 1.0), new SampleClass("A", 0, 1.1)) > 0);
		assertTrue(comparator.compare(new SampleClass(" ", 0, 1.0), new SampleClass(" ", 0, 1.0)) == 0);
		assertTrue(comparator.compare(new SampleClass("A", 0, 1.0), new SampleClass("C", 0, 1.0)) < 0);
	}
}
