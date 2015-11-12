package ex08_06;

import java.util.*;
import javafx.geometry.*;

public class ComparatorFactory
{
	private static final Comparator<Point2D> pointComparator = 
		Comparator.comparingDouble(Point2D::getX)
			.thenComparing(Point2D::getY);
	
	private static final Comparator<Rectangle2D> rectangleComparator = 
		Comparator.comparingDouble(Rectangle2D::getMinX)
			.thenComparing(Rectangle2D::getMinY)
			.thenComparing(Rectangle2D::getMaxX)
			.thenComparing(Rectangle2D::getMaxY);
	
	/**
	 * Point2DをgetX->getYの順で比較するコンパレータを取得します
	 */
	public static Comparator<Point2D> getPoint2DComparator()
	{
		return pointComparator;
	}
	
	/**
	 * Rectangle2DをgetMinX->getMinY->getMaxX->getMaxYの順で比較するコンパレータを取得します
	 */
	public static Comparator<Rectangle2D> getRectangle2DComparator()
	{
		return rectangleComparator;
	}
}
