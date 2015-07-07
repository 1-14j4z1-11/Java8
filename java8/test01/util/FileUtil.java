package util;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileUtil
{
	private FileUtil() { };
	
	/**
	 * サブディレクトリを作成します
	 * @param rootDirPath ディレクトリを作成する親ディレクトリのパス
	 * @param names 作成するディレクトリ名の配列
	 * @return 全てのディレクトリ作成に成功した場合はtrueを返し、それ以外はfalseを返します
	 */
	public static boolean createDirs(String rootDirPath, String[] names)
	{
		boolean result = true;
		
		for(String name : names)
		{
			File sub = new File(rootDirPath + File.separatorChar + name);
			
			if(sub.exists())
				continue;
			
			try
			{
				result &= sub.mkdirs();
			}
			catch(SecurityException e)
			{
				result = false;
				break;
			}
		}
		
		return result;
	}

	/**
	 * サブファイルを作成します
	 * 作成されるファイルは全て空ファイルになります
	 * @param rootDirPath ファイルを作成する親ディレクトリのパス
	 * @param names 作成するファイル名の配列
	 * @return 全てのファイル作成に成功した場合はtrueを返し、それ以外はfalseを返します
	 */
	public static boolean createFiles(String rootDirPath, String[] names)
	{
		boolean result = true;
		
		for(String name : names)
		{
			File sub = new File(rootDirPath + File.separatorChar + name);
			
			if(sub.exists())
				continue;
			
			try
			{
				result &= sub.createNewFile();
			}
			catch(SecurityException | IOException e)
			{
				result = false;
				break;
			}
		}

		return result;
	}

	/**
	 * パスで指定されたファイルまたはディレクトリを削除します
	 * @param path 削除ファイル/ディレクトリのパス
	 */
	public static void removeFile(String path)
	{
		File file = new File(path);
		
		if(!file.exists())
			return;
		
		try
		{
			file.delete();
		}
		catch(SecurityException e)
		{ }
	}
	
	/**
	 * 指定されたディレクトリ内に存在するファイル/ディレクトリを削除します
	 * @param rootDirPath 削除するファイル/ディレクトリを含む親ディレクトリのパス
	 * @param names 削除ファイル/ディレクトリ名の配列
	 */
	public static void removeFiles(String rootDirPath, String[] names)
	{
		for(String name : names)
		{
			removeFile(rootDirPath + File.separatorChar + name);
		}
	}

	/**
	 * ** 結果確認用メソッド **
	 * expectedのパス一覧が過不足なくresultに含まれるかどうかを確認する
	 * @param expected 期待結果(ディレクトリ名(String)の配列)
	 * @param result 実行結果(Fileの配列)
	 * @return 実行結果が期待結果と一致する場合trueを返す、そうでない場合はfalse
	 */
	public static boolean resultFilesContainsExpectedPaths(String[] expected, File[] result)
	{
		// 要素数が一致
		if(expected.length != result.length)
		{
			System.err.printf("Not equals expected size : %d - result size : %d%n", expected.length, result.length);
			return false;
		}
		
		List<String> expectedList = Arrays.asList(expected);
		
		// 一覧にサブフォルダが含まれており、かつ重複がない
		for(File sub : result)
		{
			String subPath = sub.getName();
			int first = expectedList.indexOf(subPath);
			int last = expectedList.lastIndexOf(subPath);
			
			if(first == -1)
			{
				System.err.printf("Not contains : %s%n", subPath);
				return false;
			}
			if(first != last)
			{
				System.err.printf("contains same instance : %s%n", subPath);
				return false;
			}
		}
		
		return true;
	}
}
