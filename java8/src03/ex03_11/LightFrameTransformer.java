package ex03_11;

import java.util.*;
import ex03_08.*;
import ex03_08.ImageTransformer.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class LightFrameTransformer
{
	private LightFrameTransformer() { throw new UnsupportedOperationException(); }
	
	/**
	 * 画像の輝度を上げ、灰色の外枠を付与します
	 * @param in 入力画像
	 * @return 輝度を上げ、外枠を付与した画像
	 * @exception NullPointerException 引数がnullの場合
	 */
	public static Image transform(Image in)
	{
		Objects.requireNonNull(in, "Argument 'in' must not be null.");
		
		ColorTransformer transformer = ColorTransformerUtil.chainTransformers(
			ColorTransformerUtil.convertColorTransFormer(Color::brighter),
			ColorTransformerFactory.outerFrameTransformer(in, 10, Color.GRAY));
		
		return ImageTransformer.transform(in, transformer);
	}
}
