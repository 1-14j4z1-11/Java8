package ex04_07;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class BorderSample extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage)
	{
		Button button = new Button();
		
		button.setBorder(new Border(new BorderImage(createImage(), new BorderWidths(10), new Insets(0), new BorderWidths(10), true, BorderRepeat.STRETCH, BorderRepeat.STRETCH)));
		
		stage.setScene(new Scene(button));
		
		stage.show();
	}
	
	private static Image createImage()
	{
		final int width = 100;
		final int height = 100;
		
		WritableImage image = new WritableImage(width, height);
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				image.getPixelWriter().setArgb(i, j, (i + j) % 2 == 0 ? 0xFFFFFFFF : 0xFF000000);
			}
		}
		
		return image;
	}
}
