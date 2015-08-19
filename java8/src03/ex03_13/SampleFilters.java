package ex03_13;

import javafx.scene.image.*;
import javafx.scene.paint.*;
import ex03_12.*;

public enum SampleFilters implements FilterOperator
{
	AveragingFilter
	{
		@Override
		public Color apply(PixelReader reader, Size size, int x, int y)
		{
			int count = 0;
			double r = 0, g = 0, b = 0, a = 0;
			
			for(int i = -1; i <= 1; i++)
			{
				for(int j = -1; j <= 1; j++)
				{
					Color color = reader.getColor(
						(x + i + size.getWidth()) % size.getWidth(),
						(y + j + size.getHeight()) % size.getHeight());

					r += color.getRed();
					g += color.getGreen();
					b += color.getBlue();
					a += color.getOpacity();
					count++;
				}
			}
			
			return new Color(r / count, g / count, b / count, a / count);
		}
	},
	
	LaplacianFilter
	{
		@Override
		public Color apply(PixelReader reader, Size size, int x, int y)
		{
			double r = 0, g = 0, b = 0, a = 0;
			
			for(int i = -1; i <= 1; i++)
			{
				for(int j = -1; j <= 1; j++)
				{
					// 4近傍以外は対象外
					if(i * j != 0)
						continue;
					
					Color color = reader.getColor(
						(x + i + size.getWidth()) % size.getWidth(),
						(y + j + size.getHeight()) % size.getHeight());
					
					boolean isCenter = (i == 0) && (j == 0);
					
					r += isCenter ? 4 * color.getRed() : -color.getRed();
					g += isCenter ? 4 * color.getGreen() : -color.getGreen();
					b += isCenter ? 4 * color.getBlue() : -color.getBlue();
					a += isCenter ? 4 * color.getOpacity() : -color.getOpacity();
				}
			}
			
			return new Color(Math.abs(r), Math.abs(g), Math.abs(b), Math.abs(a));
		}
	};
}
