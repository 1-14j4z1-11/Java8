package test03_16;

import static org.junit.Assert.*;
import java.util.concurrent.atomic.*;
import org.junit.Test;
import ex03_16.ChainAsyncTask;

public class ChainAsyncTaskTest
{
	@Test(expected = NullPointerException.class)
	public void chainWithNullFirstTask()
	{
		ChainAsyncTask.doInOrderAsync(null, (r, t) -> { }, t -> { });
	}
	
	public void chainWithOnlyFirstTask()
	{
		AtomicBoolean t1Executed = new AtomicBoolean(false);
		
		ChainAsyncTask.doInOrderAsync(
			() ->
			{
				t1Executed.set(true);
				return null;
			},
			null,
			null);
		
		assertTrue(t1Executed.get());
	}
	
	@Test
	public void chainTaskEachCase()
	{
		chainTask(true, true);
		chainTask(true, false);
		chainTask(false, true);
		chainTask(false, false);
	}

	public void chainTask(boolean isFirstSuccess, boolean isSecondSuccess)
	{
		AtomicBoolean t1Executed = new AtomicBoolean(false);
		AtomicBoolean t2Executed = new AtomicBoolean(false);
		AtomicBoolean t3Executed = new AtomicBoolean(false);
		AtomicReference<String> t2Result = new AtomicReference<>(null);
		AtomicReference<Throwable> t2Error = new AtomicReference<>(null);
		AtomicReference<Throwable> t3Error = new AtomicReference<>(null);
		AtomicBoolean finished = new AtomicBoolean(false);
		
		String firstResult = "Result";
		RuntimeException firstException = new RuntimeException();
		RuntimeException secondException = new RuntimeException();
		
		ChainAsyncTask.doInOrderAsync(
			() ->
			{
				t1Executed.set(true);

				if(isFirstSuccess)
					return firstResult;
				else
					throw firstException;
			},
			(r, t)->
			{
				t2Executed.set(true);
				t2Result.set(r);
				t2Error.set(t);
				
				if(isSecondSuccess)
					finished.set(true);
				else
					throw secondException;
			},
			t ->
			{
				t3Executed.set(true);
				t3Error.set(t);
				finished.set(true);
			});
		
		while(!finished.get());	// 完了待ち
		
		assertTrue(t1Executed.get());						// firstは必ず実行される
		assertTrue(t2Executed.get());						// secondは必ず実行される
		assertTrue(isSecondSuccess ^ t3Executed.get());		// erroredはsecondの結果依存
		
		if(isFirstSuccess)
		{
			assertEquals(firstResult, t2Result.get());
			assertNull(t2Error.get());
		}
		else
		{
			assertNull(t2Result.get());
			assertTrue(firstException == t2Error.get());
		}
		
		if(isSecondSuccess)
		{
			assertNull(t3Error.get());
		}
		else
		{
			assertTrue(secondException == t3Error.get());
		}
	}
}
