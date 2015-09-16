package test05_11;

import static org.junit.Assert.*;
import java.time.*;
import org.junit.Test;
import ex05_11.FlightScheduler;

public class FlightSchedulerTest2
{
	@Test
	public void calcFlightTime()
	{
		ZoneId from = ZoneId.of("America/Los_Angeles");	// -08:00
		ZoneId to = ZoneId.of("Japan");	// +09:00
		
		FlightScheduler flight = new FlightScheduler(from, to);
		ZonedDateTime dst = flight.destinationTime(LocalDateTime.of(2000, 1, 1, 15, 0), Duration.ofHours(1));
		
		assertEquals(ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 9, 0), to), dst);
	}
}
