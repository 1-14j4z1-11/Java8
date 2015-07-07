package ex01_07;

public class RunnableChain
{
	private RunnableChain() { throw new UnsupportedOperationException(); }
	
	/**
	 * 2個のRunnableを逐次実行する1個のRunnableを生成します
	 * @param r1 最初に実行するRunnable
	 * @param r2 次に実行するRunnable
	 * @return r1、r2の順で実行するRunnable
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public static Runnable andThen(Runnable r1, Runnable r2)
	{
		if((r1 == null) || (r2 == null))
			throw new NullPointerException();
		
		return () -> 
		{
			r1.run();
			r2.run();
		};
	};
}
