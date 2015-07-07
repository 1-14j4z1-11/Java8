package ex02_03;

import java.util.*;

public class WordCounter
{
	/**
	 * wordLength以上の長さを持つ文字列がwords中にいくつ含まれるかを返します
	 * @param words 検索する文字列配列
	 * @param wordLength 検索対象の文字の長さ
	 * @return 一定長さ以上の文字列の数
	 * @exception NullPointerException wordsがnullの場合
	 */
	public static long countSequential(String[] words, int wordLength)
	{
		if(words == null)
			throw new NullPointerException();
		
		long count =  Arrays.stream(words)
			.filter(s -> (s.length() >= wordLength))
			.count();
		
		return count;
	}

	/**
	 * wordLength以上の長さを持つ文字列がwords中にいくつ含まれるかを返します
	 * @param words 検索する文字列配列
	 * @param wordLength 検索対象の文字の長さ
	 * @return 一定長さ以上の文字列の数
	 * @exception NullPointerException wordsがnullの場合
	 */
	public static long countParallel(String[] words, int wordLength)
	{
		if(words == null)
			throw new NullPointerException();
		
		long count =  Arrays.stream(words)
			.parallel()
			.filter(s -> (s.length() >= wordLength))
			.count();
		
		return count;
	}
}
