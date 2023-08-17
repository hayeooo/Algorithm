package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 1260: DFS�� BFS
 * �׷����� DFS�� Ž���� ����� BFS�� Ž���� ����� ����ϴ� ���α׷�
 * �湮�� �� �ִ� ������ ���� ���� ���, ���� ��ȣ�� ���� �� ���� �湮�ϰ� �� �̻� �湮�� �� �ִ� ���� ���� ��� �����Ѵ�.
 * ���� ��ȣ�� 1������ N�������̴�.
 * 
 */
public class BOJ_1260_DFS��BFS_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	// N: ������ ����, M: ������ ����, V: Ž���� ������ ������ ��ȣ
	static int N, M, V;
	
	// �� ������ ������ ������ �����ϴ� ��������Ʈ
	static List<Integer>[] adjList;
	
	// �� ������ �湮 ���θ� ǥ���ϴ� �迭
	static boolean[] visited;
	
	static String res="";
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// ������ ����, ������ ����, Ž���� ������ ������ ��ȣ�� �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		
		// ������ ������ŭ ��������Ʈ ũ�� �ʱ�ȭ
		adjList=new LinkedList[N+1];
		
		// �� ������ ���� ����Ʈ ����
		for (int idx=0;idx<N+1;idx++) {
			adjList[idx]=new LinkedList<>();
		}
		// ���� ������ ��������Ʈ�� �ִ´�.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int node1=Integer.parseInt(st.nextToken());
			int node2=Integer.parseInt(st.nextToken());
			adjList[node1].add(node2);
			adjList[node2].add(node1);
		}
		// �� ���� ��, ���� ��ȣ�� ���� �ͺ��� ���� �湮�ϱ� ���� �����Ѵ�.
		for (int idx=1;idx<=N;idx++) {
			Collections.sort(adjList[idx]);
		}
		
		// ������ ������ŭ �湮 ǥ�� �迭 ũ�� �ʱ�ȭ
		visited=new boolean[N+1];
		// DFS�� Ž���Ѵ�.
		dfs(V);
		// ����� ����Ѵ�.
		System.out.println(res);
		
		res="";
		// ������ ������ŭ �湮 ǥ�� �迭 ũ�� �ʱ�ȭ
		visited=new boolean[N+1];
		// BFS�� Ž���Ѵ�.
		bfs(V);
		// ����� ����Ѵ�.
		System.out.println(res);
	}
	// dfs ����
	public static void dfs(int node) {
		// ���� ��� �湮 ǥ��
		visited[node]=true;
		// ��� ���ڿ��� �߰�
		res+=node+" ";
		
		// ���� ��忡 ���� ���� �켱 Ž��
		for (int idx=0;idx<adjList[node].size();idx++) {
			int nextNode=adjList[node].get(idx);
			// �湮���� ���� ��忡 ���ؼ� dfs ����
			if (!visited[nextNode]) {
				dfs(nextNode);
			}
		}
	}
	
	// bfs ����
	// Queue Ȱ��
	public static void bfs(int node) {
		Deque<Integer> que=new ArrayDeque<>();
		// ���� ��� �湮 ǥ��
		que.add(node);
		visited[node]=true;
		
		// queue�� ������� ���� ���� �ݺ�
		while (!que.isEmpty()) {
			
			// queue���� ���ʷ� ���� ��� ���ڿ��� ����
			int curNode=que.poll();
			res+=curNode+" ";
			
			// �湮���� ���� ���� ������ ť�� �ִ´�.
			for (int idx=0;idx<adjList[curNode].size();idx++) {
				int nextNode=adjList[curNode].get(idx);
				if (visited[nextNode]) continue;
				visited[nextNode]=true;
				que.offer(nextNode);
			}
		}
	}

}
