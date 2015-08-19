package ex04_02;

import javafx.beans.property.*;

public class DelayPropertyWrapper<T>
{
	private T value;
	private Property<T> property;
	
	/**
	 * インスタンスを初期化します
	 * @param value プロパティの初期値
	 */
	public DelayPropertyWrapper(T value)
	{
		this.value = value;
	}
	
	/**
	 * このインスタンスが内包するプロパティを取得します
	 * @return
	 */
	public Property<T> property()
	{
		if(this.property == null)
		{
			this.property = new SimpleObjectProperty<>(this.value);
		}
		
		return this.property;
	}
	
	/**
	 * プロパティの値を取得します
	 * @return プロパティ値
	 */
	public T getValue()
	{
		return (this.property != null) ? this.property.getValue() : this.value;
	}
	
	/**
	 * プロパティの値を設定します
	 * @param value 設定値
	 */
	public void setValue(T value)
	{
		if(this.property != null)
			this.property.setValue(value);
		else
			this.value = value;
	}
}
