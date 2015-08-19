package test04_02;

import static org.junit.Assert.*;
import org.junit.Test;
import ex04_02.DelayPropertyWrapper;

public class DelayPropertyTest
{
	@Test
	public void testPropertyChange()
	{
		DelayPropertyWrapper<String> wrapper = new DelayPropertyWrapper<>("Default");
		
		assertEquals("Default", wrapper.getValue());
		assertEquals("Default", wrapper.property().getValue());
		
		wrapper.setValue("Changed");
		
		assertEquals("Changed", wrapper.getValue());
		assertEquals("Changed", wrapper.property().getValue());
	}
}
