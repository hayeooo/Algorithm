package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1767: 하나로
 * 임의의 정점을 선택하고 그 정점과 가장 인접한 간선을 뽑아서 진행(PQ 사용) + 정점 방문 관리 필요
 */
class IslandNode implements Comparable<IslandNode>{
	int node;
	long weight;
		
	public IslandNode(int node, long weight) {
		super();
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compareTo(IslandNode o) {
		return Long.compare(this.weight, o.weight);
	}
	
}
public class SWEA_1767_하나로_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 수
	static int N;					// 섬의 개수
	static int[] islandX;			// 섬의 X좌표
	static int[] islandY;			// 섬의 Y좌표
	static double E;				// 세율 실수
	static List<IslandNode>[] adjList;		// 간선 정보 저장ㄴ
	static boolean[] visited;		// 각 섬의 방문 여부를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 섬의 개수 입력받는다.
			N=Integer.parseInt(br.readLine().trim());
			islandX=new int[N];
			islandY=new int[N];
			visited=new boolean[N];
			
			// 섬의 X 좌표를 구한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandX[idx]=Integer.parseInt(st.nextToken());
			}
			
			// 섬의 Y 좌표를 구한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandY[idx]=Integer.parseInt(st.nextToken());
			}
			// 세율을 입력받는다.
			E=Double.parseDouble(br.readLine().trim());
			
			
			// 하나의 섬에서 모든 섬을 이을 수 있다. 간선의 정보를 저장한다.
			adjList=new ArrayList[N];
			for (int start=0;start<N;start++) {
				adjList[start]=new ArrayList<IslandNode>();
				
				for (int end=0;end<N;end++) {
					if (start==end)continue;
					adjList[start].add(new IslandNode(end,calDist(islandX[start],islandY[start],islandX[end],islandY[end])));
				}
			}
			
			// priorityQueue에 임의의 섬을 넣고
			// 그 섬에서 가장 가까운 섬을 이어나가는 흐름으로 진행된다.
			
			Queue<IslandNode> pq=new PriorityQueue<>();
			pq.offer(new IslandNode(0,0));
			
			long res=0;
			
			while (!pq.isEmpty()) {
				IslandNode curIsland=pq.poll();
				int curIslandNum=curIsland.node;
				
				// 이미 방문한 섬인 경우 무시
				if (visited[curIslandNum]) continue;
				
				// 방문한 섬이라고 표시
				visited[curIslandNum]=true;
				res+=curIsland.weight;
				
				// 인접한 섬들을 탐색한다.
				for (int idx=0;idx<adjList[curIslandNum].size();idx++) {
					IslandNode nextIsland=adjList[curIslandNum].get(idx);
					int nextIslandNum=nextIsland.node;
					
					if (visited[nextIslandNum]) continue;
					
					pq.offer(nextIsland);
				}
			}
			sb.append("#").append(test_case).append(" ").append(Math.round(E*res)).append("\n");
		}
		System.out.println(sb);
	}
	// 두 섬 사이의 거리를 구한다.
	public static long calDist(int x1,int y1,int x2,int y2) {
		long dist1=(long)Math.pow(x1-x2, 2);
		long dist2=(long)Math.pow(y1-y2, 2);
		return dist1+dist2;
	}
}
