package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 17471: 게리멘더링
 * 1. bfs로 그래프의 개수를 구한다.
 * 	1-1. 1개의 그래프가 나왔다면 두 개의 그래프로 쪼개야 한다.(부분집합을 활용한다.)
 * 	1-2. 2개의 그래프가 나왔다면 그 값의 차이가 최솟값
 * 	1-3. 3개의 그래프가 나왔다면 두 개의 선거구로 나눌 수 없다.(-1)
 * 
 */
public class BOJ_17471_게리맨더링_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	
	static int N;						// 구역의 개수
	static int[] population;				// 인구 수를 저장하는 배열
	static List<Integer>[] adjList;		// 각 구역에 인접한 구역을 저장하는 리스트
	
	static boolean[] isRegionA;			// A 구역에 들어갈 구역
	static boolean[] visited;			// bfs, dfs에 사용할 방문 표시 배열
	static int minDiff=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 구역의 개수 입력받는다.
		N=Integer.parseInt(br.readLine().trim());
		population=new int[N+1];
		adjList=new ArrayList[N+1];
		
		// 인구 수를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		for (int region=1;region<=N;region++) {
			population[region]=Integer.parseInt(st.nextToken());
		}
		
		// 각 구역의 인접 구역 정보를 저장한다.
		for (int idx=0;idx<=N;idx++) {
			adjList[idx]=new ArrayList<>();
		}
		for (int region=1;region<=N;region++) {
			st=new StringTokenizer(br.readLine().trim());
			int adjCnt=Integer.parseInt(st.nextToken());
			
			// 인접한 지역이 있는 경우 인접리스트에 저장한다.
			for (int idx=0;idx<adjCnt;idx++) {
				adjList[region].add(Integer.parseInt(st.nextToken()));
			}
		}
		// 두 선거구로 나눌 수 없는 경우
		// 입력 받은 정보를 바탕으로 그래프의 개수가 3개 이상일 때 두 선거구로 나눌 수 없다.
		int graphCnt=0;
		visited=new boolean[N+1];
		for (int region=1;region<=N;region++) {
			if (visited[region]) continue;
			bfs(region);
			graphCnt+=1;
		}
		// 그래프가 3개인 경우, 두 개의 선거구로 나눌 수 없다.
		if (graphCnt>=3) {
			System.out.println(-1);
		}
		// 그래프가 2개인 경우, 각각을 선거구로 지정하는 경우만 존재한다.
		else if (graphCnt==2) {
			visited=new boolean[N+1];
			bfs(1);
			int regionA=0;
			int regionB=0;
			for (int region=1;region<=N;region++) {
				if (visited[region]) regionA+=population[region];
				else {
					regionB+=population[region];
				}
			}
			System.out.println(Math.abs(regionA-regionB));
		}
		// 그래프가 1개인 경우, 2개의 그래프로 쪼개야한다.
		else {
			// 지역의 부분집합을 구한다.(nC1, nC2, nC3..)
			visited=new boolean[N+1];
			isRegionA=new boolean[N+1];
			getSubset(1,0);
			System.out.println(minDiff);
		}
	}
	
	// 그래프가 연결되어있는지 확인
	public static void bfs(int start) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curRegion=que.poll();
			
			for (int adjRegion:adjList[curRegion]) {
				if (visited[adjRegion])continue;
				que.offer(adjRegion);
				visited[adjRegion]=true;
			}
		}
	}
	// 나눈 구역 내 지역들이 서로 연결되어 있는지 검사
	public static boolean isConnect(int start) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curRegion=que.poll();
			
			// 인접한 지역
			for (int adjRegion:adjList[curRegion]) {
				// 이미 방문한 경우 무시
				if (visited[adjRegion]) continue;
				// 다른 구역일 경우 무시
				if (isRegionA[start]!=isRegionA[curRegion]) continue;
				que.offer(adjRegion);
				visited[adjRegion]=true;
			}
		}
		// 가능한 모든 지역을 탐색한 후
		// start와 같은 구역 중 방문되지 않은 구역이 있다면 return false
		boolean isValid=true;
		for (int region=1;region<=N;region++) {
			// 같은 구역인 경우
			// 방문이 안된 곳이라면 연결할 수 없다.
			if (isRegionA[region]==isRegionA[start] && !visited[region]) {
				isValid=false;
				break;
			}
		}
		return isValid;
	}
	
	// 부분집합 구하기
	public static void getSubset(int cnt, int chosen) {
		if (cnt==N+1) {
			if (chosen==0 || chosen==N) return;
			// 나눠진 A와 B구역 연결성 체크
			boolean connect=true;
			visited=new boolean[N+1];
			for (int region=1;region<=N;region++) {
				// A 구역인 경우
				if (isRegionA[region]) {
					connect=isConnect(region);
					break;
				}
			}
			// A구역이 서로 연결되었다면
			// B구역 연결성 검사
			if (connect) {
				visited=new boolean[N+1];
				for (int region=1;region<=N;region++) {
					// B 구역인 경우
					if (!isRegionA[region]) {
						connect=isConnect(region);
						break;
					}
				}
			}
			// 2 개의 구역 모두 연결 가능한 경우
			if (connect) {
				int regionA=0;
				int regionB=0;
				// 각 구역의 인구 합을 구한다.
				for (int idx=1;idx<=N;idx++) {
					if (isRegionA[idx]) regionA+=population[idx];
					else regionB+=population[idx];
				}
				// 최소 인구 차이를 구한다.
				minDiff=Math.min(minDiff, Math.abs(regionA-regionB));
			}
			return;
		}
		// A 지역에 들어갈 수 있는 지역을 고른다.
		isRegionA[cnt]=true;
		getSubset(cnt+1,chosen+1);
		isRegionA[cnt]=false;
		getSubset(cnt+1,chosen);
		
	}

}
	
	
