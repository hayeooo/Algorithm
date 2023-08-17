package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 좀비퇴치
 * N*N 크기의 맵에 1*1 크기로 나누어진 각 칸에 좀비의 숫자가 들어있다.
 * 미사일은 + 모양으로 상,하,좌,우에 해당하는 영역의 좀비를 퇴치할 수 있다.
 * 미사일 파워가 0일 때, 미사일이 떨어지는 칸의 좀비들만 퇴치할 수 있고,
 * 파워가 1일 때는 미사일에 떨어지는 칸에 더해 상, 하, 좌, 우 영역으로 1칸씩 좀비를 퇴치할 수 있다.
 */
public class SWEA_좀비퇴치 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;				// 테스트케이스 개수
	static int N,P;				// 맵의 크기 N, 미사일의 위력 P
	static int[][] map;			// 좀비의 수가 담길 배열
	
	static int[] dx= {-1,1,0,0};		// 상, 하, 좌, 우
	static int[] dy= {0,0,-1,1};
	
	static int maxZombie;		// 최대 좀비 수
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 개수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// 맵의 크기, 미사일의 위력을 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			P=Integer.parseInt(st.nextToken());
			
			// 맵의 크기를 초기화한다.
			map=new int[N][N];
			
			// 좀비의 수를 입력 받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			maxZombie=0;
			// 0,0 부터 N-1,N-1까지 최대 좀비 수를 구한다.
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					killZombies(row,col);
				}
			}
			// 결과를 출력한다.
			System.out.println("#"+test_case+" "+maxZombie);
		}
		
	}
	public static void killZombies(int x,int y) {
		// 미사일에 있는 좀비의 수를 더한다.
		int total=map[x][y];
		// 4 방향을 탐색하며 위력만큼 미사일 폭발
		for (int d=0;d<4;d++) {
			for (int p=1;p<=P;p++) {
				int nextX=x+p*dx[d];
				int nextY=y+p*dy[d];
				
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					break;
				}
				total+=map[nextX][nextY];
			}
		}
		maxZombie=Math.max(maxZombie, total);
	}

}
