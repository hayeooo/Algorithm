package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * SWEA 1251: 하나로
 * 총 환경 부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통 시스템 설계
 * 두 섬을 모두 연결할 수 있는 가상의 간선이 존재하지만 최소로 지불해야 한다.
 * 한 정점에서 출발하여 가장 가까운 간선을 선택하는 식으로 MST를 구성해나간다.(Prim Algo)
 * 
 */
public class SWEA_1251_하나로_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 수
	static int N;					// 섬의 개수
	static int[] islandX;			// 각 섬들의 정수인 X좌표
	static int[] islandY;			// 각 섬들의 정수인 Y좌표
	static int E;					// 해저터널 건설의 환경 부담 세율 실수
	
	static boolean[] visited;		// 해당 섬들을 연결했는지 여부를 저장하는 배열
	static double totalCost;			// 모든 섬들을 잇는 최소 환경 부담금
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 섬의 개수를 입력받는다.
			N=Integer.parseInt(br.readLine().trim());
			islandX=new int[N];
			islandY=new int[N];
			
			// 섬의 X좌표들을 저장한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandX[idx]=Integer.parseInt(st.nextToken());
			}
			
			// 섬의 Y좌표들을 저장한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandY[idx]=Integer.parseInt(st.nextToken());
			}
			
			// 세율을 입력받는다.
			E=Integer.parseInt(br.readLine().trim());
			
			totalCost=0;
			connectIsland(0);
			sb.append("#").append(test_case).append(" ").append(totalCost).append("\n");
		}
		System.out.println(sb);

	}
	// 섬들을 잇는다.
	public static void connectIsland(int start) {
		visited=new boolean[N];
		Deque<Integer> que=new ArrayDeque<>();
		
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curIsland=que.poll();
			
			int minIdx=-1;
			double minCost=Integer.MAX_VALUE;
			
			for (int idx=0;idx<N;idx++) {
				// 이미 연결한 섬이면 무시
				if (visited[idx]) continue;
				
				double tmpCost=getCost(islandX[curIsland],islandY[curIsland],islandX[idx],islandY[idx]);
				if (tmpCost<minCost) {
					minIdx=idx;
					minCost=tmpCost;
				}
			}
			
			// 선택할 섬이 없을 경우
			if (minIdx==-1) break;
			
			// 최소 비용으로 연결할 수 있는 섬 선택
			que.offer(minIdx);
			visited[minIdx]=true;
			totalCost+=minCost;
		}
	}
	
	// 세율을 구한다.
	public static double getCost(int x1,int y1, int x2,int y2) {
		return E*((x1-x2)^2+(y1-y2)^2);
	}

}
