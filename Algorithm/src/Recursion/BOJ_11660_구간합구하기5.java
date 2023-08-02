package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ: 116600 구간 합 구하기
 * N*N개의 표에서 (x1,y1) 부터 (x2,y2)까지 합을 구하는 프로그램
 * row 별로 누적 합을 계산
 * x1~x2까지의 행에서 {y2의 배열 요소 값 -(y1-1)의 배열 요소 값}을 모두 더하면 된다.
 * 
 * */
public class BOJ_11660_구간합구하기5 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;						// 표의 크기
	static int M;						// 합을 구해야 하는 횟수
	static int[][] sumArr;				// 누적합을 저장하는 배열
	
	public static void main(String[] args) throws IOException{
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 입력
		N=Integer.parseInt(st.nextToken());	
		M=Integer.parseInt(st.nextToken());
		
		// 누적합을 저장할 배열 크기 초기화
		sumArr=new int[N+1][N+1];
		
		// 행 별 누적합을 구해 저장
		for (int row=1;row<=N;row++) {
			int row_sum=0;
			st=new StringTokenizer(br.readLine().trim());		// 한 행 배열 입력
			for (int col=1;col<=N;col++) {
				row_sum+=Integer.parseInt(st.nextToken());	
				sumArr[row][col]=row_sum;				// 누적 합 저장
			}
		}
		// 결과 문자열을 담을 객체 
		sb=new StringBuilder();
		for (int tc=0;tc<M;tc++) {
			st=new StringTokenizer(br.readLine().trim());		// x1,y1,x2,y2 입력
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			int result=0;
			for (int row=x1;row<=x2;row++) {				// x1~x2 행의 각 배열에서
				result+=(sumArr[row][y2]-sumArr[row][y1-1]);// {y2 배열 값 - (y1-1) 배열 값}을 저장
			}
			sb.append(result+"\n");							// 개행 문자 추가
		}
		System.out.print(sb);								// 값 출력
		
	}
}
