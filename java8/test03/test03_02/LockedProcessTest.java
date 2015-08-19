package test03_02;

import static org.junit.Assert.*;
import java.util.Optional;
import java.util.concurrent.locks.*;
import org.junit.Test;
import ex03_02.LockedProcess;

public class LockedProcessTest
{
	@Test
	public void testWithProcess()
	{
		ReentrantLock lock = new ReentrantLock();
		
		Optional<Throwable> result = LockedProcess.withLock(lock, () ->
			{
				assertTrue(lock.isLocked());
			});
		
		assertFalse(lock.isLocked());
		assertFalse(result.isPresent());
	}
	
	@Test
	public void testWithThrowableProcess()
	{
		ReentrantLock lock = new ReentrantLock();
		RuntimeException exception = new RuntimeException();
		
		Optional<Throwable> result = LockedProcess.withLock(lock, () ->
			{
				assertTrue(lock.isLocked());
				throw exception;
			});
		
		assertFalse(lock.isLocked());
		assertTrue(result.get() == exception);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithIllegalArgument1()
	{
		LockedProcess.withLock(null, () -> { });
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithIllegalArgument2()
	{
		LockedProcess.withLock(new ReentrantLock(), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithIllegalArgument3()
	{
		LockedProcess.withLock(null, null);
	}
}
