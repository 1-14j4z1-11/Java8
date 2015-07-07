package ex02_06;

import java.util.stream.*;

public class CharacterStreamFactory
{
	/**
	 * 引数の文字列の文字を順に返すストリームを生成します</br>
	 * 引数がnullの場合は空ストリームを返します
	 * @param text ストリームを生成する文字列
	 * @return 文字を順に返すCharacterのストリーム
	 */
	public static Stream<Character> create(String text)
	{
		if(text == null)
			return Stream.empty();
		
		return IntStream.range(0, text.length()).mapToObj(text::charAt);
	}
}
