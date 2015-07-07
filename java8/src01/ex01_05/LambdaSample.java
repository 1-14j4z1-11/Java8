package ex01_05;

import java.util.concurrent.*;

public class LambdaSample
{
	public static void function()
	{
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 
		{
			return HeavyTask.task1();
		})
		.thenApply((str) ->
		{
			return HeavyTask.task2(str);
		})
		.thenApply((str) ->
		{
			return HeavyTask.task3(str);
		});
		
		try
		{
			System.out.printf("%d%n", future.get());
		}
		catch(InterruptedException | ExecutionException e)
		{ }
	}
}
