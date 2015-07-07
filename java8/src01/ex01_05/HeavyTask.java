package ex01_05;

public class HeavyTask
{
	public static String task1()
	{
		try
		{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{ }
		
		return "Test";
	}
	
	public static String task2(String str)
	{
		try
		{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{ }
		
		return str + str;
	}
	
	public static int task3(String str)
	{
		try
		{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{ }
		
		return str.length();
	}
}
