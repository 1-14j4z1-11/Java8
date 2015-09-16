package ex05_12;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class ScheduleTimer
{
	private static final int TIMER_INTERVAL = 60;	// �b
	private static final Duration ALERM_DURATION = Duration.ofHours(1);
	
	private final List<ScheduleItem> schedules = new ArrayList<>();
	private final Map<ScheduleItem, Boolean> alerted = new HashMap<>();
	private final List<Consumer<? super List<ScheduleItem>>> onAlertHandlers = new ArrayList<>();
	private boolean isRunning = false;
	private ScheduledExecutorService executor;
	
	public void addSchedule(ScheduleItem schedule)
	{
		this.schedules.add(schedule);
		this.alerted.put(schedule, false);
	}
	
	public void onAlerted(Consumer<? super List<ScheduleItem>> handler)
	{
		this.onAlertHandlers.add(handler);
	}
	
	/**
	 * �X�P�W���[���̊Ď����J�n���A�\�莞�Ԃɋ߂Â��ƒʒm���܂�
	 */
	public void start()
	{
		synchronized (this)
		{
			if(this.isRunning)
				return;
			
			this.isRunning = true;
			this.executor = Executors.newSingleThreadScheduledExecutor();
		}
		
		this.executor.scheduleAtFixedRate(() ->
			{
				List<ScheduleItem> schedules = this.getNearSchedule();
				
				if(this.isRunning && (schedules.size() > 0))
				{
					this.onAlertHandlers.forEach(handler -> handler.accept(schedules));
					this.schedules.stream()
						.filter(s -> this.alerted.containsKey(s))
						.forEach(s -> this.alerted.put(s, true));
				}
			},
			0, TIMER_INTERVAL, TimeUnit.SECONDS);
	}
	
	/**
	 * �X�P�W���[���̊Ď����I�����܂�
	 */
	public void end()
	{
		if(this.executor == null)
			return;
		
		ScheduledExecutorService tmp;
		
		synchronized (this)
		{
			this.isRunning = false;
			tmp = this.executor;
			this.executor = null;
		}
		
		tmp.shutdown();
	}
	
	private List<ScheduleItem> getNearSchedule()
	{
		LocalDateTime now = LocalDateTime.now();
		
		return this.schedules.stream()
			.filter(s -> this.alerted.containsKey(s))
			// �ʒm�𔭍s���Ă��Ȃ�	
			.filter(s -> this.alerted.get(s))
			// �\�莞�Ԃ��߂��Ă��Ȃ�
			.filter(s -> !Duration.between(now, s.getTime()).isNegative())
			// �ʒm���ԂɂȂ��Ă���
			.filter(s -> Duration.between(now, s.getTime()).compareTo(ALERM_DURATION) < 0)
			.collect(Collectors.toList());
	}
}
