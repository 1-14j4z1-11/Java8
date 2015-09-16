package ex05_07;

import java.time.*;

public class TimeInterval
{
	private final LocalDateTime start;
	private final LocalDateTime end;
	
	private TimeInterval(LocalDateTime start, LocalDateTime end)
	{
		if(start.compareTo(end) > 0)
			throw new IllegalArgumentException("'start' must not be later than 'end'");
		
		this.start = start;
		this.end = end;
	}
	
	/** 時刻インターバルの開始時刻を取得します */
	public LocalDateTime getStartTime()
	{
		return this.start;
	}
	
	/** 時刻インターバルの終了時刻を取得します */
	public LocalDateTime getEndTime()
	{
		return this.end;
	}
	
	/**
	 * 2つのインスタンス間で時刻インターバルが重複しているかを判定します</br>
	 * 一方の終了時刻ともう一方の開始時刻が一致しているのみの場合は重複と扱いません
	 * @param other 重複判定するもう一方のインスタンス
	 * @return 重複している場合はtrueを返し、していない場合はfalseを返す
	 */
	public boolean overlapsWith(TimeInterval other)
	{
		return (this.start.compareTo(other.end) < 0)
			&& (this.end.compareTo(other.start) > 0);
	}
	
	/**
	 * 時刻インターバルのインスタンスを生成します
	 * @param start 開始時刻
	 * @param end 終了時刻
	 * @return startからendまでの時刻インターバルを示すインスタンス
	 * @exception IllegalArgumentException endよりstartの方が時刻が遅い場合
	 */
	public static TimeInterval between(LocalDateTime start, LocalDateTime end)
	{
		return new TimeInterval(start, end);
	}
}
