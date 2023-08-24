package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 3124: 최소 스패닝 트리
 * 그래프가 주어졌을 때, MST를 구하는 프로그램
 * MST: 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 가중치의 합이 최소인 트리
 * 입력으로 주어지는 그래프는 하나의 연결 그래프이다.
 * 
 * 1. 테스트케이스 수를 입력받는다.
 * 2. 정점의 개수와 간선의 개수를 입력받는다.
 * 3. E 개의 줄에 각 간선에 대한 정보를 나타내는 정수 A, B, C를 입력받는다.
 * 4. C는 음수일 수 있다.
 * 
 * Kruskal 알고리즘을 사용하였다.
 * => 간선들을 정렬해야 한다.
 */

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int weight;

	public Edge(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight,o.weight);
	}


}
public class SWEA_3124_최소스패닝트리_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 수
	static int V,E;					// 정점의 개수, 간선의 개수
	static int[] parent;			// 각 정점이 속한 트리의 대표 정점을 저장하는 배열
	static int[] rank;				// 각 정점의 높이를 저장하는 배열
	static Edge[] edges;			// 간선의 정보를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트 케이스 수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		// 각 테스트 케이스에 걸쳐
		for (int test_case=1;test_case<=T;test_case++) {
			// 정점의 개수와 간선의 개수를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			edges=new Edge[E];
			
			// E줄에 걸쳐 간선 정보를 입력 받는다.
			for (int idx=0;idx<E;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				int node1=Integer.parseInt(st.nextToken())-1;
				int node2=Integer.parseInt(st.nextToken())-1;
				int weight=Integer.parseInt(st.nextToken());
				
				edges[idx]=new Edge(node1,node2,weight);
			}
			// 간선들을 weight를 중심으로 오름차순 정렬을 한다.
			Arrays.sort(edges);
			
			makeSet();
			
			// 현재까지 선택된 간선 개수 (V-1)개까지 선택되어야 한다.
			int selectedCnt=0;
			// 최소 가중치 합
			long totalWeight=0;
			
			// MST를 구성한다.
			for (Edge edge:edges) {
				if (union(edge.start,edge.end)) {
					totalWeight+=edge.weight;
					selectedCnt+=1;
					if (selectedCnt==V-1) break;
				}
			}
			// 테스트케이스 번호와 최소신장트리 가중치 값을 저장한다.
			sb.append("#"+test_case+" "+totalWeight+"\n");
		}
		// 결과를 출력한다.
		System.out.println(sb);
	}
	
	// 정점 집합 초기화
	public static void makeSet() {
		// 정점의 개수에 맞게 배열 크기를 초기화한다.
		parent=new int[V];
		rank=new int[V];
		
		// 각 정점이 속한 트리의 대표 정점은 자기 자신이다.
		for (int node=0;node<V;node++) {
			parent[node]=node;
		}
		
	}
	
	
	// 부분 트리에서 해당 정점이 속하는 대표 정점 return + 경로 압축
	public static int find(int a) {
		// 현재 정점이 대표 정점이라면 리턴
		if (a==parent[a]) {
			return a;
		}
		// 그렇지 않은 경우, 대표 정점 탐색 및 경로 압축
		return parent[a]=find(parent[a]);
	}
	
	// 두 개의 부분트리를 합치는 연산
	public static boolean union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		// 이미 두 정점이 같은 트리에 속한다면 합쳐질 수 없다.
		if (aRoot==bRoot) {
			return false;
		}
		if (rank[aRoot]>rank[bRoot]) {
			parent[bRoot]=aRoot;
		}
		else if (rank[aRoot]<rank[bRoot]) {
			parent[aRoot]=bRoot;
		}
		else {
			rank[aRoot]+=1;
			parent[bRoot]=aRoot;
		}
		return true;

		
	}
}
