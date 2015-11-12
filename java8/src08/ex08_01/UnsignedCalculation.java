package ex08_01;

public class UnsignedCalculation
{
	public static void calculateSigned(int a, int b)
	{
		long add = a + b;
		long sub = a - b;
		long div = a / b;
		int comp = Integer.compare(a, b);
		
		printResult("<SignedResult>", add, sub, div, comp);
	}
	
	public static void calculateUnsigned(int a, int b)
	{
		long add = Integer.toUnsignedLong(a) + Integer.toUnsignedLong(b);
		long sub = Integer.toUnsignedLong(a) - Integer.toUnsignedLong(b);
		long div = Integer.divideUnsigned(a, b);
		int comp = Integer.compareUnsigned(a, b);
		
		printResult("<UnsignedResult>", add, sub, div, comp);
	}
	
	private static void printResult(String header, long add, long sub, long div, long comp)
	{
		System.out.println(header);
		System.out.printf("add  : %d%n", add);
		System.out.printf("sub  : %d%n", sub);
		System.out.printf("div  : %d%n", div);
		System.out.printf("comp : %d%n", comp);
	}
}
