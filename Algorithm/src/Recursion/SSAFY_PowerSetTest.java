package Recursion;

import java.util.Scanner;

public class SSAFY_PowerSetTest {

	static int N;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		isSelected=new boolean[N];
		
		for (int i=0;i<N;i++) {
			input[i]=sc.nextInt();
		}
		generateSubset(0);
	}
	private static void generateSubset(int cnt) {	// cnt: 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스
		
		if (cnt==N) {
			for (int i=0;i<N;i++) {
				System.out.print((isSelected[i]?input[i]:"X")+"\t");
			}
			System.out.println();
			return;
		}
		isSelected[cnt]=true;
		generateSubset(cnt+1);
		isSelected[cnt]=false;
		generateSubset(cnt+1);
	}
}
