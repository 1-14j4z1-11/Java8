package test06_10;

import java.util.*;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import ex06_10.WebLinkFetcher;

public class WebLinkFetcherTest
{
	@Test
	public void test()
	{
		try
		{
			List<String> links = WebLinkFetcher.fetchLinks().get();
			
			System.out.println("[Extract links]");
			links.forEach(link -> System.out.println("  " + link));
		}
		catch(InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
	}
}
