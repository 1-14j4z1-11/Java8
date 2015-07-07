package test01_02;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import util.FileUtil;
import ex01_02.DirectorySearcher;

public class DirectorySearcherTest
{
	/* 
	 * 以下のディレクトリを構成
	 *  main_dir -+- sub_dir1
	 *            +- sub_dir2
	 *            +- sub_dir3 -+- sub_dir3_1
	 *            |            +- sub_dir3_2
	 *            +- sub_file1.txt
	 *            +- sub_file2.txt
	 */
	private static final String ROOT_DIR = "./main_dir/";
	private static final String[] SUB_DIRS = new String[]	// ルート直下のサブディレクトリ
		{
			"sub_dir1",
			"sub_dir2",
			"sub_dir3",
		};
	private static final String[] SUB_DIRS_IN3 = new String[]	// sub_dir3直下のサブディレクトリ
		{
			"sub_dir3_1",
			"sub_dir3_2",
		};
	private static final String[] SUB_FILES = new String[]	// ルート直下のファイル
		{
			"sub_file1.txt",
			"sub_file2.txt",
		};

	// サブディレクトリがあるディレクトリを探索
	@Test
	public void searchDirectoryIncludingSubDirs()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		File root = new File(ROOT_DIR + SUB_DIRS[2]);
		
		File[] subs_lambda = searcher.searchSubDirectoriesWithLambda(root);
		File[] subs_method = searcher.searchSubDirectoriesWithMethod(root);
		
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(SUB_DIRS_IN3, subs_lambda));
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(SUB_DIRS_IN3, subs_method));
	}
	
	// サブディレクトリ、サブファイル、深さ2のサブディレクトリがあるディレクトリを探索
	@Test
	public void searchDirectoryIncludingSubDirsAndFilesAndSubSubDirs()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		File root = new File(ROOT_DIR);
		
		File[] subs_lambda = searcher.searchSubDirectoriesWithLambda(root);
		File[] subs_method = searcher.searchSubDirectoriesWithMethod(root);
		
		// 1階層下のディレクトリのみが返る
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(SUB_DIRS, subs_lambda));
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(SUB_DIRS, subs_method));
	}
	
	// 空ディレクトリを探索
	@Test
	public void searchEmptyDirectory()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		File root = new File(ROOT_DIR + SUB_DIRS[0]);
		
		File[] subs_lambda = searcher.searchSubDirectoriesWithLambda(root);
		File[] subs_method = searcher.searchSubDirectoriesWithMethod(root);
		
		// 空配列が返る
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(new String[0], subs_lambda));
		assertTrue(FileUtil.resultFilesContainsExpectedPaths(new String[0], subs_method));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void searchNotDirectory1()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		File root = new File(ROOT_DIR + SUB_FILES[0]);
		
		searcher.searchSubDirectoriesWithLambda(root);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void searchNotDirectory2()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		File root = new File(ROOT_DIR + SUB_FILES[0]);
		
		searcher.searchSubDirectoriesWithMethod(root);
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullObject1()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		searcher.searchSubDirectoriesWithLambda(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullObject2()
	{
		DirectorySearcher searcher = new DirectorySearcher();
		searcher.searchSubDirectoriesWithMethod(null);
	}
	
	@BeforeClass
	public static void setUpClass()
	{
		// テスト用のディレクトリを生成
		if(!FileUtil.createDirs(ROOT_DIR, SUB_DIRS)
			|| !FileUtil.createDirs(ROOT_DIR + SUB_DIRS[2], SUB_DIRS_IN3)
			|| !FileUtil.createFiles(ROOT_DIR, SUB_FILES))
		{
			System.err.println("DirectorySearcherTest#setUp failed");
		}
	}

	@AfterClass
	public static void tearDownClass()
	{
		// テスト用のディレクトリを削除
		FileUtil.removeFiles(ROOT_DIR + SUB_DIRS[2], SUB_DIRS_IN3);
		FileUtil.removeFiles(ROOT_DIR, SUB_DIRS);
		FileUtil.removeFiles(ROOT_DIR, SUB_FILES);
		FileUtil.removeFile(ROOT_DIR);
	}
}
