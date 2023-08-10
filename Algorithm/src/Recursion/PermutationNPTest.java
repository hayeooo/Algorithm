package Recursion;

import java.util.Arrays;
import java.util.Scanner;

public class PermutationNPTest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int[] input=new int[N];
		
		for (int i=0;i<N;i++) {
			input[i]=sc.nextInt();
		}
		Arrays.sort(input);  	// ���������� ���·� ����
		do {
			System.out.println(Arrays.toString(input));
		}while (np(input));
	}
	
	private static boolean np(int[] p) {	// p: ���� ������ ���ϴ� ���� ������ �迭
		// 1. �� ���ʺ��� Ž���ϸ� ����� ã��
		int N=p.length;
		int i=N-1;
		while(i>0 &&p[i-1]<=p[i]) i--;
		
		if (i==0) return false;		// ���� ������ ����( ���� ū ������ ����)
		
		// 2. ����� ����(i-1) ��ġ�� ��ȯ�� �� �ܰ� ū �� ã��
		int j=N-1;
		while (p[i-1]>=p[j]) j--;
		
		// 3. ����� ����(i-1)��ġ�� ���� �� �ܰ� ū ���� ��ȯ�ϱ�
		swap(p,i-1,j);
		
		// 4. ������ڸ����� �� �ڱ����� ���� ���������� ���·� �ٲ��ֱ�
		int k=N-1;
		while (i<k) {
			swap(p,i++,k--);
		}
		return true;
	}
	
	private static void swap(int[] p, int a, int b) {
		int temp=p[a];
		p[a]=p[b];
		p[b]=temp;
	}
}
