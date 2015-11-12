package ex08_05;

import java.util.*;
import java.util.stream.*;

public class WordSearcher
{
	private WordSearcher() { throw new UnsupportedOperationException(); }
	
	/**
	 * wordsから指定された長さ以上の文字列を抽出します
	 * @param words 検索対象の文字列のリスト
	 * @param length 検索する文字列の長さ
	 * @return 指定長以上の文字列のリスト
	 * @exception NullPointerException wordsがnullの場合
	 * @exception IllegalArgumentException lengthが0以下の場合
	 */
	public static List<String> searchLongWords(List<String> words, int length)
	{
		Objects.requireNonNull(words, "Argument 'words' must not be null.");
		
		if(length <= 0)
			throw new IllegalArgumentException("Argument 'length' must be positive number.");
		
		List<String> result = new ArrayList<>();
		
		words.forEach(word ->
		{
			if(word.length() >= length)
				result.add(word);
		});
		
		return result;
	}
	
	/** 比較用 */
	public static List<String> searchLongWordsWithLamba(List<String> words, int length)
	{
		Objects.requireNonNull(words, "Argument 'words' must not be null.");
		
		if(length <= 0)
			throw new IllegalArgumentException("Argument 'length' must be positive number.");
		
		return words.stream().filter(str -> str.length() >= length).collect(Collectors.toList());
	}
}
