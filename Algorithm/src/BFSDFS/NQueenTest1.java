package BFSDFS;

import java.util.Scanner;

// ���� �࿡ ���� ���� �ʴ� ����
// ������ ���� ����ȣ�� ����ϴ� ����

public class NQueenTest1 {
	
	static int N, col[], ans;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		col=new int[N+1];	// 1������ ���
		ans=0;				// ������ ����� ��
		
		setQueen(1);
		System.out.println(ans);
		
	}
	// �ش� ���� ���� �࿡ ������ ��� ���� ���ƺ���
	public static void setQueen(int row) {	
		// ����ġ��
		if (!isAvailable(row-1)) {	// �������� ������ �� ���� Ȯ���ϰ� pruning
			return;
		}
		// ��������
		if (row>N) {
			ans+=1;
			return;
		}
		// ������Ʈ
		for (int c=1;c<=N;c++) {		// 1������ N����� �õ�
			col[row]=c;
			setQueen(row+1);
		}
	}
	
	public static boolean isAvailable(int row) {	// row: ���������� ������ ���� ��
		for (int i=1;i<row;i++) {
			if (col[i]==col[row] || row-i==Math.abs(col[row]-col[i])) {
				return false;
			}
		}
		return true;
	}

}
