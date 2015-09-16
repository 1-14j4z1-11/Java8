package test06_09;

import static org.junit.Assert.*;
import org.junit.Test;
import ex06_09.*;

public class FibonacciMatrixTest
{
	@Test(expected = IllegalArgumentException.class)
	public void calcMatrixOfZero()
	{
		FibonacciMatrix.calcMatrix(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calcMatrixOfNegativeValue()
	{
		FibonacciMatrix.calcMatrix(-1);
	}
	
	@Test
	public void test()
	{
		for(int i = 1; i < 10; i++)
		{
			assertTrue(calcMatrix(i));
		}
	}
	
	public boolean calcMatrix(int n)
	{
		Matrix fn = FibonacciMatrix.calcMatrix(n);
		Matrix expected = calcExpectedMatrix(n);
		
		assertEquals(expected, fn);
		
		return expected.equals(fn);
	}
	
	private Matrix calcExpectedMatrix(int n)
	{
		int a = 1, b = 1, c = 0;
		
		for(int i = 0; i < n - 1; i++)
		{
			int pa = a, pb = b, pc = c;
			c = pb;
			b = pb + pc;
			a = pa + pb;
		}
		
		return new Matrix(a, b, b, c);
	}
}
