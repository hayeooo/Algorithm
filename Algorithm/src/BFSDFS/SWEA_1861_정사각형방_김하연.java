package BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_���簢����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	static int N;
	static int[][] board;
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};				// ��, ��, ��, ��
	static int maxRoomNum;											// ���� ���� ������ ���� �̵��� �� �ִ� ���� ����
	static int max;													// ���� ���� �̵� ������ ���� ����
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// �׽�Ʈ ���̽� ���� �Է�
		T=Integer.parseInt(br.readLine());
		
		// �� �׽�Ʈ���̽����� �ݺ�
		for (int test_case=1;test_case<=T;test_case++) {
			
			// �� ���� ũ�� �� �迭 ũ�� �ʱ�ȭ
			N=Integer.parseInt(br.readLine());
			board=new int[N][N];
			
			// ���������� ���� ���� �ʱ�ȭ
			maxRoomNum=0;
			max=0;
			
			// �� ���� �Է� �ޱ�
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					board[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// �� ���� �������� �̵� ������ ���� ���� ���ϱ�
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					dfs(row,col,1,board[row][col]);
				}
			}
			// ����� ����Ѵ�.
			System.out.println("#"+test_case+" "+maxRoomNum+" "+max);
		}
	}
	
	// Ž���� �� �ִ� ���� �ִ��� Ž���Ѵ�.
	// x,y: ���� ��ġ cnt: ������� �̵� ������ ���� ����, startNum: ���� ���� ����
	// ���� ���ȣ�� ���� ���ȣ�� ���̰� 1�̾���ϰ�, ��� �� ��ȣ�� �ٸ��� ������ �湮 ������ Ȯ���� �ʿ䰡 ����.
	public static void dfs(int x, int y, int cnt, int startNum) {
		
		int curRoomNum=board[x][y];
		// �ִ� count ��
		if (max==cnt) {
			maxRoomNum=Math.min(maxRoomNum, startNum);
		}
		else if (max<cnt) {
			max=cnt;
			maxRoomNum=startNum;
		}
		// ���� Ž��
		for (int cd=0;cd<dir.length;cd++) {
			int nextRow=x+dir[cd][0];
			int nextCol=y+dir[cd][1];
			
			// �迭 ������ ����� ��� continue;
			if (nextRow<0 || nextRow>=N || nextCol<0 || nextCol>=N) {
				continue;
			}
			// ���� Ž���� �� ��ȣ
			int nextRoomNum=board[nextRow][nextCol];
			// ���� �� ��ȣ�� ���̰� 1�� �ƴ϶�� continue;
			if (nextRoomNum-curRoomNum!=1) {
				continue;
			}
			// ���� �� ��ȣ Ž��
			dfs(nextRow,nextCol,cnt+1,startNum);
		}
		
	}
}
