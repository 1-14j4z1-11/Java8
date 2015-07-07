package test02_09;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.junit.*;
import ex02_09.ListStreamUtil;

public class ListStreamUtilTest
{
	private interface StreamFunction extends Function<Stream<ArrayList<Integer>>, ArrayList<Integer>>
	{ }
	
	private List<StreamFunction> functions;
	
	@Before
	public void setup()
	{
		// テスト対象のメソッドを追加する
		this.functions = new ArrayList<>();
		this.functions.add(ListStreamUtil::margeListStream1);
		this.functions.add(ListStreamUtil::margeListStream2);
		this.functions.add(ListStreamUtil::margeListStream3);
	}
	
	@Test
	public void reduceEmptyStream()
	{
		for(StreamFunction func : this.functions)
		{
			Stream<ArrayList<Integer>> stream = Stream.empty();
			ArrayList<Integer> reduced = func.apply(stream);
			
			assertEquals(0, reduced.size());
		}
	}
	
	@Test
	public void reduceStreamWithEmptyList()
	{
		for(StreamFunction func : this.functions)
		{
			Stream<ArrayList<Integer>> stream = Stream.of(new ArrayList<>());
			ArrayList<Integer> reduced = func.apply(stream);
			
			assertEquals(0, reduced.size());
		}
	}
	
	@Test
	@SuppressWarnings("serial")
	public void reduceStreamWithSingletonList()
	{
		Integer[] expected = { 0 };
		
		for(StreamFunction func : this.functions)
		{
			Stream<ArrayList<Integer>> stream = Stream.of(
				new ArrayList<Integer>(){ { add(0); } });
			ArrayList<Integer> reduced = func.apply(stream);
			
			assertArrayEquals(expected, reduced.toArray());
		}
	}
	
	@Test
	@SuppressWarnings("serial")
	public void reduceStreamWithMultipleSingletonLists()
	{
		Integer[] expected = { 1, 2, 3, 4 };
		
		for(StreamFunction func : this.functions)
		{
			Stream<ArrayList<Integer>> stream = Stream.of(
				new ArrayList<Integer>(){ { add(1); } },
				new ArrayList<Integer>(){ { add(2); } },
				new ArrayList<Integer>(){ { add(3); } },
				new ArrayList<Integer>(){ { add(4); } });
			ArrayList<Integer> reduced = func.apply(stream);
			
			assertArrayEquals(expected, reduced.toArray());
		}
	}
	
	@Test
	@SuppressWarnings("serial")
	public void reduceStreamWithMultipleLists()
	{
		Integer[] expected = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
		
		for(StreamFunction func : this.functions)
		{
			Stream<ArrayList<Integer>> stream = Stream.of(
				new ArrayList<Integer>(){ { add(10); add(11); add(12); } },
				new ArrayList<Integer>(){ { add(13); } },
				new ArrayList<Integer>(){ { add(14); add(15); } },
				new ArrayList<Integer>(){ { add(16); add(17); add(18); add(19); } });
			ArrayList<Integer> reduced = func.apply(stream);
			
			assertArrayEquals(expected, reduced.toArray());
		}
	}
}
