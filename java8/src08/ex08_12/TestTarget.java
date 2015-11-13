package ex08_12;

import java.lang.reflect.*;

public class TestTarget
{
	@TestCase(params = 4, expected = 16)
	@TestCase(params = 3, expected = 9)
	@TestCase(params = 0, expected = 0)
	public static int run(int x)
	{
		return x * x;
	}
	
	public static void main(String[] args)
	{
		try
		{
			Method method = TestTarget.class.getMethod("run", int.class);
			
			if(method.getAnnotation(TestCases.class) != null)
			{
				TestCases testCases = method.getAnnotation(TestCases.class);
				
				for(TestCase testCase : testCases.value())
					System.out.println(test(testCase, method) ? "Test success" : "Test failed [1]");
			}
			else
			{
				System.err.println("Test failed [0]");
			}
		}
		catch(ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}
	
	private static boolean test(TestCase test, Method method)
	{
		try
		{
			int result = (Integer)method.invoke(null, test.params());
			System.out.printf("Result = %d, Expected = %d -> ", result, test.expected());
			return (test.expected() == result);
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
