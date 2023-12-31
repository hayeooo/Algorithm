package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 15649: N과 M(1)
 * 자연수 N과 M이 주어졌을 때, 중복 없는 길이가 M인 수열을 구하는 프로그램 : 순열
 * 수열은 사전 순으로 증가하는 순서
 * 1. N과 M 입력 받기
 * 2. 1..N까지 순회하며(사전 순) M만큼의 길이를 가진 수열 구하기(재귀함수)
 * 3. 배열을 사용하여 해당 숫자의 중복 체크하기
 * */

public class BOJ_15649_N과M1 {
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] visited;					// 중복 체크할 배열
	static int[] res;							// 결과 값을 저장할 배열
	static StringBuffer sb;						
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine()," ");
		sb=new StringBuffer();
		
		// 입력
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 입력 값에 따른 visited, res 배열 크기 설정
		visited=new boolean[N+1];
		res=new int[M];
		
		// 순열 구하기
		perm(0);
		
	}
	
	public static void perm(int curDepth) {
		
		// 현재 자리수가 M이라면 결과값을 출력하고 재귀 종료
		if (curDepth==M) {
			printArr();
			return;
		}
		// 1..N까지 반복(사전 순)하며 순열 찾기
		for (int num=1;num<=N;num++) {
			if (visited[num]) continue;	// 이미 방문한 숫자는 생략
			res[curDepth]=num;			// 현재 숫자를 결과 배열에 넣기
			visited[num]=true;			// 방문 표시
			perm(curDepth+1);			// 다음 자릿수 숫자 찾기
			visited[num]=false;			// 현재 자릿수 이외의 다른 수를 넣어보기 위해 false -> true
		}
	}
	// 결과 배열을 출력하는 함수
	public static void printArr() {
		sb.setLength(0);
		for (int idx=0;idx<M;idx++) {
			sb.append(res[idx]+" ");
		}
		System.out.println(sb);
	}
	
	
}
