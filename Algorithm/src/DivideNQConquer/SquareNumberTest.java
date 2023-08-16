package DivideNQConquer;

import java.util.Scanner;

public class SquareNumberTest {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long X=sc.nextLong();
		int N=sc.nextInt();
		
	}
	// Àç±Í: ºÐÇÒ Á¤º¹ ¹ÌÀû¿ë
	// X^n = X*X^n-1
	// X^n-1 =X*X^n-2
	private static long exp1(long x,int n) {
		if (n==1) {
			return x;
		}
		return x*exp1(x,n-1);
	}
	
	// Àç±Í : ºÐÇÒ Á¤º¹ Àû¿ë
	// n: Â¦¼ö X^n= X^(n/2) * X^(n/2)
	// n: È¦¼ö X^n= X^(n-1/2) * X^(n-1/2) * X
	// n: È¦¼ö X^n/2 ==>  X^(n-1/2)¿Í °°À½
	private static long exp2(long x, int n) {
		if (n==1) {
			return x;
		}
		long y=exp2(x,n/2);
		return (n%2==0)?y*y:y*y*x;
	}
}
