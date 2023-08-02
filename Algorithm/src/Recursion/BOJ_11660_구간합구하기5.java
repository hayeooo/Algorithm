package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ: 116600 ���� �� ���ϱ�
 * N*N���� ǥ���� (x1,y1) ���� (x2,y2)���� ���� ���ϴ� ���α׷�
 * row ���� ���� ���� ���
 * x1~x2������ �࿡�� {y2�� �迭 ��� �� -(y1-1)�� �迭 ��� ��}�� ��� ���ϸ� �ȴ�.
 * 
 * */
public class BOJ_11660_�����ձ��ϱ�5 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;						// ǥ�� ũ��
	static int M;						// ���� ���ؾ� �ϴ� Ƚ��
	static int[][] sumArr;				// �������� �����ϴ� �迭
	
	public static void main(String[] args) throws IOException{
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// �Է�
		N=Integer.parseInt(st.nextToken());	
		M=Integer.parseInt(st.nextToken());
		
		// �������� ������ �迭 ũ�� �ʱ�ȭ
		sumArr=new int[N+1][N+1];
		
		// �� �� �������� ���� ����
		for (int row=1;row<=N;row++) {
			int row_sum=0;
			st=new StringTokenizer(br.readLine().trim());		// �� �� �迭 �Է�
			for (int col=1;col<=N;col++) {
				row_sum+=Integer.parseInt(st.nextToken());	
				sumArr[row][col]=row_sum;				// ���� �� ����
			}
		}
		// ��� ���ڿ��� ���� ��ü 
		sb=new StringBuilder();
		for (int tc=0;tc<M;tc++) {
			st=new StringTokenizer(br.readLine().trim());		// x1,y1,x2,y2 �Է�
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			int result=0;
			for (int row=x1;row<=x2;row++) {				// x1~x2 ���� �� �迭����
				result+=(sumArr[row][y2]-sumArr[row][y1-1]);// {y2 �迭 �� - (y1-1) �迭 ��}�� ����
			}
			sb.append(result+"\n");							// ���� ���� �߰�
		}
		System.out.print(sb);								// �� ���
		
	}
}
