package test01_09;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import ex01_09.*;

public class Collection2Test
{
	private Collection2<SampleClass> objects;
	
	private static class SampleClass
	{
		private final boolean change;
		private int value;

		public SampleClass(boolean change, int value)
		{
			this.change = change;
			this.value = value;
		}
		
		public int getValue() { return value; }
		public void setValue(int value) { this.value = value; }
		public boolean shouldChange() { return change; }
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + (change ? 1231 : 1237);
			result = prime * result + value;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if(this == obj)
				return true;
			if(obj == null)
				return false;
			if(getClass() != obj.getClass())
				return false;
			SampleClass other = (SampleClass) obj;
			if(change != other.change)
				return false;
			if(value != other.value)
				return false;
			return true;
		}
	}
	
	@Test
	public void testForEachIf1()
	{
		createCollection(this.objects, new SampleClass[]
		{
			new SampleClass(true,	1),
			new SampleClass(false,	2),
			new SampleClass(true,	3),
			new SampleClass(false,	4),
			new SampleClass(true,	5),
			new SampleClass(false,	6),
		});
		
		SampleClass[] expected = new SampleClass[]
		{
			new SampleClass(true,	1),
			new SampleClass(false,	2),
			new SampleClass(true,	9),
			new SampleClass(false,	4),
			new SampleClass(true,	25),
			new SampleClass(false,	6),
		};
		
		this.objects.forEachIf((obj) ->
		{
			obj.setValue(obj.getValue() * obj.getValue());
		},
		(obj) ->
		{
			return obj.shouldChange();
		});
		
		assertArrayEquals(expected, this.objects.toArray());
	}

	@Test
	public void testForEachIf2()
	{
		createCollection(this.objects, new SampleClass[]
		{
			new SampleClass(false,	0),
			new SampleClass(false,	10),
			new SampleClass(true,	200),
			new SampleClass(false,	4000),
			new SampleClass(true,	80000),
			new SampleClass(true,	160000),
		});
		
		SampleClass[] expected = new SampleClass[]
		{
			new SampleClass(false,	0),
			new SampleClass(false,	10),
			new SampleClass(true,	2200),
			new SampleClass(false,	4000),
			new SampleClass(true,	82000),
			new SampleClass(true,	162000),
		};
		
		this.objects.forEachIf((obj) ->
		{
			obj.setValue(obj.getValue() + 2000);
		},
		(obj) ->
		{
			return obj.shouldChange();
		});
		
		assertArrayEquals(expected, this.objects.toArray());
	}
	
	@Test(expected = NullPointerException.class)
	public void testForEachIf_WithNullAction()
	{
		this.objects.forEachIf(null, (obj) -> 
		{
			return true;
		});
	}
	
	@Test(expected = NullPointerException.class)
	public void testForEachIf_WithNullFilter()
	{
		this.objects.forEachIf((obj) -> 
		{
			return;
		}, null);
	}

	@Test(expected = NullPointerException.class)
	public void testForEachIf_WithNullActionAndFilter()
	{
		this.objects.forEachIf(null, null);
	}
	
	@Before
	public void setUp()
	{
		this.objects = new ArrayList2<>();
	}
	
	private static <T> Collection<T> createCollection(Collection<T> base, T[] objects)
	{
		if(base == null)
		{
			base = new ArrayList<>(objects.length);
		}
		
		for(T obj : objects)
		{
			base.add(obj);
		}
		
		return base;
	}
}
