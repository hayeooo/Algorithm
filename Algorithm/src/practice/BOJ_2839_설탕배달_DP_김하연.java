package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * BOJ 2839: �������
 * ���� ���� ���� �ּ� ���� ������ ��� �迭�� ����Ѵ�.
 * 1. 3kg 5kg ���� ��� ����� �� �ִ� ���,
 * ���� ����-3kg, ���� ����-5kg�� �ּ� ���� ������ 1�� ���� �����Ѵ�.
 * 2. 3kg ������ ����� �� �ִ� ���
 * ���� ���� -3kg�� �ּ� ���� ������ 1�� ���� �����Ѵ�.
 * 3. 5kg ������ ����� �� �ִ� ���
 * ���� ���� -5kg�� �ּ� ���� ������ 1�� ���� �����Ѵ�.
 * 4. ������ ����� �� ���� ���, -1�� �����Ѵ�.
 * */
public class BOJ_2839_�������_DP_���Ͽ� {
	static BufferedReader br;
	static int N;					// ���� ����
	static int[] dp;				// �޸������̼ǿ� ����� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ���� ���� ���� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// �迭�� �ʱ�ȭ�Ѵ�.
		dp=new int[N+1];
		dp[0]=0;
		dp[1]=-1;
		dp[2]=-1;
		
		// 3kg���� Nkg���� �ּ� ���� ������ �����Ѵ�.
		for (int sugar=3;sugar<=N;sugar++) {
			
			// 3�� 5 ��� ����� �� �ִ� ���
			if (sugar>=5 && dp[sugar-3]>=0 && dp[sugar-5]>=0) {
				dp[sugar]=Math.min(dp[sugar-5], dp[sugar-3])+1;
			}
			// 3�� ����� �� �ִ� ���
			else if (dp[sugar-3]>=0) {
				dp[sugar]=dp[sugar-3]+1;
			}
			// 5�� ����� �� �ִ� ���
			else if (sugar>=5 && dp[sugar-5]>=0) {
				dp[sugar]=dp[sugar-5]+1;
			}
			// �� �� ����� �� ���� ���
			else {
				dp[sugar]=-1;
			}
			
		}
		// ����� ����Ѵ�.
		System.out.println(dp[N]);
	}

}
