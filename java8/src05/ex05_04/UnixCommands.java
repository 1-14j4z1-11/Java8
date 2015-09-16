package ex05_04;

import java.time.*;

public class UnixCommands
{
	private static DayOfWeek END_LINE_DAY = DayOfWeek.SUNDAY;
	
	private UnixCommands() { throw new UnsupportedOperationException(); }
	
	/**
	 * Unixのcalコマンドど同じ結果を標準出力に出力します
	 * @param year 出力するカレンダーの年
	 * @param month 出力するカレンダーの月
	 */
	public static void cal(int year, int month)
	{
		LocalDate date = LocalDate.of(year, month, 1);
		int emptyCount = (date.getDayOfWeek().getValue() - END_LINE_DAY.getValue() + 6) % 7;
		
		for(int i = 0; i < emptyCount; i++)
		{
			System.out.print("   ");
		}
		
		while(date.getMonth().getValue() == month)
		{
			System.out.printf(" %2d", date.getDayOfMonth());
			
			if(date.getDayOfWeek() == END_LINE_DAY)
				System.out.println();
			
			date = date.plusDays(1);
		}
	}
}
