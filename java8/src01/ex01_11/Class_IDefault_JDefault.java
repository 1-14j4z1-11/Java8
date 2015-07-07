package ex01_11;

public class Class_IDefault_JDefault implements IDefault, JDefault
{
	@Override
	public void f()
	{
		// I,Jでデフォルト実装があるが、ともに競合するため、このクラスで明示的な実装が必要
		System.out.println("Class");
	}
}
