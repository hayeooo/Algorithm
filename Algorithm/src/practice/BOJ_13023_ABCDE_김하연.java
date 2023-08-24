package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 13023: ABCDE
 * a-b-c-d-e인 친구관계가 있는지 확인하는 문제
 * 친구 관계가 4인지 확인
 * 친구관계를 모두 탐색해본다.
 */
public class BOJ_13023_ABCDE_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					 // 사람의 수
	static int M;					// 친구 관계의 수
	static boolean[] visited;		// 탐색 여부를 표시하는 배열
	static boolean flag;			// 친구 관계가 4가 될 수 있는지 여부를 저장하는 변수
	static List<Integer>[] graph;	// 친구 관계를 저장하는 리스트
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M을 입력받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 친구 관계를 저장할 리스트 배열
		graph=new ArrayList[N];
		
		for (int idx=0;idx<N;idx++) {
			graph[idx]=new ArrayList<>();
		}
		
		// M개의 줄에서 친구 관계를 입력 받으며 정보를 저장한다.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			int friend1=Integer.parseInt(st.nextToken());
			int friend2=Integer.parseInt(st.nextToken());
			
			graph[friend1].add(friend2);
			graph[friend2].add(friend1);
		}
		// 한 사람씩 친구 관계를 탐색한다.
		for (int person=0;person<N;person++) {
			visited=new boolean[N];
			dfs(person,0);
			// 이미 친구 관계가 4인 관계가 존재한다면 반복을 종료한다.
			if (flag) break;
		}
		System.out.println(flag?1:0);
	}
	// 현재 사람과 관계 수를 매개변수로 받는다.
	public static void dfs(int p,int cnt) {
		if (cnt==4) {
			flag=true;
			return;
		}
		// 현재 탐색하는 사람 방문 처리
		visited[p]=true;
		// 현재 방문한 상태로 다음 친구 관계 탐색
		for (int nextP:graph[p]) {
			if (visited[nextP]) continue;
			dfs(nextP,cnt+1);
		}
		// 연결된 다른 친구 관계도 탐색해본다.
		visited[p]=false;	// 백트래킹
		
	}

}
