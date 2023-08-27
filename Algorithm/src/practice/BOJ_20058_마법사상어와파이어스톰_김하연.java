package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 20058: 마법사 상어와 파이어스톰
 * 1. 배열 돌리기 (돌릴 사각형 크기, 시작 시작지점)을 매개변수로 가지고 부분 격자의 크기에 따라 한 칸씩 움직인다.
 * 2. 파이어스톰 시전
 * 
 * 남아있는 얼음 A[r][c]의 합과 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수를 출력한다.
 */
public class BOJ_20058_마법사상어와파이어스톰_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,Q;					// 2^N개의 줄의 격자 칸, 마법사 상어가 시전한 단계 Q
	static int[][] A;					// 얼음 정보를 저장할 배열
	
	// 인접한 칸에 얼음이 있는지 탐색하기 위한 델타 배열
	static int[] dx= {-1,0,1,0};		// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	static boolean[][] visited;
	
	static int totalIce=0;
	static int iceSize;
	static int maxIce=0;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// N, Q 입력 받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		// 얼음칸 배열 크기 초기화
		int n=(int)Math.pow(2, N);
		A=new int[n][n];
		
		// 얼음칸 정보 저장
		for (int row=0;row<n;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<n;col++) {
				A[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 마법사 상어 L 단계 시전
		st=new StringTokenizer(br.readLine().trim());
		for (int l=0;l<Q;l++) {
			int curLevel=Integer.parseInt(st.nextToken());
			
			// 2^L*2^L 크기의 격자를 시계방향으로 90도 회전시킨다.
			int size=(int)Math.pow(2, curLevel);
			
			// 부분 격자의 사각형을 회전시킨다.
			for (int row=0;row<=n-size;row+=size) {
				for (int col=0;col<=n-size;col+=size) {
					rotate(row,col,size);
				}
			}
			
			// 회전시킨 후
			// 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.
			reduceIce(n);
			
			
		}
		// 시전이 끝난 후
		// 얼음 총합을 구한다.
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				totalIce+=A[row][col];
			}
		}
		
		// 가장 큰 얼음을 찾는다.
		visited=new boolean[n][n];
		
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				if (visited[row][col]) continue;
				if (A[row][col]==0) continue;
				// 현재 위치의 얼음 사이즈를 구한다.
				iceSize=searchIce(row,col,n);
				
				// 더 큰 얼음 사이즈로 값을 변경한다.
				maxIce=Math.max(maxIce, iceSize);
			}
		}
		System.out.println(totalIce+" "+maxIce);

	}
	// 회전시킬 부분 격자의 왼쪽 위 지점과 크기를 매개변수로 받아
	// 부분격자를 90도 회전시킨다.
	public static void rotate(int r,int c, int size) {
		// 바깥 사각형 테두리부터 차례로 회전한다.
		// size를 2로 나누면서 0보다 클 때까지 부분격자를 이동시킨다.
		int curR=r;
		int curC=c;
		int curSize=size;
		while (curSize>=2) {
			
			for (int move=0;move<curSize-1;move++) {
				moveByOne(curR,curC,curSize);
			}
			// 90도 회전이 끝나면 그 다음 바깥 테두리 부분격자로 크기와 위치를 조정한다.
			curR+=1;
			curC+=1;
			curSize-=2;
		}
		
		
	}
	
	// 얼음을 시계 방향으로 한 칸씩 이동한다.
	public static void moveByOne(int r, int c, int size) {
		
		int tmp=A[r][c];
		
		// 위쪽으로 이동
		for (int row=r+1;row<r+size;row++) {
			A[row-1][c]=A[row][c];
		}
		// 왼쪽으로 이동
		for (int col=c+1;col<c+size;col++) {
			A[r+size-1][col-1]=A[r+size-1][col];
		}
		// 아래쪽으로 이동
		for (int row=r+size-2;row>=r;row--) {
			A[row+1][c+size-1]=A[row][c+size-1];
		}
		// 오른쪽으로 이동
		for (int col=c+size-2;col>=c+1;col--) {
			A[r][col+1]=A[r][col];
		}
		// 임시 저장 값 배열에 저장
		A[r][c+1]=tmp;
	}
	
	public static void reduceIce(int n) {
		
		// 녹여야 할 얼음칸의 위치를 저장하는 리스트
		List<int[]> toReduce=new ArrayList<>();
		
		// 한번에 녹여야 한다.
		// 녹여야 할 얼음칸의 위치를 저장한다.
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				
				// 이미 얼음이 없는 칸이라면 넘어간다.
				if (A[row][col]==0) continue;
				
				// 바깥 테두리를 제외한 얼음에 대해 인접한 칸 중 얼음이 있는 칸이 3개가 아닌 경우 1 감소한다.
				int iceCnt=0;
				// 4방향 탐색
				for (int d=0;d<4;d++) {
					int nextX=row+dx[d];
					int nextY=col+dy[d];
					if (nextX<0 || nextX>=n || nextY<0 || nextY>=n) {
						continue;
					}
					int nextIce=A[nextX][nextY];
					if (nextIce>0) {
						iceCnt+=1;
					}
				}
				// 얼음이 없는 칸이 3개 이상일 경우
				if (iceCnt<3) {
					toReduce.add(new int[] {row,col});
				}
			}
		}
		
		for (int[] reduceLoc:toReduce) {
			A[reduceLoc[0]][reduceLoc[1]]-=1;
		}
	
	}
	// 얼음의 크기를 구한다.(bfs)
	public static int searchIce(int r,int c, int mapSize) {
		int curSize=1;
		
		Deque<int[]> que=new ArrayDeque<>();
		que.offer(new int[] {r,c});
		visited[r][c]=true;
		
		while (!que.isEmpty()) {
			int[] loc=que.poll();
			
			for (int d=0;d<4;d++) {
				int nextX=loc[0]+dx[d];
				int nextY=loc[1]+dy[d];
				
				// 배열의 범위를 넘어 탐색한 경우
				if (nextX<0 || nextX>=mapSize || nextY<0 || nextY>=mapSize) {
					continue;
				}
				// 이미 방문한 칸인 경우
				if (visited[nextX][nextY]) continue;
				
				// 얼음이 없는 칸인 경우
				if (A[nextX][nextY]==0) continue;
				
				// 조건에 만족하는 얼음칸을 큐에 넣어 다른 범위의 얼음칸을 탐색한다.
				que.offer(new int[] {nextX,nextY});
				curSize+=1;
				visited[nextX][nextY]=true;
			}
		}
		
		return curSize;
	}

}
