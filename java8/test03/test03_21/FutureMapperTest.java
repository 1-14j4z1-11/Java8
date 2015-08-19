package test03_21;

import static org.junit.Assert.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.junit.Test;
import ex03_21.FutureMapper;

public class FutureMapperTest
{
	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments1()
	{
		FutureMapper.map(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments2()
	{
		FutureMapper.map(null, t -> t);
	}

	@Test(expected = NullPointerException.class)
	public void mapWithIllegalArguments3()
	{
		FutureMapper.map(createFuture(), null);
	}
	
	@Test
	public void mapFuture()
	{
		AtomicBoolean completable = new AtomicBoolean(false);	// Futureを完了可能かどうか
		AtomicBoolean threadFinished = new AtomicBoolean(false);
		
		String expectedStr = "Result";
		Future<String> future = createControllableFuture(expectedStr, completable);
		Future<Integer> mappedFuture = FutureMapper.map(future, str -> str.length());
		AtomicInteger result = new AtomicInteger();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.submit(() ->
			{
				try
				{
					result.set(mappedFuture.get());
					threadFinished.set(true);
				}
				catch(Exception e)
				{
					fail();
				}
			});
		
		assertFalse(threadFinished.get());
		
		completable.set(true);
		try
		{
			Thread.sleep(200);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		assertTrue(threadFinished.get());
		assertEquals(expectedStr.length(), result.get());
	}
	
	private static Future<String> createFuture()
	{
		return new FutureTask<String>(() ->
			{
				return "";
			});
	}

	private static Future<String> createControllableFuture(String result, AtomicBoolean controller)
	{
		return new Future<String>()
		{
			private boolean done = false;
			private boolean cancelled = false;
			
			@Override
			public boolean isDone()
			{
				return this.done;
			}
			
			@Override
			public boolean isCancelled()
			{
				return this.cancelled;
			}
			
			@Override
			public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
			{
				synchronized(this)
				{
					this.wait(unit.toMillis(timeout));
				}
				
				if(controller.get())
					return result;
				else
					throw new TimeoutException();
			}
			
			@Override
			public String get() throws InterruptedException, ExecutionException
			{
				while(!controller.get());
				
				return result;
			}
			
			@Override
			public boolean cancel(boolean mayInterruptIfRunning)
			{
				this.cancelled = true;
				this.done = true;
				return true;
			}
		};
	}
}
