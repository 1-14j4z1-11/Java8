package ex03_23;

import java.util.Objects;
import java.util.function.*;

public class PairMapper
{
	private PairMapper() { throw new UnsupportedOperationException(); }
	
	/**
	 * Pairの各要素をfuncにより射影したPairを生成します
	 * @param pair 射影対象のPair
	 * @param func 射影関数
	 * @return 射影されたPair
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public static <T, U> Pair<U> map(Pair<T> pair, Function<T, U> func)
	{
		Objects.requireNonNull(pair, "Argument 'pair' must not be null.");
		Objects.requireNonNull(pair, "Argument 'pair' must not be null.");
		
		return new Pair<U>(func.apply(pair.getFirst()), func.apply(pair.getSecond()));
	}
}
