package test04_01;

import javafx.stage.*;
import org.junit.Test;
import ex04_01.HelloJavaFX;

public class HelloJavaFXTest
{
	@Test
	public void test()
	{
		new HelloJavaFX().start(new Stage());
	}
}
