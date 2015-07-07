package test02_06;

import static org.junit.Assert.*;
import java.util.stream.*;
import org.junit.Test;
import ex02_06.CharacterStreamFactory;

public class CharacterStreamFactoryTest
{
	@Test
	public void createWithNull()
	{
		String text = null;
		
		Stream<Character> stream = CharacterStreamFactory.create(text);
		
		assertTrue(isExpectedStream("", stream));
	}

	@Test
	public void createWithText()
	{
		String text = "Text";
		
		Stream<Character> stream = CharacterStreamFactory.create(text);
		
		assertTrue(isExpectedStream(text, stream));
	}
	
	private static boolean isExpectedStream(String expected, Stream<Character> actual)
	{
		String actualText = actual.map(c -> c.toString()).reduce("", (r, c) -> r + c);
		return expected.equals(actualText);
	}
}
