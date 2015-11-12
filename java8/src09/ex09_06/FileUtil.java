package ex09_06;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

// ex09_06と同じ
public class FileUtil
{
	private static String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * ファイルをバイト単位で判定して別ファイルに書き込みます
	 * @param inputPath 入力ファイル名
	 * @param outputPath 出力ファイル名
	 */
	public static void reverseBytes(String inputPath, String outputPath)
	{
		Objects.requireNonNull(inputPath, "Arguments must not be null");
		Objects.requireNonNull(outputPath, "Arguments must not be null");
		
		try
		{
			byte[] bytes = Files.readAllBytes(Paths.get(inputPath));
			reverseArray(bytes);
			Files.write(Paths.get(outputPath), bytes, StandardOpenOption.CREATE_NEW);
		}
		catch(IOException e)
		{
			throw new IORuntimeException(e);
		}
	}

	/**
	 * ファイルをバイト単位で判定して別ファイルに書き込みます
	 * @param inputPath 入力ファイル名
	 * @param outputPath 出力ファイル名
	 */
	public static void reverseLines(String inputPath, String outputPath)
	{
		Objects.requireNonNull(inputPath, "Arguments must not be null");
		Objects.requireNonNull(outputPath, "Arguments must not be null");
		
		try
		{
			boolean needSuffixNewLine = checkLastLineIsLineSeparator(inputPath);
			List<String> lines = Files.readAllLines(Paths.get(inputPath));
			
			if(needSuffixNewLine)
				lines.add("");
			
			Collections.reverse(lines);
			Files.write(Paths.get(outputPath), convertLinesToBytes(lines), StandardOpenOption.CREATE_NEW);
		}
		catch(IOException e)
		{
			throw new IORuntimeException(e);
		}
	}
	
	/**
	 * byte配列の要素順を反転します
	 * @param array 反転する配列
	 */
	private static void reverseArray(byte[] array)
	{
		if(array == null)
			throw new AssertionError();
		
		int length = array.length;
		
		for(int i = 0; i < length / 2; i++)
		{
			byte tmp = array[i];
			array[i] = array[length - i - 1];
			array[length - i - 1] = tmp;
		}
	}
	
	/**
	 * 文字列Listをbyte配列に変換します
	 * @param lines 変換する文字列List
	 * @return 変換したbyte配列
	 */
	private static byte[] convertLinesToBytes(List<String> lines)
	{
		if(lines.size() == 0)
			return new byte[0];
		
		return lines.stream().collect(Collectors.joining(LINE_SEPARATOR)).getBytes();
	}
	
	/**
	 * ファイルの終端が改行かどうかを判定します
	 * @param path 入力ファイル名
	 * @return ファイルの終端が改行の場合はtrue/改行でない場合はfalse
	 * @throws IOException ファイルの読み込みに失敗した場合
	 */
	private static boolean checkLastLineIsLineSeparator(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path))).endsWith(LINE_SEPARATOR);
	}
}
