package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 116559: 구간 합 구하기 4
 * 총  M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합
 * 최악의 경우 N*M, (100,000 * 100,000)
 * -> 누적합을 저장하는 배열을 만든다.
 * i번째 수부터 j번째 수까지의 합은 (j까지 누적합)-(i-1까지 누적합)이다.
 * */
public class BOJ_116559_구간합구하기4 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;				// 수의 개수
	static int M;				// 합을 구해야 하는 횟수
	static int[] arr;			// 수를 저장하는 배열
	static int[] sumArr;		// 누적합을 저장하는 배열
	
	public static void main(String[] args) throws IOException{
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());		
		M=Integer.parseInt(st.nextToken());
		
		// 배열 입력
		st=new StringTokenizer(br.readLine());
		//arr=new int[N+1];
		sumArr=new int[N+1];
		
		int row_sum=0;	// 누적 합을 저장할 변수
		
		// 배열 숫자를 읽으면서 누적합 계산하여 배열에 저장
		for (int idx=1;idx<=N;idx++) {
			//arr[idx]=Integer.parseInt(st.nextToken());
			row_sum+=Integer.parseInt(st.nextToken());;
			sumArr[idx]=row_sum;
		}
		
		// 결과 문자열을 저장할 객체 생성
		sb=new StringBuilder();
		for (int tc=0;tc<M;tc++) {
			st=new StringTokenizer(br.readLine());
			
			// 부분합 시작값과 마지막 값 인덱스 입력
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			// i~j까지 합
			int result=sumArr[j]-sumArr[i-1];
			// 결과 문자열 담기
			sb.append(result+"\n");
		}
		// 결과 출력
		System.out.print(sb);
	}
}
