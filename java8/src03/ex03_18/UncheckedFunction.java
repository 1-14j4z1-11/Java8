package ex03_18;

import java.util.*;
import java.util.function.*;

public class UncheckedFunction
{
	private UncheckedFunction() { throw new UnsupportedOperationException(); }
	
	public interface ThrowableFunction<T, U>
	{
		U apply(T t) throws Exception;
	}
	
	/**
	 * 例外をスローするFunctionから例外の要チェック例外を発生させないFunctionを生成します
	 * @param f 変換する関数
	 * @return 要チェック例外を発生させないFunction
	 */
	public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> f)
	{
		Objects.requireNonNull(f, "Argument 'f' must not be null.");
		
		return t ->
			{
				try
				{
					return f.apply(t);
				}
				catch(Throwable th)
				{
					throw new RuntimeException(th);
				}
			};
	}
}
