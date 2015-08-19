package ex03_02;

import java.util.*;
import java.util.concurrent.locks.*;

public class LockedProcess
{
	private LockedProcess() { throw new UnsupportedOperationException(); }
	
	/**
	 * lockオブジェクトをロックした状態でrunnerを実行します</br>
	 * runnerの処理中に例外が発生した場合はロックを開放し、発生した例外を返します
	 * @param lock ロックするオブジェクト
	 * @param runner 実行する処理
	 * @return 処理中に例外が発生した場合はその例外のOptionalを返します。発生しなかった場合はOptional.emptyを返します。
	 */
	public static Optional<Throwable> withLock(ReentrantLock lock, Runnable runner)
	{
		Objects.requireNonNull(lock, "lock must not be null");
		Objects.requireNonNull(runner, "runner must not be null");
		
		lock.lock();
		
		try
		{
			runner.run();
		}
		catch(Throwable t)
		{
			return Optional.of(t);
		}
		finally
		{
			lock.unlock();
		}
		
		return Optional.empty();
	}
}
