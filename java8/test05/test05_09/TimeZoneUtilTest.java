package test05_09;

import org.junit.Test;
import ex05_09.TimeZoneUtil2;

public class TimeZoneUtilTest
{
	@Test
	public void test()
	{
		TimeZoneUtil2.allTimeZoneOffset().entrySet().stream()
			.forEach(e -> System.out.printf("%s - %s%n", e.getKey(), e.getValue()));
	}
}
