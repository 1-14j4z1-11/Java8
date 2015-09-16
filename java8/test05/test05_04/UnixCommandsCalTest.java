package test05_04;

import org.junit.Test;
import ex05_04.UnixCommands;

public class UnixCommandsCalTest
{
	@Test
	public void test()
	{
		UnixCommands.cal(2013, 3);
		UnixCommands.cal(2015, 9);
	}
}
