package ex05_05;

import java.time.*;
import java.time.temporal.*;
import java.util.Objects;

public class TotalDays
{
	private TotalDays() { throw new UnsupportedOperationException(); }
	
	/**
	 * 引数の日付から現在までの日数を返します
	 * @param date 日付をカウントする開始日
	 * @return dateから今日までの日数の合計
	 */
	public static long from(LocalDate date)
	{
		Objects.requireNonNull(date, "Argument 'date' must not be null.");
		
		return date.until(LocalDate.now(), ChronoUnit.DAYS);
	}
}
