package ex03_08;

import java.util.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class ImageTransformer
{
	public interface ColorTransformer
	{
		/**
		 * 位置(x,y)の画素値を変換するためのメソッド
		 * @param x 画素の横軸方向の座標
		 * @param y 画素の縦軸方向の座標
		 * @param pixel 変換前の画素値
		 * @return 変換後の画素値
		 */
		Color apply(int x, int y, Color pixel);
	}
	
	private ImageTransformer() { throw new UnsupportedOperationException(); }
	
	/**
	 * 入力画像の各画素に対して、transformerを適用した画像を生成します</br>
	 * transformerがnullの場合は入力画像と同じ画像を生成します
	 * @param in 入力画像
	 * @param transformer 画素値の変換処理
	 * @return 変換画像
	 * @exception NullPointerException inがnullの場合
	 */
	public static Image transform(Image in, ColorTransformer transformer)
	{
		Objects.requireNonNull(in, "in must not be null.");
		
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		
		WritableImage out = new WritableImage(width, height);
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				out.getPixelWriter().setColor(i, j,
					transformer.apply(i, j, in.getPixelReader().getColor(i, j)));
			}
		}
		
		return out;
	}
	
}
