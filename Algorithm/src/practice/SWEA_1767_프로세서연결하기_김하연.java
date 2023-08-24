package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * ���μ����� ��ġ�� ������ �̵� ������ ������ ��� ����ؼ� ���� ª�� ������ ���� ��
 * �ִ��� ���� core�� ����Ǿ�� �Ѵ�.
 * �켱���� ù ��°�� core�� ��, �� ��°�� ª�� ������ ���� ���̴�.
 * 
 * core�� ��ġ�� ����Ʈ�� ��´�.
 * �� core�� 4 �������� Ž������ ���� ���� ���� �հ�, ������ �������� �ʾ��� ��츦 ����Ѵ�.
 * 
 */
public class SWEA_1767_���μ��������ϱ�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final boolean ON=true;
	static final boolean OFF=true;
	
	static int T;					// �׽�Ʈ���̽� ����
	static int N;					// N*N �迭
	static int C;					// core�� ��ü ����
	static int[][] map;				// �߽ó뽺�� ���¸� �����ϴ� �迭
	static List<int[]> coreLocList;		// �ھ� ��ġ�� ���� ����Ʈ
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static boolean[][] visited;		// �������� ����� ĭ���� �����ϴ� �迭
	static int maxConnected;		// core�� �ִ� ���� ����
	static int minLength;			// �ּ� ���� ������ ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ���̽� �� �Է� �޴´�.
		T=Integer.parseInt(br.readLine());
		
		// �׽�Ʈ���̽� ����ŭ �ݺ��Ѵ�.
		for (int test_case=1;test_case<=T;test_case++) {
			// �迭�� ũ�� �Է¹޴´�.
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			// ���� ������ �Է� �����鼭 core�� ��ġ�� �����Ѵ�.
			coreLocList=new ArrayList<int[]>();
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
					// ���� ĭ�� core�� ���
					if (map[row][col]==1) {
						coreLocList.add(new int[] {row,col});
					}
				}
			}
			// �ھ��� �� ����
			C=coreLocList.size();
			System.out.println(coreLocList.size());
			// �� �ھ�� ������ �� �ִ� ��� ����� ���� ����Ѵ�.
			maxConnected=0;
			minLength=Integer.MAX_VALUE;
			visited=new boolean[N][N];
			dfs(0,0,0);
			
			// �ھ �ִ�� �����Ͽ��� ��, ���� ������ ���� �ּҸ� ����Ѵ�.
			System.out.println("#"+test_case+" "+minLength);
		}

	}
	// coreIdx: ���� �����ϰ� �ִ� �ھ� �ε���, OnCore: ����� �ھ� ��, length: ���� ������ ��
	public static void dfs(int coreIdx,int OnCore,int length) {
		
		// ��� �ھ Ȯ������ ���
		// 1. ����� �ھ��� �� �ִ�, ������ ���� �ּڰ�
		if (coreIdx==C) {
			if (maxConnected<OnCore) {
				maxConnected=OnCore;
				minLength=length;
			}
			// ����� �ھ��� ���� ���� ����, ���� ������ �ּڰ�
			else if(maxConnected==OnCore) {
				minLength=Math.min(length, minLength);
			}
			
			return;
		}
		int curX=coreLocList.get(coreIdx)[0];
		int curY=coreLocList.get(coreIdx)[1];
		
		// ������ ����Ǿ� �ִ� ���
		if (curX==0 || curX==N-1 || curY==0 || curY==N-1) {
			dfs(coreIdx+1,OnCore+1,length);
			return;
		}
		
		// 4���� Ž��
		for (int d=0;d<4;d++) {
			int nextX=curX+dx[d];
			int nextY=curY+dy[d];
			
			// �� �������� ������ �� �ִ��� Ȯ���Ѵ�.
			boolean possible=isPossible(nextX,nextY,dx[d],dy[d]);
			
			if (!possible) continue;
			
			// ������ �� �ִٸ�, ������ �����Ѵ�.
			int newLength=connect(nextX,nextY,dx[d],dy[d]);
			// �� �������� ����� ���·� ���� core ����
			dfs(coreIdx+1,OnCore+1,length+newLength);
			
			// �ٸ� ���� Ž���� ���� ���ἱ ���󺹱�
			unconnect(nextX,nextY,dx[d],dy[d]);
			
		}
		
		// ���� �ھ ������ �������� �ʰ� ���� �ھ� ���� Ž��
		dfs(coreIdx+1,OnCore,length);
	}
	
	public static boolean isPossible(int x,int y,int dx,int dy) {
		
		int cx=x;
		int cy=y;
		boolean possible=true;
		
		while (true) {
			//System.out.println("possible");
			// �迭�� ����� ���
			if (cx<0 || cx>=N || cy<0 || cy>=N) break;
			
			// �̹� ������ ����� ���� ���
			// ������ �� ����.
			if (visited[cx][cy]) {
				possible=false;
				break;
			}
			
			// core�� ���θ��� �ִ� ���
			// ������ �� ����.
			if (map[cx][cy]==1) {
				possible=false;
				break;
			}
			cx+=dx;
			cy+=dy;
		}
		
		return possible;
	}
	// ������ �� �ִٴ� ���� �Ͽ� ������ ������ �����ϰ� ������ ���̸� ��ȯ�Ѵ�.
	public static int connect(int x, int y, int dx, int dy) {
		
		int cx=x;
		int cy=y;
		int length=0;
		while (true) {
			//System.out.println("connect");
			// �迭�� ����� ���
			if (cx<0 ||cx>=N || cy<0 || cy>=N) break;
			
			// core�� ���θ��� �ִ� ���
			if (map[cx][cy]==1) {
				break;
			}
			
			// ���� ĭ�� �������� ä���.
			visited[cx][cy]=ON;
			// ������ ���̸� 1 ������Ų��.
			length+=1;
			
			// ���� ĭ���� �̵�
			cx+=dx;
			cy+=dy;
		}
		return length;
	}
	
	// ������ ������ ���� ���ͽ�ų �� ����Ѵ�.
	// ������ ��ĥ �� �����Ƿ� false�� �ٲٸ� �ȴ�.
	public static void unconnect(int x,int y,int dx,int dy) {
		
		int cx=x;
		int cy=y;
		
		while(true) {
			//System.out.println("unconnect");
			// �迭�� ����� ���
			if (cx<0 || cx>=N || cy<0 || cy>=N) {
				break;
			}
			
			// core�� ���θ��� �ִ� ���
			if (map[cx][cy]==1) {
				break;
			}
			
			// ���� ĭ�� ������� �ٲ۴�.(�������� ���� ���·�)
			visited[cx][cy]=OFF;
			
			// ���� ĭ���� �̵�
			cx+=dx;
			cy+=dy;
			
		}
	}
	
	
	
	
	
}
