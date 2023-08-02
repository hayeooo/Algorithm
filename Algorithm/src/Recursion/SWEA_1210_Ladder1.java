package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 1210: Ladder1
 * >> 기존 풀이
 * 방향 전환을 하며 X 표시에 도착하게 되는 사다리 찾기
 * 좌우 방향으로 이동 가능한 통로가 나타나면 방향 전환
 * 방향 전환 이후에 아래 방향으로만 이동하며, 바닥에 도착하면 멈추게 된다.
 * 
 * >> 정답 풀이
 * 도착 지점에서 출발 지점으로 진행
 * */
public class SWEA_1210_Ladder1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int T = 10;
	static int[][] map;
	static int DEST;
	static int curRow, curCol;

	// 좌 -> 우 -> 상
	static final int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[100][100];
		sb=new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			br.readLine();

			for (int row = 0; row < 100; row++) {
				st = new StringTokenizer(br.readLine());

				for (int col = 0; col < 100; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if (map[row][col] == 2) {
						DEST = col;
					}
				}
			}
			curRow = 99;
			curCol = DEST;

			while (curRow > 0) {
				for (int d = 0; d < dir.length; d++) {
					int nxtRow = curRow + dir[d][0];
					int nxtCol = curCol + dir[d][1];
					
					if (nxtRow<0 || nxtRow>99 || nxtCol<0 || nxtCol>99) continue;
					if (map[nxtRow][nxtCol]==0) continue;
					map[curRow][curCol]=0;
					curRow=nxtRow;
					curCol=nxtCol;
					break;
				}
			}
			sb.append("#"+tc+" "+curCol+"\n");
		}
		System.out.println(sb);
	}

}
