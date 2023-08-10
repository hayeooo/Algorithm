package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 5215: 햄버거다이어트
 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거 주문
 * 햄버거 재료에 대한 점수와 재료에 대한 칼로리가 주어졌을 때
 * 맛에 대한 점수가 가장 높으면서, 정해진 칼로리 이하인 햄버거 점수를 출력한다.
 * 
 * 재료의 점수와 칼로리 두 가지를 고려해야하므로 2차원 배열을 사용한다.
 * 행은 각 재료의 순서, 열은 0부터 제한 칼로리까지 배치한다.
 * 배열의 각 요소는 재료 n번째의 해당 칼로리에서의 최대 점수가 저장되어 있다.
 * 최종 결과값은 마지막 행, 마지막 열에 저장되어 있다.
 * 
 * */
public class SWEA_5215_햄버거다이어트_DP_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;				// 테스트케이스 개수
	static int N;				// 재료의 개수
	static int L;				// 제한 칼로리
	
	static int[] taste;			// 
	static int[] calories;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스를 입력 받는다.
		T=Integer.parseInt(br.readLine());
		
		// 각 테스트케이스 별로
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine());
			
			// 재료의 개수와 제한 칼로리를 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			dp=new int[N][L+1];
			
			for (int idx=0;idx<N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// 각 재료의 맛 점수와 칼로리 점수를 입력 받는다.
				int taste=Integer.parseInt(st.nextToken());
				int calories=Integer.parseInt(st.nextToken());
				
				// 입력 받은 값으로 dp 배열을 채운다.
				if (idx==0) {
					for (int calIdx=calories;calIdx<=L;calIdx++) {
						dp[idx][calIdx]=taste;
					}
				}
				else {
					for (int calIdx=0;calIdx<=L;calIdx++) {
						if (calIdx>=calories) {
							dp[idx][calIdx]=Math.max(dp[idx-1][calIdx-calories]+taste,dp[idx-1][calIdx]);
						}else {
							dp[idx][calIdx]=dp[idx-1][calIdx];
						}
					}
				}
			}
			// 최종 값을 출력한다.
			System.out.println("#"+test_case+" "+dp[N-1][L]);
		}
		
		
		
		
	}
}
