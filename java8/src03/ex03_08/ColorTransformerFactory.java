package ex03_08;

import java.util.*;
import ex03_08.ImageTransformer.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class ColorTransformerFactory
{
	private ColorTransformerFactory() { throw new UnsupportedOperationException(); }
	
	/**
	 * 画像の外周を指定色で上書きするColorTransformerを生成します
	 * @param baseImage 変換対象の画像
	 * @param frameWidth 上書きする外周幅
	 * @param frameColor 上書きする色
	 * @return 外周を指定色で上書きするColorTransformer
	 * @exception NullPointerException baseImage/frameColorのいずれかがnullの場合
	 */
	public static ColorTransformer outerFrameTransformer(Image baseImage, int frameWidth, Color frameColor)
	{
		Objects.requireNonNull(baseImage, "baseImage must not be null.");
		Objects.requireNonNull(frameColor, "frameColor must not be null.");
		
		int width = (int)baseImage.getWidth();
		int height = (int)baseImage.getHeight();
		
		return (x, y, pixel) ->
		{
			if((x < frameWidth) || (width - x <= frameWidth) ||
				(y < frameWidth) || (height - y <= frameWidth))
			{
				return frameColor;
			}
			else
			{
				return pixel;
			}
		};
	}
}
