package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * ArrayList�� ����� ���� ����
 * 1. ��Ʈ�� �� ĭ���� �ִ� �κ��� �Բ� ȸ��
 * 2. ���� ���� ��Ʈ�� �ö� �κ�����, ��Ʈ�� ȸ���ϴ� �������� �� ĭ �̵��� �� �ִٸ� �̵�, �̵��� �� ���ٸ� ������ �ִ´�.
 * 3. �ø��� ��ġ�� �ִ� ĭ�� �������� 0�� �ƴϸ� �ø��� ��ġ�� �κ��� �ø���.
 * 4. �������� 0�� ĭ�� ������ K�� �̻��̶�� ������ ����
 * 
 * �κ��� �ø��� ��ġ�� �ø��ų� �κ��� � ĭ���� �̵����� �� ĭ�� �������� 0���� Ȯ��
 * 
 * */
public class BOJ_20055_�����̾Ʈ���Ƿκ� {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;					// A1...AN���� ĭ
	static int K;					// 0�� K�� �̻��̶�� ���� ����
	static List<int[]> conveyer;	// �����̾� ��Ʈ
	static int stage=0;				// �ܰ�
	static int zeroCount=0;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		// N, K �Է�
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		// �����̾Ʈ �ʱ�ȭ
		st=new StringTokenizer(br.readLine());
		conveyer=new ArrayList<int[]>();
		for (int idx=0;idx<2*N;idx++) {
			int durability=Integer.parseInt(st.nextToken());
			if (durability==0) {
				zeroCount+=1;
			}
			conveyer.add(new int[] {0,durability});	// �κ��� ������ 1, ������ 0
		}
		
		// 0�� ������ K�̸��� ��
		while (zeroCount<K) {
			stage+=1;					// ���� �ܰ踦 1 �߰�
			
			// ��Ʈ�� �� ĭ ���� �ִ� �κ��� �Բ� �� ĭ ȸ��
			// ������ ĭ�� �� ù��° ĭ���� �ű��.
			int[] moved=conveyer.remove(2*N-1);
			conveyer.add(0,moved);
			
			// ȸ���Ͽ��� ��, N-1 ��ġ�� �κ��� ���� ���
			// �κ��� ������.
			conveyer.get(N-1)[0]=0;
			
			// ���� ���� ��Ʈ�� �ö� �κ�����, ��Ʈ�� ȸ���ϴ� �������� �̵�
			for (int idx=N-2;idx>=0;idx--) {		// N-2 ��ġ���� 0��°���� ������� �κ��� ���������� �ű��.
				int[] cur=conveyer.get(idx);		// ���� ĭ�� �������� �κ� ����
				if (cur[0]==1) {					// �ش� ĭ�� �κ��� �ִٸ�
					int[] next=conveyer.get(idx+1);	// ���� ĭ���� �ű� ĭ ���� �����´�.
					if (next[0]==0 && next[1]>=1) {	// ���� ĭ�� �κ��� ����, �������� 1 �̻��ΰ��
						cur[0]=0;					// �κ��� �ű��.
						next[0]=1;
						next[1]-=1;					// �������� 1 �ٿ��� ��, 0�� ���´ٸ�
						if (next[1]==0) zeroCount+=1;	// 0�� ������ �߰��Ѵ�.
						if (idx+1==N-1) next[0]=0;		// �ű� ��ġ�� �κ��� ������ ��ġ��� �κ��� ������.
					}
				}
			}
			// �κ��� �ø��� ���
			int[] start=conveyer.get(0);
			// �ø��� ��ġ�� ĭ�� �������� 0�� �ƴ϶�� �κ� �ø�
			if (start[1]>0) {
				start[0]=1;
				start[1]-=1;
				// �÷��� ��, �������� 0�̶�� 0�� ���� �߰�
				if (start[1]==0) {
					zeroCount+=1;
				}
			}
		}
		// 0�� ������ �����Ǿ��ٸ�, ������ �ܰ� �� ���
		System.out.println(stage);
	}
}
