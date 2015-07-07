package test02_01;

import static org.junit.Assert.*;
import org.junit.Test;
import ex02_01.WordCounter;

public class WordCounterTest
{
	@Test
	public void searchSingleWordArrayWithTrueFilter()
	{
		String[] words = new String[] { "test" };
		assertEquals(1, WordCounter.countWordsParallel(words, s -> true));
	}
	
	@Test
	public void searchSingleWordArrayWithFalseFilter()
	{
		String[] words = new String[] { "test" };
		assertEquals(0, WordCounter.countWordsParallel(words, s -> false));
	}
	
	@Test
	public void searchWordsArray1()
	{
		String[] words = createStrings(24);
		assertEquals(11, WordCounter.countWordsParallel(words, s -> s.length() <= 10));
	}

	@Test
	public void searchWordsArray2()
	{
		String[] words = createStrings(25);
		assertEquals(16, WordCounter.countWordsParallel(words, s -> s.length() <= 15));
	}

	@Test
	public void searchWordsArray3()
	{
		String[] words = createStrings(31);
		assertEquals(16, WordCounter.countWordsParallel(words, s -> (s.length() % 2 == 0)));
	}
	
	/**
	 * lengthの長さを持つ文字列配列を生成</br>
	 * 配列の各要素の長さは配列のIndexに一致する
	 */
	public static String[] createStrings(int length)
	{
		StringBuilder builder = new StringBuilder();
		String[] array = new String[length];
		
		for(int i = 0; i < length; i++)
		{
			array[i] = builder.toString();
			builder.append((char)(length % 10 + '0'));
		}
		
		return array;
	}
}
