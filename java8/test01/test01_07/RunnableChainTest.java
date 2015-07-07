package test01_07;

import static org.junit.Assert.*;
import org.junit.Test;
import ex01_07.RunnableChain;

public class RunnableChainTest
{
	@Test
	public void chainRnnable()
	{
		boolean[] finished = new boolean[] { false, false };
		
		Runnable runner = RunnableChain.andThen(() -> 
		{
			// どのRunnableも完了していない
			assertFalse(finished[0]);
			assertFalse(finished[1]);
			
			// Runnable1完了
			finished[0] = true;
		},
		() ->
		{
			// Runnable1が完了している
			assertTrue(finished[0]);
			assertFalse(finished[1]);
			
			// Runnable2完了
			finished[1] = true;
		});
		
		runner.run();
		
		// Runnable1/2が完了している
		assertTrue(finished[0]);
		assertTrue(finished[1]);
	}
	
	@Test(expected = NullPointerException.class)
	public void chainNullRunnable1()
	{
		RunnableChain.andThen(null, () -> { });
	}
	
	@Test(expected = NullPointerException.class)
	public void chainNullRunnable2()
	{
		RunnableChain.andThen(() -> { }, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void chainNullRunnable3()
	{
		RunnableChain.andThen(null, null);
	}
}
