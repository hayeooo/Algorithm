package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * BOJ 3040: �鼳���ֿ� �ϰ�������
 * 
 * 9���� ������ ���ڿ� �����ִ� ���� �߿� 7�� ������ ���� ���� 100�� �Ǵ� ���� ���ڸ� ����Ѵ�.
 * 
 * */
public class BOJ_3040_�鼳���ֿ��ϰ�������_���Ͽ� {
	static BufferedReader br;
	static int[] arr;				// 9���� ������ ���� ���� ������ �����ϴ� �迭
	static int[] comb;				// ������ ������ ���� �ε��� ������ �����ϴ� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ���� ���� ������ �Է� �޴´�.
		arr=new int[9];	
		for (int idx=0;idx<9;idx++) {
			arr[idx]=Integer.parseInt(br.readLine());
		}
		
		// ������ ������ ������ �����ϴ� �迭 ũ�� �ʱ�ȭ
		comb=new int[7];
		
		// 7���� ���� ���� ������ �� ��,
		// ���� 100�� ��� 7���� ���� ���ڸ� ����Ѵ�.
		combinations(0,0,0);
	}
	// ���� ������ ���ϰ� ���� 100�� ��� 7���� ���� ���ڸ� ����Ѵ�.
	public static void combinations(int cnt, int startIdx,int sum) {
		if (cnt==7) {					// 7���� ������ �ϼ��� ���
			if (sum==100) {				// ���� 100�� ��, ���� ���ڵ��� ����Ѵ�.
				for (int combIdx=0;combIdx<7;combIdx++) {
					System.out.println(arr[comb[combIdx]]);
				}
			}
			return;
		}
		// ���� ���� ������ ����.
		for (int idx=startIdx;idx<9;idx++) {
			// ������ ���� �ε����� �����Ѵ�.
			comb[cnt]=idx;
			// ���õ� ���� ����, ������ ���� ���� �ε��� ���� ������, ������� ���ڼ��� ���� �Ű������� �����Ѵ�.
			combinations(cnt+1,idx+1,sum+arr[idx]);
		}
	}

}
