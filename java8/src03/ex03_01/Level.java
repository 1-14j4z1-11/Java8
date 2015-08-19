package ex03_01;

public enum Level
{
	DEBUG(0),
	INFO(1),
	WARN(2),
	ERROR(3),
	FATAL(4);
	
	private final int value;
	
	Level(int value)
	{
		this.value = value;
	}
	
	/**
	 * このインスタンスが示すログレベルに対して、引数のログレベルのメッセージが来た際にログ出力すべきかどうかを返す
	 * @return ログ出力すべき場合true/すべきでない場合はfalse
	 */
	boolean shouldLog(Level level)
	{
		assert (level != null);
		return (level.value >= this.value);
	}
}
