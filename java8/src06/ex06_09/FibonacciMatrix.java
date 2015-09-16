package ex06_09;

import java.util.Arrays;

public class FibonacciMatrix
{
	private FibonacciMatrix() { throw new UnsupportedOperationException(); }
	
	/**
	 * フィボナッチ数を求めるための[[1,1],[1,0]]^nの行列を計算します
	 * @param n 行列の乗算回数
	 * @return フィボナッチ行列のn乗
	 */
	public static Matrix calcMatrix(int n)
	{
		if(n <= 0)
			throw new IllegalArgumentException("'n' must be larger than 0.");
		
		Matrix identity = new Matrix(1, 1, 1, 0);
		Matrix[] values = new Matrix[n];
		
		Arrays.fill(values, identity);
		Arrays.parallelPrefix(values, (m1, m2) -> m1.multiply(m2));
		
		return values[n - 1];
	}
}
