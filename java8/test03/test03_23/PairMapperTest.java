package test03_23;

import static org.junit.Assert.*;
import org.junit.Test;
import ex03_23.*;

public class PairMapperTest
{
	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments1()
	{
		PairMapper.map(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments2()
	{
		PairMapper.map(null, t -> t);
	}

	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments3()
	{
		PairMapper.map(new Pair<>("", ""), null);
	}
	
	@Test
	public void mapPair()
	{
		Pair<String> pair = new Pair<>("123", "123456");
		Pair<Integer> mappedPair = PairMapper.map(pair, str -> str.length());

		assertEquals(3, mappedPair.getFirst().intValue());
		assertEquals(6, mappedPair.getSecond().intValue());
	}
}
