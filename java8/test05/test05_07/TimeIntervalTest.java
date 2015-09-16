package test05_07;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Test;
import ex05_07.TimeInterval;

public class TimeIntervalTest
{
	// 不正なインスタンス生成
	@Test(expected = IllegalArgumentException.class)
	public void testWithIllegalInterval()
	{
		TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 1), LocalDateTime.of(2000, 1, 1, 12, 0));
	}
	
	// 開始==終了の時刻インターバルの生成ができることを確認
	@Test
	public void testPointInterval()
	{
		TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 12, 0));
	}
	
	// 一方のインターバルをと完全に重複する部分のインターバルのテスト
	@Test
	public void testWithAllOverlappedInterval1()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 14, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// 一方のインターバルをと完全に重複する部分のインターバルのテスト
	@Test
	public void testWithAllOverlappedInterval2()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 13, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// 同一区間のインターバルのテスト
	@Test
	public void testWithSameInterval()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// 部分的に重複するインターバルのテスト
	@Test
	public void testWithParticalOverlappedInterval()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 10, 0), LocalDateTime.of(2000, 1, 1, 12, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// 一致区間のないインターバルのテスト
	@Test
	public void testWithNoneOverlappedInterval1()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 12, 0));

		assertFalse(i1.overlapsWith(i2));
		assertFalse(i2.overlapsWith(i1));
	}

	// 一致区間のないインターバルのテスト
	@Test
	public void testWithNoneOverlappedInterval2()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 10, 0), LocalDateTime.of(2000, 1, 1, 11, 0));

		assertFalse(i1.overlapsWith(i2));
		assertFalse(i2.overlapsWith(i1));
	}
}
