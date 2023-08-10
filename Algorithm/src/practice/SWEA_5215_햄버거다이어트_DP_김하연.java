package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 5215: �ܹ��Ŵ��̾�Ʈ
 * �ܹ����� ���� �ִ��� �����ϸ鼭 ������ Į�θ��� ���� �ʴ� �ܹ��� �ֹ�
 * �ܹ��� ��ῡ ���� ������ ��ῡ ���� Į�θ��� �־����� ��
 * ���� ���� ������ ���� �����鼭, ������ Į�θ� ������ �ܹ��� ������ ����Ѵ�.
 * 
 * ����� ������ Į�θ� �� ������ ����ؾ��ϹǷ� 2���� �迭�� ����Ѵ�.
 * ���� �� ����� ����, ���� 0���� ���� Į�θ����� ��ġ�Ѵ�.
 * �迭�� �� ��Ҵ� ��� n��°�� �ش� Į�θ������� �ִ� ������ ����Ǿ� �ִ�.
 * ���� ������� ������ ��, ������ ���� ����Ǿ� �ִ�.
 * 
 * */
public class SWEA_5215_�ܹ��Ŵ��̾�Ʈ_DP_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;				// �׽�Ʈ���̽� ����
	static int N;				// ����� ����
	static int L;				// ���� Į�θ�
	
	static int[] taste;			// 
	static int[] calories;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ���̽��� �Է� �޴´�.
		T=Integer.parseInt(br.readLine());
		
		// �� �׽�Ʈ���̽� ����
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine());
			
			// ����� ������ ���� Į�θ��� �Է� �޴´�.
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			dp=new int[N][L+1];
			
			for (int idx=0;idx<N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// �� ����� �� ������ Į�θ� ������ �Է� �޴´�.
				int taste=Integer.parseInt(st.nextToken());
				int calories=Integer.parseInt(st.nextToken());
				
				// �Է� ���� ������ dp �迭�� ä���.
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
			// ���� ���� ����Ѵ�.
			System.out.println("#"+test_case+" "+dp[N-1][L]);
		}
		
		
		
		
	}
}
