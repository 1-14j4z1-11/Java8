package ex02_13;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class StringStreamUtil2
{
	private static final int SHORT_WORD_LENGTH = 12;
	
	private StringStreamUtil2() { }
	
	/**
	 * Stringストリームから長さが12より短い単語がそれぞれいくつ存在するかをカウントします
	 * @param stream 検索対象のストリーム
	 * @return 長さ0～11の単語数のMap(Key:単語長/Value:単語数)
	 */
	public static Map<Integer, Long> countShortWords(Stream<String> stream)
	{
		AtomicInteger[] atomicCounts = new AtomicInteger[SHORT_WORD_LENGTH];
		
		for(int i = 0; i < SHORT_WORD_LENGTH; i++)
		{
			atomicCounts[i] = new AtomicInteger();
		}
		
		Map<Integer, Long> shortWordLengths = stream.parallel()
			.filter(str -> str.length() < SHORT_WORD_LENGTH)
			.collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
		
		// 存在しないキーに対して値0を追加
		for(int i = 0; i < SHORT_WORD_LENGTH; i++)
		{
			if(!shortWordLengths.containsKey(i))
			{
				shortWordLengths.put(i, 0L);
			}
		}
		
		return shortWordLengths;
	}
}
