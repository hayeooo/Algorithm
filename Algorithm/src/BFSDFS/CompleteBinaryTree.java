package BFSDFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

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
	
	// �ʺ� ���� ����
	public void bfs2() {
		if (isEmpty()) return;
		
		// Ž�� ������ ������ ��⿭ �ڷᱸ�� ����
		Queue<Integer> queue=new ArrayDeque<>();
		
		// Ž�� ������ ������ ť�� �ֱ�
		queue.offer(1);		// ��Ʈ ����� �ε���
		
		int breadth=0;
		while (!queue.isEmpty()) {	// Ž�� ����� �ִٸ�
			int size=queue.size();
			
			while (--size>=0) {
				
				int current=queue.poll();	// Ž�� ��� ť���� ������
				// Ž�� ��� �湮
				System.out.print(nodes[current]+"\t");
				
				// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ť�� �ֱ�
				if (current*2<=lastIndex) queue.offer(current*2);
				if (current*2+1<=lastIndex) queue.offer(current*2+1);
			}
			System.out.println();
			System.out.println("============"+breadth+"�ʺ� Ž�� �Ϸ�");
			breadth++;
		}
	}
	// {���, �ʺ�}�� �Բ� queue���� ����
	public void bfs3() {
		if (isEmpty()) return;
		
		// Ž�� ������ ������ ��⿭ �ڷᱸ�� ����
		Queue<int[]> queue=new ArrayDeque<int[]>();	// {int[] : Ž������� �ε���, �ʺ�}
		
		// Ž�� ������ ������ ť�� �ֱ�
		queue.offer(new int[] {1,0});		// ��Ʈ ����� �ε���
		
		while (!queue.isEmpty()) {	// Ž�� ����� �ִٸ�
			int[] info=queue.poll();
			int current=info[0];	// Ž�� ��� ť���� ������
			// Ž�� ��� �湮
			System.out.println(nodes[current]+"// "+info[1]);
			
			// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ť�� �ֱ�
			if (current*2<=lastIndex) queue.offer(new int[] {current*2,info[1]+1});
			if (current*2+1<=lastIndex) queue.offer(new int[] {current*2+1,info[1]+1});
		}
	}
	
	public void dfsByPreOrder(int current) {
		
		System.out.print(nodes[current]);
		
		// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ��� ȣ��� Ž���Ѵ�.
		if (current*2<=lastIndex) dfsByPreOrder(current*2);
		if (current*2+1<=lastIndex) dfsByPreOrder(current*2+1);
	}
	
	public void dfsByInOrder(int current) {
		
		// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ��� ȣ��� Ž���Ѵ�.
		if (current*2<=lastIndex) dfsByPreOrder(current*2);
		System.out.print(nodes[current]);
		if (current*2+1<=lastIndex) dfsByPreOrder(current*2+1);
	}
	
	public void dfsByPostOrder(int current) {
		
		// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ��� ȣ��� Ž���Ѵ�.
		if (current*2<=lastIndex) dfsByPreOrder(current*2);
		if (current*2+1<=lastIndex) dfsByPreOrder(current*2+1);
		System.out.print(nodes[current]);
	}
	public void dfs() {
		if (isEmpty()) return;
		
		// Ž�� ������ ������ ��⿭ �ڷᱸ�� ����
		Stack<Integer> stack=new Stack<>();
		
		// Ž�� ������ ������ ť�� �ֱ�
		stack.push(1);		// ��Ʈ ����� �ε���
		
		while (!stack.isEmpty()) {	// Ž�� ����� �ִٸ�
			int current=stack.pop();	// Ž�� ��� ť���� ������
			// Ž�� ��� �湮
			System.out.print(nodes[current]);
			
			// ���� Ž�� ����� ���� Ž���ؾ� �� ���ο� ����� ť�� �ֱ�
			if (current*2+1<=lastIndex) stack.push(current*2+1);
			if (current*2<=lastIndex) stack.push(current*2);
		}
	}
}
