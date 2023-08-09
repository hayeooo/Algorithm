package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 21610: ������ ���� ��ٶ��
 * 1. �񱸸��� (N-1,1) ��ġ���� ���� 2, ���� 2�� ������ �����.
 * 2. ������ �̵��� M�� ����Ѵ�. ����� ���� d�� �Ÿ� s�� �̷������.
 * 3. ��� ������ d �������� sĭ �̵��Ѵ�.
 * 4. �� ������ �ִ� ĭ�� �ٱ��Ͽ� ����� ���� ���� 1 �����Ѵ�.
 * 5. ������ ��� �������.
 * 6. 4���� ���� ������ ĭ���� ������ �����Ѵ�. �밢�� �������� �Ÿ��� 1�� ĭ�� ���� �ִ� �ٱ����� ����ŭ (r,c)�� �ִ� �ٱ����� ���� ���� �����Ѵ�.
 * 7. �ٱ��Ͽ� ����� ���� ���� 2 �̻��� ��� ĭ�� ������ �����, ���� ���� 2 �پ���.
 * */
public class BOJ_21610_��������ͺ�ٶ��_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// N*N ����
	static int M;					// M���� �̵�
	static int[][] map;				// ���� ĭ ����
	static List<int[]> clouds;		// ���� ��ġ ����
	static boolean[][] cloudHistory;
	static int[][] dir= {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int d;
	static int s;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N�� M ���� �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// ���� ũ�⸦ �ʱ�ȭ�Ѵ�.
		map=new int[N][N];
		
		// ���� ĭ ������ �Է� �޴´�.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		// ���� ��ġ �ʱ�ȭ
		clouds=new ArrayList<>();
		clouds.add(new int[] {N-1,0});
		clouds.add(new int[] {N-1,1});
		clouds.add(new int[] {N-2,0});
		clouds.add(new int[] {N-2,1});
		
		// M ���� �̵� ����
		for (int m=0;m<M;m++) {
			st=new StringTokenizer(br.readLine().trim());
			d=Integer.parseInt(st.nextToken());
			s=Integer.parseInt(st.nextToken());
			
			cloudHistory=new boolean[N][N];
			
			// ��� ������ d �������� sĭ �̵��Ѵ�.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int nextRow=(clouds.get(cloudIdx)[0]+dir[d-1][0]*s)%N;
				int nextCol=(clouds.get(cloudIdx)[1]+dir[d-1][1]*s)%N;
				
				// ������ ���, �迭 �� ������ ��ȯ
				if (nextRow<0) {
					nextRow+=N;
				}
				if (nextCol<0) {
					nextCol+=N;
				}
				// ������ �̵����� ���� ��ġ�� ����
				clouds.get(cloudIdx)[0]=nextRow;
				clouds.get(cloudIdx)[1]=nextCol;
				cloudHistory[nextRow][nextCol]=true;
			}
			// ���� ĭ�� �ִ� �ٱ��Ͽ� ���� ���� ������Ų��.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int[] cloudLoc=clouds.get(cloudIdx);
				int cloudRow=cloudLoc[0];
				int cloudCol=cloudLoc[1];
				map[cloudRow][cloudCol]+=1;
			}
			// �������� �� ���� ���� ������ ĭ�� ���ؼ� ������ �����Ѵ�.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int[] cloudLoc=clouds.get(cloudIdx);
				int cloudRow=cloudLoc[0];
				int cloudCol=cloudLoc[1];
				
				// �밢�� �������θ� ������ �θ���.
				for (int dirIdx=1;dirIdx<=7;dirIdx+=2) {
					int checkRow=cloudRow+dir[dirIdx][0];
					int checkCol=cloudCol+dir[dirIdx][1];
					// �迭 ���� �� ��ġ�ؾ� �Ѵ�.
					if (checkRow<0 || checkRow>=N || checkCol<0 || checkCol>=N) {
						continue;
					}
					// �밢�� ���⿡ �ִ� �ٱ��� �� ���� ����ִ� ������ ���Ѵ�.
					if (map[checkRow][checkCol]!=0) {
						map[cloudRow][cloudCol]+=1;
					}
				}
			}
			// ������ �������.
			clouds=new ArrayList<>();
			
			// ���� ���� 2 �̻��� ������ �����Ѵ�.
			// cloudHistory�� ���� ������ ����� ĭ�� �ƴ� ĭ �߿� ���� ���� 2 �̻��� ������ �����Ѵ�.
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					if (map[row][col]>=2 && !cloudHistory[row][col]) {
						map[row][col]-=2;
						clouds.add(new int[] {row,col});
					}
				}
			}
		}
		// ��� �̵��� ��ģ ��,
		// �ٱ��Ͽ� ����ִ� ���� ���� ��� ���Ѵ�.
		int total=0;
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				total+=map[row][col];
			}
		}
		// ����� ����Ѵ�.
		System.out.println(total);
		
		
		
	}
}
