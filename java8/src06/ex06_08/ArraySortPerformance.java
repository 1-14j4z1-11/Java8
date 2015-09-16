package ex06_08;

import java.util.*;
import java.util.stream.*;
import util.StopWatch;

public class ArraySortPerformance
{
	private static int TRY_COUNT = 10;
	
	public static void checkPerformance(int size)
	{
		long[] results_seq = new long[TRY_COUNT];
		long[] results_par = new long[TRY_COUNT];
		
		for(int i = 0; i < TRY_COUNT; i++)
		{
			int[] array_seq = createArray(size, i);
			int[] array_par = createArray(size, i);
			StopWatch sw = new StopWatch();
			
			sw.start();
			Arrays.sort(array_seq);
			results_seq[i] = sw.stop();
			
			sw.start();
			Arrays.parallelSort(array_par);
			results_par[i] = sw.stop();
		}
		
		System.out.printf("[Size = %8d]", size);
		System.out.printf("  S:%10.3f\tP:%10.3f%n", averageMillSec(results_seq), averageMillSec(results_par));
		
	}
	
	private static double averageMillSec(long[] array)
	{
		return Arrays.stream(array).average().getAsDouble() / 1000.0 / 1000.0;
	}
	
	private static int[] createArray(int size, long seed)
	{
		Random random = new Random(seed);
		
		return Stream.generate(() -> random.nextInt())
			.limit(size)
			.mapToInt(val -> val)
			.toArray();
	}
}
