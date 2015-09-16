package ex06_09;

import java.util.Arrays;
import java.util.Objects;

public class Matrix
{
	private final double[][] values = new double[2][2];
	
	public Matrix(double m11, double m12, double m21, double m22)
	{
		values[0][0] = m11;
		values[0][1] = m12;
		values[1][0] = m21;
		values[1][1] = m22;
	}
	
	/**
	 * r行c列の要素を取得します(r,cは0,0から開始する)
	 * @param r 取得する要素の行Index
	 * @param c 取得する要素の列Index
	 * @return r行c列の要素
	 */
	public double getAt(int r, int c)
	{
		return this.values[r][c];
	}
	
	/**
	 * 行列の乗算を行います
	 * @param matrix 乗算する行列
	 * @return 乗算結果の行列
	 */
	public Matrix multiply(Matrix matrix)
	{
		Objects.requireNonNull(matrix, "'matrix' must not be null.");
		
		return new Matrix(
			this.values[0][0] * matrix.values[0][0] + this.values[0][1] * matrix.values[1][0],
			this.values[0][0] * matrix.values[0][1] + this.values[0][1] * matrix.values[1][1],
			this.values[1][0] * matrix.values[0][0] + this.values[1][1] * matrix.values[1][0],
			this.values[1][0] * matrix.values[0][1] + this.values[1][1] * matrix.values[1][1]);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if(!Arrays.deepEquals(values, other.values))
			return false;
		return true;
	}
}
