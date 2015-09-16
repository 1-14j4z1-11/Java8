package test06_04;

import static org.junit.Assert.*;
import org.junit.Test;
import ex06_04.ItemSearcher;

public class ItemSearcherTest
{
	@Test
	public void searchMaxItem()
	{
		long[] array = new long[] { -20, -100, 100, 0, 20 };
		assertEquals(100, ItemSearcher.max(array));
	}

	@Test
	public void searchMinItem()
	{
		long[] array = new long[] { -20, -100, 100, 0, 20 };
		assertEquals(-100, ItemSearcher.min(array));
	}

	@Test
	public void searchMaxItemOfZeroSizeArray()
	{
		long[] array = new long[0];
		assertEquals(Long.MIN_VALUE, ItemSearcher.max(array));
	}

	@Test
	public void searchMinItemOfZeroSizeArray()
	{
		long[] array = new long[0];
		assertEquals(Long.MAX_VALUE, ItemSearcher.min(array));
	}

	@Test(expected = NullPointerException.class)
	public void searchMaxItemOfNullArray()
	{
		 ItemSearcher.max(null);
	}

	@Test(expected = NullPointerException.class)
	public void searchMinItemOfNullArray()
	{
		 ItemSearcher.min(null);
	}
}
