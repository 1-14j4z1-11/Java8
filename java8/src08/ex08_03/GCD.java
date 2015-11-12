package ex08_03;

public class GCD
{
	private GCD() { throw new UnsupportedOperationException(); }
	
	/**
	 * ユークリッドの互除法によりa,bの最大公約数を求めます</br>
	 * (剰余演算子を使用)
	 * @param a 最大公約数を求める値1
	 * @param b 最大公約数を求める値2
	 * @return a,bの最大公約数
	 */
	public static int calculateWithBasicOperator(int a, int b)
	{
		return (b == 0) ? Math.abs(a) : calculateWithBasicOperator(b, a % Math.abs(b));
	}

	/**
	 * ユークリッドの互除法によりa,bの最大公約数を求めます</br>
	 * ({@link java.lang.Math#floorMod(int, int)}を使用)
	 * @param a 最大公約数を求める値1
	 * @param b 最大公約数を求める値2
	 * @return a,bの最大公約数
	 */
	public static int calculateWithFloorMode(int a, int b)
	{
		return (b == 0) ? Math.abs(a) : calculateWithFloorMode(b, Math.floorMod(a, Math.abs(b)));
	}

	/**
	 * ユークリッドの互除法によりa,bの最大公約数を求めます</br>
	 * (負数を考慮した剰余演算関数を使用)
	 * @param a 最大公約数を求める値1
	 * @param b 最大公約数を求める値2
	 * @return a,bの最大公約数
	 */
	public static int calculateWithRemMethod(int a, int b)
	{
		return (b == 0) ? Math.abs(a) : calculateWithRemMethod(b, rem(a, b));
	}
	
	private static int rem(int a, int b)
	{
		int mod = a % b;
		return (mod >= 0) ? mod : Math.abs(b) + mod;
	}
}
