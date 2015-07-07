package ex02_10;

import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class StreamCalculation
{
	/**
	 * Streamに含まれる要素の平均値を算出します</br>
	 * 無限ストリームに対しては動作対象外です</br>
	 * 空ストリームに対しては0を返します
	 * @param stream 平均値を求めるストリーム
	 * @return ストリーム要素の平均値
	 */
	public static double average(Stream<Double> stream)
	{
		AtomicLong count = new AtomicLong();
		
		// ある要素を操作する時点で、その時の平均値がその都度求まっている必要があるため、合計の計算->要素数で割るということはできない
		double ave = stream.reduce(0.0, (result, element) -> 
			{
				result = result * count.get() + element;
				return result / count.incrementAndGet();
			});
		
		return ave;
	}
}
