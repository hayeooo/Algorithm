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
 * BOJ 17143: 낚시왕
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
 * 3. 상어가 이동한다.
 * 
 * 상어는 주어진 속도로 이동하고, 속도의 단위는 칸/초이다.
 */
class Shark{
	int r;			// 상어의 위치
	int c;
	int s;			// 속력
	int d;			// 이동 방향 (0: 위, 1: 아래, 2: 오른쪽, 3: 왼쪽)
	int z;			// 크기
	
	public Shark(int r, int c, int s, int d, int z) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}
public class BOJ_17143_낚시왕_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;			// R*C 격자판의 크기
	static int M;			// 상어의 수
	static Shark[][] board;
	static Deque<Shark> sharkQ;
	
	static int[] dx= {-1,0,1,0};		// 위, 오른쪽, 아래, 왼쪽
	static int[] dy= {0,1,0,-1};
	
	static long totalSize=0;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 격자판의 크기 R,C와 상어의 수를 입력받는다.
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
		
		// 낚시왕이 오른쪽으로 한 칸씩 이동한다.
		for (int person=0;person<C;person++) {
			// 상어를 잡는다.
			getShark(person);
			
			sharkQ=new ArrayDeque<Shark>();
			// 현재 남아있는 상어를 queue에 넣는다.
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (board[r][c]!=null) {
						sharkQ.offer(board[r][c]);
					}
				}
			}
			
			// 상어를 이동시킨다.
			move();
			
		}
		
		System.out.println(totalSize);
	}
	// 현재 열에서 가장 가까운 상어를 잡는다.
	public static void getShark(int col) {
		for (int r=0;r<R;r++) {
			if (board[r][col]!=null) {
				totalSize+=board[r][col].z;
				board[r][col]=null;
				break;
			}
		}
	}
	// 상어의 위치를 이동하고 board에 저장한다.
	public static void move() {
		board=new Shark[R][C];
		// 존재하는 상어를 모두 이동시킨다.
		while (!sharkQ.isEmpty()) {
			Shark curShark=sharkQ.poll();
			
			// 실제 이동할 속력을 구한다.
			// 수직방향일 경우
			if (curShark.d==0||curShark.d==2) {
				curShark.s=(curShark.s)%((R-1)*2);
			}
			// 수평방향일 경우
			else if(curShark.d==1||curShark.d==3) {
				curShark.s=(curShark.s)%((C-1)*2);
			}
			
			for (int sec=0;sec<curShark.s;sec++) {
				// 상어가 이동할 다음 위치
				int nextR=curShark.r+dx[curShark.d];
				int nextC=curShark.c+dy[curShark.d];
				
				// 배열 범위가 넘어간 경우 방향을 바꿔야 한다.
				if (nextR<0 || nextR>=R || nextC<0 || nextC>=C) {
					curShark.d=(curShark.d+2)%4;
					nextR=curShark.r+dx[curShark.d];
					nextC=curShark.c+dy[curShark.d];
				}
				curShark.r=nextR;
				curShark.c=nextC;
			}
			// 이동할 위치가 결정나면, 상어를 위치시킨다.(이미 상어가 존재하는지 체크)
			if (board[curShark.r][curShark.c]!=null) {
				// 자신의 크기가 더 크다면 잡아먹음
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
