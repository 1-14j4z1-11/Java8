package ex04_04;

import javafx.application.*;
import javafx.beans.binding.Bindings;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class CircleBindingApp extends Application
{
	public static void main(String[] args)
	{
		launch();
	}
	
	@Override
	public void start(Stage stage)
	{
		Circle circle = new Circle();
		Scene scene = new Scene(new Group(circle));

		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		circle.radiusProperty().bind(Bindings.divide(Bindings.min(scene.widthProperty(), scene.heightProperty()), 2));
		
		stage.setScene(scene);
		stage.show();
	}
}
