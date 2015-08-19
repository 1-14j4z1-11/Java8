package test03_20;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.junit.Test;
import ex03_20.ListMapper;

public class ListMapperTest
{
	@Test(expected = NullPointerException.class)
	public void testIllegalArguments1()
	{
		ListMapper.map(null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testIllegalArguments2()
	{
		ListMapper.map(null, t -> t);
	}

	@Test(expected = NullPointerException.class)
	public void testIllegalArguments3()
	{
		ListMapper.map(new ArrayList<Object>(), null);
	}
	
	@Test
	public void mapToSameClass()
	{
		List<String> in = Stream.of("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF").collect(Collectors.toList());
		List<String> expected = Stream.of("zAz", "zBBz", "zCCCz", "zDDDDz", "zEEEEEz", "zFFFFFFz").collect(Collectors.toList());
		
		Function<String, String> func = str -> "z" + str + "z";
		
		assertArrayEquals(expected.toArray(), ListMapper.map(in, func).toArray());
	}

	@Test
	public void mapToAnotherClass()
	{
		List<String> in = Stream.of("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF").collect(Collectors.toList());
		List<Integer> expected = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
		
		Function<String, Integer> func = str -> str.length();
		
		assertArrayEquals(expected.toArray(), ListMapper.map(in, func).toArray());
	}
}
