package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 21610: 마법사 상어와 비바라기
 * 1. 비구름은 (N-1,1) 위치에서 가로 2, 세로 2인 구름이 생긴다.
 * 2. 구름에 이동을 M번 명령한다. 명령은 방향 d와 거리 s로 이루어진다.
 * 3. 모든 구름이 d 방향으로 s칸 이동한다.
 * 4. 각 구름에 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 5. 구름이 모두 사라진다.
 * 6. 4에서 물이 증가한 칸에서 마법을 시전한다. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물이 양이 증가한다.
 * 7. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
 * */
public class BOJ_21610_마법사상어와비바라기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// N*N 격자
	static int M;					// M번의 이동
	static int[][] map;				// 격자 칸 정보
	static List<int[]> clouds;		// 구름 위치 정보
	static boolean[][] cloudHistory;
	static int[][] dir= {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int d;
	static int s;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N과 M 정보 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 격자 크기를 초기화한다.
		map=new int[N][N];
		
		// 격자 칸 정보를 입력 받는다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		// 구름 위치 초기화
		clouds=new ArrayList<>();
		clouds.add(new int[] {N-1,0});
		clouds.add(new int[] {N-1,1});
		clouds.add(new int[] {N-2,0});
		clouds.add(new int[] {N-2,1});
		
		// M 번의 이동 동안
		for (int m=0;m<M;m++) {
			st=new StringTokenizer(br.readLine().trim());
			d=Integer.parseInt(st.nextToken());
			s=Integer.parseInt(st.nextToken());
			
			cloudHistory=new boolean[N][N];
			
			// 모든 구름을 d 방향으로 s칸 이동한다.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int nextRow=(clouds.get(cloudIdx)[0]+dir[d-1][0]*s)%N;
				int nextCol=(clouds.get(cloudIdx)[1]+dir[d-1][1]*s)%N;
				
				// 음수일 경우, 배열 내 범위로 변환
				if (nextRow<0) {
					nextRow+=N;
				}
				if (nextCol<0) {
					nextCol+=N;
				}
				// 구름이 이동했을 때의 위치를 저장
				clouds.get(cloudIdx)[0]=nextRow;
				clouds.get(cloudIdx)[1]=nextCol;
				cloudHistory[nextRow][nextCol]=true;
			}
			// 구름 칸에 있는 바구니에 물의 양을 증가시킨다.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int[] cloudLoc=clouds.get(cloudIdx);
				int cloudRow=cloudLoc[0];
				int cloudCol=cloudLoc[1];
				map[cloudRow][cloudCol]+=1;
			}
			// 구름에서 비가 내려 물이 증가한 칸에 대해서 마법을 시전한다.
			for (int cloudIdx=0;cloudIdx<clouds.size();cloudIdx++) {
				int[] cloudLoc=clouds.get(cloudIdx);
				int cloudRow=cloudLoc[0];
				int cloudCol=cloudLoc[1];
				
				// 대각선 방향으로만 마법을 부린다.
				for (int dirIdx=1;dirIdx<=7;dirIdx+=2) {
					int checkRow=cloudRow+dir[dirIdx][0];
					int checkCol=cloudCol+dir[dirIdx][1];
					// 배열 범위 내 위치해야 한다.
					if (checkRow<0 || checkRow>=N || checkCol<0 || checkCol>=N) {
						continue;
					}
					// 대각선 방향에 있는 바구니 중 물이 들어있는 개수를 더한다.
					if (map[checkRow][checkCol]!=0) {
						map[cloudRow][cloudCol]+=1;
					}
				}
			}
			// 구름이 사라진다.
			clouds=new ArrayList<>();
			
			// 물의 양이 2 이상인 구름을 저장한다.
			// cloudHistory를 보고 구름이 사라진 칸이 아닌 칸 중에 물의 양의 2 이상인 구름을 저장한다.
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					if (map[row][col]>=2 && !cloudHistory[row][col]) {
						map[row][col]-=2;
						clouds.add(new int[] {row,col});
					}
				}
			}
		}
		// 모든 이동을 마친 후,
		// 바구니에 들어있는 물의 양을 모두 더한다.
		int total=0;
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				total+=map[row][col];
			}
		}
		// 결과를 출력한다.
		System.out.println(total);
		
		
		
	}
}
