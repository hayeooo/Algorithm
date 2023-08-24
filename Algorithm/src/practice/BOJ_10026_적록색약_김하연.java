package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * BOJ 10026: 적록색약
 * N*N 그리드의 각 칸은 R, G, B 중 하나를 색칠한 그림이 있다.
 * 같은 색상이 상하좌우로 인접한 경우, 색깔이 같을 때 같은 구역에 속한다.
 * 
 * 적록색약인 사람이 봤을 때 구역의 수와 적록색약인 사람이 볼 수 있는 구역의 수를 출력한다.
 */

public class BOJ_10026_적록색약_김하연 {
	static BufferedReader br;
	
	static int N;								// 그리드의 가로, 세로 크기
	static char[][] map;						// 구역의 정보를 저장하는 배열
	
	static int[] dx= {-1,1,0,0};				// 상하좌우 델타값을 저장하는 배열
	static int[] dy= {0,0,-1,1};	
	
	static boolean[][] visited;					// 각 칸에 방문했는지 여부를 저장하는 배열
	static int blindArea;						// 적록색야인 사람이 봤을 때 구역의 수
	static int nonBlindArea;					// 적록색약이 아닌 사람이 봤을 때 구역의 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 그리드 가로, 세로 크기 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 그리드 크기 초기화
		map= new char[N][N];
		
		// 그리드의 각 칸의 정보를 입력 받는다.
		for (int row=0;row<N;row++) {
			String line=br.readLine().trim();
			for (int col=0;col<N;col++) {
				map[row][col]=line.charAt(col);
			}
		}
		// 각 칸 방문을 표시하는 배열을 초기화한다.
		visited=new boolean[N][N];
		// 적록색약이 아닌 사람이 봤을 때 구역의 수 구하기
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				if (!visited[row][col]) {
					nonBlindBFS(row,col,map[row][col]);
					nonBlindArea+=1;
				}
			}
		}
		// 각 칸 방문을 표시하는 배열을 초기화한다.
		visited=new boolean[N][N];
		// 적록색약인 사람이 봤을 때 구역의 수 구하기
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				if (!visited[row][col] && map[row][col]=='B') {
					blindBFS(row,col,'B');
					blindArea+=1;
				}
				else if (!visited[row][col] && (map[row][col]=='R' || map[row][col]=='G')) {
					blindBFS(row,col,'R');
					blindArea+=1;
				}
			}
		}
		System.out.println(nonBlindArea+" "+blindArea);
		
	}
	
	// 적록색약 구역의 수 구하기
	public static void blindBFS(int x, int y, char color) {
		
		// 현재 위치를 queue에 넣는다.
		Deque<int[]> que=new ArrayDeque<>();
		que.offer(new int[] {x,y});
		
		// 큐가 빌 때까지 반복한다.
		while (!que.isEmpty()) {
			// 큐에서 현재 위치를 꺼낸다.
			int[] curLoc=que.poll();
			
			// 현재 칸 주변의 4칸을 탐색한다.
			for (int d=0;d<4;d++) {
				int nextX=curLoc[0]+dx[d];
				int nextY=curLoc[1]+dy[d];
				
				// 배열의 범위를 넘어갈 경우 continue
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					continue;
				}
				// 이미 방문한 곳이면 continue
				if (visited[nextX][nextY]) continue;
				
				// 배열의 범위를 넘어가지 않고, 방문한 적이 없는 칸
				// 빨간색과 초록색은 같은 구역으로 지정한다.
				if (color=='R') {
					if (map[nextX][nextY]=='R' || map[nextX][nextY]=='G') {
						visited[nextX][nextY]=true;
						que.offer(new int[] {nextX,nextY});						
					}
				}
				// 파란색인 경우
				if (color=='B' && map[nextX][nextY]=='B') {
					visited[nextX][nextY]=true;
					que.offer(new int[] {nextX,nextY});					
				}
			}
		}
		
	}
	
	public static void nonBlindBFS(int x, int y, char color) {
		Deque<int[]> que=new ArrayDeque<>();
		
		// 현재 위치를 큐에 넣는다.
		que.offer(new int[] {x,y});
		visited[x][y]=true;
		
		// 큐가 비어있지 않을 때까지 반복한다.
		while (!que.isEmpty()) {
			int[] curLoc=que.poll();
			
			// 현재 칸을 기준으로 4방 탐색
			for (int d=0;d<4;d++) {
				int nextX=curLoc[0]+dx[d];
				int nextY=curLoc[1]+dy[d];
				
				// 배열의 범위를 넘어선 경우 continue
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					continue;
				}
				// 이미 방문한 경우 continue
				if (visited[nextX][nextY]) continue;
				
				// 매개변수로 주어진 색상과 같은 색깔이면
				// 큐에 넣고 방문 표시를 한다.
				if (map[nextX][nextY]==color) {
					que.add(new int[] {nextX,nextY});
					visited[nextX][nextY]=true;
				}
			}
		}
	}

}
