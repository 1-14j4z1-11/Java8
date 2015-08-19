package ex03_01;

public class ConsoleLogger extends ConditionalLogger
{
	public ConsoleLogger(Level level)
	{
		super(level);
	}

	@Override
	protected void outputMessage(String message)
	{
		System.out.println(message);
	}
}
