package ex01_11;

public class Class_SAbst_IAbst extends SAbst implements IAbst
{
	@Override
	public void f()
	{
		// S,Iでf()の実装がないため、このクラスでf()の明示的な実装が必要
		System.out.println("Class");
	}
}
