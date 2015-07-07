package test01_03;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import util.FileUtil;
import ex01_03.FileSearcher;

public class FileSearcherTest
{
	/* 
	 * 以下のディレクトリを構成
	 *  main_dir -+- sub_dir1
	 *            +- sub_dir2
	 *            +- sub_dir3 -+- sub_file3_1.txt
	 *            |            +- sub_file3_2.txt
	 *            +- sub_dir4.txt
	 *            +- sub_file1.txt
	 *            +- sub_file2.txt
	 *            +- sub_file3.dat
	 *            +- sub_file4.dat
	 *            +- sub_file5.dat
	 */
	private static final String ROOT_DIR = "./main_dir/";
	private static final String[] SUB_DIRS = new String[]
		{
			"sub_dir1",
			"sub_dir2",
			"sub_dir3",
			"sub_dir4.txt"
		};
	private static final String[] SUB_FILES = new String[]
		{
			"sub_file1.txt",
			"sub_file2.txt",
			"sub_file3.dat",
			"sub_file4.dat",
			"sub_file5.dat",
		};
	private static final String[] SUB_FILES_IN3 = new String[]
		{
			"sub_file3_1.txt",
			"sub_file3_2.dat",
		};
	
	// サブファイルを含むディレクトリを探索
	@Test
	public void searchDirectoryContainingTxtFiles()
	{
		FileSearcher searcher = new FileSearcher();
		File root = new File(ROOT_DIR + SUB_DIRS[2]);
		
		File[] files = searcher.search(root, "txt");
		
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(new String[] { "sub_file3_1.txt" }, files));
	}

	// サブファイル/サブディレクトリ/深さ2のサブファイルを含むディレクトリを探索
	@Test
	public void searchDirectoryContainingTxtFilesAndDirs()
	{
		FileSearcher searcher = new FileSearcher();
		File root = new File(ROOT_DIR);
		
		File[] files = searcher.search(root, "txt");
		
		// 1階層下のサブファイルのみ返る
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(new String[] { "sub_file1.txt", "sub_file2.txt" }, files));
	}
	
	// 空のディレクトリを探索
	@Test
	public void searchEmptyDirectory()
	{
		FileSearcher searcher = new FileSearcher();
		File root = new File(ROOT_DIR + SUB_DIRS[0]);
		
		File[] files = searcher.search(root, "txt");
		
		// 空配列が返る
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(new String[0], files));
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchNotDirectory()
	{
		FileSearcher searcher = new FileSearcher();
		File root = new File(ROOT_DIR + SUB_FILES[0]);
		
		searcher.search(root, "txt");
	}

	@Test(expected = NullPointerException.class)
	public void searchDirectoryWithNullExtension()
	{
		FileSearcher searcher = new FileSearcher();
		File root = new File(ROOT_DIR + SUB_FILES[0]);
		
		searcher.search(root, null);
	}

	@Test(expected = NullPointerException.class)
	public void searchNullDirectory()
	{
		FileSearcher searcher = new FileSearcher();
		searcher.search(null, "txt");
	}
	
	@BeforeClass
	public static void setUpClass()
	{
		if(!FileUtil.createDirs(ROOT_DIR, SUB_DIRS)
			|| !FileUtil.createFiles(ROOT_DIR, SUB_FILES)
			|| !FileUtil.createFiles(ROOT_DIR + SUB_DIRS[2], SUB_FILES_IN3))
		{
			System.err.println("DirectorySearcherTest#setUp failed");
		}
	}

	@AfterClass
	public static void tearDownClass()
	{
		FileUtil.removeFiles(ROOT_DIR + SUB_DIRS[2], SUB_FILES_IN3);
		FileUtil.removeFiles(ROOT_DIR, SUB_DIRS);
		FileUtil.removeFiles(ROOT_DIR, SUB_FILES);
		FileUtil.removeFile(ROOT_DIR);
	}
}
