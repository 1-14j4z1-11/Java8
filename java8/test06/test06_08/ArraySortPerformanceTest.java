package test06_08;

import org.junit.Test;
import ex06_08.ArraySortPerformance;

public class ArraySortPerformanceTest
{
	@Test
	public void testPerformance()
	{
		for(int size = 1; size <= Math.pow(2, 20); size *= 2)
		{
			ArraySortPerformance.checkPerformance(size);
		}
	}
	
	/*
	 * [Size =        1]  S:     0.012	P:     0.001
	 * [Size =        2]  S:     0.001	P:     0.000
	 * [Size =        4]  S:     0.001	P:     0.001
	 * [Size =        8]  S:     0.001	P:     0.001
	 * [Size =       16]  S:     0.003	P:     0.004
	 * [Size =       32]  S:     0.006	P:     0.006
	 * [Size =       64]  S:     0.016	P:     0.015
	 * [Size =      128]  S:     0.017	P:     0.013
	 * [Size =      256]  S:     0.013	P:     0.011
	 * [Size =      512]  S:     0.037	P:     0.034
	 * [Size =     1024]  S:     0.065	P:     0.063
	 * [Size =     2048]  S:     0.131	P:     0.130
	 * [Size =     4096]  S:     0.289	P:     0.281
	 * [Size =     8192]  S:     0.675	P:     0.666
	 * [Size =    16384]  S:     0.922	P:     1.615
	 * [Size =    32768]  S:     1.586	P:     0.749
	 * [Size =    65536]  S:     3.427	P:     1.571
	 * [Size =   131072]  S:     7.693	P:     4.066
	 * [Size =   262144]  S:    16.686	P:     6.202
	 * [Size =   524288]  S:    36.677	P:    12.511
	 * [Size =  1048576]  S:    71.863	P:    20.919
	 */
}
