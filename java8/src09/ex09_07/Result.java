package ex09_07;

public class Result<T>
{
	private final boolean success;
	private final T result;
	private final Throwable error;
	
	private Result(boolean success, T result, Throwable error)
	{
		this.success = success;
		this.result = result;
		this.error = error;
	}
	
	public boolean isSuccess()
	{
		return this.success;
	}
	
	public T getResult()
	{
		return this.result;
	}
	
	public Throwable getError()
	{
		return this.error;
	}
	
	public static <T> Result<T> of(T result)
	{
		return new Result<>(true, result, null);
	}
	
	public static <T> Result<T> withError(Throwable error)
	{
		return new Result<>(false, null, error);
	}
	
	public static <T> Result<T> empty()
	{
		return new Result<>(true, null, null);
	}
}
