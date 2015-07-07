package ex02_12;

import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class StringStreamUtil
{
	private static final int SHORT_WORD_LENGTH = 12;
	
	private StringStreamUtil() { }
	
	/**
	 * Stringストリームから長さが12より短い単語がそれぞれいくつ存在するかをカウントします
	 * @param stream 検索対象のストリーム
	 * @return 長さ0～11の単語数の配列
	 */
	public static int[] countShortWords(Stream<String> stream)
	{
		AtomicInteger[] atomicCounts = new AtomicInteger[SHORT_WORD_LENGTH];
		
		for(int i = 0; i < SHORT_WORD_LENGTH; i++)
		{
			atomicCounts[i] = new AtomicInteger();
		}
		
		stream.parallel().forEach(str -> 
			{
				if(str.length() < SHORT_WORD_LENGTH)
					atomicCounts[str.length()].incrementAndGet();
			});
		
		int[] counts = new int[SHORT_WORD_LENGTH];
		
		for(int i = 0; i < SHORT_WORD_LENGTH; i++)
		{
			counts[i] = atomicCounts[i].get();
		}
		
		return counts;
	}
}
