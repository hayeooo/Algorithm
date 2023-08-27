package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 먼지의 위치와 비교하며 방향을 바꾼다.
 * => 먼지와 같은 줄이거나, 벽에 부딪힐 경우 방향을 바꾼다.
 */
public class SWEA_로봇청소기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 수
	static int N;					// 맵의 한 변의 크기
	static int M;					// 먼지의 개수
	static int[][] map;				// 맵 정보를 저장하는 배열
	static int dustCnt;				// 먼지의 개수
	static List<int[]> dustLocList;	// 먼지의 위치를 저장하는 리스트
	static int[] dx= {0,1,0,-1};	// 동, 남, 서, 북(오른쪽으로 90도)
	static int[] dy= {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			N=Integer.parseInt(br.readLine().trim());
			map=new int[N][N];
			
			dustLocList=new ArrayList<>();
			for (int idx=0;idx<11;idx++) {
				dustLocList.add(new int[] {});
			}
			
			dustCnt=0;
			// 맵 정보를 입력받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
					
					// 먼지인 경우
					if (map[row][col]!=0) {
						dustLocList.set(map[row][col], new int[] {row,col});
						dustCnt+=1;
					}
				}
			}
			
			int catchedDust=0;
			int dustIdx=1;
			int d=0;			// 오른쪽 방향을 보고 있다.
			int curX=0;
			int curY=0;
			int rotationCnt=0;
			while (catchedDust<dustCnt) {
				// 찾아야할 먼지의 위치
				int dustX=dustLocList.get(dustIdx)[0];
				int dustY=dustLocList.get(dustIdx)[1];
				
				// 먼지를 찾을 때까지
				while (curX!=dustX || curY!=dustY) {
					
					int nextX=curX+dx[d];
					int nextY=curY+dy[d];
					
					// 일단 이동한다.
					if (nextX>=0 && nextX<N && nextY>=0 && nextY<N) {
						curX=nextX;
						curY=nextY;
						
						if (curX==dustX && curY==dustY) break;
						// 같은 행에 있지만 다른 열에 있는 경우
						if (curX==dustX) {
							// 수직 방향인 경우 한 번 꺾어야 한다.
							// 북쪽을 바라보고 있고 열의 위치가 먼지보다 작을 때
							if (d==3 && curY<dustY) {
								d=(d+1)%4;
								rotationCnt+=1;
								continue;
							}
							// 남쪽을 바라보고 있고 열의 위치가 먼지보다 클 때
							if (d==1 && curY>dustY) {
								d=(d+1)%4;
								rotationCnt+=1;
								continue;
							}
							
						}
						// 같은 열에 있지만 다른 행에 있는 경우
						else if (curY==dustY) {
							
							// 수평 방향인 경우 한 번 꺾어야 한다.
							// 동쪽을 바라보고 있고, 먼지의 행이 현재 행보다 클 때
							if (d==0 && curX<dustX) {
								rotationCnt+=1;
								d=(d+1)%4;
								continue;
							}
							// 서쪽을 바라보고 있고, 먼지의 행이 현재 행보다 작을 때
							if (d==2 && curX>dustX) {
								rotationCnt+=1;
								d=(d+1)%4;
								continue;
							}
						}
						
					}
					else {
						rotationCnt+=1;
						d=(d+1)%4;
					}
					
				}
				// 먼지를 제거했으면 개수를 늘려주고 다음 먼지를 찾는다.
				catchedDust+=1;
				dustIdx+=1;
			}
			sb.append("#"+test_case+" "+rotationCnt).append("\n");
			
		}
		System.out.println(sb);
	}

}
