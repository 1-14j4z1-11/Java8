package test03_01;

import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;
import org.junit.Test;
import ex03_01.*;
import static ex03_01.Level.*;

public class ConditionalLoggerTest
{
	public static ConditionalLogger createInstance(Level level)
	{
		return new ConsoleLogger(level);
	}
	
	// LevelとそのLevelのLoggerが出力すべきログレベルの対応を示すMap
	private final Map<Level, List<Level>> logLevelMap = new HashMap<Level, List<Level>>()
		{
			private static final long serialVersionUID = 1L;
			
			{
				this.put(FATAL,	levelList(FATAL));
				this.put(ERROR,	levelList(FATAL, ERROR));
				this.put(WARN,	levelList(FATAL, ERROR, WARN));
				this.put(INFO,	levelList(FATAL, ERROR, WARN, INFO));
				this.put(DEBUG,	levelList(FATAL, ERROR, WARN, INFO, DEBUG));
			}
		};
	
	// ログレベルのListを作るための関数
	private static List<Level> levelList(Level ... level)
	{
		return Stream.of(level).collect(Collectors.toList());
	}
	
	@Test
	public void testLogIfEachLoggerLevelWithTrueCondition()
	{
		for(Level level : Level.values())
		{
			testLogIf(level, Boolean.TRUE);
		}
	}

	@Test
	public void testLogIfEachLoggerLevelWithFalseCondition()
	{
		for(Level level : Level.values())
		{
			testLogIf(level, Boolean.FALSE);
		}
	}

	@Test
	public void testLogIfEachLoggerLevelWithNullCondition()
	{
		for(Level level : Level.values())
		{
			testLogIf(level, null);
		}
	}
	
	// 特定のLoggerレベル/ログ条件で、condition/messageの呼び出しが適切に行われているかテストする
	private void testLogIf(Level loggerLevel, Boolean condition)
	{
		ConditionalLogger logger = createInstance(loggerLevel);
		
		// 指定されたロガーレベルのロガーに対して、全てのログレベルのメッセージ出力を試す
		for(Level level : Level.values())
		{
			boolean shouldLog = this.logLevelMap.get(loggerLevel).contains(level);	// ロガーレベルとログレベルからログ出力すべきかを決定
			boolean conditionValue = (condition == null) || condition.booleanValue();
			
			String text = new StringBuilder()
				.append("Logger=").append(loggerLevel).append(" | ")
				.append("Logging=").append(level).append(" | ")
				.append("Condition=").append(condition).append(" | ")
				.append("ConditionExpected=").append(shouldLog).append(" | ")
				.append("MessageExpected=").append(shouldLog && conditionValue).append(" | ")
				.toString();
			
			AtomicBoolean conditionCalled = new AtomicBoolean(false);
			AtomicBoolean messageCalled = new AtomicBoolean(false);
			
			BooleanSupplier conditionFunc = (condition == null) ?
				null :
				() -> {
					conditionCalled.set(true);
					return condition.booleanValue();
				};
			
			logger.logIf(level, conditionFunc,
				() ->
				{
					messageCalled.set(true);
					return text;
				});
			
			if(condition != null)
				assertTrue(text + "Unexpected call result : condition ", !(shouldLog ^ conditionCalled.get()));
			
			assertTrue(text + "Unexpected call result : message ", !((shouldLog && conditionValue) ^ messageCalled.get()));
		}
	}
		
	@Test(expected = NullPointerException.class)
	public void createInstanceWithIllegalArgument()
	{
		createInstance(null);
	}

	@Test(expected = NullPointerException.class)
	public void logWithIllegalArgument1()
	{
		ConditionalLogger logger = createInstance(Level.DEBUG);
		logger.logIf(null, () -> true, () -> null);
	}
	
	@Test(expected = NullPointerException.class)
	public void logWithIllegalArgument2()
	{
		ConditionalLogger logger = createInstance(Level.DEBUG);
		logger.logIf(Level.ERROR, () -> true, null);
	}

	@Test(expected = NullPointerException.class)
	public void logWithIllegalArgument3()
	{
		ConditionalLogger logger = createInstance(Level.DEBUG);
		logger.logIf(null, () -> true, null);
	}
}
