package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1987: ���ĺ�
 * ���� ��ܺ��� �����¿�� ���ݱ��� �������� ���� ���ĺ� ĭ���� �̵��Ѵ�.
 * ���� �ִ��� �� ĭ ���� �� �ִ��� ���Ѵ�.
 * 
 */
public class BOJ_1987_���ĺ�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;					// ���� R, ���� Cĭ
	static int maxCnt;				// �ִ� ĭ
	static char[][] board;			// ����
	static boolean[] visited;		// �̹� �̵��� ���ĺ����� �����ϴ� �迭
	
	// ���� �¿� delta �迭
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// R, C �Է�
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// board ũ�� �ʱ�ȭ
		board=new char[R][C];
		
		
		// board ���� �Է�
		for (int idx=0;idx<R;idx++) {
			board[idx]=br.readLine().trim().toCharArray();
		}
		
		// �̵��� ���ĺ��� �����ϴ� �迭 ũ�� �ʱ�ȭ
		visited=new boolean[26];
		
		// ���� ����� ������ ��������.
		visited[board[0][0]-'A']=true;
		
		// Ž���� �� �ִ� ���ĺ� ĭ�� �̵��ϰ�
		// �ִ� ĭ�� ������Ʈ �Ѵ�.
		dfs(0,0,1);
		
		// ����� ����Ѵ�.
		System.out.println(maxCnt);
		
		
	}
	// ĭ�� ���� ��ġ(x,y)�� ������� ĭ �̵�Ƚ���� �Ű������� �ϴ� dfs
	public static void dfs(int x, int y, int cnt) {
		
		// ��� Ž��
		for (int d=0;d<4;d++) {
			// ���� �̵��� ��ġ
			int nextX=x+dx[d];
			int nextY=y+dy[d];
			
			// �迭�� ������ �Ѿ�� continue;
			if (nextX<0 || nextX>=R || nextY<0 || nextY>=C) {
				continue;
			}
			// �̹� ������ ���ĺ��� ���
			// �ִ� �Ÿ����� �ݿ��ϰ� �����Ѵ�.
			if (visited[board[nextX][nextY]-'A']) {
				maxCnt=Math.max(maxCnt, cnt);
				continue;
			}
			// �� ������ ��� �������� ���� ���
			// ���� Ž���� �����Ѵ�.
			visited[board[nextX][nextY]-'A']=true;
			dfs(nextX,nextY,cnt+1);
			// �ٸ� ��� Ž���ϱ� ���� �湮 ǥ�ø� false�� �����Ѵ�.
			visited[board[nextX][nextY]-'A']=false;
		}
		return;
		
	}

}
