package ex03_07;

/**
 * 文字列比較の前処理として行う文字列処理を実装する
 */
public interface IStringProcessor
{
	/**
	 * 比較の前処理としての文字列変換を行います
	 * @param str 変換する文字列
	 * @return 変換した文字列
	 */
	String process(String str);
}

