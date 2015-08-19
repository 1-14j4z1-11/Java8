package test03_05;

import static org.junit.Assert.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import ex03_05.OuterFrameImageFactory;
import util.ImageUtil;

public class OuterFrameImageFactoryTest
{
	@Test
	public void transformSmallImage()
	{
		Image in = ImageUtil.createImage(100, 75, (x, y) -> Color.BLUE);
		Image out = OuterFrameImageFactory.createImage(in);
		
		Image expected = ImageUtil.createImage(100, 75, (x, y) -> 
			{
				if((x < 10) || (x >= 90) || (y < 10) || (y >= 65))
				{
					return Color.GRAY;
				}
				else
				{
					return Color.BLUE;
				}
			});
		
		assertTrue(ImageUtil.areSameImages(out, expected));
	}
}
