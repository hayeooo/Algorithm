package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * BOJ 16926: �迭 ������ 1
 * ũ�Ⱑ N*M�� �迭�� ���� ��, �ݽð� �������� ������.
 * �迭�� ���� �簢�� ���� ��ġ�� ���ڵ鳢�� �ݽð�������� ȸ���Ѵ�.
 * 
 * ȸ���� �簢���� ��ġ(row,col)�� �簢���� ũ�⸦ �������� ȸ���ϴ� �޼ҵ带 �����Ѵ�.
 * */
public class BOJ_16926_�迭������1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;				// �迭�� ���� ũ��
	static int M;				// �迭�� ���� ũ��
	static int R;				// ȸ�� ��
	static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};	// ��, ��, ��, ��
	static int[][] arr;			// �迭 ���� 
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		
		// N, M, R �Է�
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		// �迭 ���� �Է�
		arr=new int[N][M];
		
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				arr[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// ȸ���� �簢�� ��ġ�� ����, ���� ���̸� ���Ѵ�.
		int startRow=0;
		int startCol=0;
		int height=N;
		int width=M;
		
		// �簢�� ���� ���̰� 1���� Ŭ �� ȸ����Ų��.
		while (height>1 && width>1) {
			int nextHeight=Math.max(height-2, 0);
			int nextWidth=Math.max(width-2, 0);
			int rotateCnt=R%((height*width)-(nextHeight*nextWidth));
			for (int r=0;r<rotateCnt;r++) {
				rotate(startRow,startCol,width,height);
			}
			// ���� ȸ�� ��ų ���ڰ� ����ִ� �簢���� ���Ѵ�.
			startRow+=1;
			startCol+=1;
			height-=2;
			width-=2;
		}
		// ȸ���� �Ϸ��� �迭�� ����Ѵ�.
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				sb.append(arr[row][col]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	// startRow, startCol�� ��ġ�ϰ� ���� width, ���� height ���̸� ����
	// �簢�� �� ���ڵ��� ȸ���ϴ� �޼ҵ�
	public static void rotate(int startRow, int startCol, int width, int height) {
		
		int curRow=startRow;			// ���� ��
		int curCol=startCol;			// ���� ��
		int tmp=arr[curRow][curCol];	// ȸ������ �ٸ� ������ �ٲ�� ���� ������ ������ ���� ���� ��Ƶд�. 
		int nextRow;					// ���� ��ġ
		int nextCol;
		int cd=-1;				// ���� ����
		while (true) {
			// ���� ���� �簢���� �𼭸� �κп� ��ġ�� ��, ������ �ٲ۴�.
			if ((curRow==startRow && curCol==startCol) || (curRow==startRow+height-1 && curCol==startCol) || (curRow==startRow && curCol==startCol+width-1) || (curRow==startRow+height-1 && curCol==startCol+width-1)) {
				cd=(cd+1)%4;
			}
			// ���� ĭ�� �� ���� ĭ�� ��ġ
			nextRow=curRow+dir[cd][0];
			nextCol=curCol+dir[cd][1];
			
			// ���� ĭ�� ���� �������� ���ƿԴٸ�
			if (nextRow==startRow && nextCol==startCol) {
				// ���� ĭ�� �ӽ÷� ���� ���� ���� �����Ѵ�.
				arr[curRow][curCol]=tmp;
				break;
			}
			// ���� ĭ�� �ִ� ���ڸ� ���� ĭ���� �ִ´�.
			arr[curRow][curCol]=arr[nextRow][nextCol];
			// ���� ĭ�� ��ġ�� ���� ĭ���� �����Ѵ�.
			curRow=nextRow;
			curCol=nextCol;
			
		}
	}

}
