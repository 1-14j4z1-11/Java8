package util;

import java.util.stream.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class ImageUtil
{
	private ImageUtil() { }
	
	public interface PixelSupplier
	{
		/**
		 * 画素位置からその位置の画素値を取得します
		 * @param x 画素のX座標
		 * @param y 画素のY座標
		 * @return (x,y)の画素値
		 */
		Color get(int x, int y);
	}
	
	/**
	 * 画像を生成します
	 * @param width 生成する画像の幅
	 * @param height 生成する画像の高さ
	 * @param pixelSupplier 画素位置からその画素の色を生成する関数
	 * @return 画像
	 */
	public static Image createImage(int width, int height, PixelSupplier pixelSupplier)
	{
		WritableImage image = new WritableImage(width, height);

		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				image.getPixelWriter().setColor(i, j, pixelSupplier.get(i, j));
			}
		}
		
		return image;
	}
	
	/**
	 * 画素値の2次元配列を並べた画像を生成します</br>
	 * pixels[y][x]が位置(x,y)のARGB値になります</br>
	 * pixels内の各1次元配列pixels[i]は全て長さが同じである必要があります
	 * @param pixels 画素配列
	 * @return 画像の生成に成功した場合はその画像インスタンスを返し、失敗した場合はnullを返します
	 */
	public static Image createImage(int[][] pixels)
	{
		int height = pixels.length;
		int width = pixels[0].length;
		
		if(!IntStream.range(1, height).allMatch(i -> (width == pixels[i].length)))
		{
			return null;
		}
		
		WritableImage image = new WritableImage(width, height);

		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				image.getPixelWriter().setArgb(i, j, pixels[j][i]);
			}
		}
		
		return image;
	}
	
	/**
	 * 2個の画像が一致するかどうかを判定します</br>
	 * 2個の画像の少なくとも一方がnullの場合はfalseを返します
	 * @param image1 判定対象の画像1
	 * @param image2 判定対象の画像2
	 * @return 2個の画像が一致する場合はtrue/一致しない場合はfalse
	 */
	public static boolean areSameImages(Image image1, Image image2)
	{
		if((image1 == null) || (image2 == null))
		{
			return false;
		}
		if((image1.getWidth() != image2.getWidth()) ||
			(image1.getHeight() != image2.getHeight()))
		{
			return false;
		}
		
		int width = (int)image1.getWidth();
		int height = (int)image1.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color pixel1 = image1.getPixelReader().getColor(i, j);
				Color pixel2 = image2.getPixelReader().getColor(i, j);
				
				if(!pixel1.equals(pixel2))
				{
					System.err.printf("pixels are mismatch : p1 = %s <> p2 = %s%n", pixel1, pixel2);
					return false;
				}
			}
		}
		
		return true;
	}
}
