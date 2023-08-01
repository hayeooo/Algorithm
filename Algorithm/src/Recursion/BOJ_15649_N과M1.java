package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 15649: N�� M(1)
 * �ڿ��� N�� M�� �־����� ��, �ߺ� ���� ���̰� M�� ������ ���ϴ� ���α׷� : ����
 * ������ ���� ������ �����ϴ� ����
 * 1. N�� M �Է� �ޱ�
 * 2. 1..N���� ��ȸ�ϸ�(���� ��) M��ŭ�� ���̸� ���� ���� ���ϱ�(����Լ�)
 * 3. �迭�� ����Ͽ� �ش� ������ �ߺ� üũ�ϱ�
 * */

public class BOJ_15649_N��M1 {
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] visited;					// �ߺ� üũ�� �迭
	static int[] res;							// ��� ���� ������ �迭
	static StringBuffer sb;						
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine()," ");
		sb=new StringBuffer();
		
		// �Է�
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// �Է� ���� ���� visited, res �迭 ũ�� ����
		visited=new boolean[N+1];
		res=new int[M];
		
		// ���� ���ϱ�
		perm(0);
		
	}
	
	public static void perm(int curDepth) {
		
		// ���� �ڸ����� M�̶�� ������� ����ϰ� ��� ����
		if (curDepth==M) {
			printArr();
			return;
		}
		// 1..N���� �ݺ�(���� ��)�ϸ� ���� ã��
		for (int num=1;num<=N;num++) {
			if (visited[num]) continue;	// �̹� �湮�� ���ڴ� ����
			res[curDepth]=num;			// ���� ���ڸ� ��� �迭�� �ֱ�
			visited[num]=true;			// �湮 ǥ��
			perm(curDepth+1);			// ���� �ڸ��� ���� ã��
			visited[num]=false;			// ���� �ڸ��� �̿��� �ٸ� ���� �־�� ���� false -> true
		}
	}
	// ��� �迭�� ����ϴ� �Լ�
	public static void printArr() {
		sb.setLength(0);
		for (int idx=0;idx<M;idx++) {
			sb.append(res[idx]+" ");
		}
		System.out.println(sb);
	}
	
	
}
