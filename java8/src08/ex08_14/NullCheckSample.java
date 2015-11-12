package ex08_14;

import java.util.Objects;

public class NullCheckSample
{
	public static void main(String[] args)
	{
		System.out.println(getLength("TEST"));
		
		try
		{
			System.out.println(getLength(null));
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int getLength(String str)
	{
		requiredNonNull(str, "str");
		return str.length();
	}
	
	public static void requiredNonNull(Object arg, String argName)
	{
		Objects.requireNonNull(arg, String.format("Argument %s must not be null", (argName != null) ? "'" + argName + "'" : ""));
	}
}
