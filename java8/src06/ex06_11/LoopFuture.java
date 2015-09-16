package ex06_11;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.*;

public class LoopFuture
{
	private LoopFuture() { throw new UnsupportedOperationException(); }
	
	/**
	 * untilがtrueを返すまで、actionで値を取得するCompletableFutureを生成します
	 * @param action 値を生成する関数
	 * @param until 値が目的の値かどうかを判定する関数
	 * @return untilがtrueを返すまで、actionで値を取得するCompletableFuture
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until)
	{
		Objects.requireNonNull(action, "'action' must not be null.");
		
		return CompletableFuture.supplyAsync(() ->
			{
				T value = null;
				
				do
				{
					value = action.get();
				} while(!until.test(value));
				
				return value;
			});
	}
}
