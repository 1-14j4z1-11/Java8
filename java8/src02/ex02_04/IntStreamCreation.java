package ex02_04;

import java.util.*;
import java.util.stream.*;

public class IntStreamCreation
{
	@SuppressWarnings("unused")
	public void stream()
	{
		int[] values = { 1, 4, 9, 16 };
		
		// T => int[] と扱われるため、Stream.of()だとStream<int[]>が生成される
		Stream<int[]> intArraystream = Stream.of(values);
		
		// int[]からIntStreamを生成するにはArrays.stream()を使う
		IntStream intStream = Arrays.stream(values);
	}
}
