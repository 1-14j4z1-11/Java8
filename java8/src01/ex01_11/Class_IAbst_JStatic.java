package ex01_11;

public class Class_IAbst_JStatic implements IAbst, JDefault
{
	@Override
	public void f()
	{
		// Jでデフォルト実装があるが、Iと競合するため、このクラスで明示的な実装が必要(Staticは無関係)
		System.out.println("Class");
	}
}
