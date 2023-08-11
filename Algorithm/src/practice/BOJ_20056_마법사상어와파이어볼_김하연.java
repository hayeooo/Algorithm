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
 *  BOJ 20056: 마법사 상어와 파이어볼
 *  마법사 상어는 크기가 N*N인 격자에 파이어볼 M개를 발사한다.
 *  파이어볼의 위치는 (r,c) 질량은 m, 방향은 d, 속력은 s이다.
 *  격자의 행과 열은 1부터 N까지 번호가 매겨져 있고, 1과 N번째 행과 열은 각각 연결되어 있다.
 *  
 *  이동을 명령하면 다음의 일이 일어난다.
 *  1. 모든 파이어볼이 자신의 방향 d로 속력 s칸만큼 이동한다.
 *  2. 이동이 모두 끝나면, 2개 이상의 파이어볼이 있는 칸에서 다음의 일이 일어난다.
 *  	2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
 *  	2-2. 4개의 파이어볼로 나누어진다.
 *  	2-3. 나누어진 파이어볼의 질량, 속력 방향은 아래와 같다.
 *  		질량 (합쳐진 파이어볼 질량의 합)/5 
 *  		속력은 (합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)
 *  		합쳐지는 파이어볼의 방향이 모두 짝수이면, 방향은 0,2,4,6이 되고, 그렇지 않으면 1,3,5,7이 된다.
 *  	2-4. 질량이 0인 파이어볼은 소멸되어 없어진다.
 *  
 *  *풀이 방법*
 *  1. 파이어볼의 정보를 입력받아 파이어볼 리스트에 넣는다.
 *  2. 입력 받은 정보를 바탕으로 파이어볼을 이동시키고, 이동한 위치에 있는 파이어볼들을 저장한다.(같은 위치에 있는 파이어볼이 2개 이상 있는지 확인하기 위해)
 *  3. 파이어볼 리스트를 초기화한다.
 *  4. 같은 위치에 있는 파이어볼의 질량 합, 속력 합을 구하여 4개로 나누고 파이어볼 리스트에 넣는다.
 *  	4-1. 같은 위치에 있는 파이어볼이 하나인 경우, 파이어볼 하나를 리스트에 넣는다.
 *  5. 명령 횟수만큼 시행한 후, 파이어볼 리스트에서 최종적인 질량 합을 구하여 출력한다.
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
	static int[] dx= {-1,-1,0,1,1,1,0,-1};			// 8가지 방향에 대한 델타값
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	
	static List<Fireball> fireballs;				// 이동해야 할 파이어볼을 담은 리스트 배열
	static Queue<Fireball>[][] grid;				// 이동한 후, 위치한 파이어볼을 담은 큐 배열
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		// N, M, K 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		
		fireballs=new ArrayList<Fireball>();	
		grid=new Queue[N][N];					// 이동 후, 같은 위치의 파이어볼이 있는지 확인하기 위한 Queue 이차원 배열
		
		// queue 배열에 각각 객체를 생성한다.
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				grid[i][j]=new LinkedList<>();
			}
		}
		
		// 파이어볼의 정보를 입력 받는다.
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
		// K 번의 명령동안 파이어볼을 이동하고, 2개 이상의 파이어볼을 나눈다.
		for (int idx=0;idx<K;idx++) {
			moveFireball();
			divideFireball();
		}
		// 명령을 수행한 후, 파이어볼 질량의 합을 구한다.
		int result=0;
		for (int idx=0;idx<fireballs.size();idx++) {
			result+=fireballs.get(idx).m;
		}
		
		// 결과를 출력한다.
		System.out.println(result);
		
	}
	// 파이어볼을 이동시킨다.
	public static void moveFireball() {
		// 리스트에 담겨있는 파이어볼의
		// 방향과 위치에 따라 이동시키고 
		// 이동한 위치를 저장하여 그리드에 반영한다.
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
			// grid에 위치를 반영한다.
			grid[fb.r][fb.c].add(fb);
		}
	}
	// 2개 이상의 파이어볼을 나눈다.
	public static void divideFireball() {
		// 파이어볼을 나눈 후, 이동해야할 파이어볼을 저장할 리스트를 초기화한다.
		fireballs=new ArrayList<>();
		
		// 그리드 모든 위치에 대해
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				
				// 해당 위치에 파이어볼의 개수가 하나일 경우
				if (grid[row][col].size()==1) {
					// 리스트에 해당 파이어볼을 저장한다.
					fireballs.add(grid[row][col].poll());
				}
				// 해당 위치에 있는 파이어볼의 개수가 2개 이상인 경우
				else if(grid[row][col].size()>=2) {
					
					int size=grid[row][col].size();
					int sumM=0;			// 같은 위치 파이어볼 질량 총합
					int sumS=0;			// 같은 위치 파이어볼의 속력 총합
					int isOdd=0;		// 방향 홀수 개수
					
					// 해당 그리드에 파이어볼 수가 없어질 때까지 반복
					// 같은 위치에 있는 모든 파이어볼의 질량과 속력, 방향이 짝수인지 홀수인지 구한다.
					while (!grid[row][col].isEmpty()) {
						Fireball fb=grid[row][col].poll();
						sumM+=fb.m;
						sumS+=fb.s;
						if (fb.d%2!=0) isOdd+=1;
					}
					// 홀수와 짝수가 섞여있을 때
					int dirDelta=1;
					// 모두 홀수이거나 짝수이거나
					if (isOdd==0 || isOdd==size) {
						dirDelta=0;
					}
					// 나누어질 파이어볼의 질량 :질량 총합 / 5
					int mPerFb=sumM/5;
					// 나누어질 파이어볼의 속력: 속력 총합 / 같은 위치 파이어볼 수
					int sPerFb=sumS/size;
					// 나누어질 파이어볼의 질량이 0일 경우 무시한다.
					if (mPerFb==0) continue;
					// 새롭게 나누어진 파이어볼들을 리스트에 담는다.
					for (int dir=0;dir<=6;dir+=2) {
						fireballs.add(new Fireball(row,col,mPerFb,sPerFb,dir+dirDelta));
					}
				}
			}
		}
		
	}
	
	
}
