package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 1753: 최단 경로
 * 방향 그래프가 주어지면 시작점에서 다른 모든 정점으로의 최단 경로를 구한다.
 * 
 */

public class BOJ_1753_최단경로_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V, E;
	static int K;
	static List<int[]>[] edges;
	static int[] minDist;					// 출발지에서 각 정점까지의 최단거리
	static boolean[] visited;				// 각 정점을 방문했느지 여부를 체크하는 배열
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 정점의 개수와 간선의 개수를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		// 시작 정점의 번호를 입력받는다.
		K=Integer.parseInt(br.readLine().trim());
		
		// 각 정점에 연결된 간선 정보를 저장한다.
		edges=new ArrayList[V+1];
		for (int idx=0;idx<V+1;idx++) {
			edges[idx]=new ArrayList<int[]>();
		}
		// u에서 v로 가는 가중치 w
		for (int idx=0;idx<E;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			edges[u].add(new int[] {v,w});
		}
		
		// 각 정점까지의 최단거리를 저장하는 배열 크기와 값을 초기화한다.
		minDist=new int[V+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		
		// 시작 정점은 0으로 초기화한다.
		minDist[K]=0;
		visited=new boolean[V+1];
		
		for (int idx=0;idx<V;idx++) {
			
			// 노드 중 출발지에서 최단 경로를 가지는 특정 노드를 구한다.
			int curDist=Integer.MAX_VALUE;
			int curNode=-1;
			
			for (int node=1;node<V+1;node++) {
				if (!visited[node]&&minDist[node]<curDist) {
					curNode=node;
					curDist=minDist[node];
				}
			}
			// 갈 수 있는 경로가 없을 경우 무시
			if (curNode==-1) {
				continue;
			}
			// 해당 노드의 방문 표시
			visited[curNode]=true;
			
			// 선택한 노드의 인접한 노드까지 거리를 갱신한다.
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
