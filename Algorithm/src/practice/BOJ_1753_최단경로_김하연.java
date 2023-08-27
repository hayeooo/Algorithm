package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 1753: �ִ� ���
 * ���� �׷����� �־����� ���������� �ٸ� ��� ���������� �ִ� ��θ� ���Ѵ�.
 * 
 */

public class BOJ_1753_�ִܰ��_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V, E;
	static int K;
	static List<int[]>[] edges;
	static int[] minDist;					// ��������� �� ���������� �ִܰŸ�
	static boolean[] visited;				// �� ������ �湮�ߴ��� ���θ� üũ�ϴ� �迭
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ������ ������ ������ �Է¹޴´�.
		st=new StringTokenizer(br.readLine().trim());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		// ���� ������ ��ȣ�� �Է¹޴´�.
		K=Integer.parseInt(br.readLine().trim());
		
		// �� ������ ����� ���� ������ �����Ѵ�.
		edges=new ArrayList[V+1];
		for (int idx=0;idx<V+1;idx++) {
			edges[idx]=new ArrayList<int[]>();
		}
		// u���� v�� ���� ����ġ w
		for (int idx=0;idx<E;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			edges[u].add(new int[] {v,w});
		}
		
		// �� ���������� �ִܰŸ��� �����ϴ� �迭 ũ��� ���� �ʱ�ȭ�Ѵ�.
		minDist=new int[V+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		
		// ���� ������ 0���� �ʱ�ȭ�Ѵ�.
		minDist[K]=0;
		visited=new boolean[V+1];
		
		for (int idx=0;idx<V;idx++) {
			
			// ��� �� ��������� �ִ� ��θ� ������ Ư�� ��带 ���Ѵ�.
			int curDist=Integer.MAX_VALUE;
			int curNode=-1;
			
			for (int node=1;node<V+1;node++) {
				if (!visited[node]&&minDist[node]<curDist) {
					curNode=node;
					curDist=minDist[node];
				}
			}
			// �� �� �ִ� ��ΰ� ���� ��� ����
			if (curNode==-1) {
				continue;
			}
			// �ش� ����� �湮 ǥ��
			visited[curNode]=true;
			
			// ������ ����� ������ ������ �Ÿ��� �����Ѵ�.
			for (int[] adj:edges[curNode]) {
				if (visited[adj[0]]) continue;
				minDist[adj[0]]=Math.min(minDist[adj[0]], curDist+adj[1]);
			}
			
		}
		
		for (int idx=1;idx<V+1;idx++) {
			if (minDist[idx]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(minDist[idx]);
			}
		}
		
	}

}
