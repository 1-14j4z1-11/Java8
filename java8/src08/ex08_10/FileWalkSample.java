package ex08_10;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileWalkSample
{
	private static final String ROOT_PATH = "java_src";
	
	public static void main(String[] args)
	{
		try(Stream<Path> stream = Files.walk(Paths.get(ROOT_PATH), FileVisitOption.FOLLOW_LINKS))
		{
			List<Path> paths = stream.filter(FileWalkSample::filter).collect(Collectors.toList());
			paths.forEach(System.out::println);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static boolean filter(Path path)
	{
		try
		{
			return Files.readAllLines(path).stream()
				.filter(line -> line.contains("transient") || line.contains("volatile"))
				.findAny()
				.isPresent();
		}
		catch(IOException e)
		{
			return false;
		}
	}
}
