package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * SWEA 1954: ������ ����
 * ���� N�� �Է� �޾� Nũ���� ������ ���
 * 
 * 1. ������ �켱 ������ �� -> �� -> �� -> ��
 * 2. ��ġ�� (0,0), ������ �������� �ʱ�ȭ
 * 3. ���� ��ġ�� 0�� ��� �ش� ���ڸ� �ִ´�.
 * 4. ������ ��� ������ ��ȯ�Ѵ�.
 * 	4-1. �迭�� ������ ��� ���� ���
 * 	4-2. �̹� ���ڰ� ���ִ� ���
 * 5. ������ ��ȯ�ϸ� ���ڸ� �ִ´�.(�ݺ�)
 * */

public class SWEA_1954_�����̼��� {
	
	static BufferedReader br;
	static int T;					// �׽�Ʈ ���̽� ����
	static int[][] board;			// ������ ���ڸ� ������ �迭
	static StringBuilder sb;
	
	// ���� �迭: ��, ��, ��, ��
	static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		// �׽�Ʈ���̽� ���� �Է�
		T=Integer.parseInt(br.readLine().trim());
		
		// �׽�Ʈ���̽� ������ŭ �ݺ�
		for (int tc=1;tc<=T;tc++) {
			
			// ��� ���ڿ��� ������ ��ü ����
			sb=new StringBuilder();
			
			//�������� ũ�� �Է�
			int N=Integer.parseInt(br.readLine().trim());
			
			// ������ �迭 ũ�� �ʱ�ȭ
			board=new int[N][N];
			
			// ���� ��ġ�� ����
			int curRow=0;
			int curCol=0;
			int curDir=0;
			
			// ������ ���ڴ� 1���� N*N����
			for (int num=1;num<=N*N;num++) {
				
				// ���� ��ġ�� ���ڸ� �ִ´�.
				board[curRow][curCol]=num;
				
				// ���� ��ġ�� ã�´�.
				// ���� �������κ��� �ð� ��������
				for (int d=0;d<4;d++) {
					int nxtRow=curRow+dir[(curDir+d)%4][0];		// ���� ��
					int nxtCol=curCol+dir[(curDir+d)%4][1];		// ���� ��
					
					// �迭�� ������ ��� ���
					if (nxtRow<0 || nxtRow>=N || nxtCol<0 || nxtCol>=N) {
						continue;
					}
					// �̹� ���ڰ� ���ִ� ���
					if (board[nxtRow][nxtCol]!=0) {
						continue;
					}
					// ��� �ƴ� ���, ���ڸ� ���� �� �ִ�.
					// ���� ��ġ�� ������ ������Ʈ
					else {
						curRow=nxtRow;
						curCol=nxtCol;
						curDir=(curDir+d)%4;
						break;
					}
				}
			}
			// ������ ���ڸ� ��� ä���ٸ�
			// ����� �� ���ڿ� ä���
			sb.append("#"+tc+"\n");
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					sb.append(board[row][col]+" ");
				}
				sb.append("\n");
			}
			// ���ڿ� ���
			System.out.print(sb);
		}
	}
}
