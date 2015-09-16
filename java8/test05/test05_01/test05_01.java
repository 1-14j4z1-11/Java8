package test05_01;

import static org.junit.Assert.*;
import java.time.*;
import org.junit.Test;
import ex05_01.ProgrammersDay;

public class test05_01
{
	@Test
	public void test()
	{
		assertEquals(LocalDate.of(2015, 9, 13), ProgrammersDay.calcurate(2015));
		assertEquals(LocalDate.of(2016, 9, 12), ProgrammersDay.calcurate(2016));
		assertEquals(LocalDate.of(2000, 9, 12), ProgrammersDay.calcurate(2000));
		assertEquals(LocalDate.of(2100, 9, 13), ProgrammersDay.calcurate(2100));
	}
}
