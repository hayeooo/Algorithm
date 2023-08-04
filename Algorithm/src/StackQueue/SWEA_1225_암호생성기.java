package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*
 * SWEA 1225: ��ȣ������
 * n���� ���� ó���Ͽ� 8�ڸ� ��ȣ�� �����Ѵ�.
 * 1. 10���� �׽�Ʈ��ŭ �ݺ��Ѵ�.
 * 2. 8���� ���ڸ� �Է� �޴´�.
 * 3. ù ��° ���ڸ� 1 ������ ��, �� �ڷ� ������ ���� ù ��° ���� 2, �� �ڷ�....5 ����, �� �ڷ� �ű�⸦ �ݺ��Ѵ�.
 * 4. ���ڰ� ������ �� 0���� �۾����� ��� 0���� �����Ǹ� ���α׷��� ����ȴ�. 8�ڸ��� ���� ��ȣ�� �ϼ��ȴ�.
 * */
public class SWEA_1225_��ȣ������ {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static final int T=10;						// ��ü �׽�Ʈ ���̽� ��
	static int N;								// �׽�Ʈ ���̽� ��ȣ
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();					// ��� ���ڿ��� �����ϱ� ���� ��ü ����
		
		for (int test_case=1;test_case<=T;test_case++) {	// 10���� �׽�Ʈ ���̽� �ݺ�
			N=Integer.parseInt(br.readLine().trim());		// �׽�Ʈ���̽� ��ȣ �Է�
			
			Deque<Integer> que=new ArrayDeque<>();			// 8���� ���ڸ� ������ queue
			st=new StringTokenizer(br.readLine().trim());	// 8�� ���ڸ� �Է� �ޱ�
			
			// queue�� ���� �߰�
			for (int idx=0;idx<8;idx++) {
				que.offer(Integer.parseInt(st.nextToken()));
			}
			// �� ���� ũ��
			int operand=1;
			
			// 0 �� ���� ������ �ݺ�
			while (true) {
				int target=que.poll();			// que���� �� ���ڸ� ���ڸ� �̴´�.
				int check=target-operand;		// operand��ŭ ���ҽ�Ų��.
				if (check<=0) {					// ����� ���� 0���� ���ų� ���� ���
					check=0;					// ��갪�� 0�� �����ϰ�
					que.offer(check);			// que�� �� �ڿ� �����Ѵ�.
					break;						// �ݺ��� �����Ų��.
				}
				que.offer(check);				// 0 �� �ƴ� ���, que�� ��� ���� �����ϰ�
				operand=(operand%5)+1;			// �� ���� ũ�⸦ �����Ѵ�.
			}
			sb.append("#"+test_case+" ");		// �׽�Ʈ���̽� ���� ��� ���ڿ��� �߰��Ѵ�.
			for (int idx=0;idx<8;idx++) {
				sb.append(que.poll()+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);					// ����� ����Ѵ�.
	}
}
