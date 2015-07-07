package ex02_05;

import java.util.stream.Stream;

public class StreamFactory
{
	/**
	 * 無限乱数列のStream<Long>を生成します</br>
	 * 生成乱数列は、x_nを一つ前の生成値、x_n+1を次の生成値としたとき、以下の式で計算します</br>
	 * x_n+1 = (a * x_n + c) % m
	 * @param seed 乱数初期シード
	 * @param a 乱数生成パラメータa
	 * @param c 乱数生成パラメータc
	 * @param m 乱数生成パラメータm
	 * @return 無限乱数列のストリーム
	 * @exception IllegalArgumentException 引数mが0の場合
	 */
	public Stream<Long> createRandomStream(long seed, long a, long c, long m)
	{
		if(m == 0)
			throw new IllegalArgumentException();
		
		return Stream.iterate(seed, x -> (a * x + c) % m);
	}
}
