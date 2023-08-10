package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;


class Fireball{
	int r;
	int c;
	int m;
	int s;
	int d;
	
	Fireball(int r, int c, int m, int s, int d){
		this.r=r;
		this.c=c;
		this.m=m;
		this.s=s;
		this.d=d;
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
	static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static Deque<Fireball> toMove;			// �̵��ؾ��� ���̾�� ����Ʈ
	static List<Fireball> finished;			// �̵��� �Ϸ��� ���̾�� ����Ʈ
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M, K�� �Է����� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		toMove=new ArrayDeque<>();
		
		// ���̾�� ������ �Է� �޴´�.
		// �̵��ؾ��� ���̾���� �ʱ�ȭ�Ѵ�.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			Fireball fb=new Fireball(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			toMove.offer(fb);
		}
		
		// �Է¹��� ����� ������ŭ �ݺ��Ѵ�.
		for (int k=0;k<M;k++) {
			finished=new ArrayList<>();
			// �̵��ؾ��� ���̾�� �̵���Ų��.(toMove�� �ִ� ��� ���̾ ���)-> tofinish�� �ű��
			moveFireball();
			// 2�� �̻��� ���̾�� �ִ� ĭ���� ����
			
			// �̵��ؾ��� ���̾ ����, ������ 0�� ���̾�� �Ҹ�
		}
		
		
	}
	// ���̾�� �̵���Ų��.
	public static void moveFireball() {
		while (!toMove.isEmpty()) {
			Fireball fb=toMove.poll();
			
			// ���̾�� ���� ��ġ
			fb.r=(fb.r+dir[fb.d][0]*fb.s)%N;
			fb.c=(fb.c+dir[fb.d][1]*fb.s)%N;
			
			if (fb.r<0) {
				fb.r+=N;
			}
			if (fb.c<0) {
				fb.c+=N;
			}
			// �̵��� ��ģ ���̾�� �����Ѵ�.
			finished.add(fb);
		}
	}
	// 2�� �̻��� ���̾�� ������.
	public static void divideFireball() {
		
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				// row, col ���� ��ġ�� ���̾�� ���� ��. �ӷ��� ��, ���̾� ���� ����, ������ �����Ѵ�.
				int sumM = 0;
				int sumD=0;
				int cnt=0;
				int isOdd=0;
				
				for (int idx=finished.size()-1;idx>=0;idx--) {
					Fireball fb=finished.get(idx);
					if (row == fb.r && col==fb.c) {
						sumM+=fb.m;
						sumD+=fb.d;
						cnt+=1;
						if (fb.d%2==1) {
							isOdd+=1;
						}
						finished.remove(idx);
					}
				}
				
				// ���̾ ������
				
			}
		}
	}
}
