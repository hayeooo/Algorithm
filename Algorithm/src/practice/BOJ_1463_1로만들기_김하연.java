package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * BOJ 1463: 1�� �����
 * ���� X(1<=X<=10^6)�� ����� �� �ִ� ������ ���� �� �����̴�.
 * 1. X�� 3���� ������ ��������, 3���� ������.
 * 2. X�� 2�� ������ ��������, 2�� ������.
 * 3. 1�� ����.
 * 
 * X�������� ���� �� ���� ��츦 ��� �����غ��ٸ�, �뷫 3^n���� ��û���� ū ���ڰ� ���´�.
 * 1���� �����Ͽ� ����� ������ �õ��Ѵ�.
 * 1���� ������ ����ϴ� Ƚ���� �ּڰ��� �����ϰ� �̸� Ȱ���Ͽ� ���� ������ ���� ��� �ּڰ��� ���Ѵ�.
 * 
 */
public class BOJ_1463_1�θ����_���Ͽ� {
	static BufferedReader br;
	
	static int N;					// ���� N
	static int[] dp;				// �ش� �ε���(����)�� �ּ� ���� Ƚ���� �����ϴ� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		// ������ �� ������ �Է¹޴´�.
		N=Integer.parseInt(br.readLine().trim());
		dp=new int[N+1];
		
		// �ϴ�, ���� Ƚ���� �ִ����� ����
		Arrays.fill(dp, Integer.MAX_VALUE);
		// 1�� ������ �ʿ䰡 ����.
		dp[1]=0;				
		
		for (int num=2;num<=N;num++) {
			// ���� ���ڰ� 3���� �����������ٸ�
			if (num%3==0) {
				// 3���� ���� ������ �ּ� ���� Ƚ���� 1�� ���� ���� ���� �� �� �ּڰ��� �����Ѵ�.
				dp[num]=Math.min(dp[num], dp[num/3]+1);
			}
			// ���� ���ڰ� 2�� �����������ٸ�
			if (num%2==0) {
				// 2�� ���� ������ �ּ� ���� Ƚ���� 1�� ���� ���� ���� �� �� �ּڰ��� �����Ѵ�.
				dp[num]=Math.min(dp[num], dp[num/2]+1);
			}
			// 1�� �� ������ �ּ� ���� Ƚ�� Ȱ��
			dp[num]=Math.min(dp[num], dp[num-1]+1);
		}
		System.out.println(dp[N]);

	}

}
