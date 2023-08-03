package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 2961: �����̰� ���� ���ִ� ����
 * 
 * 1. ����� ���� N�� �Է� �޴´�.
 * 2. �� ����� �Ÿ��� ������ �迭�� �����Ѵ�.
 * 3. ������ �ִ� ����� �κ� ������ ���ϰ� �Ÿ��� ������ ���̸� ����Ѵ�.
 * 4. ��� ��츦 Ž���ϰ� �Ÿ��� ������ ���̰� ���� ���� �丮�� ���̸� ��ȯ�Ѵ�.
 * */
public class BOJ_2961_�����̰�������ִ����� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;								// ����� ����
	static int[] sour;							// �� ���� �迭
	static int[] bitter;						// �� ���� �迭
	static List<boolean[]> comb;				// ����� ����
	static boolean[] isSelected;				// �� ��ᰡ ���õǾ����� ������ �迭�� ����
	static int diff=Integer.MAX_VALUE;			// �Ÿ��� ������ ���̰� ���� ���� ��
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ����� ���� �Է�
		N=Integer.parseInt(br.readLine());
		
		// �� ���� �� ���� ���� ���� ����
		sour=new int[N];
		bitter=new int[N];
		
		// ����� ������ŭ �� ���� �� ���� ����
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine());
			sour[idx]=Integer.parseInt(st.nextToken());
			bitter[idx]=Integer.parseInt(st.nextToken());
		}
		// ���õ� ��� ������ ���� �迭 ũ�� �ʱ�ȭ
		isSelected=new boolean[N];
		
		// ��� ����� ������ ���� comb ��ü ����
		comb=new ArrayList<boolean[]>();
		
		// ����� ������ ���� -> comb�� ��� ����
		getSubSet(0,0);
		
		// ��� ���տ� ���� �� ��, �� ���� ���̸� ���� ���̰� ���� ���� ��� ����
		getScore();
		
		// ��� ���
		System.out.println(diff);
	}
	// ����� �κ� ������ ����
	public static void getSubSet(int cnt,int chosen) {		// cnt: ������� ����� ����� ����, chosen: ������ ���õ� ����� ����
		if (cnt==N) {										// ��� ��ᰡ ����Ǿ��� ��
			if (chosen>0) {									// �������� �����ϰ�
				comb.add(Arrays.copyOf(isSelected, N));		// ���õ� ��� ������ ����
			}
			return;
		}
		
		isSelected[cnt]=true;								// ���� ��Ḧ ����
		getSubSet(cnt+1,chosen+1);							// ���� ��Ḧ ���
		isSelected[cnt]=false;								// ���� ��Ḧ �������� ����
		getSubSet(cnt+1,chosen);							// �������� ���� ä�� ���� ��� ���
	}
	// �κ������� ���� ���
	public static void getScore() {
		for (int idx=0;idx<comb.size();idx++) {				// ��� ���տ� ���ؼ�
			boolean[] cuisine_combi=comb.get(idx);			// �ϳ��� ���� ��������
			int sourTotal=1;
			int bitterTotal=0;
			for (int cuisineIdx=0;cuisineIdx<N;cuisineIdx++) {	// �� ��� ����
				if (cuisine_combi[cuisineIdx]) {				// ���õ� �����
					sourTotal*=sour[cuisineIdx];				// ���� ��꿡 �ݿ�
					bitterTotal+=bitter[cuisineIdx];
				}
			}
			// ���̰� ���� ���� ������ ������Ʈ
			diff=Math.min(diff, Math.abs(sourTotal-bitterTotal));
		}
	}

}
