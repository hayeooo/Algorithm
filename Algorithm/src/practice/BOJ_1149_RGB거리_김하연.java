package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1149: RGB 거리
 * 1번 집부터 N번 집이 순서대로 있다.
 * 집을 빨강, 초록, 파랑 중 하나의 색으로 칠해야한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구한다.
 * 1. 1번 집의 색은 2번 집의 색과 같지 않다.
 * 2. N번 집의 색은 N-1번 집의 색과 같지 않다.
 * 3. i번 집의 색은 i-1, i+1번 집의 색과 같지 않다.
 */
public class BOJ_1149_RGB거리_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int houseCnt;				// 집의 개수
	static int[][] RGBCost;				// 각각의 집을 빨강, 초록, 파랑으로 칠했을 때의 비용
	static int[][] dp;					// 각 집을 빨강, 초록, 파랑으로 칠했을 때 비용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 집의 개수 입력받는다.
		houseCnt=Integer.parseInt(br.readLine().trim());
		
		RGBCost=new int[houseCnt][3];
		
		// 각 집의 색깔별 비용을 입력 받는다.
		for (int house=0;house<houseCnt;house++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int color=0;color<3;color++) {
				RGBCost[house][color]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 행은 빨강, 초록, 파랑 / 열은 각각의 집에 해당한다.
		// dp를 통해 해당 집에 빨강, 초록, 파란색을 칠했을 때 최소 비용을 저장하고 있다.
		dp=new int[3][houseCnt];
		
		// 첫번째 집은 최소 비용을 유도할 수 없으므로 초기화한다.
		// 첫번째 집을 빨간색으로 칠한 경우
		dp[0][0]=RGBCost[0][0];
		// 두번째 집을 초록색으로 칠한 경우
		dp[1][0]=RGBCost[0][1];
		// 세번째 집을 파란색으로 칠한 경우
		dp[2][0]=RGBCost[0][2];
		
		for (int house=1;house<houseCnt;house++) {
			// 이전 집에 칠한 색깔과 다르면 된다.
			
			// 현재 집을 빨간색으로 칠한 경우
			// 이전 집은 초록색이거나 파란색이어야 한다.
			dp[0][house]=Math.min(dp[1][house-1], dp[2][house-1])+RGBCost[house][0];
			
			// 현재 집을 초록색으로 칠한 경우
			// 이전 집은 빨간색이거나 파란색이어야 한다.
			dp[1][house]=Math.min(dp[0][house-1], dp[2][house-1])+RGBCost[house][1];
			
			// 현재 집을 파란색으로 칠한 경우
			// 이전 집은 빨간색이거나 초록색이어야 한다.
			dp[2][house]=Math.min(dp[0][house-1], dp[1][house-1])+RGBCost[house][2];
			
		}
		
		
		// 모든 집에 색을 칠한 후
		// 마지막 집의 열에 최소 비용이 들어있다.
		int minCost=Math.min(Math.min(dp[0][houseCnt-1], dp[1][houseCnt-1]),dp[2][houseCnt-1]);
		System.out.println(minCost);

	}

}
