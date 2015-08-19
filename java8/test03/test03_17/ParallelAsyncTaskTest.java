package test03_17;

import static org.junit.Assert.*;
import java.util.concurrent.atomic.*;
import org.junit.Test;
import ex03_17.ParallelAsyncTask;

public class ParallelAsyncTaskTest
{
	@Test
	public void executeTaskAndExceptionThrown()
	{
		this.executeTask(true, true);
		this.executeTask(false, true);
		this.executeTask(true, false);
		this.executeTask(false, false);
	}
	
	public void executeTask(boolean task1throws, boolean task2throws)
	{
		RuntimeException ex1 = new RuntimeException();
		RuntimeException ex2 = new RuntimeException();
		
		AtomicBoolean calledByEx1 = new AtomicBoolean(false);
		AtomicBoolean calledByEx2 = new AtomicBoolean(false);
		
		ParallelAsyncTask.doInParallelAsync(
			() ->
			{
				if(task1throws)
					throw ex1;
			},
			() ->
			{
				if(task2throws)
					throw ex2;
			},
			t ->
			{
				if(t == ex1)
					calledByEx1.set(true);
				else if(t == ex2)
					calledByEx2.set(true);
				else
					assert false;
			});
		
		try
		{
			// 結果待ち
			Thread.sleep(200);
		}
		catch(InterruptedException e)
		{ }
		
		assertFalse(task1throws ^ calledByEx1.get());
		assertFalse(task2throws ^ calledByEx2.get());
	}
	
	@Test
	public void checkTasksAreExecutedParallelly()
	{
		AtomicBoolean t1Start = new AtomicBoolean(false);
		AtomicBoolean t2Finished = new AtomicBoolean(false);
		AtomicBoolean t1Finished = new AtomicBoolean(false);
		
		ParallelAsyncTask.doInParallelAsync(
			() ->
			{
				synchronized(t1Start)
				{
					t1Start.set(true);
					t1Start.notify();
				}
				
				synchronized(t2Finished)
				{
					try
					{
						while(!t2Finished.get())
							t2Finished.wait();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				t1Finished.set(true);
			},
			() ->
			{
				synchronized(t1Start)
				{
					try
					{
						while(!t1Start.get())
							t1Start.wait();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}

				synchronized(t2Finished)
				{
					t2Finished.set(true);
					t2Finished.notify();
				}
			},
			null);
		
		while(!t1Finished.get());
		
		assertTrue(t1Start.get());
		assertTrue(t2Finished.get());
	}
}
