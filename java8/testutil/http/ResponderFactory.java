package http;

import java.util.*;
import java.util.function.*;
import com.sun.net.httpserver.*;

public class ResponderFactory
{
	public static IResponder create(BiPredicate<Method, String> canRespond, Consumer<HttpExchange> respond)
	{
		Objects.requireNonNull(canRespond, "Arguments must not be null.");
		Objects.requireNonNull(respond, "Arguments must not be null.");
		
		return new IResponder()
		{
			@Override
			public boolean canRespond(Method method, String path)
			{
				return canRespond.test(method, path);
			}
			
			@Override
			public void respond(HttpExchange exchange)
			{
				respond.accept(exchange);
			}
		};
	}
}
