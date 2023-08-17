package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * �ٶ󺸰� �ִ� ������ �������� ������, ��, ����, �� ������ Ȯ���ϸ鼭 �̵��Ѵ�.
 * 
 * ����
 * ���Ŀ� �̵��� �� �ִ� ������ �ִٸ�, �Ʒ� 2���� �� �ϳ��� �����Ѵ�.
 * 1. ������ �ɴ´�.
 * 2. �� �ڶ� ���۹��� ��Ȯ�Ѵ�.
 * �̵��� �� ���ٸ� �� �ڸ��� �״�� �ְ� �ȴ�.
 * 
 * ����
 * �̵��� �� �ִ� �������� �̵��Ѵ�.
 * 
 * ������ ���� �� K+3���� ���� ��Ȯ�� �� �� �ִ�.
 * �꿡�� ���۹��� ��Ȯ�� �� ����.
 * 
 * �κ��� ���ϴ� �Ⱓ ���� �ִ�� ��Ȯ�� �� �ִ� ���۹��� ����
 */
public class ������ǳ��۹���Ȯ�ϱ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;							// �׽�Ʈ ���̽� ��
	static int N;							// ������ ũ��
	static int D;							// �κ��� ���ϴ� �Ⱓ
	static int[][] map;						// ������ ĭ ������ ������ �迭
	static List<int[]> plants;				// ���۹��� �����ϴ� ����Ʈ (��, ��, ���� ȸ��)
	static int[][] isplanted;				// �� ȸ���� ���۹��� �ɰ���� Ȯ���ϴ� �迭
	
	static int[] dx= {-1,0,1,0};			// ��, ��, ��, ��
	static int[] dy= {0,-1,0,1};
	static int cd;							// ���� �ٶ󺸰� �ִ� ����

	static int maxPlantCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br= new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ���̽� ���� �Է� �޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// ������ ũ�� N, �κ��� ���ϴ� �Ⱓ�� �Է� �޴´�.
			N=Integer.parseInt(st.nextToken());
			D=Integer.parseInt(st.nextToken());
			
			// ������ ũ�⸦ �ʱ�ȭ�Ѵ�.
			map=new int[N][N];
			isplanted=new int[N][N];
			
			// N���� �ٿ� ������ �� ĭ�� ������ �Է� �޴´�. 1: ��, 0: ���۹�
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			maxPlantCnt=0;
			// �κ��� ��ġ�� ������ ������� �Ѵ�.
			// ���۹��� ���� �� �ִ� ��ġ���� �����ؾ� �ϳ��� ������ �� ���� �� �ִ�.
//			for (int row=0;row<N;row++) {
//				for (int col=0;col<N;col++) {
//					if (map[row][col]==0) {
//						// ��, ��, ��, �� �������� �ٶ� �� �ִ�.
//						for (int d=0;d<4;d++) {
//							// Ư�� ������ �ٶ� ä�� ���� �����Ѵ�.
//							work(row,col,d);
//						}
//					}
//				}
//			}
			work(3,3,0);
			//System.out.println(maxPlantCnt);
			
		}
		
		
	}
	// �κ��� ���� �Ѵ�.
	public static void work(int row, int col, int dir) {
		// �κ��� ó�� �����ϴ� ��ġ�� ����
		int curRow=row;
		int curCol=col;
		int curDir=dir;
		
		// ��Ȯ�� ���۹� ��
		int plantCnt=0;
		
		for (int curStage=1;curStage<=D;curStage++) {
			System.out.println("======="+curStage);
			System.out.println(curRow+" "+curCol);
			// ����
			// ���Ŀ� �̵��� �� �ִ� ������ �ִٸ�
			
			// �κ��� ������ ��ġ
			int startDir=(curDir-1)%4;
			if (startDir<0) {
				startDir+=4;
			}
			// ��Ȯ�� �� �ִ� ���۹��� ���� ��Ȯ�Ѵ�.
			boolean flag=false;
			
			// ��Ȯ�� �� �ִ� ���۹��� ã�ƺ���.
			for (int idx=0;idx<4;idx++) {
				// ������, ��, ����, �� ������ ����.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// �迭�� ������ ����� �ʾ��� ���
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && isplanted[nextRow][nextCol]<curStage) {
						isplanted[nextRow][nextCol]=0;
						plantCnt+=1;
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
						flag=true;
						break;
					}
					
				}
			}
			
			if (flag) continue;
			
			flag=false;
			
			// ��Ȯ�� ���۹��� ���� ���
			// ���۹��� �ɰ������� ���� ���
			for (int idx=0;idx<4;idx++) {
				// ������, ��, ����, �� ������ ����.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// �迭�� ������ ����� �ʾ��� ���
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && isplanted[nextRow][nextCol]==0) {
						isplanted[nextRow][nextCol]=curStage+3;
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
						flag=true;
						break;
					}
				}
			}
			if (flag) continue;
			
			int recentStage=0;
			// �ɰ��� �ִ� ���۹� �߿� ���� �ֱٿ� �ɾ��� �ִ� ��ġ�� �̵��Ѵ�.
			for (int idx=0;idx<4;idx++) {
				// ������, ��, ����, �� ������ ����.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// �迭�� ������ ����� �ʾ��� ���
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && recentStage<isplanted[nextRow][nextCol]) {
						recentStage=isplanted[nextRow][nextCol];
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
					}
					isplanted[nextRow][nextCol]=curStage;
				}
			}
			
			
		}
		// D ���� �̵��� ���� ��
		maxPlantCnt=Math.max(maxPlantCnt, plantCnt);
		
	}
}
