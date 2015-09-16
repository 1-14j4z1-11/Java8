package ex06_10;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;

public class WebLinkFetcher
{
	private WebLinkFetcher() { throw new UnsupportedOperationException(); }
	
	public static CompletableFuture<List<String>> fetchLinks()
	{
		Supplier<String> urlInput = () ->
			{
				BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
				try
				{
					System.out.print("Input URL >> ");
					return stream.readLine();
				}
				catch(Exception e)
				{
					return null;
				}
			};
		
		Function<String, String> getWebPage = url ->
			{
				URLConnection connection;
				try
				{
					connection = new URL(url).openConnection();
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuilder builder = new StringBuilder();
					String line;
					
					while((line = reader.readLine()) != null)
					{
						builder.append(line);
					}
					
					return builder.toString();
				}
				catch(IOException e)
				{
					return null;
				}
			};
		
		Function<String, List<String>> extractLink = webpage ->
			{
				if(webpage == null)
					return Collections.emptyList();
				
				List<String> links = new ArrayList<>();
				Matcher matcher = Pattern.compile("<a href=\"([^\"]*)\"").matcher(webpage);
				
				while(matcher.find())
				{
					links.add(matcher.group(1));
				}
				
				return links;
			};
		
		return CompletableFuture.supplyAsync(urlInput)
			.thenApply(getWebPage)
			.thenApply(extractLink);
	}
}
