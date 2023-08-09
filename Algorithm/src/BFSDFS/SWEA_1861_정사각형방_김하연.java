package BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	static int N;
	static int[][] board;
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};				// 상, 하, 좌, 우
	static int maxRoomNum;											// 가장 많은 개수의 방을 이동할 수 있는 시작 지점
	static int max;													// 가장 많이 이동 가능한 방의 개수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 개수 입력
		T=Integer.parseInt(br.readLine());
		
		// 각 테스트케이스마다 반복
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 방 격자 크기 및 배열 크기 초기화
			N=Integer.parseInt(br.readLine());
			board=new int[N][N];
			
			// 시작지점과 방의 개수 초기화
			maxRoomNum=0;
			max=0;
			
			// 방 정보 입력 받기
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					board[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// 각 시작 지점에서 이동 가능한 방의 개수 구하기
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					dfs(row,col,1,board[row][col]);
				}
			}
			// 결과를 출력한다.
			System.out.println("#"+test_case+" "+maxRoomNum+" "+max);
		}
	}
	
	// 탐색할 수 있는 방을 최대한 탐색한다.
	// x,y: 현재 위치 cnt: 현재까지 이동 가능한 방의 개수, startNum: 방의 시작 지점
	// 현재 방번호와 다음 방번호의 차이가 1이어야하고, 모든 방 번호가 다르기 때문에 방문 유무를 확인할 필요가 없다.
	public static void dfs(int x, int y, int cnt, int startNum) {
		
		int curRoomNum=board[x][y];
		// 최대 count 비교
		if (max==cnt) {
			maxRoomNum=Math.min(maxRoomNum, startNum);
		}
		else if (max<cnt) {
			max=cnt;
			maxRoomNum=startNum;
		}
		// 방향 탐색
		for (int cd=0;cd<dir.length;cd++) {
			int nextRow=x+dir[cd][0];
			int nextCol=y+dir[cd][1];
			
			// 배열 범위를 벗어나는 경우 continue;
			if (nextRow<0 || nextRow>=N || nextCol<0 || nextCol>=N) {
				continue;
			}
			// 다음 탐색할 방 번호
			int nextRoomNum=board[nextRow][nextCol];
			// 현재 방 번호와 차이가 1이 아니라면 continue;
			if (nextRoomNum-curRoomNum!=1) {
				continue;
			}
			// 다음 방 번호 탐색
			dfs(nextRow,nextCol,cnt+1,startNum);
		}
		
	}
}
