package ex02_01;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

public class WordCounter
{
	private static final int THREAD_NUM = 8;
	
	/**
	 * 文字列配列から条件に一致する文字列の数を取得します</br>
	 * filterにnullを指定した場合は全ての文字列を検索対象とします
	 * @param words 検索対象の文字列配列
	 * @param filter 検索対象の文字列の条件
	 * @return 条件に一致した文字列の数
	 * @exception NullPointerException wordsがnullの場合
	 */
	public static int countWordsParallel(String[] words, Predicate<String> filter)
	{
		if(words == null)
			throw new NullPointerException();
		
		if(filter == null)
			filter = str -> true;
		
		List<CompletableFuture<Integer>> futures = new ArrayList<>(THREAD_NUM);
		int unitLength = (words.length + THREAD_NUM - 1) / THREAD_NUM;
		
		for(int i = 0; i < THREAD_NUM; i++)
		{
			futures.add(countRangeAsync(words, filter, i * unitLength, unitLength));
		}
		
		AtomicInteger count = new AtomicInteger();
		futures.stream().forEach(future -> count.addAndGet(future.join()));
		
		return count.get();
	}
	
	/**
	 * 非同期で文字列検索を行うCompletableFutureを生成します</br>
	 * @param words 検索対象の文字列配列
	 * @param filter 検索対象の文字列の条件
	 * @param begIndex 配列内で検索する範囲の開始位置
	 * @param length 配列内で検索する範囲の数
	 * @return 文字列検索を行うCompletableFuture
	 */
	private static CompletableFuture<Integer> countRangeAsync(String[] words, Predicate<String> filter, int begIndex, int length)
	{
		return CompletableFuture.supplyAsync(() -> 
		{
			int wordCount = 0;
			
			for(int i = begIndex; (i < begIndex + length) && (i < words.length); i++)
			{
				if(filter.test(words[i]))
					wordCount++;
			}
			
			return wordCount;
		});
	}
}
