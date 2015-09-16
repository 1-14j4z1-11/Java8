package ex05_10;

import java.time.*;
import java.util.Objects;

/**
 * 2地点の飛行時間を計算するクラス
 */
public class FlightScheduler
{
	private final ZoneId departureZone;
	private final ZoneId destinationZone;
	
	/**
	 * インスタンスを初期化します
	 * @param departure 出発地
	 * @param destination 到着地
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public FlightScheduler(ZoneId departure, ZoneId destination)
	{
		Objects.requireNonNull(departure, "Argument 'departure' must not be null.");
		Objects.requireNonNull(destination, "Argument 'destination' must not be null.");
		
		this.departureZone = departure;
		this.destinationZone = destination;
	}
	
	/**
	 * 出発地時刻と到着地時刻から実飛行時間を算出します
	 * @param departureZone 出発地のタイムゾーンでの出発時刻
	 * @param destinationZone 到着地のタイムゾーンでの到着時刻
	 * @return 実際の飛行時間
	 */
	public Duration flightTime(LocalDateTime departureTime, LocalDateTime destinationTime)
	{
		ZonedDateTime zonedDpr = ZonedDateTime.of(departureTime, this.departureZone);
		ZonedDateTime zonedDst = ZonedDateTime.of(destinationTime, this.destinationZone);
		
		return Duration.between(zonedDpr, zonedDst);
	}
	
	/**
	 * 出発地時刻と飛行時間から到着時の現地時刻を算出します
	 * @param departureTime 出発時刻
	 * @param flightTime 飛行時間
	 * @return 到着時の現地時刻
	 */
	public ZonedDateTime destinationTime(LocalDateTime departureTime, Duration flightTime)
	{
		ZonedDateTime zonedDpr = ZonedDateTime.of(departureTime, this.departureZone);
		ZonedDateTime zonedDst = zonedDpr.plus(flightTime).withZoneSameInstant(this.destinationZone);
		
		return zonedDst;
	}
}
