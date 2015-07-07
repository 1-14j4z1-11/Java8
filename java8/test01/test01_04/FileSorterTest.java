package test01_04;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import util.FileUtil;
import ex01_04.FileSorter;

public class FileSorterTest
{
	/* 
	 * 以下のディレクトリを構成
	 *  main_dir -+- sub1 ----- sub1.txt
	 *            +- sub4.txt
	 *            +- sub7 ----- sub1.txt
	 *            +- sub2.txt
	 *            +- sub3
	 *            +- sub6.dat
	 *            +- sub6.txt
	 * 
	 * 以下のファイルは生成されない
	 *  main_dir -+- sub0
	 *            +- sub5.txt
	 */
	private static final String ROOT_DIR = "./main_dir/";
	private static final String[] SUB_DIRS = new String[]
		{
			"sub1",
			"sub4.txt",
			"sub7",
		};
	private static final String[] SUB_FILES = new String[]
		{
			"sub2.txt",
			"sub3",
			"sub6.dat",
			"sub6.txt",
		};
	private static final String[] SUB_FILES_IN1 = new String[]
		{
			"sub1.txt",
		};
	private static final String[] SUB_FILES_IN3 = new String[]
		{
			"sub1.txt",
		};
	private static final String[] DUMMY_PATHS = new String[]	// 存在しないファイル
		{
			"sub0",
			"sub5.txt",
		};
	
	@Test
	public void sortFiles1()
	{
		// パス一覧(ソート後の結果)
		String[] paths = new String[]
			{
				ROOT_DIR + SUB_DIRS[0],
				ROOT_DIR + SUB_DIRS[1],
				ROOT_DIR + SUB_DIRS[2],
				ROOT_DIR + SUB_FILES[0],
				ROOT_DIR + SUB_FILES[1],
				ROOT_DIR + SUB_FILES[2],
				ROOT_DIR + SUB_FILES[3],
				ROOT_DIR + DUMMY_PATHS[0],
				ROOT_DIR + DUMMY_PATHS[1],
			};
		
		// 順序を入れ替えてFile配列を生成
		File[] files = createFileArray(
			paths[1],
			paths[0],
			paths[6],
			paths[3],
			paths[8],
			paths[2],
			paths[5],
			paths[4],
			paths[7]);

		FileSorter sorter = new FileSorter();
		sorter.sortFiles(files);

		assertTrue(resultFilesOrderEqualsExpectedOrder(paths, files));
	}
	
	@Test
	public void sortFiles2()
	{
		// パス一覧(ソート後の結果)
		String[] paths = new String[]
			{
				ROOT_DIR + SUB_DIRS[0],
				ROOT_DIR + SUB_DIRS[1],
				ROOT_DIR + SUB_DIRS[2],
				ROOT_DIR + SUB_DIRS[0] + SUB_FILES_IN1[0],
				ROOT_DIR + SUB_FILES[0],
				ROOT_DIR + SUB_FILES[1],
				ROOT_DIR + SUB_FILES[2],
				ROOT_DIR + SUB_FILES[3],
				ROOT_DIR + SUB_DIRS[2] + SUB_FILES_IN1[0],
				ROOT_DIR + DUMMY_PATHS[0],
				ROOT_DIR + DUMMY_PATHS[1],
			};
		
		// 順序を入れ替えてFile配列を生成
		File[] files = createFileArray(
			paths[1],
			paths[0],
			paths[6],
			paths[10],
			paths[3],
			paths[8],
			paths[2],
			paths[5],
			paths[9],
			paths[4],
			paths[7]);

		FileSorter sorter = new FileSorter();
		sorter.sortFiles(files);

		assertTrue(resultFilesOrderEqualsExpectedOrder(paths, files));
	}
	
	@Test(expected = NullPointerException.class)
	public void searchNullArray()
	{
		FileSorter sorter = new FileSorter();
		sorter.sortFiles(null);
	}
	
	@BeforeClass
	public static void setUpClass()
	{
		if(!FileUtil.createDirs(ROOT_DIR, SUB_DIRS)
			|| !FileUtil.createFiles(ROOT_DIR, SUB_FILES)
			|| !FileUtil.createDirs(SUB_DIRS[0], SUB_FILES_IN1)
			|| !FileUtil.createDirs(SUB_DIRS[2], SUB_FILES_IN3))
		{
			System.err.println("DirectorySearcherTest#setUp failed");
		}
	}

	@AfterClass
	public static void tearDownClass()
	{
		FileUtil.removeFiles(SUB_FILES[2], SUB_FILES_IN3);
		FileUtil.removeFiles(SUB_FILES[0], SUB_FILES_IN1);
		FileUtil.removeFiles(ROOT_DIR, SUB_DIRS);
		FileUtil.removeFiles(ROOT_DIR, SUB_FILES);
		FileUtil.removeFile(ROOT_DIR);
	}
	
	/**
	 * ** 結果確認用メソッド **</br>
	 * resultのファイル順序がexpectedの順序と一致するかどうかを確認します</br>
	 * expected内に同名ファイルが含まれていないことを前提にしているため注意
	 * @param expected 期待結果(ディレクトリ名(String)の配列)
	 * @param result 実行結果(Fileの配列)
	 * @return 実行結果が期待結果と一致する場合trueを返す、そうでない場合はfalse
	 */
	private static boolean resultFilesOrderEqualsExpectedOrder(String[] expected, File[] result)
	{
		// 要素数が一致
		if(expected.length != result.length)
		{
			System.err.printf("Not equals expected size : %d - result size : %d", expected.length, result.length);
			return false;
		}
		
		for(int i = 0; i < expected.length; i++)
		{
			if(expected[i].equals(result[i].getPath()))
			{
				System.err.printf("NotEqual : %s != %s", expected[i], result[i].getPath());
				return false;
			}
		}
		
		return true;
	}
	
	private static File[] createFileArray(String ... paths)
	{
		File[] files = new File[paths.length];
		
		for(int i = 0; i < paths.length; i++)
		{
			files[i] = new File(paths[i]);
		}
		
		return files;
	}
}
