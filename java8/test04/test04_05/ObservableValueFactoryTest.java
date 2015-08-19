package test04_05;

import static org.junit.Assert.*;
import java.util.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import org.junit.Test;
import ex04_05.ObservableValueFactory;

public class ObservableValueFactoryTest
{
	@Test(expected = NullPointerException.class)
	public void testIllegalArgument1()
	{
		ObservableValueFactory.observe(t -> t, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testIllegalArgument2()
	{
		ObservableValueFactory.observe(null, new SimpleStringProperty());
	}
	
	@Test(expected = NullPointerException.class)
	public void testIllegalArgument3()
	{
		ObservableValueFactory.observe(null, null);
	}
	
	@Test
	public void testLister()
	{
		List<Integer> invalidationValues = new ArrayList<>();
		List<Integer> changeValues = new ArrayList<>();
		
		SimpleStringProperty strProperty = new SimpleStringProperty("A");
		ObservableValue<Integer> intValue = ObservableValueFactory.observe(str -> str.length(), strProperty);
		intValue.addListener(c -> invalidationValues.add(intValue.getValue()));
		intValue.addListener((p, o, n) -> changeValues.add(n));
		
		strProperty.setValue("BB");
		assertEquals(1, invalidationValues.size());
		assertArrayEquals(new Integer[]{ Integer.valueOf(2) }, invalidationValues.toArray());
		assertArrayEquals(new Integer[]{ Integer.valueOf(2) }, changeValues.toArray());
		
		strProperty.setValue("CCC");
		assertEquals(2, invalidationValues.size());
		assertArrayEquals(new Integer[]{ Integer.valueOf(2), Integer.valueOf(3) }, invalidationValues.toArray());
		assertArrayEquals(new Integer[]{ Integer.valueOf(2), Integer.valueOf(3) }, changeValues.toArray());
	}
}
