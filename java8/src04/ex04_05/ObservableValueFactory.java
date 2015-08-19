package ex04_05;

import java.util.Objects;
import java.util.function.*;
import javafx.beans.value.*;

public class ObservableValueFactory
{
	private ObservableValueFactory() { throw new UnsupportedOperationException(); }

	/**
	 * ObservableValue<T>からObservableValue<R>を生成します
	 * @param f T -> Rへ変換する関数
	 * @param t ObservableValue<R>を生成するためのObservableValue<T>
	 * @param u ObservableValue<R>を生成するためのObservableValue<U>
	 * @return ObservableValue<R>
	 */
	public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t)
	{
		Objects.requireNonNull(f, "Argument 'f' must not be null.");
		Objects.requireNonNull(t, "Argument 't' must not be null.");
		
		return new ObservableValueBase<R>()
			{
				{
					t.addListener(o -> { this.fireValueChangedEvent(); });
				}
				
				@Override
				public R getValue()
				{
					return f.apply(t.getValue());
				}
			};
	}
	
	/**
	 * ObservableValue<T>、ObservableValue<U>からObservableValue<R>を生成します
	 * @param f (T, U) -> Rへ変換する関数
	 * @param t ObservableValue<R>を生成するためのObservableValue<T>
	 * @param u ObservableValue<R>を生成するためのObservableValue<U>
	 * @return ObservableValue<R>
	 */
	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u)
	{
		Objects.requireNonNull(f, "Argument 'f' must not be null.");
		Objects.requireNonNull(t, "Argument 't' must not be null.");
		Objects.requireNonNull(u, "Argument 'u' must not be null.");
		
		return new ObservableValueBase<R>()
			{
				{
					t.addListener(o -> { this.fireValueChangedEvent(); });
					u.addListener(o -> { this.fireValueChangedEvent(); });
				}
				
				@Override
				public R getValue()
				{
					return f.apply(t.getValue(), u.getValue());
				}
			};
	}
}
