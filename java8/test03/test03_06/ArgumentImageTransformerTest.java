package test03_06;

import static org.junit.Assert.*;
import java.util.function.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import util.ImageUtil;
import ex03_06.ArgumentImageTransformer;

public class ArgumentImageTransformerTest
{
	@Test(expected = NullPointerException.class)
	public void transformWithNullImage()
	{
		ArgumentImageTransformer.transform(null, (c, t) -> c, 1);
	}
	
	@Test
	public void transformWithNullFunc()
	{
		Image in = ImageUtil.createImage(8, 10, (i, j) -> Color.BLACK);
		Image out = ArgumentImageTransformer.transform(in, null, 1);
		
		assertTrue(ImageUtil.areSameImages(in, out));
		assertFalse(in == out);
	}
	
	@Test
	public void transformWithArgument()
	{
		this.transformPixelOffset(0);
		this.transformPixelOffset(0.4);
	}
	
	public void transformPixelOffset(double offset)
	{
		Image in = ImageUtil.createImage(8, 10, (i, j) -> Color.BLACK);
		Image expected = ImageUtil.createImage(8, 10, (i, j) -> new Color(offset, offset, offset, 1.0));
		BiFunction<Color, Double, Color> f = (c, d) -> new Color(c.getRed() + d, c.getGreen() + d, c.getBlue() + d, c.getOpacity());
		
		Image out = ArgumentImageTransformer.transform(in, f, offset);
		assertTrue(ImageUtil.areSameImages(expected, out));
	}
}
