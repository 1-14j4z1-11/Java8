package ex02_08;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamCreationUtil
{
	/**
	 * Spliteratorをラップし、forEachRemaining()を呼び出した時に実行されるtryAdvanceの実行回数に制限を付与するためのクラス</br>
	 */
	private static class LimitedSpliterator<T> implements Spliterator<T>
	{
		private final Spliterator<T> baseSpr;
		private final long limit;
		private final AtomicLong counter = new AtomicLong();
		
		/**
		 * インスタンスを初期化します
		 * @param baseSpr 元となるSpliterator
		 * @param tryAdvanceLimit forEachRemaining()内で呼び出すtryAdvaceの呼び出し上限回数
		 */
		public LimitedSpliterator(Spliterator<T> baseSpr, long tryAdvanceLimit)
		{
			assert (baseSpr != null);
			assert (baseSpr != this);
			
			this.baseSpr = baseSpr;
			this.limit = tryAdvanceLimit;
		}
		
		// tryAdvanceの呼び出し上限に達したらfalseを返すようにOverrideする
		@Override
		public boolean tryAdvance(Consumer<? super T> action)
		{
			if(this.counter.get() >= this.limit)
				return false;
			
			return this.baseSpr.tryAdvance(val -> 
				{
					this.counter.incrementAndGet();
					action.accept(val);
				});
		}
		
		// 実行開始時にactionの呼び出し回数をリセットする
		@Override
		public void forEachRemaining(Consumer<? super T> action)
		{
			this.counter.set(0);
			while(this.tryAdvance(action));
		};
		
		@Override
		public Spliterator<T> trySplit()
		{
			return this.baseSpr.trySplit();
		}
		
		@Override
		public long estimateSize()
		{
			return this.baseSpr.estimateSize();
		}
		
		@Override
		public int characteristics()
		{
			return this.baseSpr.characteristics();
		}
	}
	
	private StreamCreationUtil() { }
	
	/**
	 * 2個のストリームの要素を交互に並べたストリームを生成します</br>
	 * ストリームの生成はどちらかのストリームが空になった時点で終了します
	 * 両方が無限ストリームの場合はnullを返します
	 * @param first ストリームの生成に使用する1個目のストリーム
	 * @param second ストリームの生成に使用する2個目のストリーム
	 * @return 2個のストリームの要素を交互に並べたストリーム
	 * @exception NullPointerException 引数のいずれかがnullの場合
	 */
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
	{
		if((first == null) || (second == null))
			throw new NullPointerException();
		
		Spliterator<T> firstSpliterator = first.spliterator();
		Spliterator<T> secondSpliterator = second.spliterator();
		
		long minSize = Math.min(firstSpliterator.estimateSize(), secondSpliterator.estimateSize());
		
		if(minSize == Long.MAX_VALUE)
			return null;
		
		long firstLimit = minSize;
		long secondLimit = firstSpliterator.estimateSize() > secondSpliterator.estimateSize()
			? minSize : minSize - 1;
		
		List<T> zippedList = new LinkedList<>();
		AtomicInteger secondCount = new AtomicInteger(-1);
		
		// Spliteratorをラップし、tryAdvace()の実行回数に制限を設ける
		firstSpliterator = new LimitedSpliterator<>(firstSpliterator, firstLimit);
		secondSpliterator = new LimitedSpliterator<>(secondSpliterator, secondLimit);
		
		// first/secondの要素が交互に並ぶListを作成
		firstSpliterator.forEachRemaining(val -> zippedList.add(val));
		secondSpliterator.forEachRemaining(val -> zippedList.add(secondCount.addAndGet(2), val));
		
		return zippedList.stream();
	}
}
