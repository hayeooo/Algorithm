package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 20058: ������ ���� ���̾��
 * 1. �迭 ������ (���� �簢�� ũ��, ���� ��������)�� �Ű������� ������ �κ� ������ ũ�⿡ ���� �� ĭ�� �����δ�.
 * 2. ���̾�� ����
 * 
 * �����ִ� ���� A[r][c]�� �հ� �����ִ� ���� �� ���� ū ����� �����ϴ� ĭ�� ������ ����Ѵ�.
 */
public class BOJ_20058_������������̾��_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,Q;					// 2^N���� ���� ���� ĭ, ������ �� ������ �ܰ� Q
	static int[][] A;					// ���� ������ ������ �迭
	
	// ������ ĭ�� ������ �ִ��� Ž���ϱ� ���� ��Ÿ �迭
	static int[] dx= {-1,0,1,0};		// ��, ��, ��, ��
	static int[] dy= {0,1,0,-1};
	static boolean[][] visited;
	
	static int totalIce=0;
	static int iceSize;
	static int maxIce=0;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// N, Q �Է� �޴´�.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		// ����ĭ �迭 ũ�� �ʱ�ȭ
		int n=(int)Math.pow(2, N);
		A=new int[n][n];
		
		// ����ĭ ���� ����
		for (int row=0;row<n;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<n;col++) {
				A[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		// ������ ��� L �ܰ� ����
		st=new StringTokenizer(br.readLine().trim());
		for (int l=0;l<Q;l++) {
			int curLevel=Integer.parseInt(st.nextToken());
			
			// 2^L*2^L ũ���� ���ڸ� �ð�������� 90�� ȸ����Ų��.
			int size=(int)Math.pow(2, curLevel);
			
			// �κ� ������ �簢���� ȸ����Ų��.
			for (int row=0;row<=n-size;row+=size) {
				for (int col=0;col<=n-size;col+=size) {
					rotate(row,col,size);
				}
			}
			
			// ȸ����Ų ��
			// ������ �ִ� ĭ 3�� �Ǵ� �� �̻�� ���������� ���� ĭ�� ������ ���� 1 �پ���.
			reduceIce(n);
			
			
		}
		// ������ ���� ��
		// ���� ������ ���Ѵ�.
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				totalIce+=A[row][col];
			}
		}
		
		// ���� ū ������ ã�´�.
		visited=new boolean[n][n];
		
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				if (visited[row][col]) continue;
				if (A[row][col]==0) continue;
				// ���� ��ġ�� ���� ����� ���Ѵ�.
				iceSize=searchIce(row,col,n);
				
				// �� ū ���� ������� ���� �����Ѵ�.
				maxIce=Math.max(maxIce, iceSize);
			}
		}
		System.out.println(totalIce+" "+maxIce);

	}
	// ȸ����ų �κ� ������ ���� �� ������ ũ�⸦ �Ű������� �޾�
	// �κа��ڸ� 90�� ȸ����Ų��.
	public static void rotate(int r,int c, int size) {
		// �ٱ� �簢�� �׵θ����� ���ʷ� ȸ���Ѵ�.
		// size�� 2�� �����鼭 0���� Ŭ ������ �κа��ڸ� �̵���Ų��.
		int curR=r;
		int curC=c;
		int curSize=size;
		while (curSize>=2) {
			
			for (int move=0;move<curSize-1;move++) {
				moveByOne(curR,curC,curSize);
			}
			// 90�� ȸ���� ������ �� ���� �ٱ� �׵θ� �κа��ڷ� ũ��� ��ġ�� �����Ѵ�.
			curR+=1;
			curC+=1;
			curSize-=2;
		}
		
		
	}
	
	// ������ �ð� �������� �� ĭ�� �̵��Ѵ�.
	public static void moveByOne(int r, int c, int size) {
		
		int tmp=A[r][c];
		
		// �������� �̵�
		for (int row=r+1;row<r+size;row++) {
			A[row-1][c]=A[row][c];
		}
		// �������� �̵�
		for (int col=c+1;col<c+size;col++) {
			A[r+size-1][col-1]=A[r+size-1][col];
		}
		// �Ʒ������� �̵�
		for (int row=r+size-2;row>=r;row--) {
			A[row+1][c+size-1]=A[row][c+size-1];
		}
		// ���������� �̵�
		for (int col=c+size-2;col>=c+1;col--) {
			A[r][col+1]=A[r][col];
		}
		// �ӽ� ���� �� �迭�� ����
		A[r][c+1]=tmp;
	}
	
	public static void reduceIce(int n) {
		
		// �쿩�� �� ����ĭ�� ��ġ�� �����ϴ� ����Ʈ
		List<int[]> toReduce=new ArrayList<>();
		
		// �ѹ��� �쿩�� �Ѵ�.
		// �쿩�� �� ����ĭ�� ��ġ�� �����Ѵ�.
		for (int row=0;row<n;row++) {
			for (int col=0;col<n;col++) {
				
				// �̹� ������ ���� ĭ�̶�� �Ѿ��.
				if (A[row][col]==0) continue;
				
				// �ٱ� �׵θ��� ������ ������ ���� ������ ĭ �� ������ �ִ� ĭ�� 3���� �ƴ� ��� 1 �����Ѵ�.
				int iceCnt=0;
				// 4���� Ž��
				for (int d=0;d<4;d++) {
					int nextX=row+dx[d];
					int nextY=col+dy[d];
					if (nextX<0 || nextX>=n || nextY<0 || nextY>=n) {
						continue;
					}
					int nextIce=A[nextX][nextY];
					if (nextIce>0) {
						iceCnt+=1;
					}
				}
				// ������ ���� ĭ�� 3�� �̻��� ���
				if (iceCnt<3) {
					toReduce.add(new int[] {row,col});
				}
			}
		}
		
		for (int[] reduceLoc:toReduce) {
			A[reduceLoc[0]][reduceLoc[1]]-=1;
		}
	
	}
	// ������ ũ�⸦ ���Ѵ�.(bfs)
	public static int searchIce(int r,int c, int mapSize) {
		int curSize=1;
		
		Deque<int[]> que=new ArrayDeque<>();
		que.offer(new int[] {r,c});
		visited[r][c]=true;
		
		while (!que.isEmpty()) {
			int[] loc=que.poll();
			
			for (int d=0;d<4;d++) {
				int nextX=loc[0]+dx[d];
				int nextY=loc[1]+dy[d];
				
				// �迭�� ������ �Ѿ� Ž���� ���
				if (nextX<0 || nextX>=mapSize || nextY<0 || nextY>=mapSize) {
					continue;
				}
				// �̹� �湮�� ĭ�� ���
				if (visited[nextX][nextY]) continue;
				
				// ������ ���� ĭ�� ���
				if (A[nextX][nextY]==0) continue;
				
				// ���ǿ� �����ϴ� ����ĭ�� ť�� �־� �ٸ� ������ ����ĭ�� Ž���Ѵ�.
				que.offer(new int[] {nextX,nextY});
				curSize+=1;
				visited[nextX][nextY]=true;
			}
		}
		
		return curSize;
	}

}
