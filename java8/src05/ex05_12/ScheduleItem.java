package ex05_12;

import java.time.*;

public class ScheduleItem
{
	private final String name;
	private final ZonedDateTime time;
	
	/**
	 * インスタンスを初期化します
	 * @param name 予定名
	 * @param time 予定時刻
	 */
	public ScheduleItem(String name, ZonedDateTime time)
	{
		this.name = name;
		this.time = time;
	}
	
	/**
	 * 予定名を取得します
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * 予定の時刻を取得します
	 */
	public ZonedDateTime getTime()
	{
		return this.time;
	}
}
