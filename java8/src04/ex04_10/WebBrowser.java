package ex04_10;

import javafx.application.*;
import javafx.beans.binding.*;
import javafx.concurrent.Worker;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.stage.*;

public class WebBrowser extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		GridPane pane = new GridPane();
		//pane.setGridLinesVisible(true);
		
		Button back = new Button("Back");
		TextField addressField = new TextField();
		Button addressButton = new Button("Go");
		WebView webView = new WebView();
		
		WebHistory history = webView.getEngine().getHistory();
		
		BooleanBinding disabledBinding = Bindings.equal(Worker.State.RUNNING, webView.getEngine().getLoadWorker().stateProperty());
		
		back.setOnAction(e ->
			{
				int index = history.getCurrentIndex();
				
				if(index > 0)
					history.go(-1);
			});
		addressField.setOnAction(e -> webView.getEngine().load(addressField.getText()));
		addressButton.setOnAction(e -> webView.getEngine().load(addressField.getText()));
		
		back.disableProperty().bind(Bindings.lessThanOrEqual(history.currentIndexProperty(), 0).and(disabledBinding));
		addressField.disableProperty().bind(disabledBinding);
		addressButton.disableProperty().bind(disabledBinding);
		
		pane.add(back, 0, 0);
		pane.add(addressField, 1, 0);
		pane.add(addressButton, 2, 0);
		pane.add(webView, 0, 1, 3, 1);
		
		Insets insets = new Insets(8);
		GridPane.setHgrow(addressField, Priority.ALWAYS);
		GridPane.setVgrow(webView, Priority.ALWAYS);
		GridPane.setMargin(back, insets);
		GridPane.setMargin(addressField, insets);
		GridPane.setMargin(addressButton, insets);
		
		stage.setScene(new Scene(pane));
		stage.show();
	}
}
