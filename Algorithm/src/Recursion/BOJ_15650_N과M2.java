package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 15650: N�� M(2)
 * �ڿ��� N�� M�� �־����� ��, 
 * 1���� N���� �ڿ��� �߿��� �ߺ� ���� M���� �� ����
 * ������ ��������
 * 
 * */

public class BOJ_15650_N��M2 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
		
	static int N;				// 1..N���� �ڿ���
	static int M;				// ������ ����
	static int[] res;			// ������ ������ �迭
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �Է�
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// ������ ������ �迭�� ũ�� �ʱ�ȭ
		res=new int[M];
		
		// ����� ���ڿ��� ������ ��ü �Ҵ�
		sb=new StringBuilder();
		// ���� ���ϱ�
		comb(0,1);
		// ��� ���
		System.out.println(sb);
		
	}
	
	public static void comb(int cnt, int start) {	// cnt: ���� ������ ����, start: start~N���� ���� ���� ����
		if (cnt==M) {							// ���̰� M�� ������ ���, 
			for (int idx=0;idx<M;idx++) {		// �迭�� �� ��� �� ��� ����
				sb.append(res[idx]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int num=start;num<=N;num++) {	// start ~ N���� �� �� ���� ���� ���ϱ�
			res[cnt]=num;					// ���� ��� �迭�� �ֱ�
			comb(cnt+1,num+1);				// ���� �ڸ� �� ���� ã��(�ߺ�X)
		}
	}
}
