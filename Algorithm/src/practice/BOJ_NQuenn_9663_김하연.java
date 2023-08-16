package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_NQuenn_9663_���Ͽ� {
	static BufferedReader br;
	static int N;			// N*N�� ü����
	
	static int[] col;		// �� �࿡ ���� �� ��° ���� ���Ҵ��� �����ϴ� �迭
	static int cnt;			// ���� ���� ����� ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// N�� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// �迭�� ũ�⸦ �ʱ�ȭ�Ѵ�.
		col=new int[N];
		
		// 0��° ����� �����Ѵ�.
		dfs(0);
		// ���� ���� ����� ���� ����Ѵ�.
		System.out.println(cnt);
	}
	
	// �� �࿡ ���� ���� �޼ҵ�
	public static void dfs(int r) {
		
		// ��� �࿡ ���� ������ ���
		if (r>=N) {
			cnt+=1;		// ���� ���� ����� ���� 1 ���ϰ�
			return;		// ��͸� �����Ѵ�.
		}
		// 0������ N-1������ ���� ���� �� �ִ�.
		for (int idx=0;idx<N;idx++) {
			// r�� idx���� ���� ���´�.
			col[r]=idx;
			// ���� ��, ���� �밢���� ������ ��� �ٸ� ���� Ž���Ѵ�.
			if (!check(r)) continue;
			// ���� �࿡ ���� �� �ִ� ���, ���� �࿡ ���� ���� ��ġ�� Ž���Ѵ�.
			dfs(r+1);
		}
	}
	// ���� ��, ���� �밢���� ��ġ�� ���� ���� ��� false, ���� ��� true
	public static boolean check(int row) {	// row: ������� ���� ��ġ�� ��
		// ���� ���� ���
		for (int idx=0;idx<row;idx++) {
			// ���� ���� ���, ���� �밢���� ���
			if (col[idx]==col[row] || row-idx==Math.abs(col[row]-col[idx])) {
				return false;
			}
		}
		return true;
	}
	

}
