package StackQueue;

public class QueueTest {
	public static void main(String[] args) {
		Queue q=new Queue();
		q.offer(1);
		System.out.println(q.peek());
		q.offer(2);
		System.out.println(q.peek());
		q.poll();
		System.out.println(q.peek());
		q.poll();
		System.out.println(q.isEmpty());
	}
}
