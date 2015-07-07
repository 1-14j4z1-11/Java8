package ex01_11;

public class Class_IAbst_JDefault implements IAbst, JStatic
{
	@Override
	public void f()
	{
		// Iで宣言されているf()の実装がないため、このクラスで実装が必要
		System.out.println("Class");
	}
}
