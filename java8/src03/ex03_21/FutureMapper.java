package ex03_21;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.*;

public class FutureMapper
{
	private FutureMapper() { throw new UnsupportedOperationException(); }
	
	/**
	 * Future<T>をfuncにより射影し、Future<U>を生成します
	 * @param future 射影対象のFuture
	 * @param func 射影関数
	 * @return 射影されたFuture
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> func)
	{
		Objects.requireNonNull(future, "Argument 'future' must not be null.");
		Objects.requireNonNull(func, "Argument 'func' must not be null.");
		
		return new Future<U>()
		{
			@Override
			public boolean cancel(boolean mayInterruptIfRunning)
			{
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled()
			{
				return future.isCancelled();
			}

			@Override
			public boolean isDone()
			{
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException
			{
				return func.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException,
				TimeoutException
			{
				return func.apply(future.get(timeout, unit));
			}
			
		};
	}
}
