package test02_11;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.*;
import org.junit.Test;
import ex02_11.ListStreamOperation;

public class ListStreamOperationTest
{
	@Test(expected = NullPointerException.class)
	public void operateNullStream()
	{
		ListStreamOperation.forEachParallel(null, val -> val);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void operateStreamWithNullFunction()
	{
		List<String> values = new ArrayList<String>()
		{
			{
				StringBuilder builder = new StringBuilder();
				
				for(int i = 0; i < 16; i++)
				{
					add(builder.append((char)(i + 'a')).toString());
				}
			}
		};
		
		List<String> result = ListStreamOperation.forEachParallel(values.stream(), null);
		assertTrue(areEqualListContents(values, result));
	}

	@SuppressWarnings("serial")
	@Test
	public void operateStreamWithConcreteFunction()
	{
		List<Integer> values = new ArrayList<Integer>()
		{
			{
				for(int i = 0; i < 16; i++)
				{
					add(i);
				}
			}
		};
		
		List<Integer> result = ListStreamOperation.forEachParallel(values.stream(), val -> val * val);
		assertTrue(areEqualListContents(resultList(values, val -> val * val), result));
	}
	
	private static <T> List<T> resultList(List<T> list, UnaryOperator<T> operator)
	{
		List<T> resultList = new ArrayList<>(list.size());
		
		for(T obj : list)
		{
			resultList.add(operator.apply(obj));
		}
		
		return resultList;
	}
	
	private static <T> boolean areEqualListContents(List<T> expected, List<T> actual)
	{
		List<T> copy = new ArrayList<>(expected.size());
		copy.addAll(expected);
		
		for(T obj : actual)
		{
			if(copy.contains(obj))
			{
				copy.remove(obj);
			}
			else
			{
				System.err.printf("Unexpected Object is contained in result : %s%n", obj);
				return false;
			}
		}
		
		return (copy.size() == 0);
	}
}
