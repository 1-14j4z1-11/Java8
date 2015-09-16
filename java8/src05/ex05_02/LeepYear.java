package ex05_02;

import java.time.LocalDate;

public class LeepYear
{
	public static void plusDaysSample()
	{
		LocalDate date = LocalDate.of(2000, 2, 29);
		
		// 1年後の2001/02/29が無効なため、2001/02/28になる
		System.out.println(date.plusYears(1));

		// 4年後の2004/02/29は有効なため、2004/02/29になる
		System.out.println(date.plusYears(4));
		
		// 1年後の2001/02/29が無効なため、一旦2001/02/28になり、そのまま3年足すため、2004/02/28になる
		System.out.println(date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
