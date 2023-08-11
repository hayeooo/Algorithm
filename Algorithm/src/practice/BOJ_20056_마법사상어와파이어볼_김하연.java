package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  BOJ 20056: ������ ���� ���̾
 *  ������ ���� ũ�Ⱑ N*N�� ���ڿ� ���̾ M���� �߻��Ѵ�.
 *  ���̾�� ��ġ�� (r,c) ������ m, ������ d, �ӷ��� s�̴�.
 *  ������ ��� ���� 1���� N���� ��ȣ�� �Ű��� �ְ�, 1�� N��° ��� ���� ���� ����Ǿ� �ִ�.
 *  
 *  �̵��� ����ϸ� ������ ���� �Ͼ��.
 *  1. ��� ���̾�� �ڽ��� ���� d�� �ӷ� sĭ��ŭ �̵��Ѵ�.
 *  2. �̵��� ��� ������, 2�� �̻��� ���̾�� �ִ� ĭ���� ������ ���� �Ͼ��.
 *  	2-1. ���� ĭ�� �ִ� ���̾�� ��� �ϳ��� ��������.
 *  	2-2. 4���� ���̾�� ����������.
 *  	2-3. �������� ���̾�� ����, �ӷ� ������ �Ʒ��� ����.
 *  		���� (������ ���̾ ������ ��)/5 
 *  		�ӷ��� (������ ���̾ �ӷ��� ��)/(������ ���̾�� ����)
 *  		�������� ���̾�� ������ ��� ¦���̸�, ������ 0,2,4,6�� �ǰ�, �׷��� ������ 1,3,5,7�� �ȴ�.
 *  	2-4. ������ 0�� ���̾�� �Ҹ�Ǿ� ��������.
 *  
 *  *Ǯ�� ���*
 *  1. ���̾�� ������ �Է¹޾� ���̾ ����Ʈ�� �ִ´�.
 *  2. �Է� ���� ������ �������� ���̾�� �̵���Ű��, �̵��� ��ġ�� �ִ� ���̾���� �����Ѵ�.(���� ��ġ�� �ִ� ���̾�� 2�� �̻� �ִ��� Ȯ���ϱ� ����)
 *  3. ���̾ ����Ʈ�� �ʱ�ȭ�Ѵ�.
 *  4. ���� ��ġ�� �ִ� ���̾�� ���� ��, �ӷ� ���� ���Ͽ� 4���� ������ ���̾ ����Ʈ�� �ִ´�.
 *  	4-1. ���� ��ġ�� �ִ� ���̾�� �ϳ��� ���, ���̾ �ϳ��� ����Ʈ�� �ִ´�.
 *  5. ��� Ƚ����ŭ ������ ��, ���̾ ����Ʈ���� �������� ���� ���� ���Ͽ� ����Ѵ�.
 * */
class Fireball{
	int r;
	int c;
	int m;
	int s;
	int d;
	Fireball(){
		
	}
	Fireball(int r, int c, int m, int s, int d){
		this.r=r;
		this.c=c;
		this.m=m;
		this.s=s;
		this.d=d;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( row:"+r+", col:"+c+", M"+m+", S:"+s+", D:"+d+")";
	}
	
}

