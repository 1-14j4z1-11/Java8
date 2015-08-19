package test03_18;

import static org.junit.Assert.*;
import java.util.function.*;
import org.junit.Test;
import ex03_18.UncheckedFunction;

public class UncheckedFunctionTest
{
	@Test(expected = NullPointerException.class)
	public void unchechedFunctionWithNullFunction()
	{
		UncheckedFunction.unchecked(null);
	}
	
	@Test
	public void unchechedFunctionWithException()
	{
		Exception exception = new Exception();
		Function<Object, Object> unchecked = UncheckedFunction.unchecked(t -> { throw exception; });
		
		try
		{
			unchecked.apply(new Object());
		}
		catch(RuntimeException e)
		{
			assertTrue(e.getCause() == exception);
		}
		catch(Throwable t)
		{
			fail();
		}
	}

	@Test
	public void unchechedFunctionWithoutException()
	{
		Function<Object, Object> unchecked = UncheckedFunction.unchecked(t -> t);
		
		Object obj = new Object();
		Object out = unchecked.apply(obj);
		
		assertEquals(obj, out);
	}
}
