package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 2001: 파리 퇴치
 * 1. 배열의 정보를 입력 받는다.
 * 2. 배열을 순회하며 M*M 정사각형 크기에 해당하는 파리의 합을 구한다. (배열 범위에 주의)
 * 3. 합을 계산하며 최댓값을 업데이트한다.
 * */

public class SWEA_2001_파리퇴치 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;						// 테스트케이스 개수
	static int N;						// N*N의 배열 크기
	static int M;						// M*M의 파리채 크기	
	static int[][] board;				// 해당 영역의 배열
	static int maxFlies = 0;			// 죽은 파리의 합계 최댓값

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트 케이스 개수 입력
		T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 반복
		for (int tc=1;tc<=T;tc++) {
			
			// 죽은 파리 합계 최댓값 초기화
			maxFlies=0;
			
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 영역의 크기 초기화
			board = new int[N][N];
			
			// 영역 정보 입력
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 파리 최대 값 구하기
			// 전체 영역 중
			for (int row=0;row<=N-M;row++) {
				for (int col=0;col<=N-M;col++) {
					int tmpSum=0;
					// 파리채 영역에 해당하는 파리의 개수를 더한다.
					for (int startRow=row;startRow<row+M;startRow++) {
						for (int startCol=col;startCol<col+M;startCol++) {
							tmpSum+=board[startRow][startCol];
						}
					}
					// 현재 파리채 영역에 해당하는 파리 수, 최댓값 수 중 큰 수를 저장한다.
					maxFlies=Math.max(tmpSum, maxFlies);
				}
			}
			// 테스트케이스 별 결과를 StringBuilder에 저장한다.
			sb.append("#"+tc+" "+maxFlies+"\n");
		}
		// 결과를 출력한다.
		System.out.println(sb);
	}

}
