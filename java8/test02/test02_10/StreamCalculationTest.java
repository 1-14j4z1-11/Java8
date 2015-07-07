package test02_10;

import static org.junit.Assert.*;
import java.util.stream.*;
import org.junit.Test;
import ex02_10.StreamCalculation;

public class StreamCalculationTest
{
	@Test
	public void averageForEmptyStream()
	{
		assertEquals(0.0, StreamCalculation.average(Stream.empty()), 0.0);
	}

	@Test
	public void averageForSingletonStream()
	{
		assertEquals(12.5, StreamCalculation.average(Stream.of(12.5)), 0.0);
	}

	@Test
	public void averageForFiniteStream()
	{
		Double[] values = { 3.14, 2.71, 6.63, 1.38, 3.00, 1.60 };
		Stream<Double> stream = Stream.of(values);
		
		assertEquals(calcAverage(values), StreamCalculation.average(stream), 1e-5);
	}
	
	private double calcAverage(Double[] values)
	{
		double sum = 0.0;
		long count = 0;
		
		for(Double val : values)
		{
			sum += val;
			count++;
		}
		
		return (count == 0) ? 0.0 : (sum / count);
	}
}
