package practice;

import java.util.Arrays;
import java.util.Scanner;

// 무향 그래프
public class AdjListTest {
	static class Node{
		int vertex;				// 관계를 맺고 있는 타 정점 정보
		Node next;				// 연결리스트 유지를 위한 노드 참조
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
		
		// 간선이 있으면 1, 없으면 0
		
		for (int i=0;i<E;i++) {
			int from=sc.nextInt();
			int to=sc.nextInt();
			adjList[from]=new Node(to,adjList[from]);
			adjList[to]=new Node(from,adjList[to]);
		}
		for (Node node: adjList) {	// node: 각 정점의 인접리스트의 헤드
			System.out.println(node);
		}
	}

}
