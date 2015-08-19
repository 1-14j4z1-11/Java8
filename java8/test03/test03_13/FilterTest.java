package test03_13;

import static org.junit.Assert.*;
import java.util.concurrent.atomic.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.Test;
import util.ImageUtil;
import ex03_08.ImageTransformer.*;
import ex03_12.*;
import ex03_13.*;

public class FilterTest
{
	@Test
	public void testfilterTransform()
	{
		Image in = ImageUtil.createImage(16, 20, (x, y) -> ((x + y) % 2 == 0) ? Color.BLACK : Color.WHITE);
		Image expected = ImageUtil.createImage(16, 20, (x, y) ->
		{
			double even = 1.0 * 4 / 9;
			double odd = 1.0 * 5 / 9;
			return ((x + y) % 2 == 0) ? new Color(even, even, even, 1.0) : new Color(odd, odd, odd, 1.0);
		});
		
		LatentImage latent = LatentImage.from(in);
		
		AtomicBoolean t1Executed = new AtomicBoolean(false);
		AtomicBoolean f1Executed = new AtomicBoolean(false);
		AtomicBoolean illegalOrder = new AtomicBoolean(false);
		
		ColorTransformer t1 = (x, y, color) ->
			{
				if(f1Executed.get())
				{
					illegalOrder.set(true);
				}
				
				t1Executed.set(true);
				return color;
			};
		FilterOperator f1 = (reader, size, x, y) ->
			{
				f1Executed.set(true);
				return SampleFilters.AveragingFilter.apply(reader, size, x, y);
			};
			
		latent.transform(t1);
		
		assertFalse(t1Executed.get());
		assertFalse(f1Executed.get());
		
		latent.filitering(f1);

		assertTrue(t1Executed.get());
		assertTrue(f1Executed.get());
		assertFalse(illegalOrder.get());
		
		assertTrue(ImageUtil.areSameImages(expected, latent.toImage()));
	}
}
