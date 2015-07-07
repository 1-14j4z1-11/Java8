package ex01_06;

public class UncheckRunnable
{
	private UncheckRunnable() { throw new UnsupportedOperationException(); }
	
	public interface RunnableEx
	{
		void run() throws Exception;
	}
	
	/**
	 * uncheck()メソッドにより元のRunnableExで発生した例外をラップする例外クラス
	 */
	public static class RunnableException extends RuntimeException
	{
		private static final long serialVersionUID = 1L;
		
		public RunnableException(Throwable cause)
		{
			super(cause);
		}
	}
	
	/**
	 * 引数のRunnableExから検査例外を発生させないRunnableを生成します
	 * @param runner 検査例外を含むRunnableExインスタンス
	 * @return 検査例外を発生させないRunnableインスタンス
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static Runnable uncheck(RunnableEx runner)
	{
		if(runner == null)
			throw new NullPointerException();
		
		return () ->
		{
			try
			{
				runner.run();
			}
			catch(Exception e)
			{
				throw new RunnableException(e);
			}
		};
	}
}
