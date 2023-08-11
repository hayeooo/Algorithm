package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * BOJ 2839: 설탕배달
 * 설탕 무게 별로 최소 봉지 개수를 담는 배열을 사용한다.
 * 1. 3kg 5kg 봉지 모두 사용할 수 있는 경우,
 * 현재 무게-3kg, 현재 무게-5kg의 최소 봉지 개수에 1을 더해 저장한다.
 * 2. 3kg 봉지만 사용할 수 있는 경우
 * 현재 무게 -3kg의 최소 봉지 개수에 1을 더해 저장한다.
 * 3. 5kg 봉지만 사용할 수 있는 경우
 * 현재 무게 -5kg의 최소 봉지 개수에 1을 더해 저장한다.
 * 4. 봉지를 사용할 수 없는 경우, -1을 저장한다.
 * */
public class BOJ_2839_설탕배달_DP_김하연 {
	static BufferedReader br;
	static int N;					// 설탕 무게
	static int[] dp;				// 메모이제이션에 사용할 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 담을 설탕 무게 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 배열을 초기화한다.
		dp=new int[N+1];
		dp[0]=0;
		dp[1]=-1;
		dp[2]=-1;
		
		// 3kg부터 Nkg까지 최소 봉지 개수를 저장한다.
		for (int sugar=3;sugar<=N;sugar++) {
			
			// 3과 5 모두 사용할 수 있는 경우
			if (sugar>=5 && dp[sugar-3]>=0 && dp[sugar-5]>=0) {
				dp[sugar]=Math.min(dp[sugar-5], dp[sugar-3])+1;
			}
			// 3만 사용할 수 있는 경우
			else if (dp[sugar-3]>=0) {
				dp[sugar]=dp[sugar-3]+1;
			}
			// 5만 사용할 수 있는 경우
			else if (sugar>=5 && dp[sugar-5]>=0) {
				dp[sugar]=dp[sugar-5]+1;
			}
			// 둘 다 사용할 수 없는 경우
			else {
				dp[sugar]=-1;
			}
			
		}
		// 결과를 출력한다.
		System.out.println(dp[N]);
	}

}
