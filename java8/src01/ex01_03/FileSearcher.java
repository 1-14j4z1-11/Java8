package ex01_03;

import java.io.*;

public class FileSearcher
{
	/**
	 * directoryに含まれるサブファイルのから、拡張子がextensionのものを検索します
	 * @param directory 検索対象のディレクトリ
	 * @param extension 検索するファイルの拡張子
	 * @return 検索されたファイルの配列
	 * @exception NullPointerException 引数がnullの場合
	 * @exception IllegalArgumentException 引数のdirectoryがディレクトリでない場合
	 */
	public File[] search(File directory, String extension)
	{
		if((directory == null) || (extension == null))
			throw new NullPointerException();
		
		if(!directory.isDirectory())
			throw new IllegalArgumentException();
		
		return directory.listFiles((file)->(!file.isDirectory() && checkExtension(file, extension)));
	}
	
	private static boolean checkExtension(File file, String ext)
	{
		if((file == null) || !file.isFile() || (ext == null))
			return false;
		
		int index = file.getName().lastIndexOf(".");
		
		if((index == -1) || (index == file.getName().length() - 1))
			return false;
		
		String fileExt = file.getName().substring(index + 1).toLowerCase();
		
		return fileExt.equals(ext.toLowerCase());
	}
}
