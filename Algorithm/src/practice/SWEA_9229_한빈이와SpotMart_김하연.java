package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N���� ���ں����� index�� 2���� ���� ������ �̴� ������ ���Ѵ�.
 * ���� �� ������ ���԰� M �׷��� �ʰ����� �����鼭 ��� �ٴ� �� �ִ� �ִ� ���� ���� ���Ѵ�.
 * 
 * 1. �׽�Ʈ���̽� �� �Է� �ޱ�
 * 2. ���� ������ ����(N)�� ���� �� ����(M) �Է� �ޱ�
 * 3. N���� ���� ���� ���� �Է� �ޱ�
 * 4. ���� ���� ����ŭ �ε����� 2���� �̴� ���� ���ϱ�
 * 5. 2���� �̾��� ���, ���� ������ ���� ���ϱ�
 * 	5-1. ���� �� ���Ѻ��� Ŭ ��, ����
 * 	5-2. ���� �� ���Ѻ��� ���� ��, ���� ���� ���Ͽ� ���� ū �� ����
 * */
public class SWEA_9229_�Ѻ��̿�SpotMart_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int TC;					// �׽�Ʈ ���̽� ��
	static int N;					// ���� ���� ��
	static int M;					// ���� ���� ����
	static final int COUNT=2;		// ��� �� �� �ִ� ���� ���� ��
	static int res;					// �ִ� ���� ���� ��
	static int[] comb;				// ���� ������ ��� �迭
	static int[] snacks;			// ���� ���� ������ ��� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ���̽� �� �Է�
		TC=Integer.parseInt(br.readLine());
		
		for (int tc=1;tc<=TC;tc++) {
			res=-1;										// ��� ������ ���� ���� ���� ���� ���� ���Ը� �Ѵ� ��� -1 ���
			st=new StringTokenizer(br.readLine());
			// N�� M �Է� �޴´�.
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			// ���� ���� ����ŭ �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
			snacks=new int[N];
			
			// ��� �� �� �ִ� ���� ���� index�� �����ϴ� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
			comb=new int[COUNT];
			
			// ���� ���� ���Ը� �Է� �޴´�.
			st=new StringTokenizer(br.readLine());
			for (int idx=0;idx<N;idx++) {
				snacks[idx]=Integer.parseInt(st.nextToken());
			}
			// 2���� ���� ���� ������ ���� �ִ� ���� ���� ���Ը� ���Ѵ�.
			combinations(0,0);
			
			// ����� ����Ѵ�.
			System.out.println("#"+tc+" "+res);
		}
		
	}
	
	public static void combinations(int cnt,int startIdx) {
		// �� ���� ���� ������ COUNT�� ��
		if (cnt==COUNT) {
			// �� ������ ���� ���� ���Ը� ���Ѵ�.
			int total=0;
			for (int idx=0;idx<COUNT;idx++) {
				total+=snacks[comb[idx]];
			}
			// ���� ������ ���� ���� ���,
			// ������ ��� ū ���Ը� ��������� �ִ´�.
			if (total<=M) {
				res=Math.max(total, res);
			}
			// ��͸� �����Ѵ�.
			return;
		}
		// ���� ���� �� �ε����� ���� ������ �����Ѵ�.
		for (int snackIdx=startIdx;snackIdx<N;snackIdx++) {
			comb[cnt]=snackIdx;
			combinations(cnt+1,snackIdx+1);		// �ߺ� ������ �ƴϹǷ�, ������ �ε����� ���� �ε������� Ž���ϵ��� �Ѵ�.
		}
	}
}
