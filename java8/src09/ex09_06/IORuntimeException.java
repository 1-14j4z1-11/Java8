package ex09_06;

import java.io.IOException;

/**
 * IOExceptionをRuntimeExceptionにするExceptionラッパー
 */
public class IORuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public IORuntimeException(IOException cause)
	{
		super(cause);
	}
	
	public IORuntimeException(String message, IOException cause)
	{
		super(message, cause);
	}
	
	public IOException getCause()
	{
		return (IOException)super.getCause();
	}
}
