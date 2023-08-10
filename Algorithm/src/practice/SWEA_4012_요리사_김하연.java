package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 4012: �丮��
 * N ���� ����Ḧ N/2���� ������ �� ���� �丮�� �Ѵ�.
 * �� ������ ���� �ּҰ� �ǵ��� ��Ḧ ����Ѵ�.
 * ����� ���� ����� ���� �ó����� ������ �����ȴ�.
 * 
 * 1. �׽�Ʈ���̽� ������ �Է� �޴´�.
 * 2. ������� ���� �Է� �޴´�.
 * 3. N/2���� ������ ���Ѵ�.
 * 4. �� ������� �ó����� ���ϰ� �� ���� ������ �ó����� ����.
 * 5. ������ ���̰� ���� ���� ���� �����Ѵ�.
 * */
public class SWEA_4012_�丮��_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;							// �׽�Ʈ���̽� ����
	static int N;							// ����� ����
	static int[][] s;						// ����� ���� �ó��� ������ ���� �迭
	static int[] A;							// A ���� ���� ����
	static int[] B;							// B ���� ���� ����
	static boolean[] selected;				// A ���Ŀ� �� ����� ���� ���θ� ���� �迭
	static int diff;						// ���� ���� ���� �ּҰ��� ����Ǵ� ����
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ���̽� ���� �Է� �޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		// �׽�Ʈ���̽� ����
		for (int test_case=1;test_case<=T;test_case++) {
			// ������� �� �Է� �ޱ�
			N=Integer.parseInt(br.readLine().trim());
			
			// �ó��� ������ ������ �迭�� �����Ѵ�.
			s=new int[N][N];
			
			// �ó��� ������ �Է� �޴´�.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					s[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// A���İ� B������ ũ�⸦ �ʱ�ȭ�Ѵ�.
			A=new int[N/2];
			B=new int[N/2];
			// ���� �� ���̸� �ִ�� �����Ѵ�.
			diff=Integer.MAX_VALUE;
			// ������� ������ �����ϰ�, �� ���տ��� �ó��� ���� ���Ѵ�.
			// �� ������ �ó��� ���� �� ���� �ּڰ��� �����Ѵ�.
			combinations(0,0);
			
			// �׽�Ʈ���̽� ��ȣ�� ���� �� ������ �ּڰ��� ��� ���ڿ��� �����Ѵ�.
			sb.append("#"+test_case+" "+diff+"\n");
		}
		// ����� ����Ѵ�.
		System.out.println(sb);
		
	}
	// A ������ ����� ������ ����.
	// A ���Ŀ� ������ �ʰ� ���� ������ B���Ŀ� ���ȴٰ� �����Ѵ�.
	// �� ���� ������� �ó��� ���� ���Ѵ�.
	// �ó��� ���� ������ �ּڰ��� �����Ѵ�.
	public static void combinations(int cnt,int startIdx) {
		// N/2 ũ�⸸ŭ ����Ḧ �������� ���
		if (cnt==(int)(N/2)) {
			
			selected=new boolean[N];
			// A���Ŀ� �� ������ true�� �����Ѵ�.
			for (int idx=0;idx<N/2;idx++) {
				selected[A[idx]]=true;
			}
			// true�� �ƴ� ������ B���Ŀ� ���� ������ �����Ѵ�.
			int bIdx=0;
			for (int idx=0;idx<N;idx++) {
				if (selected[idx]) continue;
				B[bIdx++]=idx;
			}
			// �ó����� ���Ѵ�.
			int synergyA=getSynergy(A);
			int synergyB=getSynergy(B);
			
			// �ó��� ������ �ּҰ��� �����Ѵ�.
			diff=Math.min(diff, Math.abs(synergyA-synergyB));
			return;
		}
		// ���� ��ġ�� ���� ����Ḧ �����Ѵ�.
		for (int idx=startIdx;idx<N;idx++) {
			A[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	// �� ������ �ó��� ���� �����Ѵ�.
	public static int getSynergy(int[] arr) {
		int total=0;
		// ��� ������� Sij�� Sji ���� ���Ѵ�.
		for (int i=0;i<arr.length-1;i++) {
			for (int j=i+1;j<arr.length;j++) {
				total+=s[arr[i]][arr[j]];
				total+=s[arr[j]][arr[i]];
			}
		}
		return total;
	}
	
	
	
}

