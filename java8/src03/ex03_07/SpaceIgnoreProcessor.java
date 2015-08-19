package ex03_07;

public enum SpaceIgnoreProcessor implements IStringProcessor
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
	 * スペースを無視して文字列比較するため文字列のスペース除去を行います
	 */
	IGNORE
	{
		@Override
		public String process(String str)
		{
			return str.replaceAll(" ", "");
		}
	};
}
