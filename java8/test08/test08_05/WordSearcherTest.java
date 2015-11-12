package test08_05;

import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;
import util.*;
import ex08_05.WordSearcher;

public class WordSearcherTest
{
	@Test(expected = NullPointerException.class)
	public void searchNullList()
	{
		WordSearcher.searchLongWords(null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchWithNegativeLength()
	{
		WordSearcher.searchLongWords(createStrings(6, 1), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchWithZeroLength()
	{
		WordSearcher.searchLongWords(createStrings(6, 1), 0);
	}
	
	@Test
	public void searchList()
	{
		final int randomSeed = 31;
		
		List<String> list = createStrings(16, 20);
		Collections.shuffle(list, new Random(randomSeed));
		
		List<String> expected = list.stream()
			.filter(str -> (str.length() >= 12))
			.collect(Collectors.toList());
		
		List<String> filtered = WordSearcher.searchLongWords(list, 12);
		
		for(String str : expected)
		{
			assertTrue("Result does not contains a expected element.", filtered.remove(str));
		}
		
		assertEquals("Result contains unexpected element(s).", 0, filtered.size());
	}
	
	@Test
	public void testPerformance()
	{
		final int randomSeed = 31;
		final int maxWordLength = 16;
		final int wordDuplicates = 20;
		final int searchWordLength = 12;
		
		StopWatch sw = new StopWatch();
		
		List<String> list_NoLambda = createStrings(maxWordLength, wordDuplicates);
		List<String> list_Lambda = createStrings(maxWordLength, wordDuplicates);
		Collections.shuffle(list_NoLambda, new Random(randomSeed));
		Collections.shuffle(list_Lambda, new Random(randomSeed));

		sw.start();
		WordSearcher.searchLongWords(list_NoLambda, searchWordLength);
		sw.stop();
		System.out.printf("Without lambda  = %10.3f [ms]%n", sw.getMilliTime());
		
		sw.start();
		WordSearcher.searchLongWordsWithLamba(list_Lambda, searchWordLength);
		sw.stop();
		System.out.printf("With lambda     = %10.3f [ms]%n", sw.getMilliTime());
	}
	
	/**
	 * 0 ~ maxLengthの各長さの文字列をduplicateずつ持つ文字列リストを生成</br>
	 * リスト内の各要素の長さはIndex % (maxLength + 1)に一致する
	 */
	public static List<String> createStrings(int maxLength, int duplicate)
	{
		List<String> list = new ArrayList<>(maxLength + 1);
		
		for(int i = 0; i < duplicate; i++)
		{
			StringBuilder builder = new StringBuilder();
			
			for(int j = 0; j <= maxLength; j++)
			{
				list.add(builder.toString());
				builder.append((char)(maxLength % 10 + '0'));
			}
		}
		
		return list;
	}
}
