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
 * BOJ 1260: DFS와 BFS
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램
 * 방문할 수 있는 정점이 여러 개인 경우, 정점 번호가 작은 것 먼저 방문하고 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
 * 정점 번호는 1번부터 N번까지이다.
 * 
 */
public class BOJ_1260_DFS와BFS_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	// N: 정점의 개수, M: 간선의 개수, V: 탐색을 시작할 정점의 번호
	static int N, M, V;
	
	// 각 정점의 인접한 정점을 저장하는 인접리스트
	static List<Integer>[] adjList;
	
	// 각 정점의 방문 여부를 표시하는 배열
	static boolean[] visited;
	
	static String res="";
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 정점의 개수, 간선의 개수, 탐색을 시작할 정점의 번호를 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		
		// 정점의 개수만큼 인접리스트 크기 초기화
		adjList=new LinkedList[N+1];
		
		// 각 정점에 대한 리스트 생성
		for (int idx=0;idx<N+1;idx++) {
			adjList[idx]=new LinkedList<>();
		}
		// 간선 정보를 인접리스트에 넣는다.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int node1=Integer.parseInt(st.nextToken());
			int node2=Integer.parseInt(st.nextToken());
			adjList[node1].add(node2);
			adjList[node2].add(node1);
		}
		// 다 넣은 후, 정점 번호가 작은 것부터 먼저 방문하기 위해 정렬한다.
		for (int idx=1;idx<=N;idx++) {
			Collections.sort(adjList[idx]);
		}
		
		// 정점의 개수만큼 방문 표시 배열 크기 초기화
		visited=new boolean[N+1];
		// DFS로 탐색한다.
		dfs(V);
		// 결과를 출력한다.
		System.out.println(res);
		
		res="";
		// 정점의 개수만큼 방문 표시 배열 크기 초기화
		visited=new boolean[N+1];
		// BFS로 탐색한다.
		bfs(V);
		// 결과를 출력한다.
		System.out.println(res);
	}
	// dfs 구현
	public static void dfs(int node) {
		// 현재 노드 방문 표시
		visited[node]=true;
		// 결과 문자열에 추가
		res+=node+" ";
		
		// 인접 노드에 대한 깊이 우선 탐색
		for (int idx=0;idx<adjList[node].size();idx++) {
			int nextNode=adjList[node].get(idx);
			// 방문하지 않은 노드에 대해서 dfs 수행
			if (!visited[nextNode]) {
				dfs(nextNode);
			}
		}
	}
	
	// bfs 구현
	// Queue 활용
	public static void bfs(int node) {
		Deque<Integer> que=new ArrayDeque<>();
		// 현재 노드 방문 표시
		que.add(node);
		visited[node]=true;
		
		// queue가 비어있지 않을 동안 반복
		while (!que.isEmpty()) {
			
			// queue에서 차례로 꺼내 결과 문자열에 저장
			int curNode=que.poll();
			res+=curNode+" ";
			
			// 방문하지 않은 인접 노드들을 큐에 넣는다.
			for (int idx=0;idx<adjList[curNode].size();idx++) {
				int nextNode=adjList[curNode].get(idx);
				if (visited[nextNode]) continue;
				visited[nextNode]=true;
				que.offer(nextNode);
			}
		}
	}

}
