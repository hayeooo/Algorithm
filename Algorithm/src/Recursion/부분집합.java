package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * �κ�����
 * N���� ���ڸ� �Է� �޾� 0~N���� ������ ���
 * ��� ���ڴ� 2���� ��츦 ������.
 * 	1. �ش� ���ڰ� ���Ե� ���
 * 	2. �ش� ���ڰ� ���Ե��� ���� ���
 * ����, �κ������� 2^N���� ���´�.
 * */
public class �κ����� {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;							// ������ ����
	static int R;							// �κ����� �ִ� ũ��
	static int[] numbers;					// ���ڸ� �����ϴ� �迭
	static boolean[] isSelected;				// ���ڸ� ����ߴ��� ���θ� ��� �迭
	
	public static void main(String[] args) throws IOException {
		
		// �Է�
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		// ���ڸ� ������ �迭 ũ�� �ʱ�ȭ
		numbers=new int[N];
		// ���ڰ� ���õǾ����� ���θ� �Ǵ��ϱ� ���� �迭 ũ�� �ʱ�ȭ
		isSelected=new boolean[N];
		
		// �迭 �Է� ����
		st=new StringTokenizer(br.readLine());
		for (int idx=0;idx<N;idx++) {
			numbers[idx]=Integer.parseInt(st.nextToken());
		}
		// �κ����� �޼ҵ� ȣ��
		getSubset(0);
	}
	
	public static void getSubset(int cnt) {	// cnt: ����� ������ ����
		if (cnt==N) {						// ��� ���ڸ� ����Ͽ��ٸ�
			for (int idx=0;idx<N;idx++) {	// ���õ� ���ڵ��� ���
				if (isSelected[idx]) {
					System.out.print(numbers[idx]+" ");
				}
			}
			System.out.println();
			return;
		}
		isSelected[cnt]=true;				// �ش� ���ڸ� �־��� ��,
		getSubset(cnt+1);					// �κ����� ���ϱ�
		
		isSelected[cnt]=false;				// �ش� ���ڸ� ���� �ʾ��� ��,
		getSubset(cnt+1);					// �κ����� ���ϱ�
	}

}
