package test02_12;

import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;
import ex02_12.StringStreamUtil;

public class StringStreamUtilTest
{
	@Test
	public void countEmptyStream()
	{
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		Stream<String> stream = Stream.empty();
		int[] counts = StringStreamUtil.countShortWords(stream);
		
		assertArrayEquals(expected, counts);
	}
	
	@Test
	public void countStreamContainingNoTarget()
	{
		int[] expected =	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] histogram =	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 12 };
		
		Stream<String> stream = Stream.of(createWords(histogram));
		int[] counts = StringStreamUtil.countShortWords(stream);
		
		assertArrayEquals(expected, counts);
	}
	
	@Test
	public void countStreamContaining()
	{
		int[] expected =	{ 12, 30, 24, 5, 3, 13, 0, 1, 33, 9, 61, 2 };
		int[] histogram =	{ 12, 30, 24, 5, 3, 13, 0, 1, 33, 9, 61, 2, 12, 1 };
		
		Stream<String> stream = Stream.of(createWords(histogram));
		int[] counts = StringStreamUtil.countShortWords(stream);
		
		assertArrayEquals(expected, counts);
	}
	
	private static int RANDOM_SEED = 64;
	private static String[] SAMPLE_WORDS = 
		{
			"",
			"1",
			"12",
			"123",
			"1234",
			"12345",
			"123456",
			"1234567",
			"12345678",
			"123456789",
			"1234567890",
			"12345678901",
			"123456789012",
			"1234567890123",
		};
	
	private static String[] createWords(int... histogram)
	{
		int total = Arrays.stream(histogram).sum();
		List<String> list = new ArrayList<>(total);
		
		for(int i = 0; (i < histogram.length) && (i < SAMPLE_WORDS.length); i++)
		{
			for(int j = 0; j < histogram[i]; j++)
			{
				list.add(SAMPLE_WORDS[i]);
			}
		}
		
		Collections.shuffle(list, new Random(RANDOM_SEED));
		
		return list.toArray(new String[total]);
	}
}
