package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA ������ġ
 * N*N ũ���� �ʿ� 1*1 ũ��� �������� �� ĭ�� ������ ���ڰ� ����ִ�.
 * �̻����� + ������� ��,��,��,�쿡 �ش��ϴ� ������ ���� ��ġ�� �� �ִ�.
 * �̻��� �Ŀ��� 0�� ��, �̻����� �������� ĭ�� ����鸸 ��ġ�� �� �ְ�,
 * �Ŀ��� 1�� ���� �̻��Ͽ� �������� ĭ�� ���� ��, ��, ��, �� �������� 1ĭ�� ���� ��ġ�� �� �ִ�.
 */
public class SWEA_������ġ {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;				// �׽�Ʈ���̽� ����
	static int N,P;				// ���� ũ�� N, �̻����� ���� P
	static int[][] map;			// ������ ���� ��� �迭
	
	static int[] dx= {-1,1,0,0};		// ��, ��, ��, ��
	static int[] dy= {0,0,-1,1};
	
	static int maxZombie;		// �ִ� ���� ��
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ���̽� ������ �Է� �޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// ���� ũ��, �̻����� ������ �Է� �޴´�.
			N=Integer.parseInt(st.nextToken());
			P=Integer.parseInt(st.nextToken());
			
			// ���� ũ�⸦ �ʱ�ȭ�Ѵ�.
			map=new int[N][N];
			
			// ������ ���� �Է� �޴´�.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			maxZombie=0;
			// 0,0 ���� N-1,N-1���� �ִ� ���� ���� ���Ѵ�.
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					killZombies(row,col);
				}
			}
			// ����� ����Ѵ�.
			System.out.println("#"+test_case+" "+maxZombie);
		}
		
	}
	public static void killZombies(int x,int y) {
		// �̻��Ͽ� �ִ� ������ ���� ���Ѵ�.
		int total=map[x][y];
		// 4 ������ Ž���ϸ� ���¸�ŭ �̻��� ����
		for (int d=0;d<4;d++) {
			for (int p=1;p<=P;p++) {
				int nextX=x+p*dx[d];
				int nextY=y+p*dy[d];
				
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					break;
				}
				total+=map[nextX][nextY];
			}
		}
		maxZombie=Math.max(maxZombie, total);
	}

}
