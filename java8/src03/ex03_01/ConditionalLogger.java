package ex03_01;

import java.util.Objects;
import java.util.function.*;

public abstract class ConditionalLogger
{
	private final Level level;
	
	/**
	 * インスタンスを初期化します
	 * @param level ログレベル
	 * @exception NullPointerException levelがnullの場合
	 */
	public ConditionalLogger(Level level)
	{
		Objects.requireNonNull(level, "level must not be null.");
		
		this.level = level;
	}
	
	/**
	 * conditionがtrueの場合にmessageをログ出力します</br>
	 * levelがこのインスタンスのログレベル以下の場合はcondition/messageともに評価されません
	 * @param level ログレベル
	 * @param condition ログを出力する条件(nullの場合は条件なしでmessageを出力します)
	 * @param message ログ内容
	 * @exception NullPointerException level/messageのいずれかがnullの場合
	 */
	public void logIf(Level level, BooleanSupplier condition, Supplier<String> message)
	{
		Objects.requireNonNull(level, "level must not be null.");
		Objects.requireNonNull(level, "message must not be null.");
		
		if(!this.level.shouldLog(level))
			return;
		
		if((condition == null) || condition.getAsBoolean())
			this.outputMessage(message.get());
	}
	
	protected abstract void outputMessage(String message);
}
