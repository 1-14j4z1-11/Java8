package test09_10;

import test09_09.LabeledPointTestBase;
import ex09_09.ILabeledPoint;
import ex09_10.ComparableLabeledPoint;

public class ComparableLabeledPointTest extends LabeledPointTestBase
{
	@Override
	protected ILabeledPoint createInstance(String label, int x, int y)
	{
		return new ComparableLabeledPoint(label, x, y);
	}
}
