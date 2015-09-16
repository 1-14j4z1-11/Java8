package ex05_05;

import java.time.*;
import java.time.temporal.*;
import java.util.Objects;

public class TotalDays
{
	private TotalDays() { throw new UnsupportedOperationException(); }
	
	/**
	 * �����̓��t���猻�݂܂ł̓�����Ԃ��܂�
	 * @param date ���t���J�E���g����J�n��
	 * @return date���獡���܂ł̓����̍��v
	 */
	public static long from(LocalDate date)
	{
		Objects.requireNonNull(date, "Argument 'date' must not be null.");
		
		return date.until(LocalDate.now(), ChronoUnit.DAYS);
	}
}
