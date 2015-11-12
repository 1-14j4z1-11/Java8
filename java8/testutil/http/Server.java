package http;

import java.io.*;
import java.net.*;
import java.util.*;
import com.sun.net.httpserver.*;

public class Server
{
	private static final int BACK_LOG = 10;
	private static final int SERVER_STOP_DELAY = 0;
	
	private final List<IResponder> responders = new ArrayList<>();
	private final InetSocketAddress address;
	private HttpServer server;
	private boolean active = false;
	
	public Server(String ip, int port)
	{
		Objects.requireNonNull(ip, "Argument 'ip' must not be null.");
		
		if(!HttpUtil.isIPv4Format(ip))
			throw new IllegalArgumentException("'ip' does not match IPv4 format(xx.xx.xx.xx)");
		
		if(!HttpUtil.isValidPort(port))
			throw new IllegalArgumentException("'port' is illegal value.");
		
		try
		{
			this.address = new InetSocketAddress(ip, port);
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("One of Arguments is illegal value.", e);
		}
	}
	
	public boolean isActive()
	{
		return this.active;
	}
	
	public synchronized boolean start()
	{
		if(this.active)
			throw new IllegalStateException("Server is already active.");
		
		try
		{
			this.server = HttpServer.create(this.address, BACK_LOG);
			this.server.createContext("/", this::response);
			this.server.start();
			this.active = true;
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
	
	public synchronized boolean stop()
	{
		if(!this.active)
			return false;
		
		this.server.stop(SERVER_STOP_DELAY);
		this.server = null;
		this.active = false;
		
		return true;
	}
	
	public synchronized void addResponder(IResponder responder)
	{
		if(this.active)
			throw new IllegalStateException();
		
		Objects.requireNonNull(responder, "Argument must not be null.");
		this.responders.add(responder);
	}
	
	private void response(HttpExchange exchange)
	{
		Method method = Method.parse(exchange.getRequestMethod());
		String path = exchange.getRequestURI().getPath();
		
		this.responders.stream()
			.filter(r -> r.canRespond(method, path))
			.findFirst()
			.ifPresent(r ->
			{
				try
				{
					r.respond(exchange);
				}
				finally
				{
					exchange.close();
				}
			});
	}
}
