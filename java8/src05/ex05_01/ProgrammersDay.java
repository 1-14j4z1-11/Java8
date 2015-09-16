package ex05_01;

import java.time.*;
import java.time.temporal.*;

public class ProgrammersDay
{
	private ProgrammersDay() { throw new UnsupportedOperationException(); }
	
	/**
	 * プログラマーの日を計算します
	 * @param year プログラマーの日を求める年
	 * @return その年のプログラマーの日
	 */
	public static LocalDate calcurate(int year)
	{
		return LocalDate.of(year, 1, 1).plus(255, ChronoUnit.DAYS);
	}
}
