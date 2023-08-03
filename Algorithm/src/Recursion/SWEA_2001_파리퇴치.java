package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 2001: �ĸ� ��ġ
 * 1. �迭�� ������ �Է� �޴´�.
 * 2. �迭�� ��ȸ�ϸ� M*M ���簢�� ũ�⿡ �ش��ϴ� �ĸ��� ���� ���Ѵ�. (�迭 ������ ����)
 * 3. ���� ����ϸ� �ִ��� ������Ʈ�Ѵ�.
 * */

public class SWEA_2001_�ĸ���ġ {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;						// �׽�Ʈ���̽� ����
	static int N;						// N*N�� �迭 ũ��
	static int M;						// M*M�� �ĸ�ä ũ��	
	static int[][] board;				// �ش� ������ �迭
	static int maxFlies = 0;			// ���� �ĸ��� �հ� �ִ�

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ ���̽� ���� �Է�
		T = Integer.parseInt(br.readLine());
		
		// �׽�Ʈ ���̽� �ݺ�
		for (int tc=1;tc<=T;tc++) {
			
			// ���� �ĸ� �հ� �ִ� �ʱ�ȭ
			maxFlies=0;
			
			// �Է�
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// ������ ũ�� �ʱ�ȭ
			board = new int[N][N];
			
			// ���� ���� �Է�
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// �ĸ� �ִ� �� ���ϱ�
			// ��ü ���� ��
			for (int row=0;row<=N-M;row++) {
				for (int col=0;col<=N-M;col++) {
					int tmpSum=0;
					// �ĸ�ä ������ �ش��ϴ� �ĸ��� ������ ���Ѵ�.
					for (int startRow=row;startRow<row+M;startRow++) {
						for (int startCol=col;startCol<col+M;startCol++) {
							tmpSum+=board[startRow][startCol];
						}
					}
					// ���� �ĸ�ä ������ �ش��ϴ� �ĸ� ��, �ִ� �� �� ū ���� �����Ѵ�.
					maxFlies=Math.max(tmpSum, maxFlies);
				}
			}
			// �׽�Ʈ���̽� �� ����� StringBuilder�� �����Ѵ�.
			sb.append("#"+tc+" "+maxFlies+"\n");
		}
		// ����� ����Ѵ�.
		System.out.println(sb);
	}

}
