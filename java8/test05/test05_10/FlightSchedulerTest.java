package test05_10;

import static org.junit.Assert.*;
import java.time.*;
import org.junit.Test;
import ex05_10.FlightScheduler;

public class FlightSchedulerTest
{
	@Test(expected = NullPointerException.class)
	public void createInstanceWithIllegalArgument1()
	{
		new FlightScheduler(null, ZoneId.of("Japan"));
	}

	@Test(expected = NullPointerException.class)
	public void createInstanceWithIllegalArgument2()
	{
		new FlightScheduler(ZoneId.of("Japan"), null);
	}

	@Test(expected = NullPointerException.class)
	public void createInstanceWithIllegalArgument3()
	{
		new FlightScheduler(null, null);
	}
	
	@Test
	public void calcFlightTime()
	{
		ZoneId from = ZoneId.of("America/Los_Angeles");	// -08:00
		ZoneId to = ZoneId.of("Japan");	// +09:00
		
		FlightScheduler flight = new FlightScheduler(from, to);
		Duration duration = flight.flightTime(LocalDateTime.of(2000, 1, 1, 15, 0), LocalDateTime.of(2000, 1, 2, 9, 0));
		
		assertEquals(Duration.ofHours(1), duration);
	}
}
