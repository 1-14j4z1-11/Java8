package test06_05;

import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import org.junit.Test;
import ex06_05.WordSearcher;

public class WordSearcherTest
{
	private String[] PATHS = new String[]
		{
			"test06/files/file1.txt",
			"test06/files/file2.txt"
		};
	
	protected Function<File[], Map<String, Set<File>>> getTestFunction()
	{
		return WordSearcher::search;
	}
	
	@Test(expected = NullPointerException.class)
	public void searchNullFileArray()
	{
		this.getTestFunction().apply(null);
	}
	
	@Test
	public void searchWords()
	{
		File[] files = Arrays.stream(PATHS).map(path -> new File(path)).toArray(File[]::new);
		Map<String, Set<File>> map = this.getTestFunction().apply(files);
		
		checkContainsItems(map, "aaaa", files[1]);
		checkContainsItems(map, "bbbb", files[1]);
		checkContainsItems(map, "ccc", files);
		checkContainsItems(map, "dddd", files);
		checkContainsItems(map, "eee", files[1]);
		checkContainsItems(map, "ffff", files[0]);
	}
	
	private static void checkContainsItems(Map<String, Set<File>> map, String key, File ... values)
	{
		assertTrue("Result map does not contains key : " + key, map.containsKey(key));
		assertEquals(values.length, map.get(key).size());
		assertTrue(Arrays.stream(values).allMatch(val -> map.get(key).contains(val)));
	}
}
