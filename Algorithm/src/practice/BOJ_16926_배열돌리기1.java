package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * BOJ 16926: 배열 돌리기 1
 * 크기가 N*M인 배열이 있을 때, 반시계 방향으로 돌린다.
 * 배열에 같은 사각형 선에 위치한 숫자들끼리 반시계방향으로 회전한다.
 * 
 * 회전할 사각형의 위치(row,col)와 사각형의 크기를 기준으로 회전하는 메소드를 구현한다.
 * */
public class BOJ_16926_배열돌리기1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;				// 배열의 세로 크기
	static int M;				// 배열의 가로 크기
	static int R;				// 회전 수
	static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};	// 동, 남, 서, 북
	static int[][] arr;			// 배열 정보 
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		
		// N, M, R 입력
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		// 배열 정보 입력
		arr=new int[N][M];
		
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				arr[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 회전할 사각형 위치와 가로, 세로 길이를 구한다.
		int startRow=0;
		int startCol=0;
		int height=N;
		int width=M;
		
		// 사각형 변의 길이가 1보다 클 때 회전시킨다.
		while (height>1 && width>1) {
			for (int r=0;r<R;r++) {
				rotate(startRow,startCol,width,height);
			}
			// 다음 회전 시킬 숫자가 들어있는 사각형을 구한다.
			startRow+=1;
			startCol+=1;
			height-=2;
			width-=2;
		}
		// 회전을 완료한 배열을 출력한다.
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				sb.append(arr[row][col]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	// startRow, startCol에 위치하고 가로 width, 세로 height 길이를 가진
	// 사각형 내 숫자들을 회전하는 메소드
	public static void rotate(int startRow, int startCol, int width, int height) {
		
		int curRow=startRow;			// 시작 행
		int curCol=startCol;			// 시작 열
		int tmp=arr[curRow][curCol];	// 회전으로 다른 값으로 바뀌기 전에 임의의 공간에 시작 값을 담아둔다. 
		int nextRow;					// 다음 위치
		int nextCol;
		int cd=-1;				// 현재 방향
		while (true) {
			// 시작 값이 사각형의 모서리 부분에 위치할 때, 방향을 바꾼다.
			if ((curRow==startRow && curCol==startCol) || (curRow==startRow+height-1 && curCol==startCol) || (curRow==startRow && curCol==startCol+width-1) || (curRow==startRow+height-1 && curCol==startCol+width-1)) {
				cd=(cd+1)%4;
			}
			// 현재 칸에 들어갈 다음 칸의 위치
			nextRow=curRow+dir[cd][0];
			nextCol=curCol+dir[cd][1];
			
			// 다음 칸이 시작 지점으로 돌아왔다면
			if (nextRow==startRow && nextCol==startCol) {
				// 현재 칸에 임시로 넣은 시작 값을 대입한다.
				arr[curRow][curCol]=tmp;
				break;
			}
			// 다음 칸에 있는 숫자를 현재 칸으로 넣는다.
			arr[curRow][curCol]=arr[nextRow][nextCol];
			// 현재 칸의 위치를 다음 칸으로 수정한다.
			curRow=nextRow;
			curCol=nextCol;
			
		}
	}

}
