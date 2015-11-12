package ex09_02;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CloseExceptionSample
{
	private CloseExceptionSample() { throw new UnsupportedOperationException(); }
	
	public static void main(String[] args)
	{
		try
		{
			execute();
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static void execute() throws IOException
	{
		String inputPath = "not_exist_file";
		String outputPath = "out.txt";
		Scanner in = null;
		PrintWriter out = null;
		
		try
		{
			in = new Scanner(Paths.get(inputPath));
			out = new PrintWriter(outputPath);
			
			while(in.hasNext())
			{
				out.println(in.next().toLowerCase());
			}
		}
		catch(IOException e)
		{
			throw e;
		}
		finally
		{
			safeClose(in);
			safeClose(out);
		}
	}
	
	/**
	 * Closeableインスタンスを安全にクローズします</br>
	 * nullの場合はOptional.empty()を返し、何も処理をしません
	 * @param closeable クローズするインスタンス
	 * @return クローズの際に発生した例外(Optional)
	 */
	private static Optional<Throwable> safeClose(Closeable closeable)
	{
		try
		{
			if(closeable != null)
				closeable.close();
		}
		catch(Throwable t)
		{
			return Optional.of(t);
		}
		
		return Optional.empty();
	}
}
