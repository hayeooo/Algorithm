package DivideNQConquer;

import java.util.Scanner;

public class SquareNumberTest {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long X=sc.nextLong();
		int N=sc.nextInt();
		
	}
	// ���: ���� ���� ������
	// X^n = X*X^n-1
	// X^n-1 =X*X^n-2
	private static long exp1(long x,int n) {
		if (n==1) {
			return x;
		}
		return x*exp1(x,n-1);
	}
	
	// ��� : ���� ���� ����
	// n: ¦�� X^n= X^(n/2) * X^(n/2)
	// n: Ȧ�� X^n= X^(n-1/2) * X^(n-1/2) * X
	// n: Ȧ�� X^n/2 ==>  X^(n-1/2)�� ����
	private static long exp2(long x, int n) {
		if (n==1) {
			return x;
		}
		long y=exp2(x,n/2);
		return (n%2==0)?y*y:y*y*x;
	}
}
