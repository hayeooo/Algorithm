package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * SWEA 1954: 달팽이 숫자
 * 정수 N을 입력 받아 N크기의 달팽이 출력
 * 
 * 1. 방향의 우선 순위는 동 -> 남 -> 서 -> 북
 * 2. 위치는 (0,0), 방향은 동쪽으로 초기화
 * 3. 현재 위치가 0일 경우 해당 숫자를 넣는다.
 * 4. 다음의 경우 방향을 전환한다.
 * 	4-1. 배열의 범위를 벗어난 곳인 경우
 * 	4-2. 이미 숫자가 들어가있는 경우
 * 5. 방향을 전환하며 숫자를 넣는다.(반복)
 * */

public class SWEA_1954_달팽이숫자 {
	
	static BufferedReader br;
	static int T;					// 테스트 케이스 개수
	static int[][] board;			// 달팽이 숫자를 저장할 배열
	static StringBuilder sb;
	
	// 방향 배열: 동, 남, 서, 북
	static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		// 테스트케이스 개수 입력
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스 개수만큼 반복
		for (int tc=1;tc<=T;tc++) {
			
			// 결과 문자열을 저장할 객체 생성
			sb=new StringBuilder();
			
			//달팽이의 크기 입력
			int N=Integer.parseInt(br.readLine().trim());
			
			// 달팽이 배열 크기 초기화
			board=new int[N][N];
			
			// 현재 위치와 방향
			int curRow=0;
			int curCol=0;
			int curDir=0;
			
			// 달팽이 숫자는 1부터 N*N까지
			for (int num=1;num<=N*N;num++) {
				
				// 현재 위치에 숫자를 넣는다.
				board[curRow][curCol]=num;
				
				// 다음 위치를 찾는다.
				// 현재 방향으로부터 시계 방향으로
				for (int d=0;d<4;d++) {
					int nxtRow=curRow+dir[(curDir+d)%4][0];		// 다음 행
					int nxtCol=curCol+dir[(curDir+d)%4][1];		// 다음 열
					
					// 배열의 범위를 벗어난 경우
					if (nxtRow<0 || nxtRow>=N || nxtCol<0 || nxtCol>=N) {
						continue;
					}
					// 이미 숫자가 들어가있는 경우
					if (board[nxtRow][nxtCol]!=0) {
						continue;
					}
					// 모두 아닌 경우, 숫자를 넣을 수 있다.
					// 현재 위치와 방향을 업데이트
					else {
						curRow=nxtRow;
						curCol=nxtCol;
						curDir=(curDir+d)%4;
						break;
					}
				}
			}
			// 달팽이 숫자를 모두 채웠다면
			// 결과를 낼 문자열 채우기
			sb.append("#"+tc+"\n");
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					sb.append(board[row][col]+" ");
				}
				sb.append("\n");
			}
			// 문자열 출력
			System.out.print(sb);
		}
	}
}
