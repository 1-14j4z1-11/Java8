package ex06_03;

import java.util.*;
import java.util.concurrent.atomic.*;
import util.StopWatch;

public class AtomicValueSample
{
	private static final int THREAD_NUM = 1000;
	private static final int COUTNER_UP = 100000;
	
	public static void counterThreadWithAtomicLong()
	{
		Thread[] threads = new Thread[THREAD_NUM];
		AtomicLong counter = new AtomicLong();
		
		for(int i = 0; i < THREAD_NUM; i++)
		{
			threads[i] = new Thread(() ->
				{
					for(int j = 0; j < COUTNER_UP; j++)
					{
						counter.incrementAndGet();
					}
				});
		}
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Arrays.stream(threads).forEach(thread -> thread.start());
		Arrays.stream(threads).forEach(thread -> 
			{
				try
				{
					thread.join();
				}
				catch(Exception e)
				{ }
			});
		
		System.out.printf("AtomicLong :\t%.5f [ms]%n", sw.stop() / 1000.0 / 1000.0);
	}
	
	public static void counterThreadWithLongAdder()
	{
		Thread[] threads = new Thread[THREAD_NUM];
		LongAdder counter = new LongAdder();
		
		for(int i = 0; i < THREAD_NUM; i++)
		{
			threads[i] = new Thread(() ->
				{
					for(int j = 0; j < COUTNER_UP; j++)
					{
						counter.increment();
					}
				});
		}
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Arrays.stream(threads).forEach(thread -> thread.start());
		Arrays.stream(threads).forEach(thread -> 
			{
				try
				{
					thread.join();
				}
				catch(Exception e)
				{ }
			});
		
		System.out.printf("LongAdder  :\t%.5f [ms]%n", sw.stop() / 1000.0 / 1000.0);
	}
}
