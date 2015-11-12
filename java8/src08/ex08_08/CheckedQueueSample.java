package ex08_08;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class CheckedQueueSample
{
	public static void main(String[] args)
	{
		Queue<String> queue1 = new ArrayBlockingQueue<>(16);
		
		// RawTypeなのでQueueの型パラメータに依存せず変更が可能
		rawTypeFunction(queue1);
		
		System.out.println("Queue size = " + queue1.size());
		System.out.println(queue1);
		
		Queue<String> queue2 = Collections.checkedQueue(new ArrayBlockingQueue<>(16), String.class);
		
		try
		{
			rawTypeFunction(queue2);
		}
		catch(ClassCastException e)
		{
			e.printStackTrace();
		}
	}
	
	// 原型のQueueを操作する関数
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void rawTypeFunction(Queue queue)
	{
		if(queue == null)
			throw new NullPointerException();
		
		for(int i = 0; i < 10; i++)
		{
			queue.add(Integer.valueOf(i));
		}
	}
}
