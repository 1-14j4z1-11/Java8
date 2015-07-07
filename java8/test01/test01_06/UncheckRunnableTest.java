package test01_06;

import static org.junit.Assert.*;
import org.junit.Test;
import ex01_06.UncheckRunnable;
import ex01_06.UncheckRunnable.RunnableException;

public class UncheckRunnableTest
{
	@Test
	public void createUncheckRunnable()
	{
		Exception exception = new Exception();
		
		Runnable runner = UncheckRunnable.uncheck(() -> 
		{
			throw exception;
		});
		
		try
		{
			runner.run();
		}
		catch(RunnableException e)
		{
			// インスタンスが参照一致することを確認する
			assertTrue(e.getCause() == exception);
			return;
		}
		catch(Throwable t)
		{
			fail("Unexpected exception was thrown");
			return;
		}
		
		fail("No exception was thrown");
	}
	
	@Test(expected = NullPointerException.class)
	public void whenCreateRunnableWithNull_ThenThrowException()
	{
		UncheckRunnable.uncheck(null);
	}
}
