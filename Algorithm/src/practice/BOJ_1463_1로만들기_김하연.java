package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * BOJ 1463: 1로 만들기
 * 정수 X(1<=X<=10^6)에 사용할 수 있는 연산은 다음 세 가지이다.
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * 
 * X에서부터 위의 세 가지 경우를 모두 실행해본다면, 대략 3^n으로 엄청나게 큰 숫자가 나온다.
 * 1부터 시작하여 상향식 접근을 시도한다.
 * 1부터 연산을 사용하는 횟수의 최솟값을 저장하고 이를 활용하여 다음 숫자의 연산 사용 최솟값을 구한다.
 * 
 */
public class BOJ_1463_1로만들기_김하연 {
	static BufferedReader br;
	
	static int N;					// 정수 N
	static int[] dp;				// 해당 인덱스(숫자)의 최소 연산 횟수를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		// 연산을 할 정수를 입력받는다.
		N=Integer.parseInt(br.readLine().trim());
		dp=new int[N+1];
		
		// 일단, 연산 횟수를 최댓값으로 저장
		Arrays.fill(dp, Integer.MAX_VALUE);
		// 1은 연산할 필요가 없다.
		dp[1]=0;				
		
		for (int num=2;num<=N;num++) {
			// 현재 숫자가 3으로 나눠떨어진다면
			if (num%3==0) {
				// 3으로 나눈 숫자의 최소 연산 횟수에 1을 더한 값과 기존 값 중 최솟값을 저장한다.
				dp[num]=Math.min(dp[num], dp[num/3]+1);
			}
			// 현재 숫자가 2로 나눠떨어진다면
			if (num%2==0) {
				// 2로 나눈 숫자의 최소 연산 횟수에 1을 더한 값과 기존 값 중 최솟값을 저장한다.
				dp[num]=Math.min(dp[num], dp[num/2]+1);
			}
			// 1을 뺀 숫자의 최소 연산 횟수 활용
			dp[num]=Math.min(dp[num], dp[num-1]+1);
		}
		System.out.println(dp[N]);

	}

}
