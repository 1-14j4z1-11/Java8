package test06_07;

import static org.junit.Assert.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.Test;
import ex06_07.KeySearcher;

public class KeySearcherTest
{
	@Test(expected = NullPointerException.class)
	public void searchNullMap()
	{
		KeySearcher.searchKeyOfMaxValue(null);
	}
	
	@Test
	public void searchEmptyMap()
	{
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		
		assertNull(KeySearcher.searchKeyOfMaxValue(map));
	}
	
	@Test
	public void searchMap()
	{
		ConcurrentHashMap<String, Long> map = createMap();
		
		assertEquals("1000", KeySearcher.searchKeyOfMaxValue(map));
	}
	
	private static ConcurrentHashMap<String, Long> createMap()
	{
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		
		Consumer<Long> addAction = val -> map.put(val.toString(), val);
		
		addAction.accept(1L);
		addAction.accept(-3L);
		addAction.accept(1000L);
		addAction.accept(0L);
		addAction.accept(-200L);
		
		return map;
	}
}
