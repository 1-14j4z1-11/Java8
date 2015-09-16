package ex05_01;

import java.time.*;
import java.time.temporal.*;

public class ProgrammersDay
{
	private ProgrammersDay() { throw new UnsupportedOperationException(); }
	
	/**
	 * �v���O���}�[�̓����v�Z���܂�
	 * @param year �v���O���}�[�̓������߂�N
	 * @return ���̔N�̃v���O���}�[�̓�
	 */
	public static LocalDate calcurate(int year)
	{
		return LocalDate.of(year, 1, 1).plus(255, ChronoUnit.DAYS);
	}
}
