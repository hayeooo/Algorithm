package practice;

import java.util.Arrays;
import java.util.Scanner;

// ���� �׷���
public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int V=sc.nextInt();
		int E=sc.nextInt();
		
		int[][] adjMatrix=new int[V][V];	// �ʱⰪ 0
		// ������ ������ 1, ������ 0
		
		for (int i=0;i<E;i++) {
			int from=sc.nextInt();
			int to=sc.nextInt();
			adjMatrix[from][to]=1;
			adjMatrix[to][from]=1;
		}
		for (int[] is:adjMatrix) {
			System.out.println(Arrays.toString(is));
		}
	}

}
