package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * ����, �ߺ� X
 * ������ �ǹ̰� ���� ������ �̹� ������ ���ڴ� ����
 * ����� ���� ���� �� ���� ������ �ִ� ���ڵ��� ���� ����̴�.
 * */
public class ���� {
	
	static BufferedReader br;
	
	static int N;			// 1..N���� ���� ����
	static int R;			// ���� ������ ����
	static int[] res;		// ����� ���� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �Է�
		N=Integer.parseInt(br.readLine().trim());
		R=Integer.parseInt(br.readLine().trim());
		
		// ����� ���� �迭 ũ�� �ʱ�ȭ
		res=new int[R];
		
		// ���� �޼ҵ� ����
		combinations(0,1);
	}
	
	public static void combinations(int cnt, int start) {	// cnt: ���� ������ ����, start: ���� ������ ���� ����
		if (cnt==R) {										// R ��ŭ ���ڸ� ����� ��� ��� ��� �� ��� ����
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int num=start;num<=N;num++) {					// ���� �������� ������ ���ڸ� ����
			res[cnt]=num;
			combinations(cnt+1,num+1);						// ���� �ڸ� ��, ���� ã��(�ߺ� ����)
		}
	}

}
