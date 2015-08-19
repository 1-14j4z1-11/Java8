package ex03_05;

import ex03_08.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

/*
 * ex03_08のコードを流用しています
 */
public class OuterFrameImageFactory
{
	private OuterFrameImageFactory() { throw new UnsupportedOperationException(); }
	
	/**
	 * 外周10pixelを灰色に上書きした画像を生成します
	 * @param in 入力画像
	 * @return 外周10pixelを灰色に上書きした画像
	 */
	public static Image createImage(Image in)
	{
		return ImageTransformer.transform(in, ColorTransformerFactory.outerFrameTransformer(in, 10, Color.GRAY));
	}
}
