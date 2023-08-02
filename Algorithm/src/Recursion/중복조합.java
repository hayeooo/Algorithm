package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * �ߺ� ����
 * ������ �ǹ̰� ������, �ߺ��� ���ڸ� ����� �� �ִ�.
 * �̹� ������ ���� ����(�ߺ��� ����)�Ͽ� ���� ������ ���ڸ� �����Ѵ�.
 * */
public class �ߺ����� {
	static BufferedReader br;
	static int N;					// 1..N ������ ���� ���
	static int R;					// R ������ ����
	static int[] res;				// ����� ���� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �Է�
		N=Integer.parseInt(br.readLine());
		R=Integer.parseInt(br.readLine());
		
		// ����� ���� �迭 ũ�� �ʱ�ȭ
		res=new int[R];
		
		// �ߺ� ���� �޼ҵ� ����
		dupComb(0,1);
	}
	
	public static void dupComb(int cnt,int start) {			// cnt: ������� ���� ������ ����, start: ����� ���� ������ ���� ����
		if (cnt==R) {										// R ���̸� ������ ���, ��� ��� �� ��� ����
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int num=start;num<=N;num++) {					// ���� �������� ������ ���� ��� �迭�� �ֱ�
			res[cnt]=num;
			dupComb(cnt+1,num);								// �� ����(�ߺ� ����)���� ���� �ڸ� ���ڸ� ã�´�.
		}
	}

}
