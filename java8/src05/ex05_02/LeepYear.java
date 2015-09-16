package ex05_02;

import java.time.LocalDate;

public class LeepYear
{
	public static void plusDaysSample()
	{
		LocalDate date = LocalDate.of(2000, 2, 29);
		
		// 1�N���2001/02/29�������Ȃ��߁A2001/02/28�ɂȂ�
		System.out.println(date.plusYears(1));

		// 4�N���2004/02/29�͗L���Ȃ��߁A2004/02/29�ɂȂ�
		System.out.println(date.plusYears(4));
		
		// 1�N���2001/02/29�������Ȃ��߁A��U2001/02/28�ɂȂ�A���̂܂�3�N�������߁A2004/02/28�ɂȂ�
		System.out.println(date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
