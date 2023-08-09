package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * BOU 11286 : ���� ��
 * 1. �迭�� 0�� �ƴ� ������ �ִ´�.
 * 2. �迭���� ������ ���� ���� ���� ����ϰ�, �� ���� �迭���� �����Ѵ�.
 * 	2-1. ������ ���� ���� ���� �������� ����, ���� ���� ���� ����ϰ�, �� ���� �迭���� �����Ѵ�.
 * 3. 0�� �־��� ȸ����ŭ ���� ����Ѵ�.
 * 4. �迭�� ������� �� ������ ���� ���� ���� ����϶�� �� ��쿡 0�� ����Ѵ�.
 * 
 * */
public class BOJ_11286_������_���Ͽ� {
	static BufferedReader br;
	static StringBuilder sb;				// ��� ���ڿ��� ������ ��ü
	static PriorityQueue<Integer> plus;		// ���� ������ ������ minHeap
	static PriorityQueue<Integer> minus;	// ���� ������ ������ minHeap
	
	static int N;						// ���� ����
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// ������ ���� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// �ּ� �� ��ü�� �����Ѵ�.
		plus=new PriorityQueue<Integer>();
		minus=new PriorityQueue<Integer>();
		
		// ���꿡 ���� ������ ��Ÿ���� ���� x�� �Է¹޴´�.
		for (int idx=0;idx<N;idx++) {
			int x=Integer.parseInt(br.readLine().trim());
			// 0 �� ��� ������ ���� ���� ������ ����Ѵ�.
			if (x==0) {					
				sb.append(getNum()+"\n");
				
			}
			// ������ ���, ������ �����س��� ���� ���� ���·� �����Ѵ�.
			else if (x<0) {
				minus.add(-x);
			}
			// ����� ���, ����� �����س��� ���� �����Ѵ�.
			else {
				plus.add(x);
			}
		}
		// ��� ���ڿ��� ����Ѵ�.
		System.out.println(sb);
		
	}
	// ������ ���� ���� ���� �����ϴ� �޼ҵ�
	public static int getNum() {
		int result=0;
		// �� ���� �� ��� ������� ���� ���
		if (!plus.isEmpty() && !minus.isEmpty()) {
			int plusCompare=plus.peek();
			int minusCompare=minus.peek();
			
			// ������ ũ�⸦ ���Ͽ� �� ���� ������ ���� ���Ҹ� �����Ѵ�.
			if (plusCompare<minusCompare) {
				result=plus.poll();
			}else if (plusCompare>=minusCompare) {	// ������ ���� ���, ũ�Ⱑ ���� ���� �����ؾ� �ϹǷ�
				result=-minus.poll();				// ������ ����ִ� ���� ���ڸ� �����Ѵ�.
				
			}
		}
		// ������ ����ִ� ���� ����ִ� ���, ����� ����ִ� ���ڸ� �����Ѵ�.
		else if(!plus.isEmpty()) {
			result=plus.poll();
			
		}
		// ����� ����ִ� ���� ����ִ� ���, ������ ����ִ� ���ڸ� �����Ѵ�.
		else if (!minus.isEmpty()) {
			result=-minus.poll();
			
		}
		// �� ���� �� ��� ����ִ� ��� 0�� �����Ѵ�.
		else {
			result=0;
		}
		return result;
	}
}
