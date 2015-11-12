package test08_03;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.*;
import org.junit.Test;
import ex08_03.GCD;

/**
 * ユークリッドの互除法のアルゴリズムをテストするクラス
 */
public class GCDTest
{
	/**
	 * テストメソッドのラッパークラス
	 */
	private static class TestMethod
	{
		private final String name;
		private final IntBinaryOperator method;
		
		public TestMethod(String name, IntBinaryOperator method)
		{
			Objects.requireNonNull(method);
			
			this.name = name;
			this.method = method;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int test(int a, int b)
		{
			return this.method.applyAsInt(a, b);
		}
	}
	
	/**
	 * テスト対象のメソッド一覧
	 */
	private final List<TestMethod> testMethods = new ArrayList<TestMethod>()
	{
		private static final long serialVersionUID = 1L;

		{
			add(new TestMethod("BasicOperator", GCD::calculateWithBasicOperator));
			add(new TestMethod("FloorMode", GCD::calculateWithFloorMode));
			add(new TestMethod("RemMethod", GCD::calculateWithRemMethod));
		}
	};
	
	@Test
	public void test()
	{
		testCase(0, 0, 0);
		
		testCase(3, 0, 3);
		testCase(-3, 0, 3);
		
		testCase(12, 3, 3);
		testCase(-12, 3, 3);
		testCase(12, -3, 3);
		testCase(-12, -3, 3);
		
		testCase(3, 12, 3);
		testCase(-3, 12, 3);
		testCase(3, -12, 3);
		testCase(-3, -12, 3);
		
		testCase(252, 105, 21);
		testCase(-252, 105, 21);
		testCase(252, -105, 21);
		testCase(-252, -105, 21);
		
		testCase(31, 33, 1);
		testCase(-31, 33, 1);
		testCase(31, -33, 1);
		testCase(-31, -33, 1);
	}
	
	private void testCase(int a, int b, int expected)
	{
		for(TestMethod method : this.testMethods)
		{
			int result = method.test(a, b);
			assertEquals(String.format("[Failed] TestCase : method = %s, a = %d, b = %d", method.getName() ,a , b),
				expected, result);
		}
	}
}
