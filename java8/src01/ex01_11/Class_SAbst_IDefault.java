package ex01_11;

public class Class_SAbst_IDefault extends SAbst implements IDefault
{
	@Override
	public void f()
	{
		// Iにデフォルト実装があるが、クラスSで抽象メソッドとなっているため、明示的な実装が必要
		System.out.println("Class");
	}
}
