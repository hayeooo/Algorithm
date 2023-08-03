package Recursion;

import java.util.Scanner;

public class SSAFY_SubSetSumTest {

	static int N;
	static int TARGET;
	static int[] input;
	static boolean[] isSelected;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		TARGET = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		generateSubset(0);
		generateSubset2(0, 0, 0);
	}

	private static void generateSubset(int cnt) { // cnt: 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스

		if (cnt == N) {

			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					temp += input[i];
				}
			}
			if (temp == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.print(input[i] + "\t");
					}
				}
				System.out.println();
			}
			return;
		}
		isSelected[cnt] = true;
		generateSubset(cnt + 1);
		isSelected[cnt] = false;
		generateSubset(cnt + 1);
	}

	private static void generateSubset2(int cnt, int sum, int selectedCount) { // cnt: 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스
		// sum: 직전까지 선택한 원소의 합
		if (cnt == N) {

			if (selectedCount > 0 && sum == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.print(input[i] + "\t");
					}
				}
				System.out.println();
			}
			return;
		}
		isSelected[cnt] = true;
		generateSubset2(cnt + 1, sum + input[cnt], selectedCount + 1);
		isSelected[cnt] = false;
		generateSubset2(cnt + 1, sum, selectedCount);
	}
}
