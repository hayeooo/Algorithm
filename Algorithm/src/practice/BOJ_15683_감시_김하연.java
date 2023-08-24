package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * BOJ 15683: ����
 * 
 * �� CCTV�� ��� ������ ����Ͽ�, �簢 ������ �ּ� ũ�⸦ ���Ѵ�.
 * ��� ������ ����� ��, �ߺ� ������ ����Ѵ�.
 */
public class BOJ_15683_����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;					// �繫���� ���� ũ�� N, ���� ũ�� M
	static int[][] board;			// �繫�� �� ĭ�� ������ �����ϴ� �迭
	static boolean[][] visited;		// ������ ĭ�� �湮 ǥ�ø� �ϱ� ���� �迭
	static int[] comb;				// CCTV�� �ٶ󺸴� ������ �����ϴ� �迭
	
	// cctv�� �ٶ󺸴� ���� �򰥸��� �ʱ� ���� �����.
	static final int NORTH=0;
	static final int EAST=1;
	static final int SOUTH=2;
	static final int WEST=3;
	
	// ������ �� �ʿ��� ���� ��Ÿ��
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int cctvCnt;						// �ʿ� ���� cctv ����
	
	static int minZeroCnt=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// �繫���� ����, ���� ũ�⸦ �Է¹޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		board=new int[N][M];
		
		// �繫���� ������ �Է¹����鼭 CCTV�� ������ ����.
		for (int row=0;row<N;row++) {
			// �� �پ� �繫�� ������ �Է¹޴´�.
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				board[row][col]=Integer.parseInt(st.nextToken());
				// ���� ĭ ��ȣ�� 1~5�� ���
				if (board[row][col]>=1 && board[row][col]<=5) {
					cctvCnt+=1;
				}
			}
		}
		// cctv ���� ������ �����ϴ� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		// cctv ��ȣ�� ������� cctv�� �ٶ� �� �ִ� ��� ���� ������ ���Ѵ�.
		comb=new int[cctvCnt];
		combDirection(0);
		System.out.println(minZeroCnt);

	}
	// cctv ������ŭ ���� �� �ִ� ��� ���� ������ ���Ѵ�.
	public static void combDirection(int cnt) {
		if (cnt==cctvCnt) {
			// ���� cctv�� index
			int seq=0;
			int zeroCnt=0;
			// �簢���� ������ ���ϱ� ���� �ʿ��� �湮 ǥ�� �迭
			visited=new boolean[N][M];
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (board[row][col]>=1 && board[row][col]<=5) {
						// cctv ��ȣ�� ��ġ, cctv�� �ٶ󺸴� �������� ������ ĭ�� true�� ǥ���Ѵ�.
						simul(board[row][col],row,col,comb[seq]);
						// ���� cctv ���� �ε����� �����Ѵ�.
						seq+=1;
					}
				}
			}
			// �簢���� ĭ ������ ���Ѵ�.
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (!visited[row][col] && board[row][col]==0) {
						zeroCnt+=1;
					}
				}
			}
			// �簢���� �ּڰ��� �����Ѵ�.
			minZeroCnt=Math.min(minZeroCnt, zeroCnt);
			return;
		}
		// �ߺ� ����
		// cctv�� ������ �ϳ� ����(0: ��, 1: ��, 2: ��, 3: ��)
		// ���� cctv ������ �����ϱ� ���� ����Լ��� ȣ���Ѵ�.
		for (int idx=0;idx<4;idx++) {
			comb[cnt]=idx;
			combDirection(cnt+1);
		}
	}
	// cctv ��ȣ�� �°� Ž���� ������ �����ش�.
	public static void simul(int cctvNum, int x, int y, int dir) {
		// cctv ��ȣ�� 1���� ���
		if (cctvNum==1) {
			// cctv�� ������ ������ ���
			if (dir==0) {
				// ������ �����Ѵ�.
				watch(x,y,1);
			}
			// cctv�� ������ ������ ���
			else if(dir==1) {
				// ������ �����Ѵ�.
				watch(x,y,2);
			}
			// cctv�� ������ ������ ���
			else if(dir==2) {
				// ������ �����Ѵ�.
				watch(x,y,3);
			}
			// cctv�� ������ ������ ���
			else {
				// ������ �����Ѵ�.
				watch(x,y,0);
			}
		}
		// cctv ��ȣ�� 2���� ���
		else if (cctvNum==2) {
			// cctv ������ ��, ������ ���
			if (dir==0 || dir==2) {
				// ��, �� ������ �����Ѵ�.
				watch(x,y,1);
				watch(x,y,3);
			}
			// cctv ������ ��, ������ ���
			else {
				// ��, ������ �����Ѵ�.
				watch(x,y,0);
				watch(x,y,2);
			}
			
		}
		// cctv ��ȣ�� 3���� ���
		else if (cctvNum==3) {
			// cctv�� �ٶ󺸴� ������ ������ ���
			if (dir==0) {
				watch(x,y,0);
				watch(x,y,1);
			}
			// cctv�� �ٶ󺸴� ������ ������ ���
			else if(dir==1) {
				watch(x,y,1);
				watch(x,y,2);
			}
			// cctv�� �ٶ󺸴� ������ ������ ���
			else if(dir==2) {
				watch(x,y,2);
				watch(x,y,3);
			}
			// cctv�� �ٶ󺸴� ������ ������ ���
			else {
				watch(x,y,3);
				watch(x,y,0);
			}
		}
		// cctv ��ȣ�� 4���� ���
		else if (cctvNum==4) {
			// cctv�� ������ �ٶ� ��
			if (dir==0) {
				// ��, ��, ������ �����Ѵ�.
				watch(x,y,0);
				watch(x,y,1);
				watch(x,y,3);
			}
			// cctv�� ������ �ٶ� ��
			else if(dir==1) {
				// ��, ��, ������ �����Ѵ�.
				watch(x,y,0);
				watch(x,y,1);
				watch(x,y,2);
			}
			// cctv�� ������ �ٶ� ��
			else if(dir==2) {
				// ��, ��, ������ �����Ѵ�.
				watch(x,y,1);
				watch(x,y,2);
				watch(x,y,3);
			}
			// cctv�� ������ �ٶ� ��
			else {
				// ��, ��, ������ �����Ѵ�.
				watch(x,y,2);
				watch(x,y,3);
				watch(x,y,0);
			}
		}
		// cctv ��ȣ�� 5���� ���
		else if (cctvNum==5) {
			// ��� ������ �����Ѵ�.
			watch(x,y,0);
			watch(x,y,1);
			watch(x,y,2);
			watch(x,y,3);
		}
	}
	// ������ �� �ִ� ĭ�� ���� true�� ǥ���Ѵ�.
	public static void watch(int x,int y,int d) {
		// ����� ��ġ�� �ʱ�ȭ�Ѵ�.
		int curX=x;
		int curY=y;
		while (true) {
			// ���� Ž���� ��ġ
			int nextX=curX+dx[d];
			int nextY=curY+dy[d];
			
			// �迭�� ������ ��� ��� �ݺ��� �ߴ��Ѵ�.
			if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
				break;
			}
			// ���� ���, �ݺ��� �ߴ��Ѵ�.
			if (board[nextX][nextY]==6) break;
			
			// ���� ĭ�� �湮 ǥ�ø� �Ѵ�.
			visited[nextX][nextY]=true;
			
			// ��ġ�� ������Ʈ �Ѵ�.
			curX=nextX;
			curY=nextY;
		}
	}
	
}
