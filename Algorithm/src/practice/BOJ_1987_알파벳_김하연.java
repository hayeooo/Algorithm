package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1987: 알파벳
 * 좌측 상단부터 상하좌우로 지금까지 지나오지 않은 알파벳 칸으로 이동한다.
 * 말이 최대한 몇 칸 지날 수 있는지 구한다.
 * 
 */
public class BOJ_1987_알파벳_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;					// 세로 R, 가로 C칸
	static int maxCnt;				// 최대 칸
	static char[][] board;			// 보드
	static boolean[] visited;		// 이미 이동한 알파벳인지 저장하는 배열
	
	// 상하 좌우 delta 배열
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// R, C 입력
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// board 크기 초기화
		board=new char[R][C];
		
		
		// board 정보 입력
		for (int idx=0;idx<R;idx++) {
			board[idx]=br.readLine().trim().toCharArray();
		}
		
		// 이동한 알파벳을 저장하는 배열 크기 초기화
		visited=new boolean[26];
		
		// 좌측 상단은 무조건 지나간다.
		visited[board[0][0]-'A']=true;
		
		// 탐색할 수 있는 알파벳 칸을 이동하고
		// 최대 칸을 업데이트 한다.
		dfs(0,0,1);
		
		// 결과를 출력한다.
		System.out.println(maxCnt);
		
		
	}
	// 칸의 현재 위치(x,y)와 현재까지 칸 이동횟수를 매개변수로 하는 dfs
	public static void dfs(int x, int y, int cnt) {
		
		// 사방 탐색
		for (int d=0;d<4;d++) {
			// 다음 이동할 위치
			int nextX=x+dx[d];
			int nextY=y+dy[d];
			
			// 배열의 범위를 넘어가면 continue;
			if (nextX<0 || nextX>=R || nextY<0 || nextY>=C) {
				continue;
			}
			// 이미 지나온 알파벳인 경우
			// 최대 거리값을 반영하고 종료한다.
			if (visited[board[nextX][nextY]-'A']) {
				maxCnt=Math.max(maxCnt, cnt);
				continue;
			}
			// 두 조건을 모두 만족하지 않은 경우
			// 다음 탐색을 진행한다.
			visited[board[nextX][nextY]-'A']=true;
			dfs(nextX,nextY,cnt+1);
			// 다른 길로 탐색하기 위해 방문 표시를 false로 저장한다.
			visited[board[nextX][nextY]-'A']=false;
		}
		return;
		
	}

}
