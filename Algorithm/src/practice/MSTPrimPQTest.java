package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MSTPrimPQTest {
	
	static class Vertex implements Comparable<Vertex>{
		int no, weight;	// ���� ��ȣ, Ʈ�� ������ �������� ���� ���� ���

		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
	static int V, adjMatrix[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		V=Integer.parseInt(in.readLine());
		adjMatrix=new int[V][V];
		
		for (int i=0;i<V;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for (int j=0;j<V;j++) {
				adjMatrix[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// �湮 ����(Ʈ�� ���� ǥ��)
		boolean[] visited=new boolean[V];
		int[] minEdge=new int[V];	// �ڽŰ� Ʈ���� ������ �� �ּ� ���� ���
		
		PriorityQueue<Vertex> pQueue=new PriorityQueue<>();
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);	// �ּҰ� ������ ���� ū ������ ����
		minEdge[0]=0;								// 0�� ������ Ʈ�� ������ ��������
		pQueue.offer(new Vertex(0,minEdge[0]));
		
		int result=0;								// �ּ� ���� Ʈ���� ���
		int min=0, minVertex=0;
		int cnt=0;
		
		while (!pQueue.isEmpty()) {
			
			// step1: �̹湮(��Ʈ��) ���� �� �ּҰ�������� ������ ����
			Vertex cur=pQueue.poll();
			minVertex=cur.no;
			min=cur.weight;
			
			if (visited[minVertex]) continue;
			
			// step2: Ʈ�� ������ �߰�
			visited[minVertex]=true;		// �湮 ó��
			result+=min;					// ����Ʈ�� ��� ����
			
			if(++cnt==V) break;
			
			// step3: Ʈ���� �߰��� ���ο� ���� �������� ��Ʈ�� �������� ���� ��� ���
			for (int i=0;i<V;i++) {
				if(!visited[i]&&adjMatrix[minVertex][i]!=0) {
					if (minEdge[i]>adjMatrix[minVertex][i]) {
						minEdge[i]=adjMatrix[minVertex][i];
						pQueue.offer(new Vertex(i,adjMatrix[minVertex][i]));
					}
				}
			}
		}
	}

}
