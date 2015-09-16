package ex06_01;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class AtomicStringSample
{
	public static void run()
	{
		AtomicReference<String> ref = new AtomicReference<>("");
		AtomicInteger maxLength = new AtomicInteger();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 20; i++)
		{
			int seed = i;
			
			pool.submit(() ->
				{
					maxLength.accumulateAndGet(createAction(ref, seed).get(), Math::max);
				});
		}
		
		try
		{
			pool.shutdown();
			pool.awaitTermination(10, TimeUnit.SECONDS);
			
			System.out.printf("Expected max length = %d%n", maxLength.get());
			System.out.printf("Actual max length   = %d [%s]%n", ref.get().length(), ref.get());
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private static Supplier<Integer> createAction(AtomicReference<String> ref, int seed)
	{
		return () ->
			{
				final int LOOP = 10;
				final int MAX_LENGTH = 64;

				Random random = new Random(seed);
				int maxLength = 0;
				
				for(int i = 0; i < LOOP; i++)
				{
					int length = (random.nextInt(MAX_LENGTH) + random.nextInt(MAX_LENGTH)) / 2;
					String str = createString(length);
					
					maxLength = Math.max(maxLength, length);
					ref.accumulateAndGet(str, (s1, s2) -> (s1.length() < s2.length()) ? s2 : s1);
				}
				
				return maxLength;
			};
	}

	private static String createString(int length)
	{
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		
		for(int i = 0; i < length; i++)
		{
			builder.append((char)(random.nextInt() % 0x5e + 0x20));
		}
		
		return builder.toString();
	}
}
