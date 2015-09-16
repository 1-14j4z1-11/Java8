package ex05_07;

import java.time.*;

public class TimeInterval
{
	private final LocalDateTime start;
	private final LocalDateTime end;
	
	private TimeInterval(LocalDateTime start, LocalDateTime end)
	{
		if(start.compareTo(end) > 0)
			throw new IllegalArgumentException("'start' must not be later than 'end'");
		
		this.start = start;
		this.end = end;
	}
	
	/** �����C���^�[�o���̊J�n�������擾���܂� */
	public LocalDateTime getStartTime()
	{
		return this.start;
	}
	
	/** �����C���^�[�o���̏I���������擾���܂� */
	public LocalDateTime getEndTime()
	{
		return this.end;
	}
	
	/**
	 * 2�̃C���X�^���X�ԂŎ����C���^�[�o�����d�����Ă��邩�𔻒肵�܂�</br>
	 * ����̏I�������Ƃ�������̊J�n��������v���Ă���݂̂̏ꍇ�͏d���ƈ����܂���
	 * @param other �d�����肷���������̃C���X�^���X
	 * @return �d�����Ă���ꍇ��true��Ԃ��A���Ă��Ȃ��ꍇ��false��Ԃ�
	 */
	public boolean overlapsWith(TimeInterval other)
	{
		return (this.start.compareTo(other.end) < 0)
			&& (this.end.compareTo(other.start) > 0);
	}
	
	/**
	 * �����C���^�[�o���̃C���X�^���X�𐶐����܂�
	 * @param start �J�n����
	 * @param end �I������
	 * @return start����end�܂ł̎����C���^�[�o���������C���X�^���X
	 * @exception IllegalArgumentException end���start�̕����������x���ꍇ
	 */
	public static TimeInterval between(LocalDateTime start, LocalDateTime end)
	{
		return new TimeInterval(start, end);
	}
}
