package test06_06;

import java.io.*;
import java.util.*;
import java.util.function.*;
import test06_05.WordSearcherTest;
import ex06_06.WordSearcher2;

public class WordSearcher2Test extends WordSearcherTest
{
	@Override
	protected Function<File[], Map<String, Set<File>>> getTestFunction()
	{
		return WordSearcher2::search;
	}
}
