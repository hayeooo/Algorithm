package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 1210: Ladder1
 * 방향 전환을 하며 X 표시에 도착하게 되는 사다리 찾기
 * 좌우 방향으로 이동 가능한 통로가 나타나면 방향 전환
 * 방향 전환 이후에 아래 방향으로만 이동하며, 바닥에 도착하면 멈추게 된다.
 * 
 * 
 * */
public class SWEA_1210_Ladder1 {
	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	static int[][] board;
	static boolean[][] visited;
	static int[][] dir= {{1,0},{0,1}};
	static boolean exist;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		board=new int[100][100];
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			visited=new boolean[100][100];
			
			for (int row=0;row<100;row++) {
				st=new StringTokenizer(br.readLine()," ");
				for (int col=0;col<100;col++) {
					board[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(board);
			for (int col=0;col<100;col++) {
				exist=false;
				if (board[0][col]==1) {
					dfs(0,col);
					System.out.println(exist);
					if (exist) {
						System.out.println(col);
						break;
					}
				}
			}
		}
	}
	
	public static void dfs(int curRow, int curCol) {
		if (curRow>99 || curCol>99) {
			return;
		}
		visited[curRow][curCol]=true;
		if (curRow==99 && board[curRow][curCol]==2) {
			exist=true;
			return;
		}
		for (int d=0;d<2;d++) {
			int nxtRow=curRow+dir[d][0];
			int nxtCol=curCol+dir[d][1];
			
			if (nxtCol>99 || nxtRow>99) continue;
			if (!visited[nxtRow][nxtCol] && board[nxtRow][nxtCol]==1) {
				dfs(nxtRow,nxtCol);
				visited[nxtRow][nxtCol]=false;
			}
		}
		return;
	}

	

}
