package ex01_04;

import java.io.*;
import java.util.Arrays;

public class FileSorter
{
	/**
	 * File配列をソートします</br>
	 * ディレクトリ/ファイルの種類(ディレクトリ->ファイル->それ以外)を第1ソートキー、
	 * ディレクトリ/ファイルの絶対パスを第2ソートキーとしてソートします
	 * @param directory 検索対象のディレクトリ
	 * @param extension 検索するファイルの拡張子
	 * @return 検索されたファイルの配列
	 * @exception NullPointerException 引数がnullの場合
	 */
	public void sortFiles(File[] files)
	{
		if(files == null)
			throw new NullPointerException();
		
		Arrays.sort(files, (file1, file2) ->
		{
			int type1 = getFileTypeValue(file1);
			int type2 = getFileTypeValue(file2);
			
			if(type1 == type2)
			{
				return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			}
			else
			{
				return type1 - type2;
			}
		});
	}
	
	/**
	 * Fileの種類ををキーとしてソートするための値を取得します
	 */
	private static int getFileTypeValue(File file)
	{
		if(file.isDirectory())
			return 1;
		else if(file.isFile())
			return 2;
		else 
			return 3;
	}
}
