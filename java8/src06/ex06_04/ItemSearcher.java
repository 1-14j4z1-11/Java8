package ex06_04;

import java.util.*;
import java.util.concurrent.atomic.*;

public class ItemSearcher
{
	private ItemSearcher() { throw new UnsupportedOperationException(); }
	
	/**
	 * long配列内の最大値を取得します</br>
	 * 要素数が0の場合はLong.MIN_VALUEを返します
	 * @param array 検索する配列
	 * @return 配列内の最大値
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static long max(long[] array)
	{
		Objects.requireNonNull(array, "'array' must not be null.");
		
		LongAccumulator accumulator = new LongAccumulator(Math::max, Long.MIN_VALUE);
		Arrays.stream(array).parallel().forEach(val -> accumulator.accumulate(val));
		
		return accumulator.longValue();
	}
	
	/**
	 * long配列内の最小値を取得します</br>
	 * 要素数が0の場合はLong.MAX_VALUEを返します
	 * @param array 検索する配列
	 * @return 配列内の最小値
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static long min(long[] array)
	{
		Objects.requireNonNull(array, "'array' must not be null.");
		
		LongAccumulator accumulator = new LongAccumulator(Math::min, Long.MAX_VALUE);
		Arrays.stream(array).parallel().forEach(val -> accumulator.accumulate(val));
		
		return accumulator.longValue();
	}
}
