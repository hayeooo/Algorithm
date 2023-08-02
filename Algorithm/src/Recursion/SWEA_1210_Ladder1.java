package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 1210: Ladder1
 * >> ���� Ǯ��
 * ���� ��ȯ�� �ϸ� X ǥ�ÿ� �����ϰ� �Ǵ� ��ٸ� ã��
 * �¿� �������� �̵� ������ ��ΰ� ��Ÿ���� ���� ��ȯ
 * ���� ��ȯ ���Ŀ� �Ʒ� �������θ� �̵��ϸ�, �ٴڿ� �����ϸ� ���߰� �ȴ�.
 * 
 * >> ���� Ǯ��
 * ���� �������� ��� �������� ����
 * */
public class SWEA_1210_Ladder1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int T = 10;
	static int[][] map;
	static int DEST;
	static int curRow, curCol;

	// �� -> �� -> ��
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
