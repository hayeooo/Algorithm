package Recursion;

import java.util.Arrays;
import java.util.Scanner;

public class SSAFY_DiceTest {

	static int N, numbers[];
	static boolean[] isSelected;

	public static void main(String[] args) {
		// �Է����� �ֻ��� ������ Ƚ����, �ֻ��� ������ ���(1,2,3,4)
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // �ֻ��� ������ Ƚ�� (0<N<7)
		int M = sc.nextInt();// �ֻ��� ������ ���
		numbers = new int[N]; // ������ �ֻ��� ����

		switch (M) {
		case 1:
			dice1(0); // �ߺ�����
			break;
		case 2: // ����
			isSelected = new boolean[7];
			dice2(0);
			break;
		case 3:
			dice3(0,1);
			break;
		case 4:
			dice4(0,1);
			break;
		}
	}

	private static void dice1(int cnt) { // cnt+1��° �ֻ��� ������, cnt: �������� ������ �ֻ��� Ƚ��

		if (cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// �� �� ���� �� ������ ��Ȳ�� ���� �õ�(1,2,3,4,5,6 �ֻ��� ���� ����)
		for (int i = 1; i <= 6; i++) {
			numbers[cnt] = i;
			// �� �ֻ����� ���� i�� ������ ���·� ���� �ֻ��� ������ ����
			dice1(cnt + 1);

		}
	}

	private static void dice2(int cnt) { // cnt+1��° �ֻ��� ������, cnt: �������� ������ �ֻ��� Ƚ��

		if (cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// �� �� ���� �� ������ ��Ȳ�� ���� �õ�(1,2,3,4,5,6 �ֻ��� ���� ����)
		for (int i = 1; i <= 6; i++) {
			// ���� �ֻ����� ���� �ߺ��Ǵ� �� üũ
			if (isSelected[i])
				continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			// �� �ֻ����� ���� i�� ������ ���·� ���� �ֻ��� ������ ����
			dice2(cnt + 1);
			// �� �ֻ����� ���� �ٸ� �������� �õ��ϱ� ���� �غ�
			isSelected[i] = false;
		}
	}
	// �ߺ� ����
	private static void dice3(int cnt, int start) {
		if (cnt==N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i=start;i<=6;i++) {
			numbers[cnt]=i;
			dice3(cnt+1,i);
		}
	}
	// ���� (�ߺ�X)
	private static void dice4(int cnt, int start) {
		if (cnt==N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i=start;i<=6;i++) {
			numbers[cnt]=i;
			dice4(cnt+1,i+1);
		}
	}

}
