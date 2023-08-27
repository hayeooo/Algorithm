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
 * BOJ 17143: ���ÿ�
 * 1. ���ÿ��� ���������� �� ĭ �̵��Ѵ�.
 * 2. ���ÿ��� �ִ� ���� �ִ� ��� �߿��� ���� ���� ����� �� ��´�.
 * 3. �� �̵��Ѵ�.
 * 
 * ���� �־��� �ӵ��� �̵��ϰ�, �ӵ��� ������ ĭ/���̴�.
 */
class Shark{
	int r;			// ����� ��ġ
	int c;
	int s;			// �ӷ�
	int d;			// �̵� ���� (0: ��, 1: �Ʒ�, 2: ������, 3: ����)
	int z;			// ũ��
	
	public Shark(int r, int c, int s, int d, int z) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}
public class BOJ_17143_���ÿ�_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;			// R*C �������� ũ��
	static int M;			// ����� ��
	static Shark[][] board;
	static Deque<Shark> sharkQ;
	
	static int[] dx= {-1,0,1,0};		// ��, ������, �Ʒ�, ����
	static int[] dy= {0,1,0,-1};
	
	static long totalSize=0;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �������� ũ�� R,C�� ����� ���� �Է¹޴´�.
		st=new StringTokenizer(br.readLine().trim());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		
		board=new Shark[R][C];
		
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			
			if (d==1) {
				d=2;
			}
			else if(d==2) {
				d=1;
			}
			Shark shark=new Shark(r,c,s,d,z);
			board[r][c]=shark;
		}
		
		// ���ÿ��� ���������� �� ĭ�� �̵��Ѵ�.
		for (int person=0;person<C;person++) {
			// �� ��´�.
			getShark(person);
			
			sharkQ=new ArrayDeque<Shark>();
			// ���� �����ִ� �� queue�� �ִ´�.
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (board[r][c]!=null) {
						sharkQ.offer(board[r][c]);
					}
				}
			}
			
			// �� �̵���Ų��.
			move();
			
		}
		
		System.out.println(totalSize);
	}
	// ���� ������ ���� ����� �� ��´�.
	public static void getShark(int col) {
		for (int r=0;r<R;r++) {
			if (board[r][col]!=null) {
				totalSize+=board[r][col].z;
				board[r][col]=null;
				break;
			}
		}
	}
	// ����� ��ġ�� �̵��ϰ� board�� �����Ѵ�.
	public static void move() {
		board=new Shark[R][C];
		// �����ϴ� �� ��� �̵���Ų��.
		while (!sharkQ.isEmpty()) {
			Shark curShark=sharkQ.poll();
			
			// ���� �̵��� �ӷ��� ���Ѵ�.
			// ���������� ���
			if (curShark.d==0||curShark.d==2) {
				curShark.s=(curShark.s)%((R-1)*2);
			}
			// ��������� ���
			else if(curShark.d==1||curShark.d==3) {
				curShark.s=(curShark.s)%((C-1)*2);
			}
			
			for (int sec=0;sec<curShark.s;sec++) {
				// �� �̵��� ���� ��ġ
				int nextR=curShark.r+dx[curShark.d];
				int nextC=curShark.c+dy[curShark.d];
				
				// �迭 ������ �Ѿ ��� ������ �ٲ�� �Ѵ�.
				if (nextR<0 || nextR>=R || nextC<0 || nextC>=C) {
					curShark.d=(curShark.d+2)%4;
					nextR=curShark.r+dx[curShark.d];
					nextC=curShark.c+dy[curShark.d];
				}
				curShark.r=nextR;
				curShark.c=nextC;
			}
			// �̵��� ��ġ�� ��������, �� ��ġ��Ų��.(�̹� �� �����ϴ��� üũ)
			if (board[curShark.r][curShark.c]!=null) {
				// �ڽ��� ũ�Ⱑ �� ũ�ٸ� ��Ƹ���
				if (board[curShark.r][curShark.c].z<curShark.z) {
					board[curShark.r][curShark.c]=new Shark(curShark.r,curShark.c,curShark.s,curShark.d,curShark.z);
				}
			}
			else {
				board[curShark.r][curShark.c]=new Shark(curShark.r,curShark.c,curShark.s,curShark.d,curShark.z);
			}
		}
	}
	public static void printShark() {
		System.out.println("============================");
		for (int row=0;row<R;row++) {
			System.out.println(Arrays.toString(board[row]));
		}
	}
}
