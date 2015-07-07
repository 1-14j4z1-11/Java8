package test02_08;

import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;
import ex02_08.StreamCreationUtil;

public class StreamCreationUtilTest
{
	// firstがサイズ0のストリーム -> 空のストリームが生成される
	@Test
	public void zipZeroStreamAndFiniteStream()
	{
		Integer[] array = { 0, 1, 2 };
		
		Stream<Integer> first = Stream.empty();
		Stream<Integer> second = Stream.of(array);
		
		isExpectedStream(new Integer[0], StreamCreationUtil.zip(first, second));
	}
	
	// secondがサイズ0のストリーム -> 空のストリームが生成される
	@Test
	public void zipFiniteStreamAndZeroStream()
	{
		Integer[] array = { 0, 1, 2 };
		
		Stream<Integer> first = Stream.of(array);
		Stream<Integer> second = Stream.empty();
		
		isExpectedStream(new Integer[0], StreamCreationUtil.zip(first, second));
	}

	// 単一要素を持つ2個のストリーム -> 単一要素のストリームが生成される
	@Test
	public void zipSingleObjectStreams()
	{
		Integer[] firstArray = { 0 };
		Integer[] secondArray = { 9 };
		
		Stream<Integer> first = Stream.of(firstArray);
		Stream<Integer> second = Stream.of(secondArray);
		
		isExpectedStream(new Integer[] { 0 }, StreamCreationUtil.zip(first, second));
	}
	
	// サイズがfirst > secondのストリームからストリームを生成-> secondのサイズ*2のストリームが生成される
	@Test
	public void zipFiniteStreams()
	{
		Integer[] firstArray = { 0, 1, 2, 3 };
		Integer[] secondArray = { 4, 5, 6 };
		Integer[] expected = { 0, 4, 1, 5, 2, 6 };

		Stream<Integer> first = Stream.of(firstArray);
		Stream<Integer> second = Stream.of(secondArray);
		
		isExpectedStream(expected, StreamCreationUtil.zip(first, second));
	}

	// サイズが同じ2個のストリームからストリームを生成-> サイズ*2-1のストリームが生成される
	@Test
	public void zipSameSizeFiniteStreams()
	{
		Integer[] firstArray = { 0, 1, 2, 3 };
		Integer[] secondArray = { 4, 5, 6, 7 };
		Integer[] expected = { 0, 4, 1, 5, 2, 6, 3 };

		Stream<Integer> first = Stream.of(firstArray);
		Stream<Integer> second = Stream.of(secondArray);
		
		isExpectedStream(expected, StreamCreationUtil.zip(first, second));
	}
	
	// 有限ストリームと無限ストリームからストリームを生成-> firstのサイズ*2-1のストリームが生成される
	@Test
	public void zipFiniteStreamAndInfiniteStream()
	{
		Integer[] firstArray = { 1, 2, 3, 4 };
		Integer[] expected = { 1, 99, 2, 99, 3, 99, 4 };
		
		Stream<Integer> first = Stream.of(firstArray);
		Stream<Integer> second = Stream.generate(() -> 99);
		
		isExpectedStream(expected, StreamCreationUtil.zip(first, second));
	}

	// 無限ストリームと有限からストリームを生成-> secondのサイズ*2のストリームが生成される
	@Test
	public void zipInfiniteStreamAndFiniteStream()
	{
		Integer[] secondArray = { 1, 2, 3, 4 };
		Integer[] expected = { 99, 1, 99, 2, 99, 3, 99, 4 };
		
		Stream<Integer> first = Stream.generate(() -> 99);
		Stream<Integer> second = Stream.of(secondArray);
		
		isExpectedStream(expected, StreamCreationUtil.zip(first, second));
	}
	
	// 無限ストリーム2個からストリームを生成-> nullが返る
	@Test
	public void zipInfiniteStreams()
	{
		Stream<Integer> first = Stream.generate(() -> 0);
		Stream<Integer> second = Stream.generate(() -> 0);
		
		assertNull(StreamCreationUtil.zip(first, second));
	}
	
	// firstがnullの場合
	@Test(expected = NullPointerException.class)
	public void zipWithNullStream1()
	{
		StreamCreationUtil.zip(null, Stream.of(0));
	}
	
	// secondがnullの場合
	@Test(expected = NullPointerException.class)
	public void zipWithNullStream2()
	{
		StreamCreationUtil.zip(Stream.of(0), null);
	}
	
	/**
	 * actualのStreamがexpectedItemsを返し、それ以外の要素を持たないストリームかどうかを判定する
	 * @param expectedItems 期待結果の配列
	 * @param actual 実行結果のStream
	 * @return 実行結果が期待結果と一致するかどうか
	 */
	private static boolean isExpectedStream(Integer[] expectedItems, Stream<Integer> actual)
	{
		Iterator<Integer> iter = actual.iterator();
		
		for(int i = 0; i < expectedItems.length; i++)
		{
			Integer cur = iter.next();
			if(!expectedItems[i].equals(cur))
			{
				System.err.printf("expected = %s, actual = %s%n", expectedItems[i], cur);
				return false;
			}
		}
		
		if(iter.hasNext())
		{
			System.err.printf("Stream has unexpected item%n");
			return false;
		}
		
		return true;
	}
}
