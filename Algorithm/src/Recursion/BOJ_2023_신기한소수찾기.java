package Recursion;

import java.util.Scanner;
/*
 * BOJ 2023: �ű��� �Ҽ�ã��
 * N�ڸ� ���ڿ���
 * ���ʺ��� 1�ڸ�, 2�ڸ�, ... , N�ڸ� �� ��� �Ҽ��� ��� �ű��� �Ҽ��̴�.
 * N�� �־����� ��, N�ڸ� �ű��� �Ҽ��� ��� ã�´�.
 * 
 * 1. �Ҽ��� �Ǳ� ���� �� �ڸ��� ���� �� �ִ� ���ڴ� {1, 2, 3, 5, 7, 9}�̴�.
 * 2. ��, 1�� ù��° �ڸ��� ���� ��, �Ҽ��� �ƴϹǷ� isPrime �Լ����� 1�� ���� ���, false�� return�Ѵ�.
 * 3. �ߺ��� ����� ������ �̿��Ͽ�, �� �ڸ��� ���� �� �ִ� ���ڵ��� ���� ���� ������ ���� ������ �Ҽ����� �Ǵ��Ѵ�.
 * 4. �Ҽ��� �ƴ� ���, ������ �ߴ��ϸ� N�ڸ����� ��� �Ҽ��� ��� ����� ����Ѵ�.
 * 
 * */
public class BOJ_2023_�ű��ѼҼ�ã�� {
	static int N;								// N �ڸ� ��
	static int[] nums = { 1, 2, 3, 5, 7, 9 };	// �ű��� �Ҽ��� �Ǳ� ���� �� �� �ִ� ��� ����
				
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// �ڸ� �� �Է�
		N = sc.nextInt();
		
		// �ű��� �Ҽ� ���� �����
		perm(0,"");
	}
	public static void perm(int cnt,String cur) {		// cnt: ������� ������ ������ ��, cur: ������� ������ ����
		if (cnt==N) {									// N�ڸ� ������ �ϼ��Ͽ��� ��
			if (isPrime(Integer.parseInt(cur))) {		// ��ü ���ڰ� �Ҽ����� Ȯ��
				System.out.println(cur);				// true��� �ű��� �Ҽ��̹Ƿ� ���
			}
			return;										// ��� ����
		}
		for (int idx=0;idx<nums.length;idx++) {			// �ű��� �Ҽ��� �� �� �ִ� ��� ���ڿ� ���� �ݺ�				
			String tmp=cur+nums[idx];						// ������� ���� ����
			if (!isPrime(Integer.parseInt(tmp))) continue;	// �Ҽ��� �ƴ� ���, �ٸ� ���� �ֱ�
			perm(cnt+1,tmp);								// �Ҽ��� ���, ���� �ڸ� ���� �ֱ�
		}
		return;
	}
	// num�� �Ҽ����� �˻�
	public static boolean isPrime(int num) {	
		boolean result=true;				// �ϴ� �Ҽ���� ����				
		if (num==1) return false;			// 1�� �Ҽ��� �ƴ�.
		if (num==2) return true;			// 2�� �Ҽ��� �ƴ�.
		
		// 2���� num�� ������ ���̿� ���� �� �ϳ��� ������ �������ٸ� �Ҽ��� �ƴ�.
		for (int divided=2;divided<=(int)Math.sqrt(num);divided++) {
			if (num%divided==0) {
				result=false;
				break;
			}
		}
		return result;						// ����� ����.
	}
}
