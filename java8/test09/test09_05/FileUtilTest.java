package test09_05;

import static org.junit.Assert.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;
import ex09_05.FileUtil;

public class FileUtilTest
{
	/**
	 * テスト対象メソッドのラッパー
	 */
	private static class FileIOTestMethod
	{
		private final BiConsumer<String, String> method;
		
		public FileIOTestMethod(BiConsumer<String, String> method)
		{
			this.method = method;
		}
		
		public void test(String inputPath, String outputPath)
		{
			this.method.accept(inputPath, outputPath);
		}
	}
	
	/**
	 * テスト用のファイル一覧
	 */
	private static enum SampleFiles
	{
		EMPTY("empty.txt"),
		NEWLINE("new_line.txt"),
		SINGLEBYTE("single_byte.txt"),
		SINGLELINE("single_line.txt"),
		RBYTE_SINGLELINE("rbyte_single_line.txt"),
		SINGLEROW("single_row.txt"),
		RLINE_SINGLEROW("rline_single_row.txt"),
		MULTILINES("multi_lines.txt"),
		RLINE_MULTILINES("rline_multi_lines.txt");

		private static final String SAMPLE_DIR = "testfiles";
		private final Path path;
		
		private SampleFiles(String filename)
		{
			this.path = Paths.get(SAMPLE_DIR, filename);
		}

		public Path getPath()
		{
			return this.path;
		}
		
		public String getPathStr()
		{
			return this.path.toString();
		}
	}
	
	public static final String TEST_DIR = "testfiles";
	private static final List<Runnable> testFinished = new ArrayList<>();
	
	private final FileIOTestMethod reverseBytesMethod = new FileIOTestMethod(FileUtil::reverseBytes);
	private final FileIOTestMethod reverseLinesMethod = new FileIOTestMethod(FileUtil::reverseLines);
	
	private final String outputPath = UUID.randomUUID().toString();		// 出力パス
	private final String existingPath = UUID.randomUUID().toString();	// 既存ファイルのパス
	
	@Test
	public void reverseBytesOfEmptyFile()
	{
		fileIOTestFlow(reverseBytesMethod, SampleFiles.EMPTY, SampleFiles.EMPTY, "ReverseBytes empty");
	}
	
	@Test
	public void reverseBytesOfSingleByteFile()
	{
		fileIOTestFlow(reverseBytesMethod, SampleFiles.SINGLEBYTE, SampleFiles.SINGLEBYTE, "ReverseBytes single byte");
	}
	
	@Test
	public void reverseBytesOfSingleLineFile()
	{
		fileIOTestFlow(reverseBytesMethod, SampleFiles.SINGLELINE, SampleFiles.RBYTE_SINGLELINE, "ReverseBytes single line");
	}
	
	@Test
	public void reverseLinesOfEmptyFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.EMPTY, SampleFiles.EMPTY, "ReverseLines empty");
	}

	@Test
	public void reverseLinesOfNewLineFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.NEWLINE, SampleFiles.NEWLINE, "ReverseLines new line");
	}
	
	@Test
	public void reverseLinesOfSingleByteFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.SINGLEBYTE, SampleFiles.SINGLEBYTE, "ReverseLines single byte");
	}
	
	@Test
	public void reverseLinesOfSingleLineFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.SINGLELINE, SampleFiles.SINGLELINE, "ReverseLines single line");
	}
	
	@Test
	public void reverseLinesOfSingleRowFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.SINGLEROW, SampleFiles.RLINE_SINGLEROW, "ReverseLines single row");
	}
	
	@Test
	public void reverseLinesOfMultiLinesFile()
	{
		fileIOTestFlow(reverseLinesMethod, SampleFiles.MULTILINES, SampleFiles.RLINE_MULTILINES, "ReverseLines multi lines");
	}
	
	public FileUtilTest()
	{
		testFinished.add(() ->
		{
			// テスト用に生成したファイルの削除
			try
			{
				if(Files.exists(getPath(this.outputPath)))
				{
					Files.delete(getPath(this.outputPath));
				}
				if(Files.exists(getPath(this.existingPath)))
				{
					Files.delete(getPath(this.existingPath));
				}
			}
			catch(Throwable t)
			{ }
		});
	}
	
	@AfterClass
	public static void tearDownClass()
	{
		while(testFinished.size() > 0)
		{
			testFinished.remove(0).run();
		}
	}
	
	@Before
	public void setup()
	{
		// テスト前にoutputPathのファイルがないことを保障する
		try
		{
			if(Files.exists(getPath(this.outputPath)))
			{
				Files.delete(getPath(this.outputPath));
			}
		}
		catch(Throwable e)
		{
			System.err.println("setup Failed.");
		}
		
		// テスト前にexistingPathのファイルが存在することを保証する
		try
		{
			if(!Files.exists(getPath(this.existingPath)))
			{
				Files.createFile(getPath(this.existingPath));
			}
		}
		catch(Throwable e)
		{
			System.err.println("setup Failed.");
		}
	}
	
	private static Path getPath(String filename)
	{
		return Paths.get(TEST_DIR, filename);
	}
	
	private void fileIOTestFlow(FileIOTestMethod method, SampleFiles inputFile, SampleFiles expectedFile, String testName)
	{
		byte[] result = null;
		byte[] expected = null;
		
		try
		{
			method.test(inputFile.getPathStr(), getPath(this.outputPath).toString());
			
			result = Files.readAllBytes(getPath(this.outputPath));
			expected = Files.readAllBytes(expectedFile.getPath());
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			fail();
		}
		
		try
		{
			assertArrayEquals(expected, result);
		}
		catch(AssertionError e)
		{
			System.err.println("Test failed - " + testName);
			
			System.err.println("<Expected>");
			System.err.println(Arrays.toString(expected));

			System.err.println("<Result>");
			System.err.println(Arrays.toString(result));
			
			throw e;
		}
	}
}
