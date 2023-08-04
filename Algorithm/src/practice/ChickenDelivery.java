package practice;
/*
 * BOJ ġŲ ���
 * ����(�ߺ�X)�� �̿��Ͽ� ��ġ�� �� �ִ� ġŲ�� ���� ����
 * 1. N: ġŲ �� ����, M: �ִ� ġŲ �� ���� �Է� �ޱ�
 * 2. ���� ������ �Է� �����鼭 �� ��ġ, ġŲ �� ��ġ�� �迭�� �����Ѵ�.
 * 3. ġŲ �� ��ġ�� ���� �迭���� �ε����� �������� ������ �����.
 * 4. ��ü ġŲ �� ���� ���̸�ŭ �ݺ��ϸ�
 *  4-1. �ϳ��� ġŲ �� ���հ�
 *  4-2. ��� ���� ġŲ�Ÿ��� ����ϰ�
 *  4-3. ������ ���� ���� ���� ġŲ �Ÿ��� �����Ѵ�.
 *  4-4. �ϳ��� ������ ������ ������ ���� �ּ� ġŲ �Ÿ��� ���Ѵ�.
 *  4-5. ��� ������ �ݺ��ϸ� ���� ���� ġŲ �Ÿ����� ����� ����Ѵ�.
 *  
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
	
	static int N;							// N*N ũ�⸦ ���� ���� ����
	static int M;							// ġŲ���� �ִ� ���� M
	
	static List<int[]> chickenLoc;				// ġŲ�� ��ġ
	static List<int[]> houseLoc;				// �� ��ġ
	static List<int[]> chickenComb;						// ġŲ�� ����, �ε���
	static int[] comb;
	static int result=Integer.MAX_VALUE;
	static int minDist;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		chickenLoc=new ArrayList<int[]>();				// ġŲ�� ��ġ�� ������ ����
		houseLoc=new ArrayList<int[]>();				// �� ��ġ�� ������ ����
		chickenComb=new ArrayList<int[]>();				// ġŲ�� ������ ������ ����
		
		// �Է�
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		
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
			}
		}
		
		// ġŲ�� ���� �ϼ��ϱ�
		// combinations �޼ҵ� ���� ��, M���� ġŲ�� ������ chickenComb�� �����.
		comb=new int[M];
		combinations(0,0);
		
		// ġŲ �Ÿ� ���ϱ� ����Ž��
		// chickenComb �� ���տ� ���ؼ�
		// ������ �Ÿ� ���ϱ�
		for (int combIdx=0;combIdx<chickenComb.size();combIdx++) {				// ���� ���� ġŲ�� combination ��
			// ����Ǿ� �ִ� �� (�� ��)�� combination �� ġŲ �� �� ���� ª�� �Ÿ� ����
			
			int[] pickedChickens=chickenComb.get(combIdx);						// �ϳ��� ġŲ�� ������ ����.
			int tmpSum=0;						
			for (int houseIdx=0;houseIdx<houseLoc.size();houseIdx++) {			// ��� �� ��
				int houseRow=houseLoc.get(houseIdx)[0];							//�ϳ��� ����
				int houseCol=houseLoc.get(houseIdx)[1];
				int minDist=Integer.MAX_VALUE;
				
				
				for (int chickenIdx=0;chickenIdx<M;chickenIdx++) {				// ���õ� ġŲ�� ���� �ȿ� ����ִ� ġŲ���� ���� ��� ġŲ�Ÿ��� ���Ѵ�.
					int chickenRow=chickenLoc.get(pickedChickens[chickenIdx])[0];
					int chickenCol=chickenLoc.get(pickedChickens[chickenIdx])[1];
					
					int diff=Math.abs(houseRow-chickenRow)+Math.abs(houseCol-chickenCol);	
					minDist=Math.min(minDist, diff);							// ���� ª�� ġŲ �Ÿ��� �����Ѵ�.
				}
				// ��������� minDist���� ġŲ ���� �ȿ��� ���� ����� ġŲ������ �Ÿ��� ����Ǿ� �ִ�.
				tmpSum+=minDist;
			}
			// ������ �� ���� �ִ� ġŲ ��ΰ� ����Ǿ��ְ�, �ٸ� ġŲ�� ���� ġŲ �Ÿ��� ���Ͽ� ���� ª�� ġŲ �Ÿ��� ������Ʈ �Ѵ�.
			result=Math.min(result, tmpSum);
		}
		
		// ��� ���
		System.out.println(result);
	}
	// ġŲ�� ���� ���ϱ�(chickenLoc�� index ����)
	public static void combinations(int cnt, int start) {	// cnt: ������� ������ ������ ����, start: ������ ������ ���� ����
		if (cnt==M) {
			int[] tmp=Arrays.copyOf(comb, M);
			chickenComb.add(tmp);
			return;
		}
		for (int idx=start;idx<chickenLoc.size();idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
}
