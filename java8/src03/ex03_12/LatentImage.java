package ex03_12;

import java.util.*;
import java.util.function.*;
import ex03_08.ImageTransformer;
import ex03_08.ImageTransformer.*;
import ex03_11.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class LatentImage
{
	private Image baseImage;
	private final List<ColorTransformer> transformers = new ArrayList<>();
	
	protected LatentImage(Image baseImage)
	{
		this.baseImage = baseImage;
	}
	
	/**
	 * 画素変換処理を追加します</br>
	 * この処理は遅延実行されます
	 * @param transformer 追加する変換処理
	 * @return 自身のインスタンス
	 * @exception NullPointerException 引数がnullの場合
	 */
	public LatentImage transform(ColorTransformer transformer)
	{
		Objects.requireNonNull(transformer, "Argument 'transformer' must not be null.");
		
		this.transformers.add(transformer);
		return this;
	}

	/**
	 * 画素変換処理を追加します</br>
	 * この処理は遅延実行されます
	 * @param transformer 追加する変換処理
	 * @return 自身のインスタンス
	 * @exception NullPointerException 引数がnullの場合
	 */
	public LatentImage transform(UnaryOperator<Color> transformer)
	{
		return this.transform(ColorTransformerUtil.convertColorTransFormer(transformer));
	}
	
	/**
	 * フィルタリング処理を追加します</br>
	 * この処理を行う前に追加された全ての変換処理が実行されます
	 * @param operator フィルタリング処理
	 * @return 自身のインスタンス
	 */
	public LatentImage filitering(FilterOperator operator)
	{
		this.toImage();
		this.baseImage = ImageFilterApplier.apply(this.baseImage, operator);
		
		return this;
	}
	
	/**
	 * 設定されている画像変換処理を行い、変換画像を取得します
	 * @return 変換画像
	 */
	public Image toImage()
	{
		for(ColorTransformer t : this.transformers)
		{
			this.baseImage = this.transform(this.baseImage, t);
		}
		
		this.transformers.clear();
		
		return this.baseImage;
	}

	/**
	 * 画像変換処理を行い、変換画像を取得します
	 * @param in 入力画像
	 * @param t 変換処理
	 * @return 変換画像
	 */
	protected Image transform(Image in, ColorTransformer t)
	{
		return ImageTransformer.transform(in, t);
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
		
		return new LatentImage(image);
	}
}
