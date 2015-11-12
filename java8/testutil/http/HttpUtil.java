package http;

import java.util.regex.*;

public class HttpUtil
{
	private static final Pattern IPV4_PATTERN = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
	
	private HttpUtil() { throw new UnsupportedOperationException(); }
	
	public static boolean isValidPort(int port)
	{
		return (0 <= port) && (port <= 65535);
	}
	
	public static boolean isIPv4Format(String ip)
	{
		return IPV4_PATTERN.matcher(ip).matches();
	}
}