public class BOJ_20056_������������̾_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;			// N*N ����
	static int M;			// ���̾ M�� �߻�
	static int K;			// ��� Ƚ��
	
	/* dir
	 * 7 0 1
	 * 6 X 2
	 * 5 4 3
	 * */
	static int[] dx= {-1,-1,0,1,1,1,0,-1};			// 8���� ���⿡ ���� ��Ÿ��
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	
	static List<Fireball> fireballs;				// �̵��ؾ� �� ���̾�� ���� ����Ʈ �迭
	static Queue<Fireball>[][] grid;				// �̵��� ��, ��ġ�� ���̾�� ���� ť �迭
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		// N, M, K �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		
		fireballs=new ArrayList<Fireball>();	
		grid=new Queue[N][N];					// �̵� ��, ���� ��ġ�� ���̾�� �ִ��� Ȯ���ϱ� ���� Queue ������ �迭
		
		// queue �迭�� ���� ��ü�� �����Ѵ�.
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				grid[i][j]=new LinkedList<>();
			}
		}
		
		// ���̾�� ������ �Է� �޴´�.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine());
			Fireball fb=new Fireball();
			fb.r=Integer.parseInt(st.nextToken())-1;
			fb.c=Integer.parseInt(st.nextToken())-1;
			fb.m=Integer.parseInt(st.nextToken());
			fb.s=Integer.parseInt(st.nextToken());
			fb.d=Integer.parseInt(st.nextToken());
			fireballs.add(fb);
			
			
		}
		// K ���� ��ɵ��� ���̾�� �̵��ϰ�, 2�� �̻��� ���̾�� ������.
		for (int idx=0;idx<K;idx++) {
			moveFireball();
			divideFireball();
		}
		// ����� ������ ��, ���̾ ������ ���� ���Ѵ�.
		int result=0;
		for (int idx=0;idx<fireballs.size();idx++) {
			result+=fireballs.get(idx).m;
		}
		
		// ����� ����Ѵ�.
		System.out.println(result);
		
	}
	// ���̾�� �̵���Ų��.
	public static void moveFireball() {
		// ����Ʈ�� ����ִ� ���̾��
		// ����� ��ġ�� ���� �̵���Ű�� 
		// �̵��� ��ġ�� �����Ͽ� �׸��忡 �ݿ��Ѵ�.
		for (int idx=0;idx<fireballs.size();idx++) {
			Fireball fb=fireballs.get(idx);
			fb.r+=dx[fb.d]*(fb.s);
			fb.c+=dy[fb.d]*(fb.s);
			fb.r%=N;
			fb.c%=N;
			if (fb.r<0) {
				fb.r+=N;
			}
			if (fb.c<0) {
				fb.c+=N;
			}
			// grid�� ��ġ�� �ݿ��Ѵ�.
			grid[fb.r][fb.c].add(fb);
		}
	}
	// 2�� �̻��� ���̾�� ������.
	public static void divideFireball() {
		// ���̾�� ���� ��, �̵��ؾ��� ���̾�� ������ ����Ʈ�� �ʱ�ȭ�Ѵ�.
		fireballs=new ArrayList<>();
		
		// �׸��� ��� ��ġ�� ����
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				
				// �ش� ��ġ�� ���̾�� ������ �ϳ��� ���
				if (grid[row][col].size()==1) {
					// ����Ʈ�� �ش� ���̾�� �����Ѵ�.
					fireballs.add(grid[row][col].poll());
				}
				// �ش� ��ġ�� �ִ� ���̾�� ������ 2�� �̻��� ���
				else if(grid[row][col].size()>=2) {
					
					int size=grid[row][col].size();
					int sumM=0;			// ���� ��ġ ���̾ ���� ����
					int sumS=0;			// ���� ��ġ ���̾�� �ӷ� ����
					int isOdd=0;		// ���� Ȧ�� ����
					
					// �ش� �׸��忡 ���̾ ���� ������ ������ �ݺ�
					// ���� ��ġ�� �ִ� ��� ���̾�� ������ �ӷ�, ������ ¦������ Ȧ������ ���Ѵ�.
					while (!grid[row][col].isEmpty()) {
						Fireball fb=grid[row][col].poll();
						sumM+=fb.m;
						sumS+=fb.s;
						if (fb.d%2!=0) isOdd+=1;
					}
					// Ȧ���� ¦���� �������� ��
					int dirDelta=1;
					// ��� Ȧ���̰ų� ¦���̰ų�
					if (isOdd==0 || isOdd==size) {
						dirDelta=0;
					}
					// �������� ���̾�� ���� :���� ���� / 5
					int mPerFb=sumM/5;
					// �������� ���̾�� �ӷ�: �ӷ� ���� / ���� ��ġ ���̾ ��
					int sPerFb=sumS/size;
					// �������� ���̾�� ������ 0�� ��� �����Ѵ�.
					if (mPerFb==0) continue;
					// ���Ӱ� �������� ���̾���� ����Ʈ�� ��´�.
					for (int dir=0;dir<=6;dir+=2) {
						fireballs.add(new Fireball(row,col,mPerFb,sPerFb,dir+dirDelta));
					}
				}
			}
		}
		
	}
	
	
}
