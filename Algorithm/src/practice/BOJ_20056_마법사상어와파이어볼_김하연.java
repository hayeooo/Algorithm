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

public class BOJ_20056_마법사상어와파이어볼_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;			// N*N 격자
	static int M;			// 파이어볼 M개 발사
	static int K;			// 명령 횟수
	
	/* dir
	 * 7 0 1
	 * 6 X 2
	 * 5 4 3
	 * */
	static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static Deque<Fireball> toMove;			// 이동해야할 파이어볼의 리스트
	static List<Fireball> finished;			// 이동을 완료한 파이어볼의 리스트
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M, K를 입력으로 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		toMove=new ArrayDeque<>();
		
		// 파이어볼의 정보를 입력 받는다.
		// 이동해야할 파이어볼들을 초기화한다.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			Fireball fb=new Fireball(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			toMove.offer(fb);
		}
		
		// 입력받은 명령의 개수만큼 반복한다.
		for (int k=0;k<M;k++) {
			finished=new ArrayList<>();
			// 이동해야할 파이어볼을 이동시킨다.(toMove에 있는 모든 파이어볼 대상)-> tofinish로 옮기기
			moveFireball();
			// 2개 이상의 파이어볼이 있는 칸에서 마법
			
			// 이동해야할 파이어볼 저장, 질량이 0인 파이어볼은 소멸
		}
		
		
	}
	// 파이어볼을 이동시킨다.
	public static void moveFireball() {
		while (!toMove.isEmpty()) {
			Fireball fb=toMove.poll();
			
			// 파이어볼의 다음 위치
			fb.r=(fb.r+dir[fb.d][0]*fb.s)%N;
			fb.c=(fb.c+dir[fb.d][1]*fb.s)%N;
			
			if (fb.r<0) {
				fb.r+=N;
			}
			if (fb.c<0) {
				fb.c+=N;
			}
			// 이동을 마친 파이어볼을 저장한다.
			finished.add(fb);
		}
	}
	// 2개 이상의 파이어볼을 나눈다.
	public static void divideFireball() {
		
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				// row, col 같은 위치의 파이어볼의 질량 합. 속력의 합, 파이어 볼의 개수, 방향을 점검한다.
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
				
				// 파이어볼 나누기
				
			}
		}
	}
}
