package ex05_12;

import java.time.*;

public class ScheduleItem
{
	private final String name;
	private final ZonedDateTime time;
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param name �\�薼
	 * @param time �\�莞��
	 */
	public ScheduleItem(String name, ZonedDateTime time)
	{
		this.name = name;
		this.time = time;
	}
	
	/**
	 * �\�薼���擾���܂�
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * �\��̎������擾���܂�
	 */
	public ZonedDateTime getTime()
	{
		return this.time;
	}
}
