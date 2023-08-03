package Recursion;

import java.util.Scanner;
/*
 * BOJ 2023: 신기한 소수찾기
 * N자리 숫자에서
 * 왼쪽부터 1자리, 2자리, ... , N자리 수 모두 소수인 경우 신기한 소수이다.
 * N이 주어졌을 때, N자리 신기한 소수를 모두 찾는다.
 * 
 * 1. 소수가 되기 위해 각 자리에 나올 수 있는 숫자는 {1, 2, 3, 5, 7, 9}이다.
 * 2. 단, 1은 첫번째 자리에 나올 때, 소수가 아니므로 isPrime 함수에서 1이 나올 경우, false를 return한다.
 * 3. 중복을 허용한 순열을 이용하여, 각 자리에 나올 수 있는 숫자들을 고르고 숫자 조합이 나올 때마다 소수인지 판단한다.
 * 4. 소수가 아닐 경우, 진행을 중단하며 N자리까지 모두 소수일 경우 결과를 출력한다.
 * 
 * */
public class BOJ_2023_신기한소수찾기 {
	static int N;								// N 자리 수
	static int[] nums = { 1, 2, 3, 5, 7, 9 };	// 신기한 소수가 되기 위해 들어갈 수 있는 모든 숫자
				
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 자리 수 입력
		N = sc.nextInt();
		
		// 신기한 소수 조합 만들기
		perm(0,"");
	}
	public static void perm(int cnt,String cur) {		// cnt: 현재까지 조합한 숫자의 수, cur: 현재까지 조합한 숫자
		if (cnt==N) {									// N자리 수까지 완성하였을 때
			if (isPrime(Integer.parseInt(cur))) {		// 전체 숫자가 소수인지 확인
				System.out.println(cur);				// true라면 신기한 소수이므로 출력
			}
			return;										// 재귀 종료
		}
		for (int idx=0;idx<nums.length;idx++) {			// 신기한 소수에 들어갈 수 있는 모든 숫자에 대해 반복				
			String tmp=cur+nums[idx];						// 현재까지 만든 숫자
			if (!isPrime(Integer.parseInt(tmp))) continue;	// 소수가 아닌 경우, 다른 숫자 넣기
			perm(cnt+1,tmp);								// 소수인 경우, 다음 자리 숫자 넣기
		}
		return;
	}
	// num이 소수인지 검사
	public static boolean isPrime(int num) {	
		boolean result=true;				// 일단 소수라고 가정				
		if (num==1) return false;			// 1은 소수가 아님.
		if (num==2) return true;			// 2는 소수가 아님.
		
		// 2부터 num의 제곱근 사이에 숫자 중 하나로 나누어 떨어진다면 소수가 아님.
		for (int divided=2;divided<=(int)Math.sqrt(num);divided++) {
			if (num%divided==0) {
				result=false;
				break;
			}
		}
		return result;						// 결과를 리턴.
	}
}
