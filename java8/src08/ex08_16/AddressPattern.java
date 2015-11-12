package ex08_16;

import java.util.regex.*;

public class AddressPattern
{
	public static void main(String[] args)
	{
		parse("Chicago, IL 60644-9998");
		parse("Mountain View, CA 94043");
	}
	
	private static void parse(String str)
	{
		Pattern pattern = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s+(?<zip>\\d{5}(-\\d{4})?)");
		Matcher matcher = pattern.matcher(str);
		
		System.out.println("<" + str + ">");
		
		if(matcher.matches())
		{
			System.out.println("  City    = " + matcher.group("city"));
			System.out.println("  State   = " + matcher.group("state"));
			System.out.println("  ZipCode = " + matcher.group("zip"));
		}
		else
		{
			System.out.println("No Match");
		}
	}
}
