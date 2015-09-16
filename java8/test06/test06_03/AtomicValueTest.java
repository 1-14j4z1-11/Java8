package test06_03;

import org.junit.Test;
import ex06_03.AtomicValueSample;

public class AtomicValueTest
{
	@Test
	public void test()
	{
		AtomicValueSample.counterThreadWithAtomicLong();
		AtomicValueSample.counterThreadWithLongAdder();
	}
}
