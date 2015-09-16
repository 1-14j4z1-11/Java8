package ex05_06;

import java.time.*;
import java.util.*;

public class DaySearcher
{
	/**
	 * �w�肳�ꂽ�N�͈̔͂�13���̋��j����S�Ď擾���܂�
	 * @param fromYear �����͈͂̔N�̊J�n��
	 * @param toYear �����͈͂̔N�̏I����(���̔N�������ΏۂɊ܂܂�܂�)
	 * @return fromYear����toYear�͈̔͂�13���̋��j����List
	 * @exception IllegalArgumentException fromYear��toYear����̏ꍇ
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
