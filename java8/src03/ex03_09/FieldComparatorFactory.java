package ex03_09;

import java.util.*;
import java.lang.reflect.*;

public class FieldComparatorFactory<T>
{
	private final Class<T> type;
	
	/**
	 * インスタンスを初期化します
	 * @param type このインスタンスが生成するコンパレータが比較するオブジェクトの型
	 */
	public FieldComparatorFactory(Class<T> type)
	{
		this.type = type;
	}
	
	/**
	 * 引数のフィールド名のフィールドを順に比較するComparatorを生成します</br>
	 * 指定されるフィールドは全てComparableを実現している必要があります
	 * @param fieldNames 比較対象のフィールド名 
	 * @return 指定されたフィールドを順に比較するコンパレータ
	 * @exception NullPointerException 引数がnullの場合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Comparator<T> lexicographicComparator(String ... fieldNames)
	{
		Objects.requireNonNull(fieldNames, "fieldNames must not be null.");
		List<Field> fields = new ArrayList<>(fieldNames.length);
		
		boolean comparableAll = Arrays.stream(fieldNames).allMatch(name -> 
			{
				try
				{
					Field field = type.getDeclaredField(name);
					fields.add(field);
					List<Class<?>> ifs = Arrays.asList(field.getType().getInterfaces());
					return ifs.contains(Comparable.class);
				}
				catch(ReflectiveOperationException | NullPointerException e)
				{
					return false;
				}
			});
		
		if(!comparableAll)
		{
			throw new IllegalArgumentException("fieldNames contains not 'Comparable' field's name.");
		}
		
		return (o1, o2) -> 
			{
				for(Field field : fields)
				{
					try
					{
						boolean defaultAccessible = field.isAccessible();
						field.setAccessible(true);
						
						Comparable f1 = (Comparable)field.get(o1);
						Comparable f2 = (Comparable)field.get(o2);
						int result = f1.compareTo(f2);
						
						field.setAccessible(defaultAccessible);
						
						if(result == 0)
						{
							continue;
						}
						
						return result;
					}
					catch(IllegalArgumentException | IllegalAccessException e)
					{
						continue;
					}
				}
				
				return 0;
			};
	}
}
