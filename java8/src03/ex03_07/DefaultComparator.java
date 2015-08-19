package ex03_07;

import java.util.Comparator;

public enum DefaultComparator implements Comparator<String>
{
	/**
	 * 通常の文字列比較を行います
	 */
	NORMAL
	{
		@Override
		public int compare(String s1, String s2)
		{
			return s1.compareTo(s2);
		}
	},
	
	/**
	 * 逆順になるように文字列比較を行います
	 */
	REVERSE
	{
		@Override
		public int compare(String s1, String s2)
		{
			return s2.compareTo(s1);
		}
	};
}

