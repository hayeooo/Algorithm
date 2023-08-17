package practice;

import java.util.Arrays;
import java.util.Scanner;

// ���� �׷���
public class AdjListTest {
	static class Node{
		int vertex;				// ���踦 �ΰ� �ִ� Ÿ ���� ����
		Node next;				// ���Ḯ��Ʈ ������ ���� ��� ����
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int V=sc.nextInt();
		int E=sc.nextInt();
		
		Node adjList[]=new Node[V];
		
		// ������ ������ 1, ������ 0
		
		for (int i=0;i<E;i++) {
			int from=sc.nextInt();
			int to=sc.nextInt();
			adjList[from]=new Node(to,adjList[from]);
			adjList[to]=new Node(from,adjList[to]);
		}
		for (Node node: adjList) {	// node: �� ������ ��������Ʈ�� ���
			System.out.println(node);
		}
	}

}
