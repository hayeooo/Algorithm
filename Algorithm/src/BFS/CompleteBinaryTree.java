package BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex=0; 	// ä���� ������ ����� �ε���
	private final int SIZE; // �ִ� ����� ����

	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new Object[size + 1];
	}
	
	public boolean isEmpty() {
		return lastIndex==0;
	}
	
	public boolean isFull() {
		return lastIndex==SIZE;
	}
	public boolean add(T data) {
		if (isFull()) {
			return false;
		}
		nodes[++lastIndex]=data;
		return true;
	}
	public void bfs() {
		if (isEmpty()) return;
		
		// Ž�� ������ ������ ��⿭ �ڷᱸ�� ����
		Queue<Integer> queue=new ArrayDeque<>();
		
		// Ž�� ������ ������ ť�� �ֱ�
		queue.offer(1);		// ��Ʈ ����� �ε���
		
		while (!queue.isEmpty()) {	// Ž�� ����� �ִٸ�
			int current=queue.poll();	// Ž�� ��� ť���� ������
			// Ž�� ��� �湮
			System.out.println(nodes[current]);
			
			// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ť�� �ֱ�
			if (current*2<=lastIndex) queue.offer(current*2);
			if (current*2+1<=lastIndex) queue.offer(current*2+1);
		}
	}
}
