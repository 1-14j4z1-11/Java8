package test02_05;

import static org.junit.Assert.*;
import java.util.stream.*;
import org.junit.Test;
import ex02_05.StreamFactory;

public class StreamFactoryTest
{
	@Test
	public void createRandomStream()
	{
		long a = 25214903917L;
		long c = 11L;
		long m = (long)Math.pow(2, 48);
		
		StreamFactory factory = new StreamFactory();
		Stream<Long> stream = factory.createRandomStream(10, a, c, m);
		Long expected = 10L;
		
		for(Object obj : stream.limit(100).toArray())
		{
			assertEquals(expected.toString(), obj.toString());
			expected = (expected * a + c) % m;
			
			System.out.println(obj.toString());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void createRandomStreamWithMEqualsZero()
	{
		long a = 10;
		long c = 9L;
		long m = 0;
		
		StreamFactory factory = new StreamFactory();
		factory.createRandomStream(10, a, c, m);
	}
}
