package ex02_09;

import java.util.*;
import java.util.stream.*;

public class ListStreamUtil
{
	private ListStreamUtil() { };
	
	/**
	 * ArrayListのストリームに含まれる全ての要素を結合したArrayListを生成します
	 * @param stream 結合したArrayListを生成するためのストリーム
	 * @return 結合したArrayList
	 */
	public static <T> ArrayList<T> margeListStream1(Stream<ArrayList<T>> stream)
	{
		Optional<ArrayList<T>> op = stream.reduce((result, element) ->
			{
				if(result == null)
					result = new ArrayList<T>();
				
				result.addAll(element);
				
				return result;
			});
		
		return op.orElse(new ArrayList<T>());
	}

	/**
	 * ArrayListのストリームに含まれる全ての要素を結合したArrayListを生成します
	 * @param stream 結合したArrayListを生成するためのストリーム
	 * @return 結合したArrayList
	 */
	public static <T> ArrayList<T> margeListStream2(Stream<ArrayList<T>> stream)
	{
		ArrayList<T> list = stream.reduce(new ArrayList<T>(), (result, element) -> 
			{
				result.addAll(element);
				return result;
			});
		
		return list;
	}

	/**
	 * ArrayListのストリームに含まれる全ての要素を結合したArrayListを生成します
	 * @param stream 結合したArrayListを生成するためのストリーム
	 * @return 結合したArrayList
	 */
	public static <T> ArrayList<T> margeListStream3(Stream<ArrayList<T>> stream)
	{
		ArrayList<T> list = stream.reduce(new ArrayList<T>(),
			(result, element) -> 
				{
					result.addAll(element);
					return result;
				},
			(result, element) ->
				{
					result.addAll(element);
					return result;
				});
		
		return list;
	}
}
