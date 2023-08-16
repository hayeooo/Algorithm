package DivideNQConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1992: ����Ʈ��
 * �־��� ������ ��� 0���θ� �Ǿ� ������ ���� ����� "0"
 * ��� 1�θ� �Ǿ� ������ ���� ����� "1"
 * ���� 0�� 1�� ���� ������ ��ü�� �� ���� ��Ÿ���� ���ϰ� 4 ���� �������� �ɰ� ����
 * 
 * ���Ұ� ���� �˰���
 * 1. ��� 0 �Ǵ� 1�� ��� �ش� ���ڸ� return
 * 2. �׷��� ���� ���, ��ȣ�� �ְ� 4���� ���� ������ ���� ����Լ��� ȣ���ϰ� ��ȣ�� �ݴ´�.
 * 
 * �� ������ �������� ���� �� ��� ���̴�.
 */
public class BOJ_1992_����Ʈ��_���Ͽ� {
	static BufferedReader br;
	
	static int N;						// ������ ũ��
	static char[][] map;				// ������ ������ ���� �迭
	static String res;					// ���� ����� ���� ���ڿ�
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ũ�⸦ �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// ���� ������ ������ �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		map=new char[N][N];
		// ���� ����� ���� ���ڿ��� �� ���ڿ��� �ʱ�ȭ�Ѵ�.
		res="";
		
		// ���� ������ �Է� �޴´�.
		for (int row=0;row<N;row++) {
			map[row]=br.readLine().trim().toCharArray();
		}
		// ������ �����Ѵ�.
		compress(0,0,N);
		// ���� ����� ����Ѵ�.
		System.out.println(res);
	}
	// ������ �����ϴ� ����Լ�
	public static void compress(int row, int col, int size) { // �� ������ row: ���� col: �� size: ���� ũ��
		
		// size�� 1�� ���
		if (size==1) {
			// ����� �ش� ���ڸ� ���ϰ� �����Ѵ�.
			res+=map[row][col];
			return;
		}
		// �ȿ� ���ڰ� ��� 1�̰ų� 0�� ���
		if (check(row,col,size)) {
			// ����� �ش� ���ڸ� ���ϰ� �����Ѵ�.
			res+=map[row][col];
			return;
		}
		// 0�� 1�� �����ִ� ���
		// 4 �������� �ɰ� ���� (1��и�)+(2��и�)+(3��и�)+(4��и�)
		res+="(";
		int nextSize=size/2;										// ���� Ž���� ������ ũ��
		compress(row,col,nextSize);									// ���� ��
		compress(row,col+nextSize,nextSize);						// ������ ��
		compress(row+nextSize,col,nextSize);						// ���� �Ʒ�
		compress(row+nextSize,col+nextSize,nextSize);				// ������ �Ʒ�
		res+=")";
		
	}
	// �ش� ������ ���ڰ� ��� ������ �Ǵ��ϴ� �Լ�
	public static boolean check(int row, int col, int size) {
		// �� ������ ���ڿ� ����
		for (int r=row;r<row+size;r++) {
			for (int c=col;c<col+size;c++) {
				if (map[row][col]!=map[r][c]) {	// �ٸ��ٸ�
					return false;				// false�� return
				}
			}
		}
		// ���ڰ� ��� ���ٸ�
		// true�� return
		return true;
	}
}

