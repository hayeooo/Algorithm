package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_NQuenn_9663_김하연 {
	static BufferedReader br;
	static int N;			// N*N인 체스판
	
	static int[] col;		// 각 행에 퀸을 몇 번째 열에 놓았는지 저장하는 배열
	static int cnt;			// 퀸을 놓는 방법의 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// N을 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 배열의 크기를 초기화한다.
		col=new int[N];
		
		// 0번째 행부터 시작한다.
		dfs(0);
		// 퀸을 놓는 방법의 수를 출력한다.
		System.out.println(cnt);
	}
	
	// 각 행에 퀸을 놓는 메소드
	public static void dfs(int r) {
		
		// 모든 행에 퀸을 놓았을 경우
		if (r>=N) {
			cnt+=1;		// 퀸을 놓는 방법의 수를 1 더하고
			return;		// 재귀를 종료한다.
		}
		// 0열부터 N-1열까지 퀸을 놓을 수 있다.
		for (int idx=0;idx<N;idx++) {
			// r행 idx열에 퀸을 놓는다.
			col[r]=idx;
			// 같은 열, 같은 대각선에 놓였을 경우 다른 열을 탐색한다.
			if (!check(r)) continue;
			// 현재 행에 놓을 수 있는 경우, 다음 행에 놓을 퀸의 위치를 탐색한다.
			dfs(r+1);
		}
	}
	// 같은 열, 같은 대각선에 위치한 퀸이 있을 경우 false, 없을 경우 true
	public static boolean check(int row) {	// row: 현재까지 퀸이 위치한 행
		// 같은 열인 경우
		for (int idx=0;idx<row;idx++) {
			// 같은 열인 경우, 같은 대각선인 경우
			if (col[idx]==col[row] || row-idx==Math.abs(col[row]-col[idx])) {
				return false;
			}
		}
		return true;
	}
	

}
