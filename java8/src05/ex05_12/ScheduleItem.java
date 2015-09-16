package ex05_12;

import java.time.*;

public class ScheduleItem
{
	private final String name;
	private final ZonedDateTime time;
	
	/**
	 * ƒCƒ“ƒXƒ^ƒ“ƒX‚ğ‰Šú‰»‚µ‚Ü‚·
	 * @param name —\’è–¼
	 * @param time —\’è
	 */
	public ScheduleItem(String name, ZonedDateTime time)
	{
		this.name = name;
		this.time = time;
	}
	
	/**
	 * —\’è–¼‚ğæ“¾‚µ‚Ü‚·
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * —\’è‚Ì‚ğæ“¾‚µ‚Ü‚·
	 */
	public ZonedDateTime getTime()
	{
		return this.time;
	}
}
