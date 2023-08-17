package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * SWEA 1873: 상호의 배틀필드
 * 사용자의 전차는 사용자의 입력에 따라 격차판으로 이루어진 게임 맵에서 다양한 동작을 한다.
 * 사용자가 넣을 수 있는 동작은 U, D, L, R, S이다.
 * 게임 맵 밖으로 전차가 이동하려고 한다면 이동하지 않는다.
 * 포탄이 벽에 부딪히면 포탄은 소멸하고, 벽돌로 만들어진 벽이라면 파괴되어 평지가 된다.
 * 초기 게임 맵의 상태와 사용자가 넣을 입력이 순서대로 주어질 때, 모든 입력을 처리하고 나면 게임 맵의 상태가 어떻게 되는지 구한다.
 */
public class SWEA_1873_상호의배틀필드_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 수
	static int H,W;					// 게임 맵의 높이 H, 너비 W
	static char[][] map;			// H*W크기의 격자판
	static int N;					// 사용자가 넣을 입력의 개수
	static String cmd;				// 길이가 N인 문자열
	
	static int[] dx= {-1,1,0,0};	// up, down, left, right
	static int[] dy= {0,0,-1,1};
	
	static Map<Character,Integer> dirHm=new HashMap<>();
	
	static int carX,carY;			// 전차의 현재 위치	
	static int cd;					// 전차의 현재 방향 (up:0, down:1, left:2, right:3)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		dirHm.put('U', 0);
		dirHm.put('D', 1);
		dirHm.put('L', 2);
		dirHm.put('R', 3);
		
		sb=new StringBuilder();
		
		// 테스트 케이스 수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 맵의 높이 H, 너비 W를 입력 받는다.
			st=new StringTokenizer(br.readLine().trim());
			H=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			
			// 게임 맵의 크기를 초기화한다.
			map=new char[H][W];
			
			// H개의 각각의 줄에 길이가 W인 문자열을 입력 받는다.
			for (int row=0;row<H;row++) {
				String line=br.readLine().trim();
				for (int col=0;col<W;col++) {
					map[row][col]=line.charAt(col);
					// 초기 맵에서 전차를 발견한 경우
					if (map[row][col]=='^') {			// up
						carX=row;
						carY=col;
						cd=0;
						map[row][col]='.';
					}
					else if (map[row][col]=='v') {		// down
						carX=row;
						carY=col;
						cd=1;
						map[row][col]='.';
					}
					else if (map[row][col]=='<') {		// left
						carX=row;
						carY=col;
						cd=2;
						map[row][col]='.';
					}
					else if (map[row][col]=='>') {		// right
						carX=row;
						carY=col;
						cd=3;
						map[row][col]='.';
					}
				}
			}
			// 사용자가 넣을 입력의 개수를 나타내는 정수 N을 입력 받는다.
			N=Integer.parseInt(br.readLine().trim());
			
			// N인 문자열 차례대로 입력을 처리한다.
			cmd=br.readLine().trim();
			for (int idx=0;idx<N;idx++) {
				
				// 입력이 S라면, 포탄을 쏜다.
				if (cmd.charAt(idx)=='S') {
					shoot();
				}
				// 그 외 입력은 전차를 움직인다.
				else {
					moveCar(cmd.charAt(idx));
				}
			}
			// 전차를 위치에 놓는다.
			if (cd==0) {
				map[carX][carY]='^';
			}
			else if(cd==1) {
				map[carX][carY]='v';
			}
			else if (cd==2) {
				map[carX][carY]='<';
			}
			else if (cd==3) {
				map[carX][carY]='>';
			}
			
			// 테스트케이스를 결과 문자열에 넣는다.
			sb.append("#"+test_case+" ");
			// 게임 맵 지도를 출력한다.
			for (int row=0;row<H;row++) {
				for (int col=0;col<W;col++) {
					sb.append(map[row][col]);
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);
	}
	// 포탄을 발사한다.
	// 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
	public static void shoot() {
		int nextX=carX+dx[cd];
		int nextY=carY+dy[cd];
		
		while (true) {
			// 포탄 발사 위치가 배열 범위를 넘어가는 경우
			if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
				break;
			}
			// 벽돌로 만들어진 벽에 충돌하는 경우
			if (map[nextX][nextY]=='*') {
				// 벽돌을 평지로 만든다.
				map[nextX][nextY]='.';
				break;
			}
			// 강철로 만들어진 벽에 충돌하는 경우
			if (map[nextX][nextY]=='#') {
				// 아무일도 일어나지 않고 종료한다.
				break;
			}
			// 그 외의 경우
			// 포탄의 위치를 바꾼다.
			nextX+=dx[cd];
			nextY+=dy[cd];
		}
	}
	// 전차가 바라보는 방향을 바꾸고, 이동 방향의 칸이 평지라면 그 칸으로 이동한다.
	public static void moveCar(char d) {
		// 전차가 바라보는 방향을 바꾼다.
		cd=dirHm.get(d);
		
		int nextX=carX+dx[cd];
		int nextY=carY+dy[cd];
		
		// 다음 이동 위치가 배열의 범위를 벗어나는 경우
		if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
			return;
		}
		// 평지라면, 한 칸 이동한다.
		if (map[nextX][nextY]=='.') {
			carX=nextX;
			carY=nextY;
		}
	}

}
