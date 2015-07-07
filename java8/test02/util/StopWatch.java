package util;

public class StopWatch
{
	private static long DEFAULT_TIME = -1L;
	private long startTime = DEFAULT_TIME;
	
	/**
	 * 計測を開始します</br>
	 * 連続で呼び出した場合、最後に呼び出し時点を開始時間とします
	 */
	public void start()
	{
		this.startTime = System.nanoTime();
	}
	
	/**
	 * 計測を終了し、start()を呼び出してから経過した時間を返します</br>
	 * 連続で呼び出した場合、各呼び出し時点での経過時間を取得します</br>
	 * start()が一度も実行されていない場合は0を返します
	 * @return 経過時間[ns]
	 */
	public long stop()
	{
		return (startTime == DEFAULT_TIME) ? 0 : System.nanoTime() - this.startTime;
	}
}
