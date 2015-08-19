package ex03_12;

import javafx.scene.image.*;
import javafx.scene.paint.*;

public interface FilterOperator
{
	public static class Size
	{
		private final int width;
		private final int height;
		
		Size(int width, int height)
		{
			this.width = width;
			this.height = height;
		}

		public final int getWidth() { return this.width; }
		public final int getHeight() { return this.height; }
	}
	
	/**
	 * 位置(x,y)の画素値を変換します
	 * @param reader 変換画像PixelReaderインスタンス
	 * @param imageSize 画像のサイズ
	 * @param x 変換する画素の位置X
	 * @param y 変換する画素の位置Y
	 * @return 位置(x,y)の変換後の画素値
	 */
	Color apply(PixelReader reader, Size imageSize, int x, int y);
}
