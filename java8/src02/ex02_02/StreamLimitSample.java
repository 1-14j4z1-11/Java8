package ex02_02;

import java.util.Random;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class StreamLimitSample
{
	private static final int RANDOM_SEED = 2;
	private StreamLimitSample() { }
	
	/**
	 * limitにより、必要回数しかStreamの操作が行われていないことを確認するメソッド
	 */
	public static void limitSample()
	{
		Random random = new Random(RANDOM_SEED);
		
		Stream<String> stream = Stream.iterate("", str -> 
			{
				if(str.length() >= 5)
					return "";
				else
					return str += (char)(random.nextInt(0x7e - 0x21) + 0x21);
			});
		
		AtomicInteger count = new AtomicInteger();
		stream.filter(str -> 
			{
				System.out.printf("filter : %2d | str = \"%s\"%n", count.incrementAndGet(), str);
				return (str.length() >= 4);
			})
			.limit(5)
			.forEach(str -> System.out.printf("%nforEach : \"%s\"%n%n", str));
	}
}
