package ex05_06;

import java.time.*;
import java.util.*;

public class DaySearcher
{
	/**
	 * 指定された年の範囲で13日の金曜日を全て取得します
	 * @param fromYear 検索範囲の年の開始日
	 * @param toYear 検索範囲の年の終了日(この年も検索対象に含まれます)
	 * @return fromYearからtoYearの範囲の13日の金曜日のList
	 * @exception IllegalArgumentException fromYearがtoYearより後の場合
	 */
	public static List<LocalDate> allThirteenFriday(int fromYear, int toYear)
	{
		final int targetDay = 13;
		
		if(fromYear > toYear)
			throw new IllegalArgumentException("'fromYear' must not be larger than 'toYear'.");
		
		List<LocalDate> list = new ArrayList<>((toYear - fromYear + 1) * 12 / 7);
		
		for(int y = fromYear; y <= toYear; y++)
		{
			for(int m = 1; m <= 12; m++)
			{
				LocalDate date = LocalDate.of(y, m, targetDay);
				
				if(date.getDayOfWeek() == DayOfWeek.FRIDAY)
					list.add(date);
			}
		}
		
		return list;
	}
}
