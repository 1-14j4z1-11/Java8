package http;

import java.util.*;

public enum Method
{
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private final String name;
	
	private Method(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
	
	public static Method parse(String method)
	{
		Objects.requireNonNull(method, "Argument must not be null.");
		
		method = method.toUpperCase();
		
		for(Method m : Method.values())
		{
			if(m.name.equals(method))
			{
				return m;
			}
		}
		
		throw new IllegalArgumentException("Undefined method : '" + method + "'");
	}
}
