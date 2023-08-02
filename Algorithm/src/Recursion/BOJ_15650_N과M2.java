package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 15650: N과 M(2)
 * 자연수 N과 M이 주어졌을 때, 
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 수열은 오름차순
 * 
 * */

public class BOJ_15650_N과M2 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
		
	static int N;				// 1..N까지 자연수
	static int M;				// 수열의 길이
	static int[] res;			// 수열을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 수열을 저장할 배열의 크기 초기화
		res=new int[M];
		
		// 출력할 문자열을 저장할 객체 할당
		sb=new StringBuilder();
		// 조합 구하기
		comb(0,1);
		// 결과 출력
		System.out.println(sb);
		
	}
	
	public static void comb(int cnt, int start) {	// cnt: 현재 수열의 길이, start: start~N까지 숫자 시작 범위
		if (cnt==M) {							// 길이가 M인 수열인 경우, 
			for (int idx=0;idx<M;idx++) {		// 배열의 값 출력 및 재귀 종료
				sb.append(res[idx]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int num=start;num<=N;num++) {	// start ~ N까지 중 한 가지 숫자 정하기
			res[cnt]=num;					// 조합 결과 배열에 넣기
			comb(cnt+1,num+1);				// 다음 자리 수 숫자 찾기(중복X)
		}
	}
}
