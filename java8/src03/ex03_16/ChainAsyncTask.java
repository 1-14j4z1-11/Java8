package ex03_16;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ChainAsyncTask
{
	private ChainAsyncTask() { throw new UnsupportedOperationException(); }
	
	/*
	 * 2個目のBiConsumerで発生した例外を受け取るためのハンドラが必要なため、第3引数が必要
	 */
	
	/**
	 * 非同期で2個の処理を順次実行します</br>
	 * 例外の発生有無にかかわらず、firstの実行結果がsecondに渡されます
	 * (成功した場合は第1引数に結果を渡し、例外が発生した場合は第2引数に例外が渡されます。その際、他方の引数はnullになります)</br>
	 * secondの実行中に例外が発生した場合secondErroredが呼び出されます
	 * @param first 1個目の処理
	 * @param second 2個目の処理(nullの場合は何も処理を行いません)
	 * @param secondErrored 2個目の処理で例外が発生した場合に呼ばれる処理(nullの場合は何も処理を行いません)
	 * @exception NullPointerException firstがnullの場合
	 */
	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> secondErrored)
	{
		Objects.requireNonNull(first, "Argument 'first' must not be null");
		
		BiConsumer<T, Throwable> actualSecond = (second != null) ? second : (r, t) -> { };
		Consumer<Throwable> actualErrored = (secondErrored != null) ? secondErrored : t -> { };

		ExecutorService pool = Executors.newCachedThreadPool();

		pool.submit(() ->
			{
				T result = null;
				Throwable throwable = null;
				
				try
				{
					result = first.get();
				}
				catch(Throwable t)
				{
					throwable = t;
				}
				
				try
				{
					actualSecond.accept(result, throwable);
				}
				catch(Throwable t)
				{
					actualErrored.accept(t);
				}
			});
		pool.shutdown();
	}
}
