package ex05_09;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;
import ex05_08.*;

public class TimeZoneUtil2
{
	private TimeZoneUtil2() { throw new UnsupportedOperationException(); }

	/**
	 * �I�t�Z�b�g�̕���0�łȂ��A�S�Ẵ^�C���]�[��ID�ƃI�t�Z�b�g���Ԃ��擾���܂�
	 */
	public static Map<ZoneId, ZoneOffset> allTimeZoneOffset()
	{
		return ZoneId.getAvailableZoneIds().stream()
			.map(zone -> ZoneId.of(zone))
			.filter(TimeZoneUtil2::zoneHasMinuteOffset)
			.collect(Collectors.toMap(zone -> zone, TimeZoneUtil::getOffsetWithBaseTime));
	}
	
	private static boolean zoneHasMinuteOffset(ZoneId zone)
	{
		return (TimeZoneUtil.getOffsetWithBaseTime(zone).get(ChronoField.OFFSET_SECONDS) % 3600) != 0;
	}
}
