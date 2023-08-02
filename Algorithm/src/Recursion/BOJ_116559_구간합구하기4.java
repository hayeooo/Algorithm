package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 116559: ���� �� ���ϱ� 4
 * ��  M���� �ٿ� �Է����� �־��� i��° ������ j��° ������ ��
 * �־��� ��� N*M, (100,000 * 100,000)
 * -> �������� �����ϴ� �迭�� �����.
 * i��° ������ j��° �������� ���� (j���� ������)-(i-1���� ������)�̴�.
 * */
public class BOJ_116559_�����ձ��ϱ�4 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;				// ���� ����
	static int M;				// ���� ���ؾ� �ϴ� Ƚ��
	static int[] arr;			// ���� �����ϴ� �迭
	static int[] sumArr;		// �������� �����ϴ� �迭
	
	public static void main(String[] args) throws IOException{
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �Է�
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());		
		M=Integer.parseInt(st.nextToken());
		
		// �迭 �Է�
		st=new StringTokenizer(br.readLine());
		//arr=new int[N+1];
		sumArr=new int[N+1];
		
		int row_sum=0;	// ���� ���� ������ ����
		
		// �迭 ���ڸ� �����鼭 ������ ����Ͽ� �迭�� ����
		for (int idx=1;idx<=N;idx++) {
			//arr[idx]=Integer.parseInt(st.nextToken());
			row_sum+=Integer.parseInt(st.nextToken());;
			sumArr[idx]=row_sum;
		}
		
		// ��� ���ڿ��� ������ ��ü ����
		sb=new StringBuilder();
		for (int tc=0;tc<M;tc++) {
			st=new StringTokenizer(br.readLine());
			
			// �κ��� ���۰��� ������ �� �ε��� �Է�
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			// i~j���� ��
			int result=sumArr[j]-sumArr[i-1];
			// ��� ���ڿ� ���
			sb.append(result+"\n");
		}
		// ��� ���
		System.out.print(sb);
	}
}
