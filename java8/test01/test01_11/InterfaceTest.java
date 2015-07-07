package test01_11;

import org.junit.Test;
import ex01_11.*;

public class InterfaceTest
{
	@Test
	public void testInterfaceIAndJ()
	{
		new Class_IAbst_JAbst().f();		// Class
		new Class_IAbst_JDefault().f();		// Class
		new Class_IAbst_JStatic().f();		// Class
		new Class_IDefault_JDefault().f();	// Class
		new Class_IDefault_JStatic().f();	// I
		
		IStatic.f();						// I
		JStatic.f();						// J
	}

	@Test
	public void testSuperClassSAndInterfaceI()
	{
		new Class_SAbst_IDefault().f();	// Class
		new Class_SAbst_IAbst().f();	// Class
		new Class_SImpl_IAbst().f();	// S
		new Class_SImpl_IDefault().f();	// S
	}
}
