package ex03_12;

import ex03_12.FilterOperator.Size;
import javafx.scene.image.*;

// package
class ImageFilterApplier
{
	public static Image apply(Image in, FilterOperator oparator)
	{
		int width = (int)in.getWidth();
		int height = (int)in.getHeight();
		
		Size size = new Size(width, height);
		PixelReader reader = in.getPixelReader();
		WritableImage out = new WritableImage(width, height);
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				out.getPixelWriter().setColor(i, j, oparator.apply(reader, size, i, j));
			}
		}
		
		return out;
	}
}
