package test05_06;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.*;
import ex05_06.DaySearcher;

public class DaySearcherTest
{
	@Test(expected = IllegalArgumentException.class)
	public void searchIllegalYearRange()
	{
		DaySearcher.allThirteenFriday(2000, 1999);
	}

	@Test
	public void searchOneYear()
	{
		LocalDate[] expected = { LocalDate.of(2000, 10, 13) };
		LocalDate[] result = DaySearcher.allThirteenFriday(2000, 2000).toArray(new LocalDate[0]);
		
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void search20Century()
	{
		List<LocalDate> dates = DaySearcher.allThirteenFriday(1901, 2000);
		
		System.out.println(dates);
	}
}
