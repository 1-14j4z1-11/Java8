package test03_12;

import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import util.ImageUtil;
import ex03_08.ImageTransformer.ColorTransformer;
import ex03_12.LatentImage;

public class LatentImageTest
{
	/*
	 * ex03_08で行ったテストは省略
	 */
	
	/**
	 * テストを行うためのLatentImageを生成する関数</br>
	 * (test03_15用にprotected)
	 */
	protected LatentImage createInstance(Image in)
	{
		return LatentImage.from(in);
	}
	
	@Test(expected = NullPointerException.class)
	public void createInstanceWithNullImage()
	{
		this.createInstance(null);
	}

	@Test
	public void transformWithNoneTransformer()
	{
		Image in = ImageUtil.createImage(20, 25, (x, y) -> new Color(x / 255.0, y / 255.0, (x + y) / 255.0, 1.0));
		LatentImage latent = this.createInstance(in);
		Image out = latent.toImage();
		
		assertTrue(ImageUtil.areSameImages(in, out));
	}
	
	@Test
	public void checkTransformDelayed()
	{
		AtomicBoolean t1Executed = new AtomicBoolean(false);
		AtomicBoolean t2Executed = new AtomicBoolean(false);
		AtomicBoolean illegalProcessOrder = new AtomicBoolean();
		
		ColorTransformer t1 = (x, y, pixel) ->
			{
				t1Executed.set(true);
				return Color.BLACK;
			};
		ColorTransformer t2 = (x, y, pixel) ->
			{
				// t1より先に実行されている場合はNG
				if(!t1Executed.get())
				{
					illegalProcessOrder.set(true);
				}
				
				t2Executed.set(true);
				return (x < 10) ? Color.WHITE : pixel;
			};
		
		Image in = ImageUtil.createImage(20, 25, (x, y) -> Color.YELLOW);
		LatentImage latent = this.createInstance(in);
		
		latent.transform(t1);
		
		// 設定時点では実行されていない
		assertFalse(t1Executed.get());
		assertFalse(t2Executed.get());

		latent.transform(t2);

		// 設定時点では実行されていない
		assertFalse(t1Executed.get());
		assertFalse(t2Executed.get());
		
		Image out = latent.toImage();
		
		// toImage()で実行される
		assertTrue(t1Executed.get());
		assertTrue(t2Executed.get());
		
		// t1->t2で実行されている
		assertFalse(illegalProcessOrder.get());
		
		Image expected = ImageUtil.createImage(20, 25, (x, y) -> (x < 10) ? Color.WHITE : Color.BLACK);
		assertTrue(ImageUtil.areSameImages(expected, out));
	}
}
