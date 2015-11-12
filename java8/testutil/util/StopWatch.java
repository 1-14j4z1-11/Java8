package util;

public class StopWatch
{
	private static long DEFAULT_TIME = -1L;
	private long startTime = DEFAULT_TIME;
	private long stopTime = DEFAULT_TIME;
	
	/**
	 * 計測を開始します</br>
	 * 連続で呼び出した場合、最後に呼び出し時点を開始時間とします
	 */
	public void start()
	{
		this.startTime = System.nanoTime();
	}
	
	/**
	 * 計測を終了し、{@link util.StopWatch#start()}を呼び出してから経過した時間を返します</br>
	 * 連続で呼び出した場合、start()を呼び出してから各呼び出し時点での経過時間を取得します</br>
	 * {@link util.StopWatch#start()}が一度も実行されていない場合は0を返します
	 * @return 経過時間[ns]
	 */
	public long stop()
	{
		this.stopTime = System.nanoTime();
		return this.getNanoTime();
	}
	
	/**
	 * {@link util.StopWatch#start()}を呼び出してから{@link util.StopWatch#stop()}を呼び出すまでに経過した時間をナノ秒で取得します</br>
	 * {@link util.StopWatch#start()}/{@link util.StopWatch#stop()}の呼び出しが正常に行われていない場合は0を返します
	 * @return 計測時間
	 */
	public long getNanoTime()
	{
		if((this.startTime == DEFAULT_TIME) || (this.stopTime == DEFAULT_TIME))
			return 0;
		else if(this.startTime > this.stopTime)
			return 0;
		else
			return this.stopTime - this.startTime;
	}
	
	/**
	 * 計測時間をミリ秒で取得します
	 * @return 計測時間
	 */
	public double getMilliTime()
	{
		return this.getNanoTime() / 1000_000.0;
	}
}
