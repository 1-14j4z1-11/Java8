package test06_11;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import org.junit.Test;
import ex06_11.LoopFuture;

public class LoopFutureTest
{
	@Test
	public void test()
	{
		BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
		char[] password = new char[] { 's', 'e', 'c', 'r', 'e', 't' };
		
		CompletableFuture<Void> future = LoopFuture.repeat(() ->
			{
				try
				{
					System.out.print("Input password >> ");
					return new PasswordAuthentication("user", stream.readLine().toCharArray());
				}
				catch(Exception e)
				{
					return null;
				}
			},
			auth ->
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e)
				{ }
				
				return Arrays.equals(password, auth.getPassword());
			})
			.thenAccept(str -> System.out.println("Inputted correct password"));
		
		try
		{
			future.get();
		}
		catch(InterruptedException | ExecutionException e)
		{ }
	}
}
