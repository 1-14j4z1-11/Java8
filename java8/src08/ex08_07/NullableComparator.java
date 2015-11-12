package ex08_07;

import java.util.*;

public class NullableComparator
{
	private NullableComparator() { throw new UnsupportedOperationException(); }
	
	public static <T extends Comparable<? super T>> Comparator<T> reversedComparator()
	{
		Comparator<T> comparator = Comparator.nullsFirst(Comparator.<T>naturalOrder());
		return (o1, o2) -> comparator.compare(o2, o1);
	}
	
	public static <T extends Comparable<? super T>> Comparator<T> expectedComparator()
	{
		return Comparator.nullsFirst(Comparator.<T>naturalOrder()).reversed();
	}
}
