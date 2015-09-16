package ex06_05;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class WordSearcher
{
	/**
	 * ファイルの配列から全ての単語を抽出し、単語と各単語が含まれていたファイル一覧を取得します</br>
	 * ファイルが存在しないなどで読み取りに失敗したファイルは集計から除外されます
	 * @param files 読み取るファイルの一覧
	 * @return 読み取った単語とその単語が含まれるファイルの一覧
	 */
	public static Map<String, Set<File>> search(File[] files)
	{
		Objects.requireNonNull(files, "'files' must not be null.'");
		
		final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(File file : files)
		{
			pool.submit(() ->
				{
					try
					{
						List<String> lines = Files.readAllLines(file.toPath());
						HashSet<File> value = new HashSet<>();
						value.add(file);

						lines.stream()
							.map(line -> line.split("\\s+"))
							.flatMap(words -> Arrays.stream(words))
							.forEach(word -> map.merge(word, value, (v1, v2) ->
								{
									HashSet<File> set = new HashSet<>();
									set.addAll(v1);
									set.addAll(v2);
									return set;
								}));
					}
					catch(Exception e)
					{
						e.printStackTrace();
						return;
					}
				});
		}
		
		try
		{
			pool.shutdown();
			pool.awaitTermination(10, TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{ }
		
		return map;
	}
}
