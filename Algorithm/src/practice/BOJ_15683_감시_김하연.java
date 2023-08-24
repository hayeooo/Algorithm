package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * BOJ 15683: 감시
 * 
 * 각 CCTV의 모든 방향을 고려하여, 사각 지대의 최소 크기를 구한다.
 * 모든 방향을 고려할 때, 중복 조합을 사용한다.
 */
public class BOJ_15683_감시_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;					// 사무실의 세로 크기 N, 가로 크기 M
	static int[][] board;			// 사무실 각 칸의 정보를 저장하는 배열
	static boolean[][] visited;		// 감시한 칸에 방문 표시를 하기 위한 배열
	static int[] comb;				// CCTV가 바라보는 방향을 저장하는 배열
	
	// cctv가 바라보는 방향 헷갈리지 않기 위해 적어둠.
	static final int NORTH=0;
	static final int EAST=1;
	static final int SOUTH=2;
	static final int WEST=3;
	
	// 감시할 때 필요한 방향 델타값
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static int cctvCnt;						// 맵에 속한 cctv 개수
	
	static int minZeroCnt=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 사무실의 세로, 가로 크기를 입력받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		board=new int[N][M];
		
		// 사무실의 정보를 입력받으면서 CCTV의 개수를 센다.
		for (int row=0;row<N;row++) {
			// 한 줄씩 사무실 정보를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				board[row][col]=Integer.parseInt(st.nextToken());
				// 현재 칸 번호가 1~5인 경우
				if (board[row][col]>=1 && board[row][col]<=5) {
					cctvCnt+=1;
				}
			}
		}
		// cctv 방향 조합을 저장하는 배열 크기를 초기화한다.
		// cctv 번호와 상관없이 cctv가 바라볼 수 있는 모든 방향 조합을 구한다.
		comb=new int[cctvCnt];
		combDirection(0);
		System.out.println(minZeroCnt);

	}
	// cctv 개수만큼 가질 수 있는 모든 방향 조합을 구한다.
	public static void combDirection(int cnt) {
		if (cnt==cctvCnt) {
			// 현재 cctv의 index
			int seq=0;
			int zeroCnt=0;
			// 사각지대 개수를 구하기 위해 필요한 방문 표시 배열
			visited=new boolean[N][M];
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (board[row][col]>=1 && board[row][col]<=5) {
						// cctv 번호와 위치, cctv가 바라보는 방향으로 감시할 칸에 true로 표시한다.
						simul(board[row][col],row,col,comb[seq]);
						// 다음 cctv 방향 인덱스로 설정한다.
						seq+=1;
					}
				}
			}
			// 사각지대 칸 개수를 구한다.
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (!visited[row][col] && board[row][col]==0) {
						zeroCnt+=1;
					}
				}
			}
			// 사각지대 최솟값을 저장한다.
			minZeroCnt=Math.min(minZeroCnt, zeroCnt);
			return;
		}
		// 중복 조합
		// cctv의 방향을 하나 고르고(0: 북, 1: 동, 2: 남, 3: 서)
		// 다음 cctv 방향을 선택하기 위해 재귀함수를 호출한다.
		for (int idx=0;idx<4;idx++) {
			comb[cnt]=idx;
			combDirection(cnt+1);
		}
	}
	// cctv 번호에 맞게 탐색할 방향을 정해준다.
	public static void simul(int cctvNum, int x, int y, int dir) {
		// cctv 번호가 1번일 경우
		if (cctvNum==1) {
			// cctv의 방향이 북쪽인 경우
			if (dir==0) {
				// 동쪽을 감시한다.
				watch(x,y,1);
			}
			// cctv의 방향이 동쪽인 경우
			else if(dir==1) {
				// 남쪽을 감시한다.
				watch(x,y,2);
			}
			// cctv의 방향이 남쪽인 경우
			else if(dir==2) {
				// 서쪽을 감시한다.
				watch(x,y,3);
			}
			// cctv의 방향이 서쪽인 경우
			else {
				// 북쪽을 감시한다.
				watch(x,y,0);
			}
		}
		// cctv 번호가 2번일 경우
		else if (cctvNum==2) {
			// cctv 방향이 북, 남쪽인 경우
			if (dir==0 || dir==2) {
				// 동, 서 방향을 감시한다.
				watch(x,y,1);
				watch(x,y,3);
			}
			// cctv 방향이 동, 서쪽인 경우
			else {
				// 북, 남쪽을 감시한다.
				watch(x,y,0);
				watch(x,y,2);
			}
			
		}
		// cctv 번호가 3번일 경우
		else if (cctvNum==3) {
			// cctv가 바라보는 방향이 북쪽인 경우
			if (dir==0) {
				watch(x,y,0);
				watch(x,y,1);
			}
			// cctv가 바라보는 방향이 동쪽인 경우
			else if(dir==1) {
				watch(x,y,1);
				watch(x,y,2);
			}
			// cctv가 바라보는 방향이 남쪽인 경우
			else if(dir==2) {
				watch(x,y,2);
				watch(x,y,3);
			}
			// cctv가 바라보는 방향이 서쪽인 경우
			else {
				watch(x,y,3);
				watch(x,y,0);
			}
		}
		// cctv 번호가 4번일 경우
		else if (cctvNum==4) {
			// cctv가 북쪽을 바라볼 때
			if (dir==0) {
				// 서, 북, 동쪽을 감시한다.
				watch(x,y,0);
				watch(x,y,1);
				watch(x,y,3);
			}
			// cctv가 동쪽을 바라볼 때
			else if(dir==1) {
				// 북, 동, 남쪽을 감시한다.
				watch(x,y,0);
				watch(x,y,1);
				watch(x,y,2);
			}
			// cctv가 남쪽을 바라볼 때
			else if(dir==2) {
				// 동, 남, 서쪽을 감시한다.
				watch(x,y,1);
				watch(x,y,2);
				watch(x,y,3);
			}
			// cctv가 서쪽을 바라볼 때
			else {
				// 남, 서, 북쪽을 감시한다.
				watch(x,y,2);
				watch(x,y,3);
				watch(x,y,0);
			}
		}
		// cctv 번호가 5번인 경우
		else if (cctvNum==5) {
			// 모든 방향을 감시한다.
			watch(x,y,0);
			watch(x,y,1);
			watch(x,y,2);
			watch(x,y,3);
		}
	}
	// 감시할 수 있는 칸에 대해 true로 표시한다.
	public static void watch(int x,int y,int d) {
		// 출발할 위치를 초기화한다.
		int curX=x;
		int curY=y;
		while (true) {
			// 다음 탐색할 위치
			int nextX=curX+dx[d];
			int nextY=curY+dy[d];
			
			// 배열의 범위를 벗어난 경우 반복을 중단한다.
			if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
				break;
			}
			// 벽인 경우, 반복을 중단한다.
			if (board[nextX][nextY]==6) break;
			
			// 현재 칸에 방문 표시를 한다.
			visited[nextX][nextY]=true;
			
			// 위치를 업데이트 한다.
			curX=nextX;
			curY=nextY;
		}
	}
	
}
