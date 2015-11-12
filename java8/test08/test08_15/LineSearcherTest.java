package test08_15;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import ex08_15.FileSearcher;

public class LineSearcherTest
{
	@Test
	public void searchPartialMatchedLines()
	{
		List<String> lines = FileSearcher.search("testfiles/multi_lines.txt", "A");
		assertArrayEquals(new String[] { "ABCDEFGHIJKLMN" }, lines.toArray());
	}

	@Test
	public void searchAllMatchedLines()
	{
		List<String> lines1 = FileSearcher.search("testfiles/multi_lines.txt", "^A$");
		assertArrayEquals(new String[] { }, lines1.toArray());

		List<String> lines2 = FileSearcher.search("testfiles/multi_lines.txt", "^1234567890$");
		assertArrayEquals(new String[] { "1234567890" }, lines2.toArray());
	}
}
