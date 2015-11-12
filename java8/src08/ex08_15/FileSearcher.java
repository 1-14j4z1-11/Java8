package ex08_15;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class FileSearcher
{
	/**
	 * 正規表現の条件をに一致する文字列を含む行を指定されたファイルから検索します
	 * @param path 検索するファイルパス
	 * @param pattern 検索対象の正規表現
	 * @return 検索結果
	 */
	public static List<String> search(String path, String pattern)
	{
		Objects.requireNonNull(path, "Arguments must not be null.");
		Objects.requireNonNull(pattern, "Arguments must not be null.");
		
		try
		{
			List<String> lines = Files.readAllLines(Paths.get(path));
			Predicate<String> filter = Pattern.compile(pattern).asPredicate();
			return lines.stream().filter(filter).collect(Collectors.toList());
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
