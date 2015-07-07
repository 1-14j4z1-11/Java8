package ex02_07;

import java.util.*;
import java.util.stream.*;

public class StreamFeatureUtil
{
	private StreamFeatureUtil() { } 
	
	/**
	 * streamが有限かどうかを判定します
	 * @param stream 判定するストリーム
	 * @return streamが有限の場合はtrueを返し、無限の場合はfalseを返します
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static <T> boolean isFinite(Stream<T> stream)
	{
		if(stream == null)
			throw new NullPointerException();
		
		return stream.spliterator().hasCharacteristics(Spliterator.SIZED);
	}
}
