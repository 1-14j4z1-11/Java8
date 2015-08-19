package ex03_20;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ListMapper
{
	private ListMapper() { throw new UnsupportedOperationException(); }
	
	/**
	 * List<T>の各要素をfuncで射影し、List<U>を生成します
	 * @param list 射影対象のList
	 * @param func 射影関数
	 * @return 射影されたList
	 * @exception 引数のいずれかがnullの場合
	 */
	public static <T, U> List<U> map(List<T> list, Function<T, U> func)
	{
		Objects.requireNonNull(list, "Argument 'list' must not be null.");
		Objects.requireNonNull(func, "Argument 'func' must not be null.");
		
		return list.stream().map(func).collect(Collectors.toList());
	}
}
