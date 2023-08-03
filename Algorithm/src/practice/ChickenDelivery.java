package practice;
/*
 * ����(�ߺ�X)
 * N: ġŲ �� ����, M: M �� ����/ index ������ ���� ���ϱ�
 * ���� ���� {ġŲ�� 1, ġŲ��2, ġŲ��3}�� �ִٸ� �� ���ô� ���� ���� ġŲ �� �Ÿ��� �����Ѵ�.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 1.  ġŲ �� ����(chicken) ���ϱ�+ġŲ �� ��ġ �迭�� �ֱ�
 * 2. 0~chicken-1��ŭ�� �ε����� ���� ���
 * 3. �� ���տ� ����ִ� ġŲ ���������� �Ÿ� ���ϱ�
 * */
public class ChickenDelivery {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static int[][] map;						// ���� ����
	static List<int[]> chickenLoc;				// ġŲ�� ��ġ
	static List<int[]> houseLoc;				// �� ��ġ
	static List<int[]> chickenComb;						// ġŲ�� ����, �ε���
	static int[] comb;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		chickenLoc=new ArrayList<int[]>();
		houseLoc=new ArrayList<int[]>();
		chickenComb=new ArrayList<int[]>();
		
		// �Է�
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		
		// ��, ġŲ�� ��ġ ���� ����
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine());
			for (int col=0;col<N;col++) {
				int info=Integer.parseInt(st.nextToken());
				if (info==1) {						// ���� ���
					houseLoc.add(new int[] {row,col});
				}else if(info==2) {					// ġŲ���� ���
					chickenLoc.add(new int[] {row,col});
					continue;
				}
				map[row][col]=info;
			}
		}
		
		// ġŲ�� ���� �ϼ��ϱ�
		// combinations �޼ҵ� ���� ��, M���� ġŲ�� ������ chickenComb�� �����.
		comb=new int[M];
		combinations(0,0);
		
		// ġŲ �Ÿ� ���ϱ� ����Ž��
		// chickenComb �� ���տ� ���ؼ�
		// ������ �Ÿ� ���ϱ�
		int[] tmp=new int[houseLoc.size()];
		for (int combIdx=0;combIdx<chickenComb.size();combIdx++) {
			Arrays.fill(tmp, Integer.MAX_VALUE);
			for (int idx=0;idx<M;idx++) {
				int chickenIdx=chickenComb.get(combIdx)[idx];
				int chickenRow=chickenLoc.get(chickenIdx)[0];
				int chickenCol=chickenLoc.get(chickenIdx)[1];
				
				for (int houseIdx=0;houseIdx<houseLoc.size();houseIdx++) {
					// ���� ġŲ�� ���� ���밪 ���ϱ�
					// �̹� ����Ǿ� �ִٸ�(�ٸ� ġŲ ���� �Ÿ�) �񱳸� ���� �� ���� ġŲ �Ÿ� �ֱ�
				}
			}
			// �հ�� result �� -> �� ���� �� �ֱ�
		}
		
	}
	// ġŲ�� ���� ���ϱ�
	public static void combinations(int cnt, int start) {
		if (cnt==M) {
			chickenComb.add(comb);
			return;
		}
		for (int idx=start;idx<chickenLoc.size();idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
}
