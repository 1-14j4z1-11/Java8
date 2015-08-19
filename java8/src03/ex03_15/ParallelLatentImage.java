package ex03_15;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import ex03_08.ImageTransformer.*;
import ex03_12.*;

public class ParallelLatentImage extends LatentImage
{
	protected ParallelLatentImage(Image baseImage)
	{
		super(baseImage);
	}

	/**
	 * 画像変換処理を行い、変換画像を取得します
	 * @param in 入力画像
	 * @param t 変換処理
	 * @return 変換画像
	 */
	protected Image transform(Image in, ColorTransformer t)
	{
		int parallelNum = Runtime.getRuntime().availableProcessors();
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		
		Color[][] pixels = new Color[height][width];
		
		try
		{
			ExecutorService pool = Executors.newCachedThreadPool();
			
			for(int n = 0 ; n < parallelNum; n++)
			{
				int fromY = n * height / parallelNum;
				int toY = (n + 1) * height / parallelNum;
				
				pool.submit(() ->
					{
						for(int y = fromY; y < toY; y++)
						{
							for(int x = 0; x < width; x++)
							{
								pixels[y][x] = t.apply(x, y, in.getPixelReader().getColor(x, y));
							}
						}
					});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

		WritableImage image = new WritableImage(width, height);

		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				image.getPixelWriter().setColor(i, j, pixels[j][i]);
			}
		}
		
		return image;
	}

	/**
	 * インスタンスを生成します
	 * @param image 変換対象の画像
	 * @return 画像変換を行うためのインスタンス
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static LatentImage from(Image image)
	{
		Objects.requireNonNull(image, "Argument 'image' must not be null.");
		
		return new ParallelLatentImage(image);
	}
}
