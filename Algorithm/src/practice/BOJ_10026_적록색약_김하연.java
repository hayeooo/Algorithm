package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * BOJ 10026: ���ϻ���
 * N*N �׸����� �� ĭ�� R, G, B �� �ϳ��� ��ĥ�� �׸��� �ִ�.
 * ���� ������ �����¿�� ������ ���, ������ ���� �� ���� ������ ���Ѵ�.
 * 
 * ���ϻ����� ����� ���� �� ������ ���� ���ϻ����� ����� �� �� �ִ� ������ ���� ����Ѵ�.
 */

public class BOJ_10026_���ϻ���_���Ͽ� {
	static BufferedReader br;
	
	static int N;								// �׸����� ����, ���� ũ��
	static char[][] map;						// ������ ������ �����ϴ� �迭
	
	static int[] dx= {-1,1,0,0};				// �����¿� ��Ÿ���� �����ϴ� �迭
	static int[] dy= {0,0,-1,1};	
	
	static boolean[][] visited;					// �� ĭ�� �湮�ߴ��� ���θ� �����ϴ� �迭
	static int blindArea;						// ���ϻ����� ����� ���� �� ������ ��
	static int nonBlindArea;					// ���ϻ����� �ƴ� ����� ���� �� ������ ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׸��� ����, ���� ũ�� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// �׸��� ũ�� �ʱ�ȭ
		map= new char[N][N];
		
		// �׸����� �� ĭ�� ������ �Է� �޴´�.
		for (int row=0;row<N;row++) {
			String line=br.readLine().trim();
			for (int col=0;col<N;col++) {
				map[row][col]=line.charAt(col);
			}
		}
		// �� ĭ �湮�� ǥ���ϴ� �迭�� �ʱ�ȭ�Ѵ�.
		visited=new boolean[N][N];
		// ���ϻ����� �ƴ� ����� ���� �� ������ �� ���ϱ�
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				if (!visited[row][col]) {
					nonBlindBFS(row,col,map[row][col]);
					nonBlindArea+=1;
				}
			}
		}
		// �� ĭ �湮�� ǥ���ϴ� �迭�� �ʱ�ȭ�Ѵ�.
		visited=new boolean[N][N];
		// ���ϻ����� ����� ���� �� ������ �� ���ϱ�
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
	
	// ���ϻ��� ������ �� ���ϱ�
	public static void blindBFS(int x, int y, char color) {
		
		// ���� ��ġ�� queue�� �ִ´�.
		Deque<int[]> que=new ArrayDeque<>();
		que.offer(new int[] {x,y});
		
		// ť�� �� ������ �ݺ��Ѵ�.
		while (!que.isEmpty()) {
			// ť���� ���� ��ġ�� ������.
			int[] curLoc=que.poll();
			
			// ���� ĭ �ֺ��� 4ĭ�� Ž���Ѵ�.
			for (int d=0;d<4;d++) {
				int nextX=curLoc[0]+dx[d];
				int nextY=curLoc[1]+dy[d];
				
				// �迭�� ������ �Ѿ ��� continue
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					continue;
				}
				// �̹� �湮�� ���̸� continue
				if (visited[nextX][nextY]) continue;
				
				// �迭�� ������ �Ѿ�� �ʰ�, �湮�� ���� ���� ĭ
				// �������� �ʷϻ��� ���� �������� �����Ѵ�.
				if (color=='R') {
					if (map[nextX][nextY]=='R' || map[nextX][nextY]=='G') {
						visited[nextX][nextY]=true;
						que.offer(new int[] {nextX,nextY});						
					}
				}
				// �Ķ����� ���
				if (color=='B' && map[nextX][nextY]=='B') {
					visited[nextX][nextY]=true;
					que.offer(new int[] {nextX,nextY});					
				}
			}
		}
		
	}
	
	public static void nonBlindBFS(int x, int y, char color) {
		Deque<int[]> que=new ArrayDeque<>();
		
		// ���� ��ġ�� ť�� �ִ´�.
		que.offer(new int[] {x,y});
		visited[x][y]=true;
		
		// ť�� ������� ���� ������ �ݺ��Ѵ�.
		while (!que.isEmpty()) {
			int[] curLoc=que.poll();
			
			// ���� ĭ�� �������� 4�� Ž��
			for (int d=0;d<4;d++) {
				int nextX=curLoc[0]+dx[d];
				int nextY=curLoc[1]+dy[d];
				
				// �迭�� ������ �Ѿ ��� continue
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					continue;
				}
				// �̹� �湮�� ��� continue
				if (visited[nextX][nextY]) continue;
				
				// �Ű������� �־��� ����� ���� �����̸�
				// ť�� �ְ� �湮 ǥ�ø� �Ѵ�.
				if (map[nextX][nextY]==color) {
					que.add(new int[] {nextX,nextY});
					visited[nextX][nextY]=true;
				}
			}
		}
	}

}
