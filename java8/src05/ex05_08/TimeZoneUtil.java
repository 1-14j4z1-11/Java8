package ex05_08;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class TimeZoneUtil
{
	private TimeZoneUtil() { throw new UnsupportedOperationException(); }
	
	/**
	 * 全てのタイムゾーンIDとオフセット時間を取得します
	 */
	public static Map<ZoneId, ZoneOffset> allTimeZoneOffset()
	{
		return ZoneId.getAvailableZoneIds().stream()
			.map(zone -> ZoneId.of(zone))
			.collect(Collectors.toMap(zone -> zone, TimeZoneUtil::getOffsetWithBaseTime));
	}
	
	/**
	 * ZoneIdから基準時刻との差を計算します
	 */
	public static ZoneOffset getOffsetWithBaseTime(ZoneId zoneId)
	{
		return zoneId.getRules().getOffset(LocalDateTime.of(2000, 1, 1, 0, 0));
	}
}
