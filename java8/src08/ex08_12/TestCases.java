package ex08_12;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestCases
{
	TestCase[] value();
}

