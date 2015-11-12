package test09_09;

import ex09_09.ILabeledPoint;
import ex09_09.LabeledPoint;

public class LabeledPointTest extends LabeledPointTestBase
{
	@Override
	protected ILabeledPoint createInstance(String label, int x, int y)
	{
		return new LabeledPoint(label, x, y);
	}
}
