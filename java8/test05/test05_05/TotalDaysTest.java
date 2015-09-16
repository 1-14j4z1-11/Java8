package test05_05;

import java.time.*;
import org.junit.Assert;
import org.junit.Test;
import ex05_05.TotalDays;

public class TotalDaysTest
{
	@Test
	public void calcTotalDaysFromYesterday()
	{
		Assert.assertEquals(1, TotalDays.from(LocalDate.now().minusDays(1)));
	}
	
	@Test
	public void calcTotalDaysFromOneWeekAgo()
	{
		Assert.assertEquals(7, TotalDays.from(LocalDate.now().minusWeeks(1)));
	}
	
	@Test
	public void calcTotalDaysFromOneWeekAndOneDayAgo()
	{
		Assert.assertEquals(8, TotalDays.from(LocalDate.now().minusWeeks(1).minusDays(1)));
	}
	
	@Test
	public void calcTotalDaysFromBirthDay()
	{
		System.out.println(TotalDays.from(LocalDate.of(1989, 3, 31)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithIllegalArgument()
	{
		TotalDays.from(null);
	}
}
