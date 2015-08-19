package test03_08;

import static org.junit.Assert.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import ex03_08.*;
import util.ImageUtil;

public class ColorTransformerFactoryTest
{
	@Test(expected = NullPointerException.class)
	public void transformWithIllegalArgument1()
	{
		ColorTransformerFactory.outerFrameTransformer(ImageUtil.createImage(20, 20, (x, y) -> Color.BLACK), 1, null);
	}

	@Test(expected = NullPointerException.class)
	public void transformWithIllegalArgument2()
	{
		ColorTransformerFactory.outerFrameTransformer(null, 1, Color.BLACK);
	}

	@Test(expected = NullPointerException.class)
	public void transformWithIllegalArgument3()
	{
		ColorTransformerFactory.outerFrameTransformer(null, 1, null);
	}
	
	@Test
	public void transformWithEachCase()
	{
		assertTrue(this.testTransformer(100, 60, 10, Color.BLACK));
		assertTrue(this.testTransformer(20, 20, 15, Color.TRANSPARENT));
		assertTrue(this.testTransformer(100, 100, 0, Color.GRAY));
		assertTrue(this.testTransformer(100, 60, 10, Color.WHITE));
	}
	
	public boolean testTransformer(int width, int height, int frameWidth, Color frameColor)
	{
		Color baseColor = new Color(1, 1, 1, 1);
		Image baseImage = ImageUtil.createImage(width, height, (x, y) -> baseColor);
		Image result = ImageTransformer.transform(baseImage, ColorTransformerFactory.outerFrameTransformer(baseImage, frameWidth, frameColor));
		
		Image expected = ImageUtil.createImage(width, height, (x, y) ->
			{
				if((x < frameWidth) || (width - x <= frameWidth) ||
					(y < frameWidth) || (height - y <= frameWidth))
				{
					return frameColor;
				}
				else
				{
					return baseColor;
				}
			});
		
		return ImageUtil.areSameImages(expected, result);
	}
}
