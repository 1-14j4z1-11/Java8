package ex06_07;

import java.util.*;
import java.util.concurrent.*;

public class KeySearcher
{
	private KeySearcher() { throw new UnsupportedOperationException(); }
	
	public static String searchKeyOfMaxValue(ConcurrentHashMap<String, Long> map)
	{
		Objects.requireNonNull(map, "'map' must not be null.");
		
		Map.Entry<String, Long> maxEntry = map.reduceEntries(2, (e1, e2) -> e1.getValue().compareTo(e2.getValue()) < 0 ? e2 : e1);
		
		return (maxEntry != null) ? maxEntry.getKey() : null;
	}
}
