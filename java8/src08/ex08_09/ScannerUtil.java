package ex08_09;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ScannerUtil
{
	/**
	 * Scannerを単語ごとの文字列Streamに変換します
	 * @param scanner 変換するScanner
	 * @return 単語のStream
	 */
	public static Stream<String> wordStream(Scanner scanner)
	{
		Objects.requireNonNull(scanner, "Argument must not be null.");
		return createStream(scanner, scanner::hasNext, scanner::next);
	}

	/**
	 * Scannerを行ごとの文字列Streamに変換します
	 * @param scanner 変換するScanner
	 * @return 行のStream
	 */
	public static Stream<String> lineStream(Scanner scanner)
	{
		Objects.requireNonNull(scanner, "Argument must not be null.");
		return createStream(scanner, scanner::hasNextLine, scanner::nextLine);
	}
	
	/**
	 * ScannerをIntStreamに変換します
	 * @param scanner 変換するScanner
	 * @return IntStream
	 */
	public static IntStream intStream(Scanner scanner)
	{
		Objects.requireNonNull(scanner, "Argument must not be null.");
		return createStream(scanner, scanner::hasNextInt, scanner::nextInt)
			.mapToInt(Integer::intValue);
	}
	
	/**
	 * ScannerをDoubleStreamに変換します
	 * @param scanner 変換するScanner
	 * @return DoubleStream
	 */
	public static DoubleStream doubleStream(Scanner scanner)
	{
		Objects.requireNonNull(scanner, "Argument must not be null.");
		return createStream(scanner, scanner::hasNextDouble, scanner::nextDouble)
			.mapToDouble(Double::doubleValue);
	}
	
	private static <T> Stream<T> createStream(Scanner scanner, BooleanSupplier hasNext, Supplier<T> next)
	{
		Iterator<T> iter = new Iterator<T>()
		{
			@Override
			public boolean hasNext()
			{
				while(!hasNext.getAsBoolean() && scanner.hasNext())
				{
					scanner.next();
				}
				
				return hasNext.getAsBoolean();
			}
			
			@Override
			public T next()
			{
				return next.get();
			}
		};
		
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
			iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}
}
