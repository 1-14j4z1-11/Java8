package ex02_11;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

public class ListStreamOperation
{
	private ListStreamOperation() { }
	
	/**
	 * ストリームを受け取り、そのストリームの取得とストリームの要素数を取得できるようにするクラス
	 */
	private static class CountableStream<T>
	{
		private final Stream<T> baseStream;
		private Stream<T> newStream;
		private AtomicInteger size;
		
		/**
		 * インスタンスを初期化します
		 * @param stream 元となるストリーム
		 */
		public CountableStream(Stream<T> stream)
		{
			this.baseStream = stream;
		}
		
		/**
		 * ストリームを取得します
		 * @return
		 */
		public Stream<T> getStream()
		{
			if(this.newStream == null)
				this.initNewStream();
			
			return this.newStream;
		}
		
		/**
		 * ストリームの要素数を取得します
		 * @return
		 */
		public int getCount()
		{
			if(this.size == null)
				this.initNewStream();
			
			return this.size.get();
		}
		
		private void initNewStream()
		{
			this.size = new AtomicInteger();
			List<T> list = new LinkedList<>();
			
			this.baseStream.forEachOrdered(val -> 
				{
					this.size.incrementAndGet();
					list.add(val);
				});
			
			this.newStream = list.stream();
		}
	}
	
	/**
	 * streamの各要素に対してoperatorを実行し、その結果をまとめたListを返します</br>
	 * Listの要素順序はstreamの並び順とは同一にならないことがあります</br>
	 * operatorがnullの場合は何も操作をせず、結果を返します
	 * @param stream 操作対象のStream
	 * @param operator Stream内の各要素に行う操作
	 * @return 操作結果をまとめたList
	 * @exception NullPointerException streamがnullの場合
	 */
	public static <T> List<T> forEachParallel(Stream<T> stream, UnaryOperator<T> operator)
	{
		if(stream == null)
			throw new NullPointerException();
		
		UnaryOperator<T> op = operator != null ? operator : val -> val;
		
		CountableStream<T> cStream = new CountableStream<>(stream);
		List<T> results = new ArrayList<>(cStream.getCount());
		AtomicInteger index = new AtomicInteger(-1);

		IntStream.range(0, cStream.getCount()).forEach(val -> { results.add(null); });
		
		cStream.getStream().parallel().forEach(val ->
			{
				results.set(index.incrementAndGet(), op.apply(val));
			});
		
		return results;
	}
}
