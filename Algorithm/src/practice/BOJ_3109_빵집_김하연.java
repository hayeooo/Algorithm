package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 3109: ����
 * 
 * �����̴� �������� ������ �����ϴ� �������� ��ġ�Ϸ��� �Ѵ�.
 * �������� ������ �����ϴ� ��� ������������ ù° ������ �����ؾ� �ϰ�, ������ ������ ������ �Ѵ�.
 * �� ĭ�� ������, ������ �� �밢��, ������ �Ʒ� �밢������ ������ �� �ְ�, �� ĭ�� �߽ɳ��� �����Ѵ�.
 * �ǹ��� �ִ� ��� �������� ���� �� ����.
 * �������� ������ �����ϴ� ������������ ���� �� ��ġ�Ѵ�. �� ��δ� ��ĥ �� ����.
 * �����̰� ��ġ�� �� �ִ� �������� ������ �����ϴ� ���������� �ִ� ������ ���Ѵ�.
 * 
 * ù° ������ �����Ͽ� �̵��� �� �ִ� ��� ��츦 Ž���Ѵ�.
 * {������, ������ �� �밢��, ������ �Ʒ� �밢��} �� �ǹ��� �ִ� ��� �����Ѵ�.
 */
public class BOJ_3109_����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;						// R*C ����
	static char[][] map;				// ���� ������ ���� �迭
	static int cnt;						// �������� ������ �����ϴ� ���������� �ִ� ����
	
	// ������ ��, ������, ������ �Ʒ� �밢�� delta ���� �����ϴ� �迭
	static int[] dx= {-1,0,1};
	static int[] dy= {1,1,1};
	
	// Ư�� �࿡�� ������� ��, �� �� ������ �������� �����ߴ��� ���θ� ��Ÿ���� ����
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// R*C ���� ��� �� ũ�⸦ �Է� �޴´�.
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// ���� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		map=new char[R][C];
		
		// ���� ������ �Է� �޴´�.
		for (int row=0;row<R;row++) {
			map[row]=br.readLine().trim().toCharArray();
		}
		for (int r=0;r<R;r++) {
			flag=false;
			dfs(r,0);
		}
		System.out.println(cnt);
	}
	// �� �� �������� ������ ���������� �������ؾ� �Ѵ�.
	public static void dfs(int row, int col) {
		
		// ������ ���� �������� ���
		if (col==C-1) {
			// ������ Ž������ ���� ���������� ���
			if (map[row][col]=='.') {
				cnt++;				// ������������ �ִ� ���� �ϳ� ���Ѵ�.
				map[row][col]='O';	// �̵� ���θ� ǥ���Ѵ�.
				flag=true;			// �� �� Ž���ϸ� ����� �Ѵ�.
			}
			// ��͸� �����Ѵ�.
			return;
		}
		// �� ���� ������ Ž���Ѵ�.
		for (int idx=0;idx<3;idx++) {
			int nextRow=row+dx[idx];
			int nextCol=col+dy[idx];
			
			// ���� ������ �迭�� ����� ���
			if (nextRow<0 || nextRow>=R || nextCol<0 || nextCol>=C) {
				continue;
			}
			// ���� ���ڰ� . �� ���
			if (map[nextRow][nextCol]=='.') {
				// �̹� ������ ��ζ�� ���� ǥ���Ѵ�.
				map[row][col]='O';
				dfs(nextRow,nextCol);
			}
			// �������� ������ �ϳ� �����ߴٸ� �����Ѵ�.
			if (flag) return;
		}
	}
}
