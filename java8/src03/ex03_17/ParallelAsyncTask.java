package ex03_17;

import java.util.concurrent.*;
import java.util.function.*;

public class ParallelAsyncTask
{
	private ParallelAsyncTask() { throw new UnsupportedOperationException(); }
	
	/**
	 * 2個のタスクを並列して実行します</br>
	 * タスク実行中に例外が発生した場合、各例外発生に対してhandlerが呼び出されます</br>
	 * いずれかの引数がnullの場合、その引数に該当するタスクは何も処理を行いません</br>
	 * @param first 並列実行するタスク1
	 * @param second 並列実行するタスク2
	 * @param handler いずれかのタスクで例外が発生した場合に呼び出されるハンドラ
	 */
	public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler)
	{
		Consumer<Runnable> task = r -> 
			{
				try
				{
					if(r != null)
						r.run();
				}
				catch(Throwable t)
				{
					if(handler != null)
						handler.accept(t);
				}
			};
		
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.submit(() -> task.accept(first));
		pool.submit(() -> task.accept(second));
		pool.shutdown();
	}
}
