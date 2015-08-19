package test03_07;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import ex03_07.*;

public class StringComparatorBuilderTest
{
	@Test
	public void testEachComparator()
	{
		for(DefaultComparator defaultComparator : DefaultComparator.values())
		{
			for(CaseSensitiveProcessor caseSensitive : CaseSensitiveProcessor.values())
			{
				for(SpaceIgnoreProcessor spaceIgnore : SpaceIgnoreProcessor.values())
				{
					testConcreteComparator(defaultComparator, caseSensitive, spaceIgnore);
				}
			}
		}
	}
	
	public void testConcreteComparator(DefaultComparator defaultComparator, CaseSensitiveProcessor caseSensitive, SpaceIgnoreProcessor spaceIgnore)
	{
		Comparator<String> comparator = createComparator(defaultComparator, caseSensitive, spaceIgnore);
		
		boolean isDefaultOrder = defaultComparator == DefaultComparator.NORMAL;
		boolean ignoreSpace = spaceIgnore != SpaceIgnoreProcessor.NONE;
		boolean isCaseSensitive = caseSensitive == CaseSensitiveProcessor.NONE;
		
		int smallerResult = isDefaultOrder ? -1 : 1;
		int largerResult = isDefaultOrder ? 1 : -1;

		assertEquals(normalizedComparison(comparator, "ABC", "BBC"), smallerResult);
		assertEquals(normalizedComparison(comparator, "AAAAA", "B"), smallerResult);
		assertEquals(normalizedComparison(comparator, "D", "ABCD"), largerResult);
		assertEquals(normalizedComparison(comparator, "ABCDE", "ABCDE"), 0);
		assertEquals(normalizedComparison(comparator, "", "0"), smallerResult);
		
		assertEquals(normalizedComparison(comparator, "A C", "AC"), ignoreSpace ? 0 : smallerResult);
		assertEquals(normalizedComparison(comparator, "    ", ""), ignoreSpace ? 0 : largerResult);
		assertEquals(normalizedComparison(comparator, "  A", "A  "), ignoreSpace ? 0 : smallerResult);
		assertEquals(normalizedComparison(comparator, " CCC", "AAA"), ignoreSpace ? largerResult : smallerResult);

		assertEquals(normalizedComparison(comparator, "abc", "ABC"), isCaseSensitive ? largerResult : 0);
		assertEquals(normalizedComparison(comparator, "BBB", "ccc"), isCaseSensitive ? smallerResult : smallerResult);
		assertEquals(normalizedComparison(comparator, "abc", "ABCD"), isCaseSensitive ? largerResult : smallerResult);
	}
	
	private static Comparator<String> createComparator(DefaultComparator comparator, IStringProcessor ... processors)
	{
		StringComparatorBuilder builder = new StringComparatorBuilder().setDefaultOrder(comparator);
		
		for(IStringProcessor processor : processors)
		{
			if(processor == null)
				continue;
			else if(processor instanceof CaseSensitiveProcessor)
				builder.setStringProcessor(CaseSensitiveProcessor.class, (CaseSensitiveProcessor)processor);
			else if(processor instanceof SpaceIgnoreProcessor)
				builder.setStringProcessor(SpaceIgnoreProcessor.class, (SpaceIgnoreProcessor)processor);
			else
				throw new AssertionError("Unexpected class extends IStringProcessor : " + processor.getClass().getName());
		}
		
		return builder.build();
	}
	
	private static int normalizedComparison(Comparator<String> comparator, String s1, String s2)
	{
		int result = comparator.compare(s1, s2);
		
		if(result == 0)
			return 0;
		
		return result / Math.abs(result);
	}
}
