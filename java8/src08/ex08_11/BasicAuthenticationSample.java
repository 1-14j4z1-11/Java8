package ex08_11;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import com.sun.net.httpserver.*;
import http.*;

public class BasicAuthenticationSample
{
	private static List<Server> servers = new ArrayList<>();
	
	public static void main(String[] args)
	{
		// サーバ側処理
		Server server = createServer("127.0.0.1", 50001);
		
		if(!server.start())
		{
			System.err.println("Starting server was failed.");
			return;
		}
		
		// クライアント側処理
		URL url;
		try
		{
			url = new URL("http://127.0.0.1:50001/test");
			URLConnection connection = url.openConnection();
			
			String auth = Base64.getEncoder().encodeToString("user0:password0".getBytes());
			connection.setRequestProperty("Authorization", auth);
			
			InputStream stream = connection.getInputStream();
			int c;
			
			while((c = stream.read()) != -1)
			{
				System.out.print((char)c);
			}
			System.out.println();
			
			stream.close();
		}
		catch(IOException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally
		{
			stopServers();
		}
	}
	
	private static Server createServer(String ip, int port)
	{
		Server server = new Server(ip, port);
		
		final String authUser = "user0";
		final String authPass = "password0";		
		
		server.addResponder(new IResponder()
		{
			@Override
			public void respond(HttpExchange exchange)
			{
				Runnable authSuccess = () ->
				{
					try
					{
						exchange.sendResponseHeaders(200, 0);
						exchange.getResponseBody().write("Authorization success.".getBytes());
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				};
				
				Runnable authError = () ->
				{
					try
					{
						exchange.sendResponseHeaders(403, 0);
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				};
				
				if(!exchange.getRequestHeaders().containsKey("Authorization"))
				{
					authError.run();
					return;
				}

				List<String> authHeader =exchange.getRequestHeaders().get("Authorization");
				if(authHeader.size() != 1)
				{
					authError.run();
					return;
				}
				
				String basicAuth = authHeader.get(0);
				String auth = new String(Base64.getDecoder().decode(basicAuth));
				
				String authPattern = String.format("^%s:%s$",authUser, authPass);
				
				if(Pattern.matches(authPattern, auth))
				{
					authSuccess.run();
				}
				else
				{
					authError.run();
				}
			}
			
			@Override
			public boolean canRespond(Method method, String path)
			{
				return true;
			}
		});
		
		servers.add(server);
		return server;
	}
	
	private static void stopServers()
	{
		servers.forEach(server -> server.stop());
	}
}
