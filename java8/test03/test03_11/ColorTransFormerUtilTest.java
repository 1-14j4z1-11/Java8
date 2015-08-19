package test03_11;

import static org.junit.Assert.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import util.ImageUtil;
import ex03_08.ImageTransformer.*;
import ex03_11.*;

public class ColorTransFormerUtilTest
{
	@Test(expected = NullPointerException.class)
	public void convertWithNullArgument()
	{
		ColorTransformerUtil.convertColorTransFormer(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void chianWithNullArgument()
	{
		ColorTransformerUtil.chainTransformers((ColorTransformer[])null);
	}
	
	@Test
	public void chianWithNoneArgument()
	{
		ColorTransformer t = ColorTransformerUtil.chainTransformers();
		
		assertTrue(areEqualColors(t.apply(1, 1, Color.BLUE), Color.BLUE));
		assertTrue(areEqualColors(t.apply(0, 0, Color.BLACK), Color.BLACK));
		assertTrue(areEqualColors(t.apply(100, 0, Color.TRANSPARENT), Color.TRANSPARENT));
	}
	
	@Test
	public void chianWithSingleArgument()
	{
		ColorTransformer base = (x, y, c) -> Color.rgb(x, y, (int)(c.getRed() * 255));
		ColorTransformer chained = ColorTransformerUtil.chainTransformers(base);
		
		assertTrue(areEqualColors(base.apply(1, 1, Color.BLUE), chained.apply(1, 1, Color.BLUE)));
		assertTrue(areEqualColors(base.apply(0, 0, Color.BLACK), chained.apply(0, 0, Color.BLACK)));
		assertTrue(areEqualColors(base.apply(100, 0, Color.TRANSPARENT), chained.apply(100, 0, Color.TRANSPARENT)));
	}

	@Test
	public void chianWithMultipleArguments()
	{
		ColorTransformer t1 = (x, y, c) -> Color.rgb(x, y, (int)(c.getRed() * 255));
		ColorTransformer t2 = (x, y, c) -> Color.rgb((int)(c.getBlue() * 255), (int)(c.getGreen() * 255), (int)(c.getRed() * 255));
		ColorTransformer t3 = (x, y, c) -> Color.color(1.0 - c.getRed(), 1.0 - c.getGreen(), 1.0 - c.getBlue(), c.getOpacity() * 0.5);
		ColorTransformer chained = ColorTransformerUtil.chainTransformers(t1, t2, t3);
		
		assertTrue(areEqualColors(t3.apply(1, 1, t2.apply(1, 1, t1.apply(1, 1, Color.BLUE))), chained.apply(1, 1, Color.BLUE)));
		assertTrue(areEqualColors(t3.apply(0, 0, t2.apply(0, 0, t1.apply(0, 0, Color.BLACK))), chained.apply(0, 0, Color.BLACK)));
		assertTrue(areEqualColors(t3.apply(100, 0, t2.apply(100, 0, t1.apply(100, 0, Color.TRANSPARENT))), chained.apply(100, 0, Color.TRANSPARENT)));
	}

	@Test
	public void testLightFrameTransformer()
	{
		Image in = ImageUtil.createImage(30, 30, (x, y) -> Color.BLUE);
		Image expected = ImageUtil.createImage(30, 30, (x, y) -> ((x < 10) || (x >= 20) || (y < 10) || (y >= 20)) ? Color.GRAY : Color.BLUE);
		Image result = LightFrameTransformer.transform(in);
		
		assertTrue(ImageUtil.areSameImages(expected, result));
	}
	
	@Test(expected = NullPointerException.class)
	public void testLightFrameTransformerWithNullArgument()
	{
		LightFrameTransformer.transform(null);
	}
	
	private static boolean areEqualColors(Color c1, Color c2)
	{
		return (c1.getRed() == c2.getRed()) &&
			(c1.getGreen() == c2.getGreen()) &&
			(c1.getBlue() == c2.getBlue()) &&
			(c1.getOpacity() == c2.getOpacity());
	}
}
