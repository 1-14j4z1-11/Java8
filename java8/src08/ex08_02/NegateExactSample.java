package ex08_02;

public class NegateExactSample
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println(Math.negateExact(-2147483648));
		}
		catch(ArithmeticException e)
		{
			e.printStackTrace();
		}
	}
}
