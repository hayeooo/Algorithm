package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1149: RGB �Ÿ�
 * 1�� ������ N�� ���� ������� �ִ�.
 * ���� ����, �ʷ�, �Ķ� �� �ϳ��� ������ ĥ�ؾ��Ѵ�.
 * ������ ���� ����, �ʷ�, �Ķ����� ĥ�ϴ� ����� �־����� �� �Ʒ� ��Ģ�� �����ϸ鼭 ��� ���� ĥ�ϴ� ����� �ּڰ��� ���Ѵ�.
 * 1. 1�� ���� ���� 2�� ���� ���� ���� �ʴ�.
 * 2. N�� ���� ���� N-1�� ���� ���� ���� �ʴ�.
 * 3. i�� ���� ���� i-1, i+1�� ���� ���� ���� �ʴ�.
 */
public class BOJ_1149_RGB�Ÿ�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int houseCnt;				// ���� ����
	static int[][] RGBCost;				// ������ ���� ����, �ʷ�, �Ķ����� ĥ���� ���� ���
	static int[][] dp;					// �� ���� ����, �ʷ�, �Ķ����� ĥ���� �� ���
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ���� ���� �Է¹޴´�.
		houseCnt=Integer.parseInt(br.readLine().trim());
		
		RGBCost=new int[houseCnt][3];
		
		// �� ���� ���� ����� �Է� �޴´�.
		for (int house=0;house<houseCnt;house++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int color=0;color<3;color++) {
				RGBCost[house][color]=Integer.parseInt(st.nextToken());
			}
		}
		
		// ���� ����, �ʷ�, �Ķ� / ���� ������ ���� �ش��Ѵ�.
		// dp�� ���� �ش� ���� ����, �ʷ�, �Ķ����� ĥ���� �� �ּ� ����� �����ϰ� �ִ�.
		dp=new int[3][houseCnt];
		
		// ù��° ���� �ּ� ����� ������ �� �����Ƿ� �ʱ�ȭ�Ѵ�.
		// ù��° ���� ���������� ĥ�� ���
		dp[0][0]=RGBCost[0][0];
		// �ι�° ���� �ʷϻ����� ĥ�� ���
		dp[1][0]=RGBCost[0][1];
		// ����° ���� �Ķ������� ĥ�� ���
		dp[2][0]=RGBCost[0][2];
		
		for (int house=1;house<houseCnt;house++) {
			// ���� ���� ĥ�� ����� �ٸ��� �ȴ�.
			
			// ���� ���� ���������� ĥ�� ���
			// ���� ���� �ʷϻ��̰ų� �Ķ����̾�� �Ѵ�.
			dp[0][house]=Math.min(dp[1][house-1], dp[2][house-1])+RGBCost[house][0];
			
			// ���� ���� �ʷϻ����� ĥ�� ���
			// ���� ���� �������̰ų� �Ķ����̾�� �Ѵ�.
			dp[1][house]=Math.min(dp[0][house-1], dp[2][house-1])+RGBCost[house][1];
			
			// ���� ���� �Ķ������� ĥ�� ���
			// ���� ���� �������̰ų� �ʷϻ��̾�� �Ѵ�.
			dp[2][house]=Math.min(dp[0][house-1], dp[1][house-1])+RGBCost[house][2];
			
		}
		
		
		// ��� ���� ���� ĥ�� ��
		// ������ ���� ���� �ּ� ����� ����ִ�.
		int minCost=Math.min(Math.min(dp[0][houseCnt-1], dp[1][houseCnt-1]),dp[2][houseCnt-1]);
		System.out.println(minCost);

	}

}
