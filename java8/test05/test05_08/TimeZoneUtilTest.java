package test05_08;

import org.junit.Test;
import ex05_08.TimeZoneUtil;

public class TimeZoneUtilTest
{
	@Test
	public void test()
	{
		TimeZoneUtil.allTimeZoneOffset().entrySet().stream()
			.forEach(e -> System.out.printf("%s - %s%n", e.getKey(), e.getValue()));
	}
}
