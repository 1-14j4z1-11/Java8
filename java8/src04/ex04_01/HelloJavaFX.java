package ex04_01;

import java.util.*;
import java.util.function.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class HelloJavaFX extends Application
{
	private final TextModel model = new TextModel();
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		GridPane pane = new GridPane();
		
		Label label = new Label();
		label.fontProperty().set(new Font(100));
		pane.add(label, 0, 0);
		
		TextField text = new TextField();
		text.textProperty().addListener(property ->
			{
				this.model.setText(text.getText());
			});
		pane.add(text, 0, 1);
		
		model.onTextChanged(model ->
			{
				label.textProperty().set(model.getText());
			});
		
		text.setText("Hello, JavaFX");
		
		stage.setScene(new Scene(pane));
		stage.setWidth(400);
		stage.setHeight(300);
		
		stage.show();
	}
}

class TextModel
{
	private String text = "";
	private final List<Consumer<TextModel>> listeners = new ArrayList<>(); 
	
	public final String getText()
	{
		return this.text;
	}

	public final void setText(String text)
	{
		this.text = (text != null) ? text : "";
		this.notifyChanged();
	}
	
	public void onTextChanged(Consumer<TextModel> listener)
	{
		this.listeners.add(listener);
	}
	
	public void offTextChanged(Consumer<TextModel> listener)
	{
		this.listeners.remove(listener);
	}
	
	private void notifyChanged()
	{
		for(Consumer<TextModel> listener : this.listeners)
		{
			listener.accept(this);
		}
	}
}
