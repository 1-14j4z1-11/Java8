package ex01_05;

import java.util.concurrent.*;
import java.util.function.*;

public class InnerClassSample
{
	public static void function()
	{
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>()
		{
			@Override
			public String get()
			{
				return HeavyTask.task1();
			}
		})
		.thenApply(new Function<String, String>()
		{
			@Override
			public String apply(String str)
			{
				return HeavyTask.task2(str);
			}
		})
		.thenApply(new Function<String, Integer>()
		{
			@Override
			public Integer apply(String str)
			{
				return HeavyTask.task3(str);
			}
		});
		
		try
		{
			System.out.printf("%d%n", future.get());
		}
		catch(InterruptedException | ExecutionException e)
		{ }
	}
}
