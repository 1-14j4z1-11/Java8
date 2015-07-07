package ex01_11;

public class Class_IAbst_JAbst implements IAbst, JAbst
{
	@Override
	public void f()
	{
		// I,Jともに抽象メソッドのため、このクラスでf()の実装が必要
		System.out.println("Class");
	}
}
