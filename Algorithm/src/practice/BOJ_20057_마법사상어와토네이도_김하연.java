package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * BOJ 20057: 마법사 상어와 토네이도
 * 토네이도가 회전하면서 방향마다 이동할 수 있는 거리를 저장한다. ex) {1,1,2,2,3,3,4,4,5,5,6,6,6}
 * 이동할 수 있는 거리만큼 반복하면서 모래를 이동시킨다.
 * 한 방향에서 이동할 수 있는 거리만큼 움직였다면  방향을 바꾼다.
 */
public class BOJ_20057_마법사상어와토네이도_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int[][] map;
	
	static int[] dx= {0,1,0,-1};	// 서, 남, 동, 북
	static int[] dy= {-1,0,1,0};
	static int[] dist;
	static int cx;
	static int cy;
	static int nx;
	static int ny;
	static int alphaX;
	static int alphaY;
	
	static int spreadSand;			// 배열 밖으로 사라진 모래
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine().trim());
		map=new int[N][N];
		
		// 격자 정보를 배열에 입력한다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도가 회전하면서 각 방향마다 이동할 수 있는 거리
		dist=new int[2*N-1];
		int num=1;
		for (int idx=0;idx<dist.length-2;idx+=2) {
			dist[idx]=num;
			dist[idx+1]=num;
			dist[idx+2]=num;
			num+=1;
		}
		// 초기화는 서쪽 방향
		int cd=0;
		cx=N/2;
		cy=N/2;
		
		for (int distIdx=0;distIdx<dist.length;distIdx++) {
			// 각 방향마다 이동할 수 있는 거리만큼 움직인다.
			for (int progress=0;progress<dist[distIdx];progress++) {
				
				nx=cx+dx[cd];				// y의 위치
				ny=cy+dy[cd];
				alphaX=cx+2*dx[cd];			// alpha 위치
				alphaY=cy+2*dy[cd];
				
				// 모래를 이동시킨다.
				moveSand(cd);
				
				// 한 칸 이동한다.
				cx=nx;
				cy=ny;
			}
			// 이동할 수 있는 거리만큼 움직였다면
			// 방향을 전환한다.
			cd=(cd+1)%4;
		}
		// 토네이도가 이동하는 방향에 따라 모래의 양이 결정된다.
		System.out.println(spreadSand);
	}
	
	// 모래를 이동시킨다. (y 위치를 중심으로)
	// 이동된 모래의 범위 확인
	public static void moveSand(int cd) {
		// y 위치에 있는 모든 모래가 비율과 alpha가 적혀있는 칸으로 이동한다.
		int totalSand=map[nx][ny];
		int tmp=0;		// 날아간 모래의 양, alpha에 들어갈 모래 양: totalSand-tmp
		
		// 이동할 모래 범위를 탐색한다.
		int switchDir=0;
		if (cd==0 || cd==2) {
			switchDir=1;
		}
		// 상,하 또는 좌,우
		for (int dirIdx=0+switchDir;dirIdx<4;dirIdx+=2) {
			
			// x 한 칸 위,아래 또는 좌,우 (0.01)
			// 모래가 이동할 칸이 배열 범위 내일 경우
			if (cx+dx[dirIdx]>=0 && cx+dx[dirIdx]<N && cy+dy[dirIdx]>=0 && cy+dy[dirIdx]<N) {
				map[cx+dx[dirIdx]][cy+dy[dirIdx]]+=(int)((totalSand*1)/100);
			}
			// 그렇지 않을 경우 밖으로 날아간 모래 양에 합산
			else {
				spreadSand+=(int)((totalSand*1)/100);
			}
			// 날아간 모래 양 반영
			tmp+=(int)((totalSand*1)/100);
			
			// y 한 칸 위, 아래 또는 좌, 우 (0.07)
			// 모래가 이동할 칸이 배열 범위 내일 경우
			if (nx+dx[dirIdx]>=0 && nx+dx[dirIdx]<N && ny+dy[dirIdx]>=0 && ny+dy[dirIdx]<N) {
				map[nx+dx[dirIdx]][ny+dy[dirIdx]]+=(int)((totalSand*7)/100);
			}
			// 그렇지 않을 경우 밖으로 날아간 모래 양에 합산
			else {
				spreadSand+=(int)((totalSand*7)/100);
			}
			// 날아간 모래 양 반영
			tmp+=(int)((totalSand*7)/100);
			
			// y 두 칸 위, 아래 또는 좌, 우 (0.02)
			// 모래가 이동할 칸이 배열 범위 내일 경우
			if (nx+2*dx[dirIdx]>=0 && nx+2*dx[dirIdx]<N && ny+2*dy[dirIdx]>=0 && ny+2*dy[dirIdx]<N) {
				map[nx+2*dx[dirIdx]][ny+2*dy[dirIdx]]+=(int)((totalSand*2)/100);
			}
			// 그렇지 않을 경우 밖으로 날아간 모래 양에 합산
			else {
				spreadSand+=(int)((totalSand*2)/100);
			}
			// 날아간 모래 양 반영
			tmp+=(int)((totalSand*2)/100);
			
			// alpha 한 칸 위, 아래 또는 좌, 우 (0.1)
			// 모래가 이동할 칸이 배열 범위 내일 경우
			if (alphaX+dx[dirIdx]>=0 && alphaX+dx[dirIdx]<N && alphaY+dy[dirIdx]>=0 && alphaY+dy[dirIdx]<N) {
				map[alphaX+dx[dirIdx]][alphaY+dy[dirIdx]]+=(int)((totalSand*10)/100);
			}
			// 그렇지 않을 경우 밖으로 날아간 모래 양에 합산
			else {
				spreadSand+=(int)((totalSand*10)/100);
			}
			// 날아간 모래 양 반영
			tmp+=(int)((totalSand*10)/100);
			
		}
		// alpha 한 칸 옆(현재 방향) - (0.05)
		// 모래가 이동할 칸이 배열 범위 내일 경우
		if (alphaX+dx[cd]>=0 && alphaX+dx[cd]<N && alphaY+dy[cd]>=0 && alphaY+dy[cd]<N) {
			map[alphaX+dx[cd]][alphaY+dy[cd]]+=(int)((totalSand*5)/100);
		}
		// 그렇지 않을 경우 밖으로 날아간 모래 양에 합산
		else {
			spreadSand+=(int)((totalSand*5)/100);
		}
		// 날아간 모래 양 반영
		tmp+=(int)((totalSand*5)/100);
		
		// alpha에 남은 모래양 저장, alpha 범위 확인
		if (alphaX>=0 && alphaX<N && alphaY>=0 && alphaY<N) {
			map[alphaX][alphaY]+=(totalSand-tmp);
		}
		// alpha에 남을 모래양은 날아간 모래 양에 합산한다.
		else {
			spreadSand+=(totalSand-tmp);
		}
		// y영역의 모래는 모두 날아갔다.
		map[nx][ny]=0;
	}
}
