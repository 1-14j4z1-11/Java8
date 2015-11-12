package ex09_07;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class WebPageDownloader
{
	/**
	 * downloadURLのWEBページの内容をoutputPathに保存します
	 * @param downloadURL 保存元のWebページURL
	 * @param outputPath 保存先パス
	 * @return 保存結果
	 */
	public static Result<Void> download(String downloadURL, String outputPath)
	{
		URL url;
		try
		{
			url = new URL(downloadURL);
		}
		catch(MalformedURLException e)
		{
			return Result.withError(e);
		}

		try(InputStream stream = url.openStream())
		{
			Files.copy(stream, Paths.get(outputPath));
		}
		catch(IOException e)
		{
			return Result.withError(e);
		}
		
		return Result.empty();
	}
	
	/**
	 * downloadURLのWEBページの内容をoutputPathに非同期で保存します
	 * @param downloadURL 保存元のWebページURL
	 * @param outputPath 保存先パス
	 * @return 保存結果のCompletableFuture
	 */
	public static CompletableFuture<Result<Void>> downloadAsync(String inputURL, String outputPath)
	{
		return CompletableFuture.supplyAsync(() ->
		{
			return WebPageDownloader.download(inputURL, outputPath);
		});
	}
}
