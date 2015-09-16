package test06_09;

import static org.junit.Assert.*;
import org.junit.Test;
import ex06_09.Matrix;

public class MatrixTest
{
	@Test
	public void test()
	{
		double delta = 0.0001;
		
		Matrix m1 = createMatrix(1, 2, 2, 3);
		Matrix m2 = createMatrix(3, 4, 1, 2);
		
		Matrix m12 = m1.multiply(m2);
		Matrix m21 = m2.multiply(m1);
		
		assertEquals(5, m12.getAt(0, 0), delta);
		assertEquals(8, m12.getAt(0, 1), delta);
		assertEquals(9, m12.getAt(1, 0), delta);
		assertEquals(14, m12.getAt(1, 1), delta);

		assertEquals(11, m21.getAt(0, 0), delta);
		assertEquals(18, m21.getAt(0, 1), delta);
		assertEquals(5, m21.getAt(1, 0), delta);
		assertEquals(8, m21.getAt(1, 1), delta);
	}
	
	private static Matrix createMatrix(double ... values)
	{
		return new Matrix(values[0], values[1], values[2], values[3]);
	}
}
