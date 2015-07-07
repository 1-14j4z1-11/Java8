package ex01_02;

import java.io.*;

public class DirectorySearcher
{
	/**
	 * directory内に含まれるサブディレクトリの一覧を取得します</br>
	 * (内部でラムダ式を使用)
	 * @param directory 探索対象のディレクトリ
	 * @return directoryに含まれるサブディレクトリの配列
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException 引数のdirectoryがディレクトリでない場合
	 */
	public File[] searchSubDirectoriesWithLambda(File directory)
	{
		if(directory == null)
			throw new NullPointerException();
		
		if(!directory.isDirectory())
			throw new IllegalArgumentException();
		
		return directory.listFiles(f->f.isDirectory());
	}

	/**
	 * directory内に含まれるサブディレクトリの一覧を取得します</br>
	 * (内部でメソッド参照を使用)
	 * @param directory 探索対象のディレクトリ
	 * @return directoryに含まれるサブディレクトリの配列
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException 引数のdirectoryがディレクトリでない場合
	 */
	public File[] searchSubDirectoriesWithMethod(File directory)
	{
		if(directory == null)
			throw new NullPointerException();
		
		if(!directory.isDirectory())
			throw new IllegalArgumentException();
		
		return directory.listFiles(DirectorySearcher::directoryFilter);
	}
	
	private static boolean directoryFilter(File file)
	{
		return file.isDirectory();
	}
}
