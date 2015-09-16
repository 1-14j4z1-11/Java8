package ex05_10;

import java.time.*;
import java.util.Objects;

/**
 * 2�n�_�̔�s���Ԃ��v�Z����N���X
 */
public class FlightScheduler
{
	private final ZoneId departureZone;
	private final ZoneId destinationZone;
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param departure �o���n
	 * @param destination �����n
	 * @exception NullPointerException �����̂����ꂩ��null�̏ꍇ
	 */
	public FlightScheduler(ZoneId departure, ZoneId destination)
	{
		Objects.requireNonNull(departure, "Argument 'departure' must not be null.");
		Objects.requireNonNull(destination, "Argument 'destination' must not be null.");
		
		this.departureZone = departure;
		this.destinationZone = destination;
	}
	
	/**
	 * �o���n�����Ɠ����n�����������s���Ԃ��Z�o���܂�
	 * @param departureZone �o���n�̃^�C���]�[���ł̏o������
	 * @param destinationZone �����n�̃^�C���]�[���ł̓�������
	 * @return ���ۂ̔�s����
	 */
	public Duration flightTime(LocalDateTime departureTime, LocalDateTime destinationTime)
	{
		ZonedDateTime zonedDpr = ZonedDateTime.of(departureTime, this.departureZone);
		ZonedDateTime zonedDst = ZonedDateTime.of(destinationTime, this.destinationZone);
		
		return Duration.between(zonedDpr, zonedDst);
	}
	
	/**
	 * �o���n�����Ɣ�s���Ԃ��瓞�����̌��n�������Z�o���܂�
	 * @param departureTime �o������
	 * @param flightTime ��s����
	 * @return �������̌��n����
	 */
	public ZonedDateTime destinationTime(LocalDateTime departureTime, Duration flightTime)
	{
		ZonedDateTime zonedDpr = ZonedDateTime.of(departureTime, this.departureZone);
		ZonedDateTime zonedDst = zonedDpr.plus(flightTime).withZoneSameInstant(this.destinationZone);
		
		return zonedDst;
	}
}
