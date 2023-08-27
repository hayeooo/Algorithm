package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * S1, S2�� ���� �Ѵ�.(���� ���� ����~���� ���� ���� ������ ����)
 * 
 * ù �ٿ� �׽�Ʈ ���̽� ������ �־�����.
 * �� ��° �ٿ� �л� ��, ���� �ּ� �ο� ��, ���� �ִ� �ο����� �־�����.
 * �� ��° �ٿ� �� �л��� ������ �־�����.
 * 
 */
public class SWEA_16778_�ݹ�ġ���_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ����
	static int N,N_min,N_max;		// ���̽� �ο� ��, �ּ� �ο�, �ִ� �ο�
	static int[] classScore;		// �� ���� �л� �������� �����ϴ� �迭
	static int minDiff;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ���̽� ������ �Է¹޴´�.
		T=Integer.parseInt(br.readLine());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// ���̽� �ο� ��, �ּ� �ο� , �ִ� �ο��� �Է¹޴´�.
			st=new StringTokenizer(br.readLine().trim());
			
			N=Integer.parseInt(st.nextToken());
			N_min=Integer.parseInt(st.nextToken());
			N_max=Integer.parseInt(st.nextToken());
			
			// �� ���� �ְ� ������ ���� ���� �ʱ�ȭ -> s1, s2�� �����ϱ� ����
			int minScore=Integer.MAX_VALUE;
			int maxScore=0;
			classScore=new int[N];
			
			// �л� ������ �����Ѵ�.
			st=new StringTokenizer(br.readLine());
			for (int idx=0;idx<N;idx++) {
				classScore[idx]=Integer.parseInt(st.nextToken());
				if (classScore[idx]>maxScore) {
					maxScore=classScore[idx];
				}
				if (classScore[idx]<minScore) {
					minScore=classScore[idx];
				}
			}
			minDiff=Integer.MAX_VALUE;
			int[] classroom=null;
			// �� ���� ���� ������ ���� s1, s2�� ����. (minScore~maxScore ���� ������ ����)
			for (int s1=minScore;s1<maxScore;s1++) {
				for (int s2=s1+1;s2<=maxScore;s2++) {
					// �� �л��� ������ ���� �й��� �����Ѵ�.
					classroom=new int[3];
					
					for (int idx=0;idx<N;idx++) {
						// A�й�(s2 �̻�)
						if (classScore[idx]>=s2) {
							classroom[0]+=1;
						}
						
						// B�й�(s1 �̻� s2�̸�)
						else if(classScore[idx]>=s1 && classScore[idx]<s2) {
							classroom[1]+=1;
						}
						// C�й�(s1�̸�)
						else if(classScore[idx]<s1) {
							classroom[2]+=1;
						}
					}
					
					// ���� �йݿ��� �ּ� �ο��� �ִ� �ο��� �����ϴ��� Ȯ��
					// �������� �ʴ´ٸ� �ٸ� ������ ������ �� ����
					if (!isValid(classroom)) {
						continue;
					}
					
					// �� ������ ������ ������ ������ ���
					// �ִ� �ο��� �ּ� �ο��� ���̸� ���Ѵ�.
					int maxCnt=-1;
					int minCnt=Integer.MAX_VALUE;
					for (int idx=0;idx<3;idx++) {
						if (maxCnt<classroom[idx]) {
							maxCnt=classroom[idx];
						}
						if (minCnt>classroom[idx] ) {
							minCnt=classroom[idx];
						}
					}
					minDiff=Math.min(minDiff, maxCnt-minCnt);
				}
				
			}
			sb.append("#"+test_case+" ");
			if (minDiff==Integer.MAX_VALUE) {
				sb.append(-1);
			}else {
				sb.append(minDiff);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	// �ּ� �ο����� ���ų� �ִ� �ο� �ʰ��� false
	public static boolean isValid(int[] classroom) {
		boolean valid=true;
		for (int idx=0;idx<3;idx++) {
			if (classroom[idx]<N_min) {
				valid=false;
				break;
			}
			if (classroom[idx]>N_max) {
				valid=false;
				break;
			}
		}
		return valid;
	}

}
