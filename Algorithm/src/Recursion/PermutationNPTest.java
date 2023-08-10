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
		Arrays.sort(input);  	// 오름차순의 형태로 정렬
		do {
			System.out.println(Arrays.toString(input));
		}while (np(input));
	}
	
	private static boolean np(int[] p) {	// p: 다음 순열을 원하는 기존 순열의 배열
		// 1. 맨 뒤쪽부터 탐색하며 꼭대기 찾기
		int N=p.length;
		int i=N-1;
		while(i>0 &&p[i-1]<=p[i]) i--;
		
		if (i==0) return false;		// 다음 순열은 없음( 가장 큰 순열의 형태)
		
		// 2. 꼭대기 직전(i-1) 위치에 교환할 한 단계 큰 수 찾기
		int j=N-1;
		while (p[i-1]>=p[j]) j--;
		
		// 3. 꼭대기 직전(i-1)위치의 수와 한 단계 큰 수를 교환하기
		swap(p,i-1,j);
		
		// 4. 꼭대기자리부터 맨 뒤까지의 수를 오름차순의 형태로 바꿔주기
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
