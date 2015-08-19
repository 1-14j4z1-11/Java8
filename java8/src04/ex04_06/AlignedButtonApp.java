package ex04_06;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class AlignedButtonApp extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane pane = new BorderPane();
		
		Button top = new Button("Top");
		Button left = new Button("Left");
		Button center = new Button("Center");
		Button right = new Button("Right");
		Button bottom = new Button("Bottom");
		
		pane.setTop(top);
		pane.setLeft(left);
		pane.setCenter(center);
		pane.setRight(right);
		pane.setBottom(bottom);

		BorderPane.setAlignment(top, Pos.TOP_CENTER);
		BorderPane.setAlignment(left, Pos.CENTER_LEFT);
		BorderPane.setAlignment(center, Pos.CENTER);
		BorderPane.setAlignment(right, Pos.CENTER_RIGHT);
		BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
		
		stage.setScene(new Scene(pane));
		stage.show();
	}
}
