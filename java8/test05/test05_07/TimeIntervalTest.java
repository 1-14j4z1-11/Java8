package test05_07;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Test;
import ex05_07.TimeInterval;

public class TimeIntervalTest
{
	// �s���ȃC���X�^���X����
	@Test(expected = IllegalArgumentException.class)
	public void testWithIllegalInterval()
	{
		TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 1), LocalDateTime.of(2000, 1, 1, 12, 0));
	}
	
	// �J�n==�I���̎����C���^�[�o���̐������ł��邱�Ƃ��m�F
	@Test
	public void testPointInterval()
	{
		TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 12, 0));
	}
	
	// ����̃C���^�[�o�����Ɗ��S�ɏd�����镔���̃C���^�[�o���̃e�X�g
	@Test
	public void testWithAllOverlappedInterval1()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 14, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// ����̃C���^�[�o�����Ɗ��S�ɏd�����镔���̃C���^�[�o���̃e�X�g
	@Test
	public void testWithAllOverlappedInterval2()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 13, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// �����Ԃ̃C���^�[�o���̃e�X�g
	@Test
	public void testWithSameInterval()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// �����I�ɏd������C���^�[�o���̃e�X�g
	@Test
	public void testWithParticalOverlappedInterval()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 10, 0), LocalDateTime.of(2000, 1, 1, 12, 0));

		assertTrue(i1.overlapsWith(i2));
		assertTrue(i2.overlapsWith(i1));
	}

	// ��v��Ԃ̂Ȃ��C���^�[�o���̃e�X�g
	@Test
	public void testWithNoneOverlappedInterval1()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 11, 0), LocalDateTime.of(2000, 1, 1, 12, 0));

		assertFalse(i1.overlapsWith(i2));
		assertFalse(i2.overlapsWith(i1));
	}

	// ��v��Ԃ̂Ȃ��C���^�[�o���̃e�X�g
	@Test
	public void testWithNoneOverlappedInterval2()
	{
		TimeInterval i1 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 13, 0));
		TimeInterval i2 = TimeInterval.between(LocalDateTime.of(2000, 1, 1, 10, 0), LocalDateTime.of(2000, 1, 1, 11, 0));

		assertFalse(i1.overlapsWith(i2));
		assertFalse(i2.overlapsWith(i1));
	}
}
