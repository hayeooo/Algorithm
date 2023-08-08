package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * SWEA 1223: ��Ģ���� ��ȿ�� �˻�
 * 1�� ������ root .. N/2�� ������ �����ڿ� �ǿ����� 2���� �־�� �Ѵ�.
 * N/2+1 .. N�� ������ �ǿ����� 1���� ����־�� �Ѵ�.
 * 
 * �Է� �����͸� ������ 
 * ���� ���ǿ� �����ϴ��� Ȯ���ϰ�
 * ����� �Ұ����� ���� ��� flag ������ ������Ʈ�Ѵ�.
 * 
 * */
public class SWEA_1233_��Ģ������ȿ���˻�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// ������ ����
	static final int TC=10;			// �׽�Ʈ���̽� ����
	static int isValid;				// ������ �������� ����(�����ϴٸ� 1, �������ϴٸ� 0)
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ������ŭ �ݺ�
		for (int test_case=1;test_case<=TC;test_case++) {
			N=Integer.parseInt(br.readLine());
			
			// ������ �����̶�� ����
			isValid=1;
			
			// ��Ʈ ��忡������ N/2�� ������ �����ڿ� �ǿ����ڰ� �ִ��� Ȯ��
			for (int idx=1;idx<=N/2;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// ���� ����
				int node=Integer.parseInt(st.nextToken());
				
				// ������ ����
				String operator=st.nextToken();
				
				// �����ڰ� �ƴ� ���
				if (!operator.equals("-") && !operator.equals("+") && !operator.equals("*") && !operator.equals("/")) {
					isValid=0;
				}
				// �ǿ����ڰ� 2�� �̸��� ��� �������� ����
				if (st.countTokens()<2) {
					isValid=0;
					continue;
				}
			}
			// N/2+1�� ������ N�� ������ �ǿ����ڰ� �ִ��� Ȯ��
			for (int idx=N/2+1;idx<=N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// ���� ����
				int node=Integer.parseInt(st.nextToken());
				// �ǿ����ڰ� ���� ��� : �������� ����
				if (st.countTokens()!=1) {
					isValid=0;
					continue;
				}
				// �ǿ����� ���°� �������� ���: �������� ����
				String operand=st.nextToken();
				if (operand.equals("-") || operand.equals("+") || operand.equals("*") || operand.equals("/")) {
					isValid=0;
				}
			}
			// ��� ���
			System.out.println("#"+test_case+" "+isValid);
			
		}

	}

}
