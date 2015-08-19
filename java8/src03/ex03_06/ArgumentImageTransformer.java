package ex03_06;

import java.util.Objects;
import java.util.function.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class ArgumentImageTransformer
{
	private ArgumentImageTransformer() { throw new UnsupportedOperationException(); }
	
	/**
	 * 入力画像の各画素に対して、fを適用した画像を生成します</br>
	 * fがnullの場合は入力画像と同じ画像を生成します
	 * @param in 入力画像
	 * @param f 変換処理
	 * @param arg 変換処理で用いる引数
	 * @return 関数fで各画素を変換した画像
	 * @exception NullPointerException inがnullの場合
	 */
	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg)
	{
		Objects.requireNonNull(in, "in must not be null.");
		
		BiFunction<Color, T, Color> actualFunc = (f != null) ? f : (c, t) -> c;
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		
		WritableImage out = new WritableImage(width, height);
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				out.getPixelWriter().setColor(i, j,
					actualFunc.apply(in.getPixelReader().getColor(i, j), arg));
			}
		}
		
		return out;
	}
}
