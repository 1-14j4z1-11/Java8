package ex03_11;

import java.util.Objects;
import java.util.function.*;
import javafx.scene.paint.*;
import ex03_08.ImageTransformer.*;

public class ColorTransformerUtil
{
	/**
	 * 複数のColorTransformerを合成したColorTransformerを生成します
	 * @param transformers 合成するColorTransformer
	 * @return transformersを合成したColorTransformer
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static ColorTransformer chainTransformers(ColorTransformer ... transformers)
	{
		Objects.requireNonNull(transformers, "transformers must not be null.");
		
		return (x, y, color) -> 
			{
				for(ColorTransformer t : transformers)
				{
					color = t.apply(x, y, color);
				}
				
				return color;
			};
	}
	
	/**
	 * UnaryOperator<Color>をColorTransformerに変換します
	 * @param operator 変換するUnaryOperator
	 * @return 変換されたColorTransformer
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static ColorTransformer convertColorTransFormer(UnaryOperator<Color> operator)
	{
		Objects.requireNonNull(operator, "operator must not be null.");
		
		return (x, y, color) -> operator.apply(color);
	}
}
