package test08_07;

import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.Test;
import ex08_07.NullableComparator;

public class NullableComparatorTest
{
	private final Comparator<String> expectedComparator = NullableComparator.expectedComparator();
	private final Comparator<String> testComparator = NullableComparator.reversedComparator();
	
	@Test
	public void test()
	{
		testCase("A", "B");
		testCase("A", "");
		testCase("01234567", "00123456789");
		testCase(null, "");
		testCase(null, null);
	}
	
	private void testCase(String s1, String s2)
	{
		int expected = this.expectedComparator.compare(s1, s2);
		int result = this.testComparator.compare(s1, s2);
		
		assertEquals(expected, result);
	}
}
