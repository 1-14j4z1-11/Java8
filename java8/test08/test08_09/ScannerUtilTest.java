package test08_09;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import org.junit.Test;
import ex08_09.ScannerUtil;

public class ScannerUtilTest
{
	@Test
	public void testDoubleStream()
	{
		System.out.printf("%n<DoubleStream>%n");
		Scanner scanner = createScanner();
		ScannerUtil.doubleStream(scanner).forEach(System.out::println);
	}
	
	@Test
	public void testIntStream()
	{
		System.out.printf("%n<IntStream>%n");
		Scanner scanner = createScanner();
		ScannerUtil.intStream(scanner).forEach(System.out::println);
	}

	@Test
	public void testWordStream()
	{
		System.out.printf("%n<WordStream>%n");
		Scanner scanner = createScanner();
		ScannerUtil.wordStream(scanner).forEach(System.out::println);
	}

	@Test
	public void testLineStream()
	{
		System.out.printf("%n<LineStream>%n");
		Scanner scanner = createScanner();
		ScannerUtil.lineStream(scanner).forEach(System.out::println);
	}
	
	private static Scanner createScanner()
	{
		try
		{
			return new Scanner(Paths.get("testfiles", "scanner_test.txt")).useDelimiter("[\r\n \t]");
		}
		catch(IOException e)
		{
			throw new AssertionError("Scanner instance could not be created.");
		}
	}
}
