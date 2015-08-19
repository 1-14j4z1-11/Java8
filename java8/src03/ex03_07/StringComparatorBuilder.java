package ex03_07;

import java.util.*;

public class StringComparatorBuilder
{
	private DefaultComparator defaultComparator = DefaultComparator.NORMAL;
	private final Map<Class<? extends IStringProcessor>, IStringProcessor> stringProcessors = new HashMap<>();
	
	/**
	 * 現在の設定値を反映したComparatorを生成します
	 * @return StringのComparator
	 */
	public Comparator<String> build()
	{
		return (s1, s2) -> this.defaultComparator.compare(this.processString(s1), this.processString(s2));
	}
	
	/**
	 * 生成するComparatorの標準的な順序規則を設定します
	 * @param order 順序規則
	 * @return 自身のインスタンス
	 * @exception NullPointerException 引数がnullの場合
	 */
	public StringComparatorBuilder setDefaultOrder(DefaultComparator order)
	{
		Objects.requireNonNull(order, "order must not be null.");
		
		this.defaultComparator = order;
		return this;
	}
	
	/**
	 * 生成するComparatorが文字列比較の前処理として行う文字列変換処理を設定します</br>
	 * 同一のキーに対してひとつの処理が設定できます</br>
	 * 引数のprocessorにnullを指定した場合は、keyに対する処理を削除します
	 * @param key 前処理のキー
	 * @param processor 前処理
	 * @return 自身のインスタンス
	 * @exception NullPointerException keyがnullの場合
	 */
	public <T extends IStringProcessor> StringComparatorBuilder setStringProcessor(Class<T> key, T processor)
	{
		Objects.requireNonNull(key, "key must not be null.");
		
		if((processor == null) && this.stringProcessors.containsKey(key))
		{
			this.stringProcessors.remove(key);
			return this;
		}
		
		this.stringProcessors.put(key, processor);
		return this;
	}
	
	/**
	 * stringProcessorsの処理を順次適用し、判定用の文字列を取得する関数
	 */
	private String processString(String str)
	{
		for(IStringProcessor processor : this.stringProcessors.values())
		{
			str = processor.process(str);
		}
		
		return str;
	}
}
