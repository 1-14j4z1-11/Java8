package ex07_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test
{
	public void fun() throws IOException
	{
		List<String> lines = Files.readAllLines(new File("").toPath());
		lines.stream().map(line -> line.split(" ")).flatMap(words -> Arrays.stream(words));
		
		lines.stream().distinct().sorted().collect(java.util.stream.Collectors.toList());
	}
}
