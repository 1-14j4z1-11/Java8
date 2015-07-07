package test02_03;

import static org.junit.Assert.*;
import org.junit.Test;
import ex02_03.WordCounter;
import util.StopWatch;

public class WordCounterTest
{
	@Test
	public void countSequential()
	{
		String[] words = createStrings(100000);
		StopWatch sw = new StopWatch();
		
		sw.start();
		long count = WordCounter.countSequential(words, 6);
		long time = sw.stop();
		
		assertEquals(40000, count);
		
		System.out.printf("Sequential : %10d [ns]%n", time);
	}

	@Test
	public void countParallel()
	{
		String[] words = createStrings(100000);
		StopWatch sw = new StopWatch();
		
		sw.start();
		long count = WordCounter.countParallel(words, 6);
		long time = sw.stop();
		
		assertEquals(40000, count);
		
		System.out.printf("Parallel   : %10d [ns]%n", time);
	}
	
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
		};
	
	private static String[] createStrings(int count)
	{
		String[] words = new String[count];
		int length = SAMPLE_WORDS.length;
		
		for(int i = 0; i < count; i++)
		{
			words[i] = SAMPLE_WORDS[i % length];
		}
		
		return words;
	}
}
