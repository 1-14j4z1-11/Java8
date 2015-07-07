package ex01_08;

import java.util.*;

public class Loop
{
	public static List<Runnable> lambdaWithForEach()
	{
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		
		for(String name : names)
		{
			runners.add(() -> System.out.println(name));
		}

		for(Runnable r : runners)
		{
			r.run();
		}
		
		return runners;
	}
	
	public static List<Runnable> lambdaWithFor()
	{
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		
		for(int i = 0; i < names.length; i++)
		{
			final int index = i;
			runners.add(() -> System.out.println(names[index]));
		}
		
		for(Runnable r : runners)
		{
			r.run();
		}
		
		return runners;
	}
}
