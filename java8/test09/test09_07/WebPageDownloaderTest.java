package test09_07;

import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.ExecutionException;
import http.*;
import org.junit.*;
import util.*;
import com.sun.net.httpserver.*;
import ex09_07.WebPageDownloader;

public class WebPageDownloaderTest
{
	private static final String TEST_HOST = "127.0.0.1";
	private static final int TEST_PORT = 55555;
	private static final Path SERVER_CONTENT_PATH = Paths.get("testfiles", "multi_lines.txt");
	
	private Server server;
	private String outputPath;

	@Test
	public void downloadContent()
	{
		this.server = createServer();
		boolean serverStarted = this.server.start();
		
		if(!serverStarted)
		{
			System.err.println("Starting server was failed.");
			fail();
		}
		
		this.outputPath = "testfiles/webdownload.txt";
		WebPageDownloader.download(getURLString(), this.outputPath);
		
		FileUtil.areSameFiles(SERVER_CONTENT_PATH.toString(), this.outputPath);
	}

	@Test
	public void downloadContentAsync()
	{
		this.server = createServer();
		boolean serverStarted = this.server.start();
		
		if(!serverStarted)
		{
			System.err.println("Starting server was failed.");
			fail();
		}
		
		this.outputPath = "testfiles/webdownload.txt";
		try
		{
			WebPageDownloader.downloadAsync(getURLString(), this.outputPath).get();
			FileUtil.areSameFiles(SERVER_CONTENT_PATH.toString(), this.outputPath);
		}
		catch(InterruptedException | ExecutionException e)
		{
			fail();
		}
	}
	
	@After
	public void tearDown()
	{
		if((this.server != null) && this.server.isActive())
		{
			this.server.stop();
			this.server = null;
		}
		if((this.outputPath != null) && Files.exists(Paths.get(this.outputPath)))
		{
			try
			{
				Files.delete(Paths.get(this.outputPath));
			}
			catch(IOException e)
			{ }
			finally
			{
				this.outputPath = null;
			}
		}
	}
	
	private static String getURLString()
	{
		return String.format("http://%s:%d/test", TEST_HOST, TEST_PORT);
	}
	
	private static Server createServer()
	{
		Server server = new Server(TEST_HOST, TEST_PORT);
		server.addResponder(new IResponder()
		{
			@Override
			public boolean canRespond(Method method, String path)
			{
				return (Method.GET == method) && path.equals("/test");
			}
			
			@Override
			public void respond(HttpExchange exchange)
			{
				try
				{
					exchange.sendResponseHeaders(200, 0);
					exchange.getResponseHeaders().add("Content-Type", "text/plain");
				}
				catch(IOException e1)
				{
					System.err.println("Response Process failed.");
				}
				
				try(OutputStream stream = exchange.getResponseBody())
				{
					byte[] bytes = Files.readAllBytes(SERVER_CONTENT_PATH);
					stream.write(bytes);
				}
				catch(IOException e)
				{
					System.err.println("Response Process failed.");
				}
			}
		});
		
		return server;
	}
}
