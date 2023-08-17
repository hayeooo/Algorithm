package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 바라보고 있는 방향을 기준으로 오른쪽, 앞, 왼쪽, 뒤 순서로 확인하면서 이동한다.
 * 
 * 오전
 * 오후에 이동할 수 있는 영역이 있다면, 아래 2가지 중 하나를 수행한다.
 * 1. 씨앗을 심는다.
 * 2. 다 자란 농작물을 수확한다.
 * 이동할 수 없다면 그 자리에 그대로 있게 된다.
 * 
 * 오후
 * 이동할 수 있는 영역으로 이동한다.
 * 
 * 씨앗을 심은 후 K+3일이 지나 수확을 할 수 있다.
 * 산에는 농작물을 수확할 수 없다.
 * 
 * 로봇이 일하는 기간 동안 최대로 수확할 수 있는 농작물의 개수
 */
public class 대균이의농작물수확하기 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;							// 테스트 케이스 수
	static int N;							// 영역의 크기
	static int D;							// 로봇이 일하는 기간
	static int[][] map;						// 영역의 칸 정보를 저장할 배열
	static List<int[]> plants;				// 농작물을 관리하는 리스트 (행, 열, 심은 회차)
	static int[][] isplanted;				// 몇 회차에 농작물이 심겼는지 확인하는 배열
	
	static int[] dx= {-1,0,1,0};			// 북, 서, 남, 동
	static int[] dy= {0,-1,0,1};
	static int cd;							// 현재 바라보고 있는 방향

	static int maxPlantCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br= new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// 영역의 크기 N, 로봇이 일하는 기간을 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			D=Integer.parseInt(st.nextToken());
			
			// 영역의 크기를 초기화한다.
			map=new int[N][N];
			isplanted=new int[N][N];
			
			// N개의 줄에 영역의 각 칸의 정보를 입력 받는다. 1: 산, 0: 농작물
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			maxPlantCnt=0;
			// 로봇의 위치와 방향을 정해줘야 한다.
			// 농작물을 심을 수 있는 위치에서 시작해야 하나라도 씨앗을 더 심을 수 있다.
//			for (int row=0;row<N;row++) {
//				for (int col=0;col<N;col++) {
//					if (map[row][col]==0) {
//						// 북, 동, 남, 서 방향으로 바라볼 수 있다.
//						for (int d=0;d<4;d++) {
//							// 특정 방향을 바라본 채로 일을 시작한다.
//							work(row,col,d);
//						}
//					}
//				}
//			}
			work(3,3,0);
			//System.out.println(maxPlantCnt);
			
		}
		
		
	}
	// 로봇이 일을 한다.
	public static void work(int row, int col, int dir) {
		// 로봇이 처음 시작하는 위치와 방향
		int curRow=row;
		int curCol=col;
		int curDir=dir;
		
		// 수확한 농작물 수
		int plantCnt=0;
		
		for (int curStage=1;curStage<=D;curStage++) {
			System.out.println("======="+curStage);
			System.out.println(curRow+" "+curCol);
			// 오전
			// 오후에 이동할 수 있는 영역이 있다면
			
			// 로봇의 오른쪽 위치
			int startDir=(curDir-1)%4;
			if (startDir<0) {
				startDir+=4;
			}
			// 수확할 수 있는 농작물을 먼저 수확한다.
			boolean flag=false;
			
			// 수확할 수 있는 농작물을 찾아본다.
			for (int idx=0;idx<4;idx++) {
				// 오른쪽, 앞, 왼쪽, 뒤 순으로 본다.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// 배열의 범위가 벗어나지 않았을 경우
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && isplanted[nextRow][nextCol]<curStage) {
						isplanted[nextRow][nextCol]=0;
						plantCnt+=1;
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
						flag=true;
						break;
					}
					
				}
			}
			
			if (flag) continue;
			
			flag=false;
			
			// 수확할 농작물이 없는 경우
			// 농작물이 심겨져있지 않은 경우
			for (int idx=0;idx<4;idx++) {
				// 오른쪽, 앞, 왼쪽, 뒤 순으로 본다.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// 배열의 범위가 벗어나지 않았을 경우
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && isplanted[nextRow][nextCol]==0) {
						isplanted[nextRow][nextCol]=curStage+3;
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
						flag=true;
						break;
					}
				}
			}
			if (flag) continue;
			
			int recentStage=0;
			// 심겨져 있는 농작물 중에 가장 최근에 심어져 있는 위치로 이동한다.
			for (int idx=0;idx<4;idx++) {
				// 오른쪽, 앞, 왼쪽, 뒤 순으로 본다.
				int nextRow=curRow+dx[(startDir+idx)%4];
				int nextCol=curCol+dy[(startDir+idx)%4];
				
				// 배열의 범위가 벗어나지 않았을 경우
				if (nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<N) {
					
					if (map[nextRow][nextCol]==0 && recentStage<isplanted[nextRow][nextCol]) {
						recentStage=isplanted[nextRow][nextCol];
						curRow=nextRow;
						curCol=nextCol;
						curDir=(startDir+idx)%4;
					}
					isplanted[nextRow][nextCol]=curStage;
				}
			}
			
			
		}
		// D 번의 이동이 끝난 후
		maxPlantCnt=Math.max(maxPlantCnt, plantCnt);
		
	}
}
