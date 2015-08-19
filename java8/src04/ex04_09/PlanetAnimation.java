package ex04_09;

import java.util.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.beans.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.*;
import static javafx.beans.binding.Bindings.*;

public class PlanetAnimation extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private static final double SEC_OF_PERIOD = 5.0;
	private static final double PLANET_OBJ_SIZE_RATIO = 0.01;
	
	@Override
	public void start(Stage stage)
	{
		stage.setWidth(400);
		stage.setHeight(300);
		
		StackPane pane = new StackPane();
		
		for(Planet planet : Planet.values())
		{
			setUpPlanetAnimation(pane, planet);
		}
		
		stage.setScene(new Scene(pane));
		stage.show();
	}
	
	private PathTransition setUpPlanetAnimation(StackPane pane, Planet planet)
	{
		Planet maxPlanet = Planet.maxRadiusPlanet();
		
		Ellipse track = new Ellipse();
		track.centerXProperty().bind(multiply(0.5, pane.widthProperty()));
		track.centerYProperty().bind(multiply(0.5, pane.heightProperty()));
		track.radiusXProperty().bind(multiply(0.45 * planet.getRadius() / maxPlanet.getRadius(), pane.widthProperty()));
		track.radiusYProperty().bind(multiply(0.45 * planet.getRadius() / maxPlanet.getRadius(), pane.heightProperty()));
		track.setFill(Color.TRANSPARENT);
		track.setStroke(Color.BLACK);
		
		Circle planetNode = new Circle();
		//Label planetLabel = new Label(planet.getName());
		
		planetNode.radiusProperty().bind(multiply(PLANET_OBJ_SIZE_RATIO * 0.9, min(pane.widthProperty(), pane.heightProperty())));
		
		//GridPane planetPane = new GridPane();
		//planetPane.add(planetLabel, 1, 0);
		//planetPane.add(planetNode, 0, 1);
		
		pane.getChildren().add(track);
		pane.getChildren().add(planetNode);
		//pane.getChildren().add(planetPane);
		
		PathTransition transition = new PathTransition();
		transition.setDuration(Duration.seconds(SEC_OF_PERIOD * planet.getPeriod()));
		transition.setNode(planetNode);
		//transition.setNode(planetPane);
		transition.setCycleCount(Transition.INDEFINITE);
		transition.setInterpolator(Interpolator.LINEAR);
		transition.play();
		
		InvalidationListener lister = v ->
			{
				Ellipse trackPath = new Ellipse(
					0.45 * planet.getRadius() / maxPlanet.getRadius() * pane.getWidth(),
					0.45 * planet.getRadius() / maxPlanet.getRadius() * pane.getHeight());
				
				transition.setPath(trackPath);
				transition.stop();
				transition.play();
			};

		pane.widthProperty().addListener(lister);
		pane.heightProperty().addListener(lister);
			
		return transition;
	}
}

enum Planet
{
	MERCURY("Mercury", 0.241, 0.387),
	VENUS("Venus", 0.615, 0.723),
	EARTH("Earth", 1.0, 1.0),
	MARS("Mars", 1.881, 1.524),
	JUPITER("Jupiter", 11.86, 5.203),
	SATURN("Saturn", 29.46, 9.537);
	
	private static Planet maxRadiusPlanet;

	private final String name;
	private final double period;
	private final double radius;
	
	private Planet(String name, double period, double radius)
	{
		this.name = name;
		this.period = period;
		this.radius = radius;
	}
	
	/**
	 * 惑星の名前を取得します
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * 惑星の公転周期(地球を1とする)を取得します
	 */
	public double getPeriod()
	{
		return this.period;
	}
	
	/**
	 * 惑星の起動半径を取得します
	 */
	public double getRadius()
	{
		return this.radius;
	}
	
	/**
	 * 惑星のうち、最も軌道半径の大きい惑星を取得します
	 */
	public static Planet maxRadiusPlanet()
	{
		if(maxRadiusPlanet == null)
		{
			maxRadiusPlanet = Arrays.stream(Planet.values()).max((p1, p2) -> (int)(p1.getRadius() - p2.getRadius())).get();
		}
		
		return maxRadiusPlanet;
	}
}