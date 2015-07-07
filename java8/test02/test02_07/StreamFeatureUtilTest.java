package test02_07;

import static org.junit.Assert.*;
import java.util.stream.Stream;
import org.junit.Test;
import ex02_07.StreamFeatureUtil;

public class StreamFeatureUtilTest
{
	@Test
	public void checkZeroSizeStream()
	{
		Stream<?> stream = Stream.of(new Object[0]);
		assertTrue(StreamFeatureUtil.isFinite(stream));
	}

	@Test
	public void checkFiniteStream()
	{
		Stream<?> stream = Stream.of(new Object[1024]);
		assertTrue(StreamFeatureUtil.isFinite(stream));
	}
	
	@Test
	public void checkInfiniteStream()
	{
		Stream<?> stream = Stream.generate(() -> 0);
		assertFalse(StreamFeatureUtil.isFinite(stream));
	}

	@Test(expected = NullPointerException.class)
	public void checkNullStream()
	{
		StreamFeatureUtil.isFinite(null);
	}
}
