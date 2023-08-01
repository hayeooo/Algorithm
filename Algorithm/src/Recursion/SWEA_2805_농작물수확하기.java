package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * SWEA 2805: 농작물 수확하기
 * 1. 테스트 케이스 수 입력 받기
 * 2. 농장의 크기 입력 받기
 * 3. 농작물의 가치 배열에 입력받기
 * 4. 재귀 함수를 사용하여 계산한 수익 출력
 *  4-1. 초기 시작: 배열[0][중간]
 *  4-2. start-1, end+1 로 범위를 늘려가며 수익 합계 계산
 *  4-3. start==0, end==N-1 이면 범위를 줄여가며 수익 합계 계산
 *  4-4. 배열의 마지막 row까지 도달했을 경우 재귀 함수 종료 
 * */

public class SWEA_2805_농작물수확하기 {
	static BufferedReader br;
	static int T;						// 테스트 케이스 수
	static int N;						// 농장의 크기
	static int[][] board;				// 농작물 가치를 담는 배열
	static int sum;						// 총 수익
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트 케이스 별 반복 실행
		for (int test_case=1;test_case<=T;test_case++) {
			// 배열에 입력 값만큼의 크기 할당
			N=Integer.parseInt(br.readLine().trim());
			board=new int[N][N];
			
			// 농작물의 가치 배열에 담기
			for (int row=0;row<N;row++) {
				char[] row_arr=br.readLine().trim().toCharArray();
				for (int col=0;col<N;col++) {
					board[row][col]=row_arr[col]-'0';
					
				}
			}
			// 수익 합계 초기화 및 시작점 계산
			sum=0;
			int start=(N-1)/2;
			// 재귀함수 호출로 수익 계산
			investigate(start,start,0,false);
			System.out.printf("#%d %d%n",test_case,sum);
		}
		
		
		
	}
	
	public static void investigate(int start,int end, int curDepth,boolean convert) {
		// 배열 row 크기만큼 반복했을 경우  종료
		if (curDepth==N) {
			return;
		}
		// 주어진 범위만큼의 농작물 가치 합계 구하기
		for (int veg=start;veg<=end;veg++) {
			sum+=board[curDepth][veg];
		}
		// 범위 설정 값의 전환이 필요할 때, convert: false->true
		if (start==0) {
			convert=true;
		}
		// convert의 값에 따라 계산 범위를 늘리거나 줄임.
		if (!convert) {
			investigate(start-1,end+1,curDepth+1,convert);
		}else {
			investigate(start+1,end-1,curDepth+1,convert);
		}
	}

}
