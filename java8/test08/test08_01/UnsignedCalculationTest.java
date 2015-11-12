package test08_01;

import org.junit.Test;
import ex08_01.UnsignedCalculation;

public class UnsignedCalculationTest
{
	@Test
	public void test()
	{
		testCase(Integer.MIN_VALUE, 4);
	}
	
	public void testCase(int a, int b)
	{
		System.out.println("a = " + a + ",b = " + b);
		System.out.println();
		UnsignedCalculation.calculateSigned(a, b);
		System.out.println();
		UnsignedCalculation.calculateUnsigned(a, b);
		System.out.println();
		System.out.println();
	}
}
