package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 3109: 빵집
 * 
 * 원웅이는 가스관과 빵집을 연결하는 파이프를 설치하려고 한다.
 * 가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
 * 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결한다.
 * 건물이 있는 경우 파이프를 놓을 수 없다.
 * 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치한다. 이 경로는 겹칠 수 없다.
 * 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인 최대 개수를 구한다.
 * 
 * 첫째 열에서 시작하여 이동할 수 있는 모든 경우를 탐색한다.
 * {오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선} 중 건물이 있는 경우 제외한다.
 */
public class BOJ_3109_빵집_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;						// R*C 격자
	static char[][] map;				// 격자 정보를 담을 배열
	static int cnt;						// 가스관과 빵집을 연결하는 파이프라인 최대 개수
	
	// 오른쪽 위, 오른쪽, 오른쪽 아래 대각선 delta 값을 저장하는 배열
	static int[] dx= {-1,0,1};
	static int[] dy= {1,1,1};
	
	// 특정 행에서 출발했을 때, 한 번 빵집과 가스관을 연결했는지 여부를 나타내는 변수
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// R*C 격자 행과 열 크기를 입력 받는다.
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// 격자 배열 크기를 초기화한다.
		map=new char[R][C];
		
		// 격자 정보를 입력 받는다.
		for (int row=0;row<R;row++) {
			map[row]=br.readLine().trim().toCharArray();
		}
		for (int r=0;r<R;r++) {
			flag=false;
			dfs(r,0);
		}
		System.out.println(cnt);
	}
	// 한 번 가스관과 빵집을 연결했으면 마무리해야 한다.
	public static void dfs(int row, int col) {
		
		// 마지막 열에 도착했을 경우
		if (col==C-1) {
			// 이전에 탐색하지 않은 도착지점인 경우
			if (map[row][col]=='.') {
				cnt++;				// 파이프라인의 최대 수를 하나 더한다.
				map[row][col]='O';	// 이동 여부를 표시한다.
				flag=true;			// 한 번 탐색하면 멈춰야 한다.
			}
			// 재귀를 종료한다.
			return;
		}
		// 세 개의 방향을 탐색한다.
		for (int idx=0;idx<3;idx++) {
			int nextRow=row+dx[idx];
			int nextCol=col+dy[idx];
			
			// 다음 범위가 배열을 벗어나는 경우
			if (nextRow<0 || nextRow>=R || nextCol<0 || nextCol>=C) {
				continue;
			}
			// 다음 격자가 . 일 경우
			if (map[nextRow][nextCol]=='.') {
				// 이미 지나온 경로라는 것을 표시한다.
				map[row][col]='O';
				dfs(nextRow,nextCol);
			}
			// 가스관과 빵집을 하나 연결했다면 종료한다.
			if (flag) return;
		}
	}
}
