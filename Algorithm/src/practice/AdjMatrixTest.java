package practice;

import java.util.Arrays;
import java.util.Scanner;

// 무향 그래프
public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int V=sc.nextInt();
		int E=sc.nextInt();
		
		int[][] adjMatrix=new int[V][V];	// 초기값 0
		// 간선이 있으면 1, 없으면 0
		
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
