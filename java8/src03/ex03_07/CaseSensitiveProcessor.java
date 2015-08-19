package ex03_07;

public enum CaseSensitiveProcessor implements IStringProcessor
{
	/**
	 * 何も文字列処理を行いません
	 */
	NONE
	{
		@Override
		public String process(String str)
		{
			return str;
		}
	},
	
	/**
	 * 大文字/小文字を区別せずに文字列比較を行うために、文字列を全て小文字に変換します
	 */
	IGNORE_BY_LOWERCASE
	{
		@Override
		public String process(String str)
		{
			return str.toLowerCase();
		}
	},
	
	/**
	 * 大文字/小文字を区別せずに文字列比較を行うために、文字列を全て大文字に変換します
	 */
	IGNORE_BY_UPPERCASE
	{
		@Override
		public String process(String str)
		{
			return str.toUpperCase();
		}
	};
}
