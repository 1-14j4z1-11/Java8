package ex01_09;

import java.util.Collection;
import java.util.function.*;

public interface Collection2<T> extends Collection<T>
{
	/**
	 * コレクションの全ての要素のうち、filter.test()がtrueを返す要素に対してaction.accept()を適用します
	 * @param action filterによる対象要素に適用する処理
	 * @param filter 要素がactionの適用対象かどうかを判定するフィルタ
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	default void forEachIf(Consumer<T> action, Predicate<T> filter)
	{
		if((action == null) || (filter == null))
			throw new NullPointerException();
		
		for(T item : this)
		{
			if(filter.test(item))
				action.accept(item);
		}
	}
}
