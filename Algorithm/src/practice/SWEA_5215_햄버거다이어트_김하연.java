package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * SWEA 5215: �ܹ��� ���̾�Ʈ �κ�����
 * 
 * 1. �׽�Ʈ���̽� ���� �Է� �޴´�.
 * 2. �׽�Ʈ���̽� ���� ����� ���� ���� Į�θ��� �Է� �޴´�.
 * 3. �� ����� �� ������ Į�θ��� �Է� �޴´�.
 * 4. ��Ḧ 1������ N���� ������ �����ϰ� �� ������ Į�θ� �հ� �� ������ ���Ѵ�.
 * 5. Į�θ� ���� ���� Į�θ��� ���� �ʴ´ٸ� ���� ���� �� ������ �����Ѵ�.
 * *
 */
public class SWEA_5215_�ܹ��Ŵ��̾�Ʈ_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;						// �׽�Ʈ���̽� ��
	static int N;						// ����� ��
	static int L;						// ���� Į�θ�
	
	static int[] taste;					// �� ���� ���� ����
	static int[] calories;				// Į�θ��� ���� ����
	static int[] comb;					// �ܹ��� ��� ����
	static boolean[] isSelected;		// �� ��� ���� ���θ� �����ϴ� �迭
	
	static int maxScore;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ���̽� �Է�
		T=Integer.parseInt(br.readLine().trim());
		
		// �׽�Ʈ���̽� ���� �ݺ��Ѵ�.
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// ����� ��, ���� Į�θ��� �Է� �޴´�.
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			// �� ������, Į�θ��� ���� ������ �����Ѵ�.
			taste=new int[N];
			calories=new int[N];
			for (int idx=0;idx<N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				taste[idx]=Integer.parseInt(st.nextToken());
				calories[idx]=Integer.parseInt(st.nextToken());
			}
			// ���� ���� �ְ� ������ �ʱ�ȭ�Ѵ�.
			maxScore=0;
			isSelected=new boolean[N];
			
			// 1������ N������ �ܹ��� ��Ḧ �̾�
			// �ش� ���� �ܹ��Ÿ� ���� ��, ������ Į�θ��� ������ ���Ѵ�.
			// ���� Į�θ��� ���� �ʴ� ���� ���� �� �ְ����� �����Ѵ�.
			getSubSet(0,0,0);
			
			// ����� ����Ѵ�.
			System.out.println("#"+test_case+" "+maxScore);
			
		}
	}
	// ����� �κ� ������ ���Ͽ�
	// ���� Į�θ��� ���� �ʴ� ���� ���� �� ������ �����Ѵ�.
	public static void getSubSet(int cnt,int score,int caloriesSum) {	
		// N���� ��Ḧ ��� Ȯ���� ��,
		// ������ ���Ѵ�.
		if (cnt==N) {
			if (caloriesSum<=L) {
				maxScore=Math.max(score, maxScore);
			}
			return;
		}
		// N ���� ��Ḧ ������ ���� �����Ѵ�.
		isSelected[cnt]=true;
		getSubSet(cnt+1,score+taste[cnt],caloriesSum+calories[cnt]);
		
		isSelected[cnt]=false;
		getSubSet(cnt+1,score,caloriesSum);
		
	}
	

}
