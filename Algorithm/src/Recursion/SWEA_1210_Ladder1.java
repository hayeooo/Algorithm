package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 1210: Ladder1
 * ���� ��ȯ�� �ϸ� X ǥ�ÿ� �����ϰ� �Ǵ� ��ٸ� ã��
 * �¿� �������� �̵� ������ ��ΰ� ��Ÿ���� ���� ��ȯ
 * ���� ��ȯ ���Ŀ� �Ʒ� �������θ� �̵��ϸ�, �ٴڿ� �����ϸ� ���߰� �ȴ�.
 * 
 * 
 * */
public class SWEA_1210_Ladder1 {
	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	static int[][] board;
	static boolean exist;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		board=new int[100][100];
		
		
		for (int tc=1;tc<=10;tc++) {
			T=Integer.parseInt(br.readLine());
			
			for (int row=0;row<100;row++) {
				st=new StringTokenizer(br.readLine()," ");
				for (int col=0;col<100;col++) {
					board[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int col=0;col<100;col++) {
				if (board[0][col]==2) {
					dfs(0,col);
					
				}
			}
		}
	}
	
	public static void dfs(int curRow, int curCol) {
		if (curRow==99) {
			
			return;
		}
		// ���� Ž��
		if (curCol-1>=0 && board[curRow][curCol-1]==1) {
			for (int idx=curCol-1;idx>=0;idx++) {
				if (board[curRow+1][idx]==1) {
					dfs(curRow+1,idx);
					break;
				}
			}
		}
		// ������ Ž��
		else if (curCol+1<=99 && board[curRow][curCol+1]==1) {
			for (int idx=curCol+1;idx<=99;idx++) {
				if (board[curRow+1][idx]==1) {
					dfs(curRow+1,idx);
					break;
				}
			}
		}
		
		dfs(curRow+1,curCol);
	}

	

}
